package com.xuxian.marketpro.presentation.imageloader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by youarenotin on 16/8/10.
 */
public class AnimateFirstDisplayListenerTipsimg extends SimpleImageLoadingListener {
    private int width;

    public AnimateFirstDisplayListenerTipsimg(int width) {
        this.width = width;
    }
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (loadedImage != null) {
            ImageView imageView = (ImageView) view;
            int ww = (this.width / 2) / 4;
            AbViewUtil.setViewWH(imageView, ww, ww / 4);
            imageView.setImageBitmap(loadedImage);
        }
    }

    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        int ww = (this.width / 2) / 4;
        AbViewUtil.setViewWH((ImageView) view, ww, ww / 4);
    }

    public void onLoadingStarted(String imageUri, View view) {
        super.onLoadingStarted(imageUri, view);
        int ww = (this.width / 2) / 4;
        AbViewUtil.setViewWH((ImageView) view, ww, ww / 4);
    }
}
