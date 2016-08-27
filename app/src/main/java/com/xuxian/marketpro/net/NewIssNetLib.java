package com.xuxian.marketpro.net;

import android.content.Context;

import com.xuxian.marketpro.net.httpclient.HttpRequestException;

/**
 * Created by youarenotin on 16/8/27.
 */
public class NewIssNetLib {
    private static NewIssNetLib mLib;
    protected Context mContext;
    protected NewIssRequest mRequest;

    public NewIssNetLib(Context context) {
        this.mRequest = NewIssRequest.getInstance(context);
        this.mContext = context;
    }

    public synchronized static NewIssNetLib getInstance(Context context) {
        synchronized (NewIssNetLib.class) {
            if (mLib == null) {
                mLib = new NewIssNetLib(context);
            }
            return mLib;
        }
    }

    public String getForumList(String fid, int page, String token) throws HttpRequestException {
        mRequest.newParams();
        mRequest.putParams("fid", fid);
        mRequest.putParams("page", page + "");
        mRequest.putParams(NewIssRequest.TOKEN, token);
        return mRequest.get(NewIssRequest.FORUM_LIST);
    }

}
