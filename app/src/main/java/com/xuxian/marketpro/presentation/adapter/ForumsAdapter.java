package com.xuxian.marketpro.presentation.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.util.AbScreenUtils;
import com.ab.util.AbViewUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity;
import com.xuxian.marketpro.presentation.entity.ForumsInfoEntity.DataEntity.ForumsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ForumsAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ForumsEntity> mListData;
    private DisplayImageOptions.Builder options;
    private int screenWidth;
    private UserDb userDb;

    public ForumsAdapter(Context context) {
        screenWidth= AbScreenUtils.getScreenWidth(context);
        this.mListData = null;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mInflater = LayoutInflater.from(mContext);
        this.options = new DisplayImageOptions.Builder();
        this.options.showImageOnLoading(R.drawable.default_newimg);
        this.options.showImageForEmptyUri(R.drawable.default_newimg);
        this.options.showImageOnFail(R.drawable.default_newimg);
        this.options.cacheInMemory(true);
        this.options.cacheOnDisk(true);
        this.options.imageScaleType(ImageScaleType.EXACTLY);
        this.options.bitmapConfig(Config.RGB_565);
        this.options.resetViewBeforeLoading(true);
    }

    public void setData(List<ForumsEntity> mListData) {
        if (mListData == null) {
            this.mListData = new ArrayList();
        } else {
            this.mListData = mListData;
        }
        notifyDataSetChanged();
    }

    public void setUserDb(UserDb userDb) {
        this.userDb = userDb;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    @Override
    public int getCount() {
        if (mListData==null || mListData.isEmpty())
        return 0;
        return mListData.size();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.mInflater.inflate(R.layout.item_forum_layout, null);
            holder.tv_forum_descrip = (TextView) convertView.findViewById(R.id.tv_forum_descrip);
            holder.iv_forum_icon = (ImageView) convertView.findViewById(R.id.iv_forum_icon);
            convertView.setTag(holder);
            AbViewUtil.setViewWH(holder.iv_forum_icon, this.screenWidth, this.screenWidth / 2);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ForumsEntity forums = this.mListData.get(position);
        ImageLoader.getInstance().displayImage("http://bbs.xuxian.com/attachment/" + forums.getApp_logo(), holder.iv_forum_icon, this.options.build());
        holder.tv_forum_descrip.setText(forums.getDescrip());
        convertView.setOnClickListener(new ForumsOnClickListener(forums));
        return convertView;
    }



    class ViewHolder {
        ImageView iv_forum_icon;
        TextView tv_forum_descrip;
    }

    private class ForumsOnClickListener implements View.OnClickListener {
        private ForumsEntity entity;
        public ForumsOnClickListener(ForumsEntity forums) {
            this.entity=forums;
        }
        @Override
        public void onClick(View v) {
            if (ForumsAdapter.this.userDb.queryData() != null) {
               ActivityUtil.startForumListActivity(ForumsAdapter.this.mContext, this.entity);
            } else {
                ActivityUtil.startLoginActivity(mContext);
            }
        }
    }
}
