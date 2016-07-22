package com.xuxian.market.presentation.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 作者：lubo on 7/22 0022 10:30
 * 邮箱：lubo_wen@126.com
 */
public class MyApplication extends Application {
    public static String currentUserNick = "";
    public String localVersion;
    public DisplayImageOptions.Builder displayBuilder = null;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        try {
            this.localVersion = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initImageLoader(Context context) {
        ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(context)
                        .threadPriority(3)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .writeDebugLogs()
                        .build()
        );
    }

    public DisplayImageOptions getSampleOptions(int drawbleId) {
        DisplayImageOptions.Builder options = getSampleOptions();
        options.showImageOnFail(drawbleId);
        options.showImageOnLoading(drawbleId);
        options.showImageForEmptyUri(drawbleId);
        options.cacheInMemory(true);
        return options.build();

    }

    private DisplayImageOptions.Builder getSampleOptions() {
        DisplayImageOptions.Builder builder = null;
        synchronized (MyApplication.class) {
            if (displayBuilder == null)
            {
                builder = new DisplayImageOptions.Builder();
                displayBuilder = builder;
            }
        }
        return builder;
    }
}
