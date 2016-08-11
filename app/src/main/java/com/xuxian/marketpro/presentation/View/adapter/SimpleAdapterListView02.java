package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbScreenUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.entity.GoodsListEntity;
import com.xuxian.marketpro.presentation.entity.IndexEntity.GoodsEntity;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;
import com.xuxian.marketpro.presentation.imageloader.AnimateFirstDisplayListenerTipsimg;

import java.util.List;

/**
 * Created by youarenotin on 16/8/9.
 */
public class SimpleAdapterListView02 extends SectionedBaseAdapter {
    private Context mContext;
    private ShoppingCartGoodsDb detailsDb;
    private ImageLoader imageLoader;
    private List<GoodsEntity> mDataList;
    private int  screenWidth;

    public SimpleAdapterListView02(Context context) {
        this.mContext = context;
        this.detailsDb = new ShoppingCartGoodsDb(context);
        this.imageLoader = ImageLoader.getInstance();
        this.screenWidth = AbScreenUtils.getScreenWidth(context);
    }

    @Override
    public int getCountForSection(int section) {
        if (this.mDataList == null || this.mDataList.isEmpty()) {
            return 0;
        }
        if (this.mDataList.get(section).getGoods() == null || this.mDataList.get(section).getGoods().isEmpty()) {
            return 0;
        }
        if (this.mDataList.get(section).getGoods().size() % 2 == 0) {
            return this.mDataList.get(section).getGoods().size() / 2;
        }
        return (this.mDataList.get(section).getGoods().size() / 2) + 1;
    }

    @Override
    public Object getItem(int section, int postion) {
        return null;
    }

    @Override
    public long getItemId(int section, int postion) {
        return 0;
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView =View.inflate(mContext,R.layout.gridview_item_listview02,null);
            viewHolder.ll_goods_01 = (LinearLayout) convertView.findViewById(R.id.ll_goods_01);
            viewHolder.rl_goods_img_01 = (RelativeLayout) convertView.findViewById(R.id.rl_goods_img_01);
            viewHolder.iv_goods_img_01 = (ImageView) convertView.findViewById(R.id.iv_goods_img_01);
            viewHolder.tv_goods_count_01 = (TextView) convertView.findViewById(R.id.tv_goods_count_01);
            viewHolder.iv_goods_sold_out_01 = (ImageView) convertView.findViewById(R.id.iv_goods_sold_out_01);
            viewHolder.tv_goods_name_01 = (TextView) convertView.findViewById(R.id.tv_goods_name_01);
            viewHolder.tv_goods_price_01 = (TextView) convertView.findViewById(R.id.tv_goods_price_01);
            viewHolder.iv_goods_tipsim_01 = (ImageView) convertView.findViewById(R.id.iv_goods_tipsim_01);
            viewHolder.tv_goods_market_price_01 = (TextView) convertView.findViewById(R.id.tv_goods_market_price_01);
            viewHolder.ll_goods_02 = (LinearLayout) convertView.findViewById(R.id.ll_goods_02);
            viewHolder.rl_goods_img_02 = (RelativeLayout) convertView.findViewById(R.id.rl_goods_img_02);
            viewHolder.iv_goods_img_02 = (ImageView) convertView.findViewById(R.id.iv_goods_img_02);
            viewHolder.tv_goods_count_02 = (TextView) convertView.findViewById(R.id.tv_goods_count_02);
            viewHolder.iv_goods_sold_out_02 = (ImageView) convertView.findViewById(R.id.iv_goods_sold_out_02);
            viewHolder.tv_goods_name_02 = (TextView) convertView.findViewById(R.id.tv_goods_name_02);
            viewHolder.tv_goods_price_02 = (TextView) convertView.findViewById(R.id.tv_goods_price_02);
            viewHolder.iv_goods_tipsim_02 = (ImageView) convertView.findViewById(R.id.iv_goods_tipsim_02);
            viewHolder.tv_goods_market_price_02 = (TextView) convertView.findViewById(R.id.tv_goods_market_price_02);
            convertView.setTag(viewHolder);
            int w = this.screenWidth / 2;
            int h = (w / 4) * 3;
            AbViewUtil.setViewWH(viewHolder.rl_goods_img_01, w, h);
            AbViewUtil.setViewWH(viewHolder.rl_goods_img_02, w, h);
            AbViewUtil.setViewWH(viewHolder.iv_goods_img_01, w, h);
            AbViewUtil.setViewWH(viewHolder.iv_goods_img_02, w, h);
            AbViewUtil.setViewWH(viewHolder.iv_goods_sold_out_01, w / 2, ((w / 16) * 9) / 2);
            AbViewUtil.setViewWH(viewHolder.iv_goods_sold_out_02, w / 2, ((w / 16) * 9) / 2);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int position01 = position * 2;
        int position02 = (position * 2) + 1;
        GoodsListEntity goodsListEntity01 = (GoodsListEntity) ((GoodsEntity) this.mDataList.get(section)).getGoods().get(position01);
        viewHolder.ll_goods_01.setVisibility(View.VISIBLE);
        viewHolder.ll_goods_02.setVisibility(View.INVISIBLE);
        viewHolder.tv_goods_name_01.setText(goodsListEntity01.getTitle());
        this.imageLoader.displayImage(goodsListEntity01.getIcon(), viewHolder.iv_goods_img_01, MyApplication.getInstance().getSampleOptions(R.drawable.default_newimg));
        viewHolder.tv_goods_price_01.setText(mContext.getString(R.string.list_axe_text, new Object[]{goodsListEntity01.getPrice()}));
        viewHolder.tv_goods_market_price_01.setText(mContext.getString(R.string.list_axe_text_02, new Object[]{goodsListEntity01.getMarket_price()}));
        viewHolder.tv_goods_market_price_01.getPaint().setFlags(16);
        viewHolder.ll_goods_01.setId(position01);
        ShoppingCartGoodsEntity shoppingCartGoodsEntity01 = this.detailsDb.queryOneData(AbPreferenceUtils.loadPrefString(mContext, "USER_ID","0"), mDataList.get(section).getGoods().get(position01).getId());
        if (shoppingCartGoodsEntity01 != null) {
            int count01 = shoppingCartGoodsEntity01.getCount();
            if (count01 != 0) {
                viewHolder.tv_goods_count_01.setVisibility(View.VISIBLE);
                viewHolder.tv_goods_count_01.setText("" + count01);
            } else {
                viewHolder.tv_goods_count_01.setText(""+ count01);
                viewHolder.tv_goods_count_01.setVisibility(View.GONE);
            }
        } else {
            viewHolder.tv_goods_count_01.setText(""+ 0);
            viewHolder.tv_goods_count_01.setVisibility(View.GONE);
        }
        if (goodsListEntity01.getStore_nums() - goodsListEntity01.getSold_num() <= 0) {
            viewHolder.iv_goods_sold_out_01.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv_goods_sold_out_01.setVisibility(View.GONE);
        }
        if (AbStrUtil.isEmpty(goodsListEntity01.getTipsimg())) {
            viewHolder.iv_goods_tipsim_01.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.iv_goods_tipsim_01.setVisibility(View.VISIBLE);
            this.imageLoader.displayImage(goodsListEntity01.getTipsimg(), viewHolder.iv_goods_tipsim_01, MyApplication.getInstance().getSampleOptions(R.drawable.default_tipsimg), new AnimateFirstDisplayListenerTipsimg(this.screenWidth));
        }
        if (position02 < ((GoodsEntity) this.mDataList.get(section)).getGoods().size()) {
            GoodsListEntity goodsListEntity02 = (GoodsListEntity) ((GoodsEntity) this.mDataList.get(section)).getGoods().get(position02);
            viewHolder.ll_goods_01.setVisibility(View.VISIBLE);
            viewHolder.ll_goods_02.setVisibility(View.VISIBLE);
            viewHolder.tv_goods_name_02.setText(goodsListEntity02.getTitle());
            this.imageLoader.displayImage(goodsListEntity02.getIcon(), viewHolder.iv_goods_img_02, MyApplication.getInstance().getSampleOptions(R.drawable.default_newimg));
            viewHolder.tv_goods_price_02.setText(mContext.getString(R.string.list_axe_text, new Object[]{goodsListEntity02.getPrice()}));
            viewHolder.tv_goods_market_price_02.setText(mContext.getString(R.string.list_axe_text_02, new Object[]{goodsListEntity02.getMarket_price()}));
            viewHolder.tv_goods_market_price_02.getPaint().setFlags(16);
            viewHolder.ll_goods_02.setId(position02);
            ShoppingCartGoodsEntity shoppingCartGoodsEntity02 = this.detailsDb.queryOneData(AbPreferenceUtils.loadPrefString(mContext, "USER_ID", "0"), ((GoodsListEntity) ((GoodsEntity) this.mDataList.get(section)).getGoods().get(position02)).getId().intValue());
            if (shoppingCartGoodsEntity02 != null) {
                int count02 = shoppingCartGoodsEntity02.getCount();
                if (count02 != 0) {
                    viewHolder.tv_goods_count_02.setVisibility(View.VISIBLE);
                    viewHolder.tv_goods_count_02.setText("" + count02);
                } else {
                    viewHolder.tv_goods_count_02.setText("" + count02);
                    viewHolder.tv_goods_count_02.setVisibility(View.GONE);
                }
            } else {
                viewHolder.tv_goods_count_02.setText("" + 0);
                viewHolder.tv_goods_count_02.setVisibility(View.GONE);
            }
            if (goodsListEntity02.getStore_nums() - goodsListEntity02.getSold_num() <= 0) {
                viewHolder.iv_goods_sold_out_02.setVisibility(View.VISIBLE);
            } else {
                viewHolder.iv_goods_sold_out_02.setVisibility(View.GONE);
            }
            if (AbStrUtil.isEmpty(goodsListEntity02.getTipsimg())) {
                viewHolder.iv_goods_tipsim_02.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.iv_goods_tipsim_02.setVisibility(View.VISIBLE);
                imageLoader.displayImage(goodsListEntity02.getTipsimg(), viewHolder.iv_goods_tipsim_02, MyApplication.getInstance().getSampleOptions(R.drawable.default_tipsimg), new AnimateFirstDisplayListenerTipsimg(this.screenWidth));
            }
        } else {
            viewHolder.ll_goods_01.setVisibility(View.VISIBLE);
            viewHolder.ll_goods_02.setVisibility(View.INVISIBLE);
        }
        viewHolder.ll_goods_01.setOnClickListener(new ll_goods_01OnClickListener(section));
        viewHolder.ll_goods_02.setOnClickListener(new ll_goods_02OnClickListener(section));
        if (convertView==null)
            return new TextView(mContext);
        return convertView;
    }

    @Override
    public int getSectionCount() {
        if (mDataList!=null)
            return mDataList.size();
        return 0;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup viewGroup) {
        ViewHolderTitle viewHolderTitle;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.title_layout, null);
            viewHolderTitle = new ViewHolderTitle();
            viewHolderTitle.title = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolderTitle);
        } else {
            viewHolderTitle = (ViewHolderTitle) convertView.getTag();
        }
        if (!(this.mDataList == null || this.mDataList.isEmpty())) {
            viewHolderTitle.title.setText(this.mDataList.get(section).getTitle());
        }
        if (convertView==null)
            return new TextView(mContext);
        return convertView;
    }

    public void setData(List<GoodsEntity> data){
        this.mDataList=data;
        notifyDataSetChanged();
    }

//    protected void prepareSections(int sectionsNumber) {
//    }
//
//    protected void onSectionAdded(Item section, int sectionPosition) {
//    }


    @Override
    public int getViewTypeCount() {
        if (mDataList==null||mDataList.size()<2){
            return 2;
        }
        return mDataList.size();
    }

    public boolean isItemViewTypePinned(int viewType){
        return viewType==1;
    }



    class ViewHolder {
        ImageView iv_goods_img_01;
        ImageView iv_goods_img_02;
        ImageView iv_goods_sold_out_01;
        ImageView iv_goods_sold_out_02;
        ImageView iv_goods_tipsim_01;
        ImageView iv_goods_tipsim_02;
        LinearLayout ll_goods_01;
        LinearLayout ll_goods_02;
        RelativeLayout rl_goods_img_01;
        RelativeLayout rl_goods_img_02;
        TextView tv_goods_count_01;
        TextView tv_goods_count_02;
        TextView tv_goods_market_price_01;
        TextView tv_goods_market_price_02;
        TextView tv_goods_name_01;
        TextView tv_goods_name_02;
        TextView tv_goods_price_01;
        TextView tv_goods_price_02;
    }
    private static class ViewHolderTitle {
        TextView title;
    }

    private class ll_goods_01OnClickListener implements View.OnClickListener {
        private int section;
        public ll_goods_01OnClickListener(int section) {
            this.section=section;
        }

        @Override
        public void onClick(View v) {
            //TODO 启动 商品详细Activity
        }
    }

    private class ll_goods_02OnClickListener implements View.OnClickListener {
        private int section;
        public ll_goods_02OnClickListener(int section) {
            this.section=section;
        }

        @Override
        public void onClick(View v) {
            //TODO 启动 商品详细Activity
        }
    }
}
