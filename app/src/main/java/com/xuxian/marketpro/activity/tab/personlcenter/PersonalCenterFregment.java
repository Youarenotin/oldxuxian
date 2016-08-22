package com.xuxian.marketpro.activity.tab.personlcenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.View.widght.CircleImageView;
import com.xuxian.marketpro.presentation.View.widght.pop.OperationPopupWindow;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.UserEntity;

/**
 * Created by youarenotin on 16/8/2.
 */
public class PersonalCenterFregment extends SuperFragment{
    public static final String GUIDE = "guide";
    private SampleAdapter adapter;
    private CircleImageView iv_head_icon;
    private LinearLayout ll_management_shipping_address;
    private LinearLayout ll_user_information;
    private ListView lv_account;
    private final String mPageName;
    public View popView;
    public OperationPopupWindow popWindow;
    private TextView tv_point;
    private TextView tv_user_name;
    private UserDb userDb;

    public PersonalCenterFregment() {
        mPageName = "PersonalCenterFregment";
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_center_fragment, null);
        View topView = View.inflate(getActivity(), R.layout.account_header, null);
        initTitleBar();
        initFindViewById(view);
        initHeaderView(topView);
        initMenu(topView);
        setListener();
        init();
        getUserInfo();
        return view;
    }

    private void getUserInfo() {

    }

    private void initMenu(View topView) {
        this.adapter = new SampleAdapter(getActivity());
        this.adapter.add(new SampleItem(R.drawable.sliding_my_order, "我的订单"));
        this.adapter.add(new SampleItem(R.drawable.members_icon, "我的会员"));
        this.adapter.add(new SampleItem(R.drawable.youhui_icon, "我的优惠券"));
        this.adapter.add(new SampleItem(R.drawable.qiangxian_icon, "抢鲜"));
        this.adapter.add(new SampleItem(R.drawable.sliding_mall_icon, "积分商城"));
        this.lv_account.addHeaderView(topView);
        this.lv_account.setAdapter(this.adapter);
    }

    private void initHeaderView(View topView) {
        this.ll_user_information = (LinearLayout) topView.findViewById(R.id.ll_user_information);
        this.tv_user_name = (TextView) topView.findViewById(R.id.tv_user_name);
        this.iv_head_icon = (CircleImageView) topView.findViewById(R.id.iv_head_icon);
        this.ll_management_shipping_address = (LinearLayout) topView.findViewById(R.id.ll_management_shipping_address);
        this.iv_head_icon.setBorderColor(getResources().getColor(R.color.white));
        this.iv_head_icon.setBorderWidth(8);
//        this.iv_head_icon.setNeedshowline(true);
        this.tv_point = (TextView) topView.findViewById(R.id.tv_point);
        this.ll_user_information.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserEntity userEntity = PersonalCenterFregment.this.userDb.queryData();
                if (userEntity != null) {
//                    ActivityUtil.startPersonalInformationActivity(PersonalCenterFregment.this.getActivity(), userEntity);
                } else {
                    ActivityUtil.startLoginActivity(PersonalCenterFregment.this.getActivity());
                }
            }
        });
        this.ll_management_shipping_address.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (PersonalCenterFregment.this.userDb.queryData() != null) {
//                    ActivityUtil.startChooseShippingAddressActivity(PersonalCenterFregment.this.getActivity(), 1);
                } else {
                    ActivityUtil.startLoginActivity(PersonalCenterFregment.this.getActivity());
                }
            }
        });
    }

    @Override
    protected void init() {
        this.userDb = new UserDb(getActivity());
        setData(this.userDb.queryData());
    }

    public void setData(UserEntity userEntity) {
        if (userEntity != null) {
            AbPreferenceUtils.savePrefString(getActivity(), "USER_ID", userEntity.getUserid());
            this.tv_user_name.setText(userEntity.getUsername());
            this.tv_point.setText("许鲜币 " + userEntity.getPoint());
            ImageLoader.getInstance().displayImage(userEntity.getHead_ico(), this.iv_head_icon, MyApplication.getInstance().getSampleOptions(R.drawable.pintuan_default_head_icon));
            return;
        }
        this.tv_user_name.setText("点击登录");
        this.tv_point.setText(" 许鲜币  0");
        ImageLoader.getInstance().displayImage("drawable://2130838130", this.iv_head_icon);
    }

    @Override
    protected void initTitleBar() {
        if (getTitle_bar() == null) {
            titleBar();
            setTitle(R.string.account);
            setTitleLeftText("");
            int statusHeight = AbScreenUtils.getStatusHeight(getActivity());
            setTitleLeftIcon(R.drawable.set, statusHeight - 10, statusHeight - 10);
            setTitleRightIcon(R.drawable.tab_near_icon_grey, statusHeight - 10, statusHeight - 10);
            setTitleLeftViewShow(true);
            setTitleRightViewShow(true);
            getSherlockActivity().getActionBar().setCustomView(getTitle_bar());
            return;
        }
        getSherlockActivity().getActionBar().setCustomView(getTitle_bar());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            initTitleBar();
        }
    }

    @Override
    protected void initFindViewById(View view) {
        this.lv_account = (ListView) view.findViewById(R.id.lv_account);
    }

    @Override
    protected void setListener() {
        BarOnClickListener barOnClickListener = new BarOnClickListener();
        getTitleLeftClick().setOnClickListener(barOnClickListener);
        getTitleRightClick().setOnClickListener(barOnClickListener);
//        UserMonitor.getInstance().register(new UserMonitorCallback() {
//            public void AppOperation(UserEntity userEntity) {
//                PersonalCenterFregment.this.setData(userEntity);
//            }
//        }, PersonalCenterFregment.class.getName());
//        RefreshCardMonitor.getInstance().register(new RefreshCardMonitorCallback() {
//            public void AppOperation(boolean is) {
//                if (!is) {
//                    PersonalCenterFregment.this.getUseriInfo();
//                }
//            }
//        }, PersonalCenterFregment.class.getName());
    }
    private class SampleAdapter extends ArrayAdapter<SampleItem> {
        public SampleAdapter(Context context) {
            super(context,0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SlidingHolder slidingHolder;
            if (convertView == null) {
                slidingHolder = new SlidingHolder();
                convertView = View.inflate(getContext(), R.layout.item_personal_center_fregment, null);
                slidingHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                slidingHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                slidingHolder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
                convertView.setTag(slidingHolder);
            } else {
                slidingHolder = (SlidingHolder) convertView.getTag();
            }
            slidingHolder.iv_icon.setImageResource(getItem(position).iconRes);
            slidingHolder.tv_title.setText(getItem(position).title);
            convertView.setId(position);
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (PersonalCenterFregment.this.userDb.isLogin()) {
                        //已经登录⬇️
                        switch (v.getId()) {
                            case 0:
//                                ActivityUtil.startMyOrderActivity(PersonalCenterFregment.this.getActivity());
                                return;
                            case 1:
//                                ActivityUtil.startXuXianMemberActivity(PersonalCenterFregment.this.getActivity());
                                return;
                            case 2:
//                                ActivityUtil.startMyCouponsActivity(PersonalCenterFregment.this.getActivity());
                                return;
                            case 3:
//                                ActivityUtil.startUserCardsActivity(PersonalCenterFregment.this.getActivity());
                                return;
                            case 4:
//                                ActivityUtil.startXuXianMallActivity(PersonalCenterFregment.this.getActivity());
                                return;
                            default:
                                return;
                        }
                    }
                    //没有登录⬇
//                    ActivityUtil.startLoginActivity(PersonalCenterFregment.this.getActivity());
                }
            });
            return convertView;
        }
    }

    private class SampleItem {
        public int iconRes;
        public String title;

        public SampleItem(int iconRes, String title) {
            this.iconRes = iconRes;
            this.title = title;
        }
    }
    class SlidingHolder {
        ImageView iv_icon;
        TextView tv_text;
        TextView tv_title;

        SlidingHolder() {
        }
    }

    public class BarOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_title_bar_left_click:
//                    ActivityUtil.startSetActivity(PersonalCenterFregment.this.getActivity());
                case R.id.ll_title_bar_right_click:
//                    ActivityUtil.startMessagerActivity(PersonalCenterFregment.this.getActivity());
                default:
            }
        }
    }
}
