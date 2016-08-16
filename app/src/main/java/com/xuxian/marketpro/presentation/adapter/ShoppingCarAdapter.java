package com.xuxian.marketpro.presentation.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.presentation.View.adapter.SectionedBaseAdapter;
import com.xuxian.marketpro.presentation.View.listview.ShoppingCarListView;
import com.xuxian.marketpro.presentation.View.widght.pop.OperationPopupWindow;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.entity.NewCartGoodsEntity;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;
import com.xuxian.marketpro.presentation.imageloader.AnimateFirstDisplayListenerTipsimg;

import java.util.List;
import java.util.Map;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ShoppingCarAdapter extends SectionedBaseAdapter implements ShoppingCarListView.PinnedSectionListAdapter{
    private Button btn_pop_shopping_cart_increase;
    private Button btn_pop_shopping_cart_reduce;
    private Button btn_pop_shopping_cart_refresh;
    private Button btn_shopping_cart_delete;
    private int cheBoxNum;
    private ShoppingCartGoodsEntity goodsInfoEntity;
    private OperationPopupWindow goodsInfoPopupWindow;
    private int goods_number;
    private ImageButton ib_pop_shopping_cart_close;
    private ImageButton ib_pop_shopping_cart_del;
    private boolean isLoading;
    private ImageView iv_pop_shopping_cart_icon;
    private ImageView iv_pop_shopping_cart_tipsimg;
    private Activity mContext;
    private List<NewCartGoodsEntity.ShoppingCartDataEntity.GoodssectionsEntity> mListData;
    private Double mPriceCount;
    private TextView mShoppingCartAlwaysPrice;
    private int screenWidth;
    private Map<Integer, Boolean> seleteMap;
    private ShoppingCartGoodsDb shoppingCartGoodsDb;
    private TextView tv_pop_shopping_cart_count;
    private TextView tv_pop_shopping_cart_price;
    private TextView tv_pop_shopping_cart_title;
    private TextView tv_pop_shopping_cart_unit;
    private TextView tv_shopping_cart_settlement;
    private boolean visflag;

    public ShoppingCarAdapter(Activity context) {
        this.visflag = false;
        this.cheBoxNum = 0;
        this.isLoading = true;
        this.goods_number = 1;
        this.mContext = context;
        this.screenWidth = AbScreenUtils.getScreenWidth(context);
    }

    /**
     * 是否为被固定的item or section
     * @param viewType
     * @return
     */
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType==1;
    }

    /**
     * 该section子目中的数量
     * @param i
     * @return
     */
    @Override
    public int getCountForSection(int section) {
        if (mListData==null||mListData.isEmpty()||mListData.get(section).getGoodslist()==null||mListData.get(section).getGoodslist().isEmpty()){
            return 0;
        }
        return mListData.get(section).getGoodslist().size();
    }

    @Override
    public ShoppingCartGoodsEntity getItem(int section, int position) {
        if (this.mListData == null || this.mListData.isEmpty() || this.mListData.get(section).getGoodslist() == null || this.mListData.get(section).getGoodslist().isEmpty()) {
            return null;
        }
        return this.mListData.get(section).getGoodslist().get(position);

    }

    @Override
    public long getItemId(int section, int postion) {
        return 0;
    }

    /**
     * section 数量
     * @return
     */
    @Override
    public int getSectionCount() {
       if (mListData==null||mListData.isEmpty()){
           return 0;
       }
        return mListData.size();
    }

    /**
     * section view
     * @param section
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup viewGroup) {
        ViewHolderTitle viewHolderTitle;
        if (convertView == null) {
            viewHolderTitle = new ViewHolderTitle();
            viewHolderTitle.linearLayout = new LinearLayout(this.mContext);
            viewHolderTitle.linearLayout.setOrientation(0);
            viewHolderTitle.linearLayout.setPadding(20, 20, 20, 20);
            viewHolderTitle.textView_title = new TextView(this.mContext);
            viewHolderTitle.linearLayout.addView(viewHolderTitle.textView_title);
            convertView = viewHolderTitle.linearLayout;
            convertView.setTag(viewHolderTitle);
        } else {
            viewHolderTitle = (ViewHolderTitle) convertView.getTag();
        }
        convertView.setBackgroundColor(Color.parseColor(mContext.getResources().getString(R.color.click_gray)));
        if (!(this.mListData == null || this.mListData.isEmpty() || this.mListData.get(section) == null)) {
            viewHolderTitle.textView_title.setText(this.mListData.get(section).getSectionname());
        }
        return convertView;
    }

    /**
     * item view
     * @param section
     * @param postion
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup viewGroup) {
        ShoppingCarHolder holder;
        if (convertView == null) {
            holder = new ShoppingCarHolder();
            convertView = View.inflate(this.mContext, R.layout.item_shoppingcart_layout_02, null);
            holder.tv_shopping_cart_title = (TextView) convertView.findViewById(R.id.tv_shopping_cart_title);
            holder.iv_shopping_cart_img = (ImageView) convertView.findViewById(R.id.iv_shopping_cart_img);
            holder.tv_shopping_cart_price = (TextView) convertView.findViewById(R.id.tv_shopping_cart_price);
            holder.tv_shopping_cart_count = (TextView) convertView.findViewById(R.id.tv_shopping_cart_count);
            holder.iv_shop_car_tipsimg = (ImageView) convertView.findViewById(R.id.iv_shop_car_tipsimg);
            holder.cb_shopping_cart_checkbox = (CheckBox) convertView.findViewById(R.id.cb_shopping_cart_checkbox);
            convertView.setTag(holder);
            int w = this.screenWidth / 4;
            AbViewUtil.setViewWH(holder.iv_shopping_cart_img, w, (w / 4) * 3);
        } else {
            holder = (ShoppingCarHolder) convertView.getTag();
        }
        ShoppingCartGoodsEntity mDto = this.mListData.get(section).getGoodslist().get(position);
        holder.tv_shopping_cart_title.setText(mDto.getTitle());
        holder.tv_shopping_cart_price.setText(this.mContext.getString(R.string.indent_price_text, new Object[]{mDto.getPrice()}));
        holder.tv_shopping_cart_count.setText(this.mContext.getString(R.string.list_axe_text_03, new Object[]{Integer.valueOf(mDto.getCount())}));
        ImageLoader.getInstance().displayImage(mDto.getIcon(), holder.iv_shopping_cart_img, MyApplication.getInstance().getSampleOptions(R.drawable.default_newimg));
        if (AbStrUtil.isEmpty(mDto.getTipsimg())) {
            holder.iv_shop_car_tipsimg.setVisibility(INVISIBLE);
        } else {
            holder.iv_shop_car_tipsimg.setVisibility(VISIBLE);
            ImageLoader.getInstance().displayImage(mDto.getTipsimg(), holder.iv_shop_car_tipsimg, MyApplication.getInstance().getSampleOptions(R.drawable.default_tipsimg), new AnimateFirstDisplayListenerTipsimg(this.screenWidth));
        }
        if (this.visflag) {
            if (this.seleteMap.get(mDto.getId())) {
                holder.cb_shopping_cart_checkbox.setChecked(true);
            } else {
                holder.cb_shopping_cart_checkbox.setChecked(false);
            }
            holder.cb_shopping_cart_checkbox.setVisibility(0);
        } else {
            holder.cb_shopping_cart_checkbox.setVisibility(8);
        }
        convertView.setBackgroundResource(R.drawable.light_yellow_selector);
//        convertView.setOnClickListener(new AnonymousClass1(holder, mDto, section, parent, position));
        return convertView;
    }

    public int getViewTypeCount() {
        if (this.mListData == null || this.mListData.size() < 2) {
            return 2;
        }
        return this.mListData.size();
    }

    class LayoutHolder {
        LinearLayout layout;

        LayoutHolder() {
        }
    }

    class ShoppingCarHolder {
        CheckBox cb_shopping_cart_checkbox;
        ImageView iv_shop_car_tipsimg;
        ImageView iv_shopping_cart_img;
        TextView tv_shopping_cart_count;
        TextView tv_shopping_cart_price;
        TextView tv_shopping_cart_title;

        ShoppingCarHolder() {
        }
    }

    class ViewHolderTitle {
        CheckBox checkBox;
        LinearLayout linearLayout;
        TextView textView_title;

        ViewHolderTitle() {
        }
    }
}
