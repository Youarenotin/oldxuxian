package com.xuxian.marketpro.presentation.imageloader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by youarenotin on 16/8/9.
 */
public class AnimateFirstDisplayListenerBanner extends SimpleImageLoadingListener {
    private int width;
    public AnimateFirstDisplayListenerBanner(int screenWidth) {
        width=screenWidth;
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        super.onLoadingComplete(imageUri, view, loadedImage);
        ImageView imageView = (ImageView) view;
        AbViewUtil.setViewWH(imageView,width,width/2);
        imageView.setImageBitmap(loadedImage);
    }
}
