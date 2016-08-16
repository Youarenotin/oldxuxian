package com.xuxian.marketpro.activity.tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockFragmentActivity;
import com.xuxian.marketpro.activity.tab.forums.ForumsFragment;
import com.xuxian.marketpro.activity.tab.goods.GoodsFragment;
import com.xuxian.marketpro.activity.tab.personlcenter.PersonalCenterFregment;
import com.xuxian.marketpro.activity.tab.shoppingcar.ShoppingCartFragment;
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
    private ForumsFragment forumsFragment;
    private GoodsFragment goodsFragment;
    private int index;
    private Fragment mContent;
    private Button[] mTabs;
    private PersonalCenterFregment personalCenterFregment;
    private ShoppingCartFragment shoppingCartFragment;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private TextView tv_tab_shopping_number;
    private UserDb userDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        initTitleBar();
        initfindViewById();
        initFragment();
        setListener();
        init();
        //updata();  //友盟更新
        getUserInfo();
    }

    private void initFragment() {
        this.goodsFragment = new GoodsFragment(this);
        this.forumsFragment = new ForumsFragment();
        this.shoppingCartFragment = new ShoppingCartFragment();
        this.personalCenterFregment = new PersonalCenterFregment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rl_tab_fragment_container, this.goodsFragment, "goodsFragment");
        transaction.commitAllowingStateLoss();
        this.mContent = this.goodsFragment;
        this.currentTabIndex=0;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {
        //todo 个推和友盟初始化
        userDb=new UserDb(getActivity());
        shoppingCartGoodsDb=new ShoppingCartGoodsDb(getActivity());
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initfindViewById() {
        this.tv_tab_shopping_number = (TextView) findViewById(R.id.tv_tab_shopping_number);
        this.mTabs = new Button[4];
        this.mTabs[0] = (Button) findViewById(R.id.btn_tab_main);
        this.mTabs[1] = (Button) findViewById(R.id.btn_tab_shopping_cart1);
        this.mTabs[2] = (Button) findViewById(R.id.btn_tab_near);
        this.mTabs[3] = (Button) findViewById(R.id.btn_tab_me);
        this.mTabs[0].setSelected(true);
//        this.mTabs[1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shoppingCarFragment();
//            }
//        });
    }

    @Override
    protected void setListener() {
        GoodsMonitor.registerGoodsMonitor(TabMainFragmentActivity.class.getSimpleName(), new GoodsMonitor.GoodsMonitorCallback() {
            @Override
            public void appOprate(monitor.GoodsEnum goodsEnum) {
                switch (goodsEnum){
                    case SWITCH_MAIN_PAGE://切换到商品tab
                        goodsFragment();
                        break;
                    case REFRESH_GOODS://刷新shangpin

                        break;
                    case REFRESH_LISTVIEW://刷新listview

                        break;
                    case REFRESH_ADDRESS://刷新地址

                        break;
                    case SWITCH_SHOPPING_CART://切换购物车tab

                        break;
                }
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


    public void  onTabClicked(View view){
        switch (view.getId()){
            case R.id.btn_tab_main://商品tab
                goodsFragment();
                break;
            case R.id.btn_tab_shopping_cart1://购物车tab
                shoppingCarFragment();
                break;
            case R.id.btn_tab_near://附近tab
                forumsFragment();
                break;
            case R.id.btn_tab_me://我tab
                personalCenterFragment();
                break;
        }
    }

    private void personalCenterFragment() {
        clickedNumber=0;
        index=3;
        if (personalCenterFregment==null)
            personalCenterFregment=new PersonalCenterFregment();
        switchContent(personalCenterFregment,"personalCenterFregment");
    }

    private void forumsFragment() {
        this.clickedNumber=0;
        this.index=2;
        if (forumsFragment==null)
           forumsFragment=new ForumsFragment();
        switchContent(forumsFragment,"forumsFragment");
    }

    private void shoppingCarFragment() {
        this.clickedNumber=0;
        if (index!=1){
            //// TODO: 16/8/2 从别的tab调到购物车刷新购物车 刷新购物车
        }
        index=1;
        if(shoppingCartFragment==null)
            shoppingCartFragment=new ShoppingCartFragment();
        switchContent(shoppingCartFragment,"shoppingCartFragment");
    }

    private void goodsFragment() {
        this.index=0;
        if (goodsFragment==null)
            goodsFragment=new GoodsFragment(this);
        this.clickedNumber++;
        if (clickedNumber==2){
            clickedNumber=0;
            //// TODO: 16/8/2 listview滑动到顶部
        }
        switchContent(goodsFragment,"goodsFragment");
    }

    private void switchContent(Fragment fragment, String tag) {
        try {
            if (mContent!=fragment){//点击为非当前fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()){
                transaction.hide(mContent).show(fragment).commitAllowingStateLoss();
            }else{
                transaction.hide(mContent).add(R.id.rl_tab_fragment_container,fragment,tag).show(fragment).commitAllowingStateLoss();
            }
            for (Button button:mTabs){
                button.setSelected(false);
            }
            mTabs[index].setSelected(true);
            mContent=fragment;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void getUserInfo() {

    }
}
