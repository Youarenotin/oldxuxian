package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ab.util.AbStrUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.BannerEntity;
import com.xuxian.marketpro.presentation.imageloader.AnimateFirstDisplayListenerBanner;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ImageView.ScaleType.FIT_XY;

/**
 * Created by youarenotin on 16/8/9.
 */
public class HeaderViewPagerAdapter extends PagerAdapter {
    private List<BannerEntity> bannerDtos;
    private Context mContext;
    private int screenWidth;
    private TabMainFragmentActivity tabMainFragmentActivity;
    private List<BannerEntity> tempBannerEntities;

    public HeaderViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (tempBannerEntities == null || tempBannerEntities.isEmpty())
            return 0;
        return tempBannerEntities.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private class ViewHolder {
        private ImageView iv_main_banner;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView =new ImageView(mContext);
        imageView.setScaleType(FIT_XY);
        BannerEntity bannerEntity = tempBannerEntities.get(position / bannerDtos.size());
        ImageLoader.getInstance().displayImage(bannerEntity.getImg(),imageView, MyApplication.getInstance().getSampleOptions(R.drawable.default_banner_img),new AnimateFirstDisplayListenerBanner(screenWidth));
        container.addView(imageView,0);
        if (!AbStrUtil.isEmpty(bannerEntity.getBannertype()) && AbStrUtil.isNumber(bannerEntity.getBannertype())){
//            imageView.setOnClickListener(new );//banner点击事件
        }
        return imageView;
    }

    public void setData(List<BannerEntity> bannerDtos) {
        this.bannerDtos = bannerDtos;
        this.tempBannerEntities = new ArrayList<>();
        if (bannerDtos != null && !bannerDtos.isEmpty()) {
            initTempData();
        }
        notifyDataSetChanged();
    }

    private void initTempData() {
        if (bannerDtos == null || bannerDtos.size() <= 1) {
            tempBannerEntities = bannerDtos;
            return;
        }
        tempBannerEntities.add(bannerDtos.get(bannerDtos.size() - 1));
        tempBannerEntities.addAll(bannerDtos);
        tempBannerEntities.add(bannerDtos.get(0));
    }

    public void setTabMainFragmentActivity(TabMainFragmentActivity tabMainFragmentActivity) {
        this.tabMainFragmentActivity = tabMainFragmentActivity;
    }

}
