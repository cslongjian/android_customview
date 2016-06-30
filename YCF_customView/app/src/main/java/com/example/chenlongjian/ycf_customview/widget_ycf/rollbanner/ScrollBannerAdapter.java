package com.example.chenlongjian.ycf_customview.widget_ycf.rollbanner;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenlongjian.ycf_customview.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlongjian on 16/6/30.
 */
public class ScrollBannerAdapter extends PagerAdapter {


    private Context mContext;
    private List<T> mBannerList;
    private List<View> mViewPool;

    public ScrollBannerAdapter(Context context, List<T> mBannerListScroll) {
        mContext = context;
        mBannerList = mBannerListScroll;
        mViewPool = new ArrayList();
    }

    @Override
    public int getCount() {
        return mBannerList == null ? 0 : (mBannerList.size() > 1 ? mBannerList
                .size() * (Integer.MAX_VALUE / mBannerList.size())
                : mBannerList.size());
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        if (mViewPool == null) {
            mViewPool = new ArrayList();
        }
        View imageLayout;
        final ViewHolder viewHolder;
        if (mViewPool.isEmpty()) {
            imageLayout = LayoutInflater.from(mContext).inflate(
                    R.layout.item_banner_pagger_image, view, false);
            viewHolder = new ViewHolder();
            imageLayout.setTag(viewHolder);
        } else {
            imageLayout = mViewPool.remove(0);
            if (imageLayout.getTag() != null
                    && imageLayout.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) imageLayout.getTag();
            } else {
                viewHolder = new ViewHolder();
                imageLayout.setTag(viewHolder);
            }
        }

        viewHolder.mIVImg = (SimpleDraweeView) imageLayout
                .findViewById(R.id.banner_image);

        String bannerImgUrl = mBannerList.get(position % mBannerList.size()).getCt()
                .getApp_picpath();
        if (!TextUtils.isEmpty(bannerImgUrl)) {
            Uri uri = Uri.parse(bannerImgUrl);
            viewHolder.mIVImg.setImageURI(uri);
        } else {
            viewHolder.mIVImg.setImageURI(null);
        }
        ((ViewPager) view).addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mViewPool.add((View) object);
        container.removeView((View) object);
    }


    private static class ViewHolder {
        private SimpleDraweeView mIVImg;
    }

}