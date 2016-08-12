package com.ab.view.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by youarenotin on 16/8/11.
 */
public abstract class AbBaseHolder<T> {
    protected Context context;
    protected View convertView;
    protected T data;
    protected ViewGroup parent;
    protected int position;
    private View view;

    public AbBaseHolder(Context context) {
        this.context = context;
        this.view=initView();
        this.view.setTag(this);
    }

    protected abstract View initView();

    protected abstract void refreshView();

    public void setData(T data){
        this.data=data;
        refreshView();
    }

    public T getData(){
        return this.data;
    }

    public View getConvertView() {

        return view;
    }

    public void setPostion(int position) {
        this.position=position;
    }

    public void setView(View convertView) {
        this.view=convertView;
    }

    public void setViewGroup(ViewGroup parent) {
        this.parent=parent;
    }
}
