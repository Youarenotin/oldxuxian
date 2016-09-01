package com.xuxian.marketpro.net;

import android.content.Context;

import com.ab.util.AbMd5;
import com.xuxian.marketpro.net.entity.ResultDataEntity;
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


    public ResultDataEntity<Object> modifyPwdOrUname(String userId, String type, String oldPwdOrUname, String newPwdOrUname, long dateTime) {
        this.mRequest.newParams();
        this.mRequest.putParams("userId", userId);
        this.mRequest.putParams("type", type);
        if (IceUdpTransportPacketExtension.PWD_ATTR_NAME.equals(type)) {
            this.mRequest.putParams("oldPwdOrUname", AbMd5.MD5(oldPwdOrUname));
        } else {
            this.mRequest.putParams("oldPwdOrUname", oldPwdOrUname);
        }
        this.mRequest.putParams("newPwdOrUname", newPwdOrUname);
        this.mRequest.putParams("dateTime", dateTime + CoinPacketExtension.NAMESPACE);
        this.mRequest.putParams(NewIssRequest.TOKEN, urlEncode(this.mRequest.getParameterList()));
        String json = this.mRequest.post(NewIssRequest.MODIFYPWDORUNAME);
        ResultDataEntity<Object> resultData = new ResultDataEntity();
        JSONObject object = JSON.parseObject(json);
        if (object.containsKey(InviteMessgeDao.COLUMN_NAME_STATUS)) {
            resultData.setStatus((StatusEntity) JSON.parseObject(object.get(InviteMessgeDao.COLUMN_NAME_STATUS).toString(), StatusEntity.class));
        }
        return resultData;
    }
}
