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


    public AbLoadingDialog(Context context) {
        super(context);
        this.cancelable = true;
        this.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
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
        this.mAnim = new RotateAnimation(360f,0.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

    }
}
