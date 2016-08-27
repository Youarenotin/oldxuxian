package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.entity.ForumsViewPageEntity;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/27.
 */
public class ForumsViewPagerAdapter extends PagerAdapter{
    private List<ForumsViewPageEntity> forumsViewPages;
    private Context mContext;
    private DisplayImageOptions.Builder options;
    private UserEntity userDto;

    public ForumsViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
        this.options = new DisplayImageOptions.Builder();
        this.options.showImageOnLoading(R.drawable.default_newimg);
        this.options.showImageForEmptyUri(R.drawable.default_newimg);
        this.options.showImageOnFail(R.drawable.default_newimg);
        this.options.cacheInMemory(true);
        this.options.cacheOnDisk(true);
        this.options.imageScaleType(ImageScaleType.EXACTLY);
        this.options.bitmapConfig(Bitmap.Config.RGB_565);
        this.options.resetViewBeforeLoading(true);
    }

    @Override
    public int getCount() {
        if (forumsViewPages==null && forumsViewPages.size()<=0)
            return 0;
        return forumsViewPages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ForumsViewPageEntity forumsViewPage = this.forumsViewPages.get(position);
        ImageLoader.getInstance().displayImage(forumsViewPage.getImg(), imageView, this.options.build());
        container.addView(imageView, 0);
        imageView.setOnClickListener(new ForumsOnClickListener(forumsViewPage));
        imageView.setOnClickListener(new AnonymousClass1(forumsViewPage));
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setData(List<ForumsViewPageEntity> forumsViewPages) {
        this.forumsViewPages = forumsViewPages;
        notifyDataSetChanged();
    }

    public void setUserDto(UserEntity userDto) {
        this.userDto = userDto;
    }

    private class ForumsOnClickListener implements View.OnClickListener {
        private final ForumsViewPageEntity forumsViewPage;

        public ForumsOnClickListener(ForumsViewPageEntity forumsViewPage) {
            this.forumsViewPage = forumsViewPage;
        }

        @Override
        public void onClick(View v) {

        }
    }

    class AnonymousClass1 implements View.OnClickListener {
        private  ForumsViewPageEntity val$forumsViewPage;

        AnonymousClass1(ForumsViewPageEntity forumsViewPageEntity) {
            this.val$forumsViewPage = forumsViewPageEntity;
        }

        public void onClick(View v) {
            if (ForumsViewPagerAdapter.this.userDto != null) {
                ActivityUtil.startForumListDetailsActivity(ForumsViewPagerAdapter.this.mContext, this.val$forumsViewPage.getTid(), this.val$forumsViewPage.getFid());
            } else {
                ActivityUtil.startLoginActivity(ForumsViewPagerAdapter.this.mContext);
            }
        }
    }
}
