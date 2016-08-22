package com.xuxian.marketpro.presentation.View.webview;

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by youarenotin on 16/8/22.
 */
public class ProgressWebView extends WebView{
    private ProgressBar progressBar;

    public ProgressWebView(Context context) {
        super(context);
        this.progressBar=new ProgressBar(context,null);
        progressBar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,30,0,0));
        addView(progressBar);
        setWebChromeClient(new webChromeClient());
    }

    public class webChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress==100){
                view.setVisibility(GONE);
            }
            else{
                if (view.getVisibility()==GONE)
                    view.setVisibility(VISIBLE);
                progressBar.setProgress(newProgress);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams layoutParams = (LayoutParams) progressBar.getLayoutParams();
        layoutParams.x=l;
        layoutParams.y=t;
        progressBar.setLayoutParams(layoutParams);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
