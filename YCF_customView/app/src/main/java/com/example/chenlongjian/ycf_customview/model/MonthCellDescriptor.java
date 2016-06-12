package com.example.chenlongjian.ycf_customview.model;

import java.util.Date;

/**
 * Created by chenlongjian on 2016/6/12.
 */
public class MonthCellDescriptor {

    public enum RangeState {
        NONE, FIRST, MIDDLE, LAST
    }

    private final Date date;
    private final int value;
    private final boolean isCurrentMonth;
    private boolean isSelected;
    private final boolean isToday;
    private final boolean isOutday;
    private final boolean isSelectable;
    private boolean isHighlighted;
    private RangeState rangeState;

    private String itemprice;
    private int remaincount;
    private String datevalue;
    private boolean isWeekend;

    private boolean isHoliday;
    private boolean isWork;

    private int holidayType;

    private String holidayname;
    private int status;

    private boolean isLineHide;
    private int mIsReductionNum;

    public MonthCellDescriptor(Date date, boolean currentMonth,
                               boolean selectable, boolean selected, boolean today,
                               boolean outday, boolean highlighted, int value,
                               RangeState rangeState) {
        this.date = date;
        isCurrentMonth = currentMonth;
        isSelectable = selectable;
        isHighlighted = highlighted;
        isSelected = selected;
        isToday = today;
        isOutday = outday;
        this.value = value;
        this.rangeState = rangeState;
    }

    public String getHolidayname() {
        return holidayname;
    }

    public void setHolidayname(String holidayname) {
        this.holidayname = holidayname;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isHighlighted() {
        return isHighlighted;
    }

    void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public boolean isToday() {
        return isToday;
    }

    public RangeState getRangeState() {
        return rangeState;
    }

    public void setRangeState(RangeState rangeState) {
        this.rangeState = rangeState;
    }

    public int getValue() {
        return value;
    }

    public boolean isOutday() {
        return isOutday;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public int getRemaincount() {
        return remaincount;
    }

    public void setRemaincount(int remaincount) {
        this.remaincount = remaincount;
    }

    public String getDatevalue() {
        return datevalue;
    }

    public void setDatevalue(String datevalue) {
        this.datevalue = datevalue;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean isWork) {
        this.isWork = isWork;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isLineHide() {
        return isLineHide;
    }

    public void setLineHide(boolean isLineHide) {
        this.isLineHide = isLineHide;
    }

    public int getReductionNum() {
        return mIsReductionNum;
    }

    public void setReductionNum(int reductionNum) {
        this.mIsReductionNum = reductionNum;
    }

    public int getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(int holidayType) {
        this.holidayType = holidayType;
    }

}
