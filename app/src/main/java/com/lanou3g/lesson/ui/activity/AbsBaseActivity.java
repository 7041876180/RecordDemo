package com.lanou3g.lesson.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Risky on 15/10/28.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    protected static final String TAG = AbsBaseActivity.class.getSimpleName();
    protected String tag = getClass().getSimpleName();
    private ProgressDialog dialog;
    /**
     * 简化findViewById()方法
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T findView(int resId){
        T t = (T) findViewById(resId);
        return t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        setContentView(getLayout());
        ActivityControl.addAty(tag, this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("请稍后");
        dialog.setMessage("数据加载中...");
        initView();
        initData();
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {

    }


    /**
     * 返回值为所要加载的布局文件
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 显示dialog
     */
    protected void showDialog(){
        dialog.show();
    }

    /**
     * 隐藏dialog
     */
    protected void dismissDialog(){
        dialog.dismiss();
    }

    /**
     * Toast一个数据加载失败
     */
    protected void toastError() {
        Toast.makeText(this, "数据加载失败*_*!", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.removeAty(tag);
    }
}
