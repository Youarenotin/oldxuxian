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

import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperFragment;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.View.widght.CircleImageView;
import com.xuxian.marketpro.presentation.db.UserDb;

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
        return view;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    protected void setListener() {

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
}
