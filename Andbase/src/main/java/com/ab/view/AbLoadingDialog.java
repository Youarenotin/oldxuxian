package com.ab.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.R;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;

/**
 * 作者：lubo on 7/22 0022 11:29
 * 邮箱：lubo_wen@126.com
 */
public class AbLoadingDialog extends Dialog {
    private static final int CHANGE_TITLE_WHAT = 1;
    private static final int CHNAGE_TITLE_DELAYMILLIS = 300;
    private static final int MAX_SUFFIX_NUMBER = 3;
    private static final char SUFFIX = '.';
    private boolean cancelable;
    private Handler handler;
    private ImageView iv_route;
    private RotateAnimation mAnim;
    private Context mContext;
    private TextView tv;
    private TextView tv_point;

    class MyHandler extends Handler{
        private  int num;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==CHANGE_TITLE_WHAT){
                StringBuilder sb = new StringBuilder();
                if (num>=MAX_SUFFIX_NUMBER){
                    num=0;
                }
                num++;
                for (int i = 0 ; i < num; i ++){
                    sb.append(SUFFIX);
                }
                tv_point.setText(sb.toString());
                if (isShowing()){
                    sendEmptyMessageDelayed(CHANGE_TITLE_WHAT,300);
                }
                else{
                    num=0;
                }
            }
        }
    }

    public AbLoadingDialog(Context context) {
        super(context, R.style.Dialog_bocop1);
//        super(context);

        this.cancelable = true;
        this.handler = new MyHandler();
        this.mContext = context;
        init();
    }

    private void init() {
        setContentView(R.layout.activity_custom_loding_dialog_layout);
        this.iv_route = (ImageView) findViewById(R.id.iv_route);
        this.tv = (TextView) findViewById(R.id.tv);
        this.tv_point = (TextView) findViewById(R.id.tv_point);
        LinearLayout ll_dialog_bocop_loading_bg = (LinearLayout) findViewById(R.id.ll_dialog_bocop_loaing_bg);
        int ll_dialog_bocop_loading_bg_w_h = AbScreenUtils.getScreenWidth(getContext()) / MAX_SUFFIX_NUMBER;
        int iv_route_w_h = ll_dialog_bocop_loading_bg_w_h / MAX_SUFFIX_NUMBER;
        AbViewUtil.setViewWH(this.iv_route, iv_route_w_h, iv_route_w_h);
        AbViewUtil.setViewWH(ll_dialog_bocop_loading_bg, ll_dialog_bocop_loading_bg_w_h, ll_dialog_bocop_loading_bg_w_h);
        initAnim();
        getWindow().setWindowAnimations(R.anim.fade_in);
    }

    private void initAnim() {
        mAnim = new RotateAnimation(360f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnim.setDuration(2000);
        mAnim.setRepeatCount(-1);
        mAnim.setRepeatMode(Animation.RESTART);
        mAnim.setStartTime(Animation.START_ON_FIRST_FRAME);
    }

    @Override
    public void show(){
        super.show();
        iv_route.startAnimation(mAnim);
        handler.sendEmptyMessage(CHANGE_TITLE_WHAT);
    }

    @Override
    public void dismiss() {
        this.mAnim.cancel();
        super.dismiss();
    }

    @Override
    public void setCancelable(boolean flag) {
        this.cancelable = flag;
        super.setCancelable(flag);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.tv.setText(title);
        if (!isShowing()) {
            show();
        }
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(this.mContext.getString(titleId));
        if (!isShowing()) {
            show();
        }
    }

}
