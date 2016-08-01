package com.xuxian.marketpro.presentation.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 作者：lubo on 7/22 0022 10:30
 * 邮箱：lubo_wen@126.com
 */
public class MyApplication extends Application {
    public static String currentUserNick = "";
    public String localVersion;
    public DisplayImageOptions.Builder displayBuilder = null;
    public static ArrayList<Activity> mActivitys =new ArrayList<Activity>();
    private static MyApplication application;

    public MyApplication() {
    }

    public static MyApplication getInstance(){
        if (application ==null)
        {
            application=new MyApplication();
        }
        return application;
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

    /**
     * 每一张图的配置
     * @param drawbleId
     * @return
     */
    public DisplayImageOptions getSampleOptions(int drawbleId) {
        DisplayImageOptions.Builder options = getSampleOptions();
        options.showImageOnFail(drawbleId);
        options.showImageOnLoading(drawbleId);
        options.showImageForEmptyUri(drawbleId);
        options.cacheInMemory(true);
        options.cacheOnDisk(true);
        options.imageScaleType(ImageScaleType.EXACTLY);
        options.bitmapConfig(Bitmap.Config.RGB_565);
        options.resetViewBeforeLoading(true);
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

    public static void addActivity(Activity  activity){
        if (!mActivitys.contains(activity)){
            mActivitys.add(activity);
        }
    }

    public void exit(){
        ImageLoader.getInstance().stop();
        Iterator<Activity> iterator = mActivitys.iterator();
        while(iterator.hasNext()){
            Activity next = iterator.next();
            if (next!= null){
                next.finish();
            }
        }
    }
}
