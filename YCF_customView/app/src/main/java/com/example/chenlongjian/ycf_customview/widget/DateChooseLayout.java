package com.example.chenlongjian.ycf_customview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chenlongjian.ycf_customview.R;
import com.example.chenlongjian.ycf_customview.common.DataUtil;
import com.example.chenlongjian.ycf_customview.common.UIHelper;
import com.example.chenlongjian.ycf_customview.model.Holiday;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by chenlongjian on 2016/6/12.
 */
public class DateChooseLayout extends HorizontalScrollView {

    private TextView mChooseDateTxt;
    private RadioGroup mDateGroup;

    private List<Holiday> dateList = new ArrayList<>();     //展示在界面上的日期选择数据集合
    private Calendar mCurrentCalendar;      //缓存的当前日期信息
//    private HolidayOperator mHolidayOperator;
    private OnItemChooseListener mListener;
    private boolean hasFilterFlag = false;
    private Calendar mStartCalendar;
    private Calendar mEndCalendar;
    private Map<String, Integer> mCachePosition = new HashMap<>();
    private int mCellWidth = 0;
    private int mCellMargin = 0;
    private boolean mSaveChooseFlag = false;

    public DateChooseLayout(Context context) {
        super(context);
        initLayout();
    }

    public DateChooseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DateChooseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DateChooseLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout();
    }

    private void initLayout()
    {
        View.inflate(getContext(), R.layout.layout_datechoose, this);
        mChooseDateTxt = (TextView) findViewById(R.id.txt_choose_date);
        mDateGroup = (RadioGroup) findViewById(R.id.group_date);
        mCurrentCalendar = DataUtil.getCalendarInstance(Calendar.getInstance(Locale.CHINA));
        mStartCalendar = DataUtil.getCalendarInstance(Calendar.getInstance(Locale.CHINA));
//        mHolidayOperator = HolidayOperator.getInstance(getContext());
        mCellWidth = (int) (UIHelper.getDisplayWidth(getContext()) / 5.5);
        mCellMargin = UIHelper.dip2px(getContext(), 5);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mChooseDateTxt.getLayoutParams();
        lp.width = mCellWidth + mCellMargin * 2;
        mChooseDateTxt.setLayoutParams(lp);
        initDateWithStartDate(mCurrentCalendar);
        initDateData();
        initChooseView();


    }

    public void initDateWithStartDate(Calendar startDate)
    {
        Holiday noLimitDay = new Holiday();
        noLimitDay.setId((long) 0);
        noLimitDay.setDate("");
        noLimitDay.setName("不限\n日期");
        mCachePosition.put("", 0);
        dateList.add(noLimitDay);
        for(int i = 1;i < 9; i ++){
            if(1 != i){
                startDate.add(Calendar.DAY_OF_MONTH, 1);
            }
            Holiday holiday = new Holiday();
            holiday.setId((long) i);
            holiday.setDate(DataUtil.getCalendarString(DataUtil.Y4M2D2, startDate));
            holiday.setName(getShowingLabel(startDate));
            dateList.add(holiday);
            if(7 == i){
                mEndCalendar = startDate;
            }
            mCachePosition.put(holiday.getDate(), i);
        }
    }

    public void initDateData()
    {
        mDateGroup.removeAllViews();
        for (int i = 0; i < dateList.size(); i++) {
            Holiday holiday = dateList.get(i);
            mDateGroup.addView(generateRadioButton(i, holiday.getDate(), holiday.getName()));
        }
        if(mDateGroup.getChildCount() > 0){
            ((RadioButton)mDateGroup.getChildAt(0)).setChecked(true);
            changeTextColor(((RadioButton)mDateGroup.getChildAt(0)));
        }
        mDateGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = (RadioButton) group.getChildAt(checkedId);
                if (null != btn && null != btn.getTag() && btn.getTag() instanceof String) {
                    String date = (String) btn.getTag();
                    if (null != mListener) {
                        mListener.onItemChoose(date);
                        scrollToTab(mCachePosition.get(date));
                    }
                    mSaveChooseFlag = false;
                }
                for (int i = 0; i < group.getChildCount(); i++) {
                    changeTextColor((RadioButton) group.getChildAt(i));
                }
            }
        });
    }

    private void initChooseView() {
        mChooseDateTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.chooseAction();
                }
            }
        });
    }

    private String getShowingLabel(Calendar date){
        String result;
        Map<String, Holiday> map = null;
        int month = date.get(Calendar.MONTH) + 1;
        String showingDateLabel = String.format("%d月%d日", month, date.get(Calendar.DAY_OF_MONTH));
        int offsetDay = DataUtil.calculateDayOffset(mStartCalendar, date);
        if(0 == offsetDay){
            showingDateLabel = "今天";
        }else if(1 == offsetDay){
            showingDateLabel = "明天";
        }
        result = getWeekDayName(date.get(Calendar.DAY_OF_WEEK)) + "\n" + showingDateLabel;
        return result;
    }

    private String getWeekDayName(int weekDay){
        switch(weekDay){
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
        }
        return "";
    }

    /**
     * @param index
     * @param date
     * @param name
     * @return 产出每一个选择控件视图
     */
    private RadioButton generateRadioButton(final int index, final String date, String name){
        RadioButton childItem = new RadioButton(getContext());
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(mCellWidth, UIHelper.dip2px(getContext(), 45));
        layoutParams.setMargins(mCellMargin, 0, mCellMargin, 0);
        childItem.setId(index);
        childItem.setBackgroundResource(R.drawable.date_check_selector);
        childItem.setButtonDrawable(R.color.transparent);
        childItem.setLineSpacing(UIHelper.dip2px(getContext(), 3), 1.0f);
        childItem.setLayoutParams(layoutParams);
        childItem.setGravity(Gravity.CENTER);
        childItem.setText(parseShowingLabel(name));
        childItem.setTag(date);
        childItem.setPadding(10, 0, 10, 0);
        return childItem;
    }

    private SpannableString parseShowingLabel(String totalLabel){
        String[] labelArray = totalLabel.split("\n");
        SpannableString sb = new SpannableString(totalLabel);
        if(!TextUtils.isEmpty(totalLabel) && !totalLabel.contains("不限")){
            AbsoluteSizeSpan weekDaySpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 12));
            sb.setSpan(weekDaySpan, 0, labelArray[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            AbsoluteSizeSpan dateSpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 14));
            sb.setSpan(dateSpan, totalLabel.length() - labelArray[1].length(), totalLabel.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan weekDayColor = new ForegroundColorSpan(getResources().getColor(R.color.t3_50));
            sb.setSpan(weekDayColor, 0, labelArray[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan dateColor = new ForegroundColorSpan(getResources().getColor(R.color.t1_50));
            sb.setSpan(dateColor, totalLabel.length() - labelArray[1].length(), totalLabel.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            sb.setSpan(styleSpan, totalLabel.length() - labelArray[1].length(), totalLabel.length(), SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            AbsoluteSizeSpan weekDaySpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 12));
            sb.setSpan(weekDaySpan, 0, labelArray[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            AbsoluteSizeSpan dateSpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 12));
            sb.setSpan(dateSpan, totalLabel.length() - labelArray[1].length(), totalLabel.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan weekDayColor = new ForegroundColorSpan(getResources().getColor(R.color.t1_50));
            sb.setSpan(weekDayColor, 0, labelArray[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan dateColor = new ForegroundColorSpan(getResources().getColor(R.color.t1_50));
            sb.setSpan(dateColor, totalLabel.length() - labelArray[1].length(), totalLabel.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sb;
    }

    private void changeTextColor(RadioButton radioButton){
        try {
            if (null != radioButton && !TextUtils.isEmpty(radioButton.getText())) {
                if (radioButton.isChecked()) {
                    String totalTxt = radioButton.getText().toString().trim();
                    String[] text = totalTxt.split("\n");
                    SpannableString sb = new SpannableString(radioButton.getText());
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.white));
                    sb.setSpan(foregroundColorSpan, 0, radioButton.getText().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    if(!totalTxt.contains("不限")){
                        AbsoluteSizeSpan weekDaySpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 12));
                        sb.setSpan(weekDaySpan, 0, text[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        AbsoluteSizeSpan dateSpan = new AbsoluteSizeSpan(UIHelper.sp2px(getContext(), 14));
                        sb.setSpan(dateSpan, radioButton.getText().toString().trim().length() - text[1].length(), radioButton.getText().toString().trim().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                        sb.setSpan(styleSpan, radioButton.getText().toString().trim().length() - text[1].length(), radioButton.getText().toString().trim().length(), SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    radioButton.setText(sb);
                } else {
                    radioButton.setText(parseShowingLabel(radioButton.getText().toString()));
                }
            }
        }catch(Exception e){
           Log.e("tag","下标越界问题--");
            e.printStackTrace();
        }
    }

    /**
     * @param tabIndex
     * 滚动到某个位置索引
     */
    private void scrollToTab(int tabIndex) {
        int childCount = mDateGroup.getChildCount();
        if(tabIndex <= childCount){
            int cellWidth = mCellWidth + mCellMargin;
            smoothScrollTo(tabIndex * cellWidth, 0);
        }
    }

    public void setSaveChooseFlag(boolean flag){
        this.mSaveChooseFlag = flag;
    }


    public void setOnItemChooseListener(OnItemChooseListener listener) {
        this.mListener = listener;
    }

    public interface OnItemChooseListener {
        void onItemChoose(String date);
        void chooseAction();
        void onHideMask();
        void onShowMask();
    }


}
