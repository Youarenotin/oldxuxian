package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.xuxian.marketpro.presentation.entity.ShoppingCartGoodsEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class PublicListAdapter extends BaseAdapter{
    private ShoppingCartGoodsDb detailsDb;
    private ImageLoader imageLoad;
    private List<GoodsListEntity> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private int screenWidth;

    public PublicListAdapter(Context context) {
        this.mContext = context;
        this.detailsDb = new ShoppingCartGoodsDb(context);
        this.imageLoad = ImageLoader.getInstance();
        this.mInflater = LayoutInflater.from(context);
        this.screenWidth = AbScreenUtils.getScreenWidth(context);
    }

    public void setData(List<GoodsListEntity> data){
        this.list=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list==null && list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItemHolder holder;
        if (convertView==null){
            holder=new GridItemHolder();
            convertView = this.mInflater.inflate(R.layout.gridview_item_gridview, parent, false);
            holder.grid_item_image1 = (ImageView) convertView.findViewById(R.id.grid_item_image1);
            holder.grid_item_count1 = (TextView) convertView.findViewById(R.id.grid_item_count1);
            holder.grid_item_text1 = (TextView) convertView.findViewById(R.id.grid_item_text1);
            holder.grid_price_text1 = (TextView) convertView.findViewById(R.id.grid_price_text1);
            holder.iv_tipsimg1 = (ImageView) convertView.findViewById(R.id.iv_tipsimg1);
            holder.grid_original_price_text1 = (TextView) convertView.findViewById(R.id.grid_original_price_text1);
            holder.sold_out_icon_img1 = (ImageView) convertView.findViewById(R.id.sold_out_icon_img1);
            int w = this.screenWidth / 2;
            AbViewUtil.setViewWH(holder.grid_item_image1, w, (w / 4) * 3);
            AbViewUtil.setViewWH(holder.sold_out_icon_img1, w / 2, ((w / 16) * 9) / 2);
            int ww = w / 4;
            AbViewUtil.setViewWH(holder.iv_tipsimg1, ww, ww / 4);
            convertView.setTag(holder);
        }
        else{
            holder= (GridItemHolder) convertView.getTag();
        }
        GoodsListEntity baseListDto = this.list.get(position);
        this.imageLoad.displayImage(baseListDto.getIcon(), holder.grid_item_image1, MyApplication.getInstance().getSampleOptions(R.drawable.default_newimg));
        holder.grid_item_text1.setText(baseListDto.getTitle());
        holder.grid_price_text1.setText(this.mContext.getString(R.string.list_axe_text, new Object[]{baseListDto.getPrice()}));
        holder.grid_original_price_text1.setText(this.mContext.getString(R.string.list_axe_text_02, new Object[]{baseListDto.getMarket_price()}));
        holder.grid_original_price_text1.getPaint().setFlags(16);
        ShoppingCartGoodsEntity detailsDto = this.detailsDb.queryOneData(AbPreferenceUtils.loadPrefString(this.mContext, "USER_ID", "0"), ((GoodsListEntity) this.list.get(position)).getId());
        if (AbStrUtil.isEmpty(baseListDto.getTipsimg())) {
            holder.iv_tipsimg1.setVisibility(View.GONE);
        } else {
            holder.iv_tipsimg1.setVisibility(View.VISIBLE);
            this.imageLoad.displayImage(baseListDto.getTipsimg(), holder.iv_tipsimg1, MyApplication.getInstance().getSampleOptions(R.drawable.default_tipsimg));
        }
        if (detailsDto != null) {
            int count = detailsDto.getCount();
            if (count != 0) {
                holder.grid_item_count1.setVisibility(View.VISIBLE);
                holder.grid_item_count1.setText("" + count);
            } else {
                holder.grid_item_count1.setText("" + count);
                holder.grid_item_count1.setVisibility(View.GONE);
            }
        } else {
            holder.grid_item_count1.setText("" + 0);
            holder.grid_item_count1.setVisibility(View.GONE);
        }
        if (baseListDto.getStore_nums() - baseListDto.getSold_num() <= 0) {
            holder.sold_out_icon_img1.setVisibility(View.VISIBLE);
        } else {
            holder.sold_out_icon_img1.setVisibility(View.GONE);
        }
        convertView.setId(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                ActivityUtil.startGoodsDetailsActivity(PublicListAdapter.this.mContext, ((GoodsListEntity) PublicListAdapter.this.list.get(view.getId())).getId().intValue());
            }
        });
        return convertView;
    }

    class GridItemHolder {
        TextView grid_item_count1;
        ImageView grid_item_image1;
        TextView grid_item_text1;
        TextView grid_original_price_text1;
        TextView grid_price_text1;
        ImageView iv_tipsimg1;
        ImageView sold_out_icon_img1;
    }
}
