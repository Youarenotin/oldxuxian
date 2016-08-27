package com.xuxian.marketpro.presentation.View.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.easemob.chatuidemo.utils.SmileUtils;
import com.xuxian.marketpro.presentation.entity.ForumListEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by youarenotin on 16/8/27.
 */
public class ForumsListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ForumListEntity> mListData;
    private DisplayImageOptions.Builder options;

    public ForumsListAdapter(Context mContext) {
        this.mListData = null;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mInflater = LayoutInflater.from(mContext);
        this.options = new DisplayImageOptions.Builder();
        this.options.showImageOnLoading((int) R.drawable.xuxian_logo);
        this.options.showImageForEmptyUri((int) R.drawable.xuxian_logo);
        this.options.showImageOnFail((int) R.drawable.xuxian_logo);
        this.options.cacheInMemory(true);
        this.options.cacheOnDisk(true);
        this.options.imageScaleType(ImageScaleType.EXACTLY);
        this.options.bitmapConfig(Bitmap.Config.RGB_565);
        this.options.resetViewBeforeLoading(true);
        this.options.displayer(new RoundedBitmapDisplayer(90));
    }


    @Override
    public int getCount() {
        if (this.mListData == null || this.mListData.isEmpty()) {
            return 0;
        }
        return this.mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForumListEntity forumList = (ForumListEntity) this.mListData.get(position);
        if (getItemViewType(position) == 1) {
            ViewHolderTop viewHolderTop;
            if (convertView == null) {
                viewHolderTop = new ViewHolderTop();
                viewHolderTop.linearLayout = new LinearLayout(this.mContext);
                viewHolderTop.imageView = new ImageView(this.mContext);
                viewHolderTop.textView = new TextView(this.mContext);
                convertView = viewHolderTop.linearLayout;
                viewHolderTop.linearLayout.setPadding(20, 20, 20, 20);
                viewHolderTop.linearLayout.setOrientation(0);
                viewHolderTop.textView.setLeft(5);
                viewHolderTop.textView.setRight(5);
                viewHolderTop.textView.setEllipsize(TextUtils.TruncateAt.END);
                viewHolderTop.textView.setLines(1);
                viewHolderTop.textView.setSingleLine(true);
                viewHolderTop.linearLayout.addView(viewHolderTop.imageView);
                viewHolderTop.linearLayout.addView(viewHolderTop.textView);
                convertView.setTag(viewHolderTop);
            } else {
                viewHolderTop = (ViewHolderTop) convertView.getTag();
            }
            viewHolderTop.imageView.setBackgroundResource(R.drawable.zhiding_horizontal_icon);
            viewHolderTop.textView.setText(forumList.getSubject());
        } else {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(R.layout.item_forum_list_layout, null);
                holder.tv_forum_list_title = (TextView) convertView.findViewById(R.id.tv_forum_list_title);
                holder.tv_forum_list_name = (TextView) convertView.findViewById(R.id.tv_forum_list_name);
                holder.tv_forum_list_date = (TextView) convertView.findViewById(R.id.tv_forum_list_date);
                holder.tv_forum_list_hits_number = (TextView) convertView.findViewById(R.id.tv_forum_list_hits_number);
                holder.tv_forum_list_hits_number = (TextView) convertView.findViewById(R.id.tv_forum_list_hits_number);
                holder.tv_forum_list_replies_number = (TextView) convertView.findViewById(R.id.tv_forum_list_replies_number);
                holder.tv_forum_list_content = (TextView) convertView.findViewById(R.id.tv_forum_list_content);
                holder.iv_forum_list_top = (ImageView) convertView.findViewById(R.id.iv_forum_list_top);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String icon = "";
            if (forumList.getIfupload().equals("1")) {
                icon = "  [(IMAGE)]";
            } else {
                icon = "";
            }
            holder.tv_forum_list_title.setText(SmileUtils.getSmiledText(this.mContext, forumList.getSubject() + icon));
            holder.tv_forum_list_name.setText(forumList.getCreated_username());
            holder.tv_forum_list_date.setText(AbDateUtil.getStringByFormat(new Date(forumList.getLastpost_time() * 1000), AbDateUtil.dateFormatYMDHM));
            holder.tv_forum_list_hits_number.setText(forumList.getHits());
            holder.tv_forum_list_replies_number.setText(forumList.getReplies());
            holder.tv_forum_list_content.setText(forumList.getContent());
            if (forumList.getDigest().equals("1")) {
                holder.iv_forum_list_top.setVisibility(0);
                holder.iv_forum_list_top.setBackgroundResource(R.drawable.jinghua_arrow_icon);
            } else if ("topichot".equals(forumList.getIcon())) {
                holder.iv_forum_list_top.setVisibility(0);
                holder.iv_forum_list_top.setBackgroundResource(R.drawable.retie_arrow_icon);
            } else {
                holder.iv_forum_list_top.setVisibility(8);
            }
        }
        convertView.setOnClickListener(new AnonymousClass1(forumList));
        convertView.setBackgroundResource(R.drawable.light_yellow_selector);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (((ForumListEntity) this.mListData.get(position)).getType() == 1) {
                return 1;
        }
        return 0;
    }

    class ViewHolder {
        ImageView iv_forum_list_top;
        TextView tv_forum_list_content;
        TextView tv_forum_list_date;
        TextView tv_forum_list_hits_number;
        TextView tv_forum_list_name;
        TextView tv_forum_list_replies_number;
        TextView tv_forum_list_title;
    }

    class ViewHolderTop {
        ImageView imageView;
        LinearLayout linearLayout;
        TextView textView;

        ViewHolderTop() {
        }
    }

    public DisplayImageOptions.Builder getOptions() {
        return this.options;
    }

    public void setData(List<ForumListEntity> mListData) {
        if (mListData == null) {
            this.mListData = new ArrayList();
        } else {
            this.mListData = mListData;
        }
        notifyDataSetChanged();
    }

    public void addData(List<ForumListEntity> mListData) {
        this.mListData.addAll(mListData);
        notifyDataSetChanged();
    }

    private class AnonymousClass1 implements View.OnClickListener {
        public AnonymousClass1(ForumListEntity forumList) {

        }

        @Override
        public void onClick(View v) {

        }
    }
}
