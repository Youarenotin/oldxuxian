package com.xuxian.marketpro.net;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.util.AbWifiUtil;

/**
 * Created by youarenotin on 16/8/15.
 */
public abstract class AnimeAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private ProgressDialog dialog;
    private String loadingText;
    private Activity mContext;

    public AnimeAsyncTask(String loadingText, Activity mContext) {
        this.loadingText = loadingText;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!AbWifiUtil.isConnectivity(mContext)) {
            AbToastUtil.showToast(mContext, "网络没有连接!");
        }
        if (!AbStrUtil.isEmpty(this.loadingText)) {
            showRequestDialog(mContext, loadingText);
        }

    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        dissMissDialog();
    }

    protected void showRequestDialog(Activity mContext, String loadingText) {
        this.dialog = ProgressDialog.show(mContext, "提示", loadingText);
        this.dialog.setCanceledOnTouchOutside(false);//不可以触摸dialog外取消
        this.dialog.setCancelable(true);//可以back取消
    }

    private void dissMissDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
