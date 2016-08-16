package com.xuxian.marketpro.presentation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.xuxian.marketpro.presentation.View.adapter.SectionedBaseAdapter;
import com.xuxian.marketpro.presentation.View.listview.ShoppingCarListView;
import com.xuxian.marketpro.presentation.View.widght.pop.OperationPopupWindow;
import com.xuxian.marketpro.presentation.db.ShoppingCartGoodsDb;
import com.xuxian.marketpro.presentation.entity.NewCartGoodsEntity;
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;

import java.util.List;
import java.util.Map;

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
    public int getCountForSection(int i) {
        return 0;
    }

    @Override
    public Object getItem(int section, int postion) {
        return null;
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
        return 0;
    }

    /**
     * section view
     * @param section
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getSectionHeaderView(int section, View view, ViewGroup viewGroup) {
        return null;
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
    public View getItemView(int section, int postion, View view, ViewGroup viewGroup) {
        return null;
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
