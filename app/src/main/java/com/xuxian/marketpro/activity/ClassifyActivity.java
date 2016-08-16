package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ab.http.AbHttpUtil;
import com.ab.http.IHttpResponseCallBack;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.util.AbWifiUtil;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.net.AnimeAsyncTask;
import com.xuxian.marketpro.net.NewIssRequest;
import com.xuxian.marketpro.presentation.View.adapter.CityListAdapter;
import com.xuxian.marketpro.presentation.View.adapter.ClassifyAdapter;
import com.xuxian.marketpro.presentation.View.widght.ActivityStateView;
import com.xuxian.marketpro.presentation.entity.ClassifyEntity;
import com.xuxian.marketpro.presentation.entity.ClassifyListEntity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/14.
 */
public class ClassifyActivity extends SuperSherlockActivity implements AdapterView.OnItemClickListener {
    private ActivityStateView emptyview_state;
    private ClassifyAdapter mAdapter;
    private ListView mClassifyList;
    private List<ClassifyEntity> mDataList;
    private ProgressDialog dialog;
    private String loadingText="加载中...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_content);
        initTitleBar();
        initFindViewById();
        setListener();
        init();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {
        this.mAdapter=new ClassifyAdapter(getActivity());
        mClassifyList.setAdapter(mAdapter);
        AbHttpUtil.getInstance(getActivity()).postAndParse(
                NewIssRequest.getinfos(),
                ClassifyListEntity.class,
                new IHttpResponseCallBack<ClassifyListEntity>() {
                    @Override
                    public void EndToParse() {
                        dissMissDialog();
                    }

                    @Override
                    public void FailedParseBean(String str) {

                    }

                    @Override
                    public void StartToParse() {
                        if (!AbWifiUtil.isConnectivity(getActivity())) {
                            AbToastUtil.showToast(getActivity(), "网络没有连接!");
                        }
                        if (!AbStrUtil.isEmpty(loadingText)) {
                            showRequestDialog(getActivity(), loadingText);
                        }
                    }

                    @Override
                    public void SucceedParseBean(ClassifyListEntity classifyListEntity) {
                        mDataList=classifyListEntity.getData();
                        mAdapter.setData(classifyListEntity.getData());
                    }
                }
        );
    }

    protected void showRequestDialog(Activity mContext, String loadingText) {
        this.dialog = ProgressDialog.show(mContext, "提示", loadingText);
        this.dialog.setCanceledOnTouchOutside(false);//不可以触摸dialog外取消
        this.dialog.setCancelable(true);//可以back取消
    }

    private void dissMissDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    @Override
    protected void initTitleBar() {
        setTitle("分类");
    }

    @Override
    protected void initFindViewById() {
        mClassifyList = (ListView) findViewById(R.id.classify_list);
        mClassifyList.setAdapter(mAdapter);
        emptyview_state = (ActivityStateView) findViewById(R.id.emptyview_state);
    }

    @Override
    protected void setListener() {
        mClassifyList.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (this.mDataList != null && this.mDataList.size() != 0) {
            ActivityUtil.startClassifyDetailsActivity(getActivity(), this.mDataList.get(position).getClassifyname(), this.mDataList.get(position).getClassifyid());
        }
    }
}
