package com.xuxian.marketpro.appbase.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.xuxian.marketpro.presentation.entity.CityEntity;

/**
 * Created by youarenotin on 16/7/27.
 */
public class DialogUtils {
    private static String mDialogTag;

    /* renamed from: com.xuxian.market.appbase.util.AbDialogUtil.1 */
    static class AnonymousClass1 implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ boolean val$isFinish;

        AnonymousClass1(Activity activity, boolean z) {
            this.val$activity = activity;
            this.val$isFinish = z;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (this.val$activity != null && !this.val$isFinish) {
                this.val$activity.finish();
            }
        }
    }

    /* renamed from: com.xuxian.market.appbase.util.AbDialogUtil.2 */
    static class AnonymousClass2 implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ boolean val$isFinish;

        AnonymousClass2(Activity activity, boolean z) {
            this.val$activity = activity;
            this.val$isFinish = z;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (this.val$activity != null && !this.val$isFinish) {
                this.val$activity.finish();
            }
        }
    }

    /* renamed from: com.xuxian.market.appbase.util.AbDialogUtil.3 */
    static class AnonymousClass3 implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ boolean val$isFinish;

        AnonymousClass3(Activity activity, boolean z) {
            this.val$activity = activity;
            this.val$isFinish = z;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (this.val$activity != null && !this.val$isFinish) {
                this.val$activity.finish();
            }
        }
    }

    static {
        mDialogTag = "dialog";
    }

    public static AlertDialog.Builder initDialog(Activity activity, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(text);
        builder.setTitle("提示");
        builder.setCancelable(false);
        return builder;
    }

    public static AlertDialog.Builder initDialog(Activity activity, String title, String text) {
        Builder builder = new Builder(activity);
        builder.setMessage(text);
        builder.setTitle(title);
        builder.setCancelable(false);
        return builder;
    }

    public static void showDialog(Activity activity, String title, String text, boolean isFinish) {
        Builder builder = new Builder(activity);
        builder.setMessage(text);
        if (AbStrUtil.isEmpty(title)) {
            builder.setTitle("\u63d0\u793a");
        } else {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setPositiveButton("\u786e\u8ba4", new AnonymousClass1(activity, isFinish));
        if (activity != null && !activity.isFinishing()) {
            builder.create().show();
        }
    }

    public static void showDialog(Activity activity, String text, boolean isFinish) {
        Builder builder = new Builder(activity);
        builder.setMessage(text);
        builder.setTitle("\u63d0\u793a");
        builder.setCancelable(false);
        builder.setPositiveButton("\u786e\u8ba4", new AnonymousClass2(activity, isFinish));
        if (activity != null && !activity.isFinishing()) {
            builder.create().show();
        }
    }

    public static void showDialogButton(Activity activity, String text, boolean isFinish) {
        Builder builder = new Builder(activity);
        builder.setMessage(text);
        builder.setTitle("\u63d0\u793a");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new AnonymousClass3(activity, isFinish));
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (activity != null && !activity.isFinishing()) {
            builder.create().show();
        }
    }

    public static boolean isStatus(Activity activity, CityEntity.StatusEntity status) {
        if (status == null) {
            return true;
        }
        if (status.getCode() != 1 && status.getCode() != 404) {
            return true;
        }
        if (!(activity == null || activity.isFinishing())) {
            showDialog(activity, status.getMessage(), true);
        }
        return false;
    }

    public static boolean showToast(Activity activity, CityEntity.StatusEntity status) {
        if (status == null) {
            return true;
        }
        if (status.getCode() != 1 && status.getCode() != 404) {
            return true;
        }
        AbToastUtil.showToast((Context) activity, status.getMessage());
        return false;
    }

    public static void showSucceedDialog(Activity activity, CityEntity.StatusEntity statusEntity, boolean isFinish) {
        if (statusEntity != null) {
            showDialog(activity, statusEntity.getMessage(), isFinish);
        }
    }

    public static ProgressDialog showRequestDialog(Activity activity, String msg) {
        ProgressDialog dialog = ProgressDialog.show(activity, "\u63d0\u793a", msg);
        dialog.setCancelable(true);
        return dialog;
    }
}
