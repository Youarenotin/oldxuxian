package com.xuxian.marketpro.libraries.util;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.util.AbStrUtil;
import com.ab.view.progress.AbHorizontalProgressBar;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by youarenotin on 16/8/25.
 */
public class AddPhoto {
    public static final int CAMERA_CROP_DATA = 3022;
    public static final int CAMERA_WITH_DATA = 3023;
    public static final int PHOTO_PICKED_WITH_DATA = 3021;
    private File PHOTO_DIR;
    private int camIndex;
    private Activity context;
    private AbHttpUtil mAbHttpUtil;
    private AbHorizontalProgressBar mAbProgressBar;
    private DialogFragment mAlertDialog;
    private View mAvatarView;
    private File mCurrentPhotoFile;
    private String mFileName;
    private ArrayList<String> mPhotoList;
    private int max;
    private TextView maxText;
    private TextView numberText;
    private int progress;
    private int selectIndex;

    public AddPhoto(Activity context) {
        this.mPhotoList = null;
        this.selectIndex = 0;
        this.camIndex = 0;
        this.mAvatarView = null;
        this.PHOTO_DIR = null;
        this.max = 100;
        this.progress = 0;
        this.mAlertDialog = null;
        this.mAbHttpUtil = null;
        this.context = context;
    }

    public static String getPath(Activity activity, Uri uri) {
        if (AbStrUtil.isEmpty(uri.getAuthority())) {
            return null;
        }
        Cursor cursor = activity.managedQuery(uri, new String[]{"_data"}, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow("_data");
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
