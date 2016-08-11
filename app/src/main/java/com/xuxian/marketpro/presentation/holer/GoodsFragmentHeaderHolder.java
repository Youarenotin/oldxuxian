package com.xuxian.marketpro.presentation.holer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;
import com.ab.view.holder.AbBaseHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.tab.TabMainFragmentActivity;
import com.xuxian.marketpro.activity.tab.goods.BannerTpyeOnClickListener;
import com.xuxian.marketpro.activity.tab.goods.BannerTypeOnClickListener;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity;
import com.xuxian.marketpro.presentation.entity.GoodsFragmentHeaderEntity.DataEntity.AppEntity;

/**
 * Created by youarenotin on 16/8/11.
 */
public class GoodsFragmentHeaderHolder extends AbBaseHolder<AppEntity>{
    private ImageView civ_list_item_goods_header_icon;
    private int screenWidth;
    private TabMainFragmentActivity tabMainFragmentActivity;
    private TextView tv_list_item_goods_header_title;
    private Context mContext;

    public GoodsFragmentHeaderHolder(Context context,TabMainFragmentActivity tabMainFragmentActivity) {
        super(context);
        this.tabMainFragmentActivity=tabMainFragmentActivity;
        screenWidth= AbScreenUtils.getScreenWidth(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.listitem_goods_header, null);
        this.civ_list_item_goods_header_icon= (ImageView) view.findViewById(R.id.civ_list_item_goods_header_icon);
        this.tv_list_item_goods_header_title= (TextView) view.findViewById(R.id.tv_list_item_goods_header_title);
        return view;
    }

    @Override
    protected void refreshView() {
        ImageLoader.getInstance().displayImage(data.getImg(),civ_list_item_goods_header_icon, MyApplication.getInstance().getSampleOptions(R.drawable.default_newimg));
        if (!AbStrUtil.isEmpty(data.getTitle())){
            tv_list_item_goods_header_title.setText(data.getTitle());
        }
        AbViewUtil.setViewWH(civ_list_item_goods_header_icon,screenWidth/4,screenWidth/6);
        if (AbStrUtil.isNumber(data.getBannertype()) && !AbStrUtil.isEmpty(data.getBannertype())){
            civ_list_item_goods_header_icon.setOnClickListener(new BannerTpyeOnClickListener(tabMainFragmentActivity,Integer.valueOf(data.getBannertype()),data.getMessage()));
        }
    }
}
