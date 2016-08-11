package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbStrUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.activity.tab.goods.BannerTpyeOnClickListener;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.BannerEntity;
import com.xuxian.marketpro.presentation.imageloader.AnimateFirstDisplayListenerBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youarenotin on 16/8/10.
 */
public class HeadViewPagerAdapter extends PagerAdapter{
    private List<BannerEntity> bannerDtos;
    private Context mContext;
    private int screenWidth;
    private TabMainFragmentActivity tabMainFragmentActivity;
    private List<BannerEntity> tempBannerEntities;

    class ViewHolder {
        ImageView iv_main_banner;

        ViewHolder() {
        }
    }

    public HeadViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
        this.screenWidth = AbScreenUtils.getScreenWidth(mContext);
    }

    public void setData(List<BannerEntity> bannerDtos) {
        this.bannerDtos = bannerDtos;
        this.tempBannerEntities = new ArrayList();
        if (!(bannerDtos == null || bannerDtos.isEmpty())) {
            initTempData();
        }
        notifyDataSetChanged();
    }

    private void initTempData() {
        if (this.bannerDtos == null || this.bannerDtos.size() <= 1) {
            this.tempBannerEntities = this.bannerDtos;
            return;
        }
        this.tempBannerEntities.add(this.bannerDtos.get(this.bannerDtos.size() - 1));
        this.tempBannerEntities.addAll(this.bannerDtos);
        this.tempBannerEntities.add(this.bannerDtos.get(0));
    }

    public void setTabMainFragmentActivity(TabMainFragmentActivity tabMainFragmentActivity) {
        this.tabMainFragmentActivity = tabMainFragmentActivity;
    }


    @Override
    public int getCount() {
        if (bannerDtos==null||bannerDtos.isEmpty())
            return 0;
        return bannerDtos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ScaleType.FIT_XY);
        BannerEntity bannerEntity = (BannerEntity) this.tempBannerEntities.get(position % this.bannerDtos.size());
        ImageLoader.getInstance().displayImage(bannerEntity.getImg(), imageView, MyApplication.getInstance().getSampleOptions(R.drawable.default_banner_img), new AnimateFirstDisplayListenerBanner(this.screenWidth));
        container.addView(imageView, 0);
        if (!AbStrUtil.isEmpty(bannerEntity.getBannertype()) && AbStrUtil.isNumber(bannerEntity.getBannertype())) {
            imageView.setOnClickListener(new BannerTpyeOnClickListener(this.tabMainFragmentActivity, Integer.valueOf(bannerEntity.getBannertype()), bannerEntity.getMessage()));
        }
        return imageView;
    }
}
