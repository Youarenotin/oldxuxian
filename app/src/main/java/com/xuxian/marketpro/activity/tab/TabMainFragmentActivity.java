package com.xuxian.marketpro.activity.tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockFragmentActivity;
import com.xuxian.marketpro.activity.tab.goods.GoodsFragment;
import com.xuxian.marketpro.libraries.util.monitor.GoodsMonitor;
import com.xuxian.marketpro.libraries.util.monitor.monitor;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.db.UserDb;

/**
 * 作者：lubo on 8/1 0001 15:00
 * 邮箱：lubo_wen@126.com
 */
public class TabMainFragmentActivity extends SuperSherlockFragmentActivity {
    public static String VERSION_UPDATE;
    int clickedNumber;
    private int currentTabIndex;
    private long exitTime;
//    private ForumsFragment forumsFragment;
//    private GoodsFragment goodsFragment;
    private int index;
    private Fragment mContent;
    private Button[] mTabs;
//    private PersonalCenterFregment personalCenterFregment;
//    private ShoppingCartFragment shoppingCartFragment;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private TextView tv_tab_shopping_number;
    private UserDb userDb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        initTitleBar();
        initfindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initfindViewById() {
        this.tv_tab_shopping_number = (TextView) findViewById(R.id.tv_tab_shopping_number);
        this.mTabs = new Button[4];
        this.mTabs[0] = (Button) findViewById(R.id.btn_tab_main);
        this.mTabs[1] = (Button) findViewById(R.id.btn_tab_shopping_cart);
        this.mTabs[2] = (Button) findViewById(R.id.btn_tab_near);
        this.mTabs[3] = (Button) findViewById(R.id.btn_tab_me);
        this.mTabs[0].setSelected(true);

    }

    @Override
    protected void setListener() {
        GoodsMonitor.registerGoodsMonitor(TabMainFragmentActivity.class.getSimpleName(), new GoodsMonitor.GoodsMonitorCallback() {
            @Override
            public void appOprate(monitor.GoodsEnum goodsEnum) {

            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){//第一次按返回按钮时 , 如果fragment不是goodsfragment 跳回goodsfragment
            if (this.mContent==null||!(this.mContent instanceof GoodsFragment)){
                GoodsMonitor.getInstance().issueGoodsMonitorCallback(monitor.GoodsEnum.SWITCH_MAIN_PAGE);
            }
            else{
                exitAPP();
            }
        }
        return true;
    }

    /**
     * 退出app
     */
    private void exitAPP() {
       if (System.currentTimeMillis()-exitTime<2000){
           MyApplication.getInstance().exit();
       }
        AbToastUtil.showToast(getActivity(),"再按一次退出");
        this.exitTime=System.currentTimeMillis();
    }
}
