package com.example.baselibrary.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.baselibrary.R;
import com.example.baselibrary.base.BaseViewModel;
import com.example.baselibrary.base.dialog.TipsDialog;
import com.example.baselibrary.utils.StringUtil;
import com.example.baselibrary.utils.ToastUtil;
import com.example.baselibrary.base.dialog.LoadingDialog;

public abstract class BaseViewModelActivity<VM extends BaseViewModel, VB extends ViewBinding> extends BaseActivity {


    public VB banding;
    public VM viewModel;
    public LoadingDialog loadingDialog;
    public TipsDialog tipsDialog;
    private Intent intent;
    private TextView tvTitle;
    private ImageView imgBack;

    public abstract VB createViewBanding();//设置ViewBanding

    public abstract VM createViewModel();//设置ViewModel

    public abstract void initData();//设置数据

    public abstract void initListener();//监听事件

    public void setStatusBar() {//设置状态栏
        setTransStatusBar(true);
    }

    public void startActivity(Context context, Class<?> cls) {
        if (intent == null) intent = new Intent(context, cls);
        context.startActivity(intent);
    }


    public void setTitleText(String title) {
        View titleView = banding.getRoot().findViewById(R.id.toolbar_layout);
        if (titleView == null) return;
        tvTitle = banding.getRoot().findViewById(R.id.txt_title);
        imgBack = banding.getRoot().findViewById(R.id.btn_title_back);
        if (imgBack != null)
            imgBack.setOnClickListener(v -> {
                onBackPressed();
            });
        if (tvTitle != null)
            tvTitle.setText(title);
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        banding = createViewBanding();//设置ViewBanding
        setContentView(banding.getRoot());//设置布局
        viewModel = (VM) new ViewModelProvider(this).get(createViewModel().getClass());//设置ViewModel
        getLifecycle().addObserver(viewModel);//将生命周期方法延申给viewModel（添加观察者）
        setTitleText("");
        initData();//初始化数据
        initListener();

        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.MyDialogStyle);
        }
        if (tipsDialog == null) {
            tipsDialog = new TipsDialog(this);
        }
        //toast 弹出
        viewModel.toast.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ToastUtil.show(s);
            }
        });
        //loading弹窗弹出
        viewModel.showLoading.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loadingDialog.show(s);
            }
        });
        //loading弹窗关闭
        viewModel.closeLoading.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(viewModel);//移除观察者
    }


    /**
     * [防止快速点击]
     *
     * @return
     */
    private long lastClick = 0;

    public boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return true;
        }
        lastClick = System.currentTimeMillis();
        return false;
    }

    public void showLoading(boolean show) {
        showLoading(show, "加载中....");
    }

    public void showLoading(boolean show, String msg) {
        if (loadingDialog == null) loadingDialog = new LoadingDialog(this, R.style.MyDialogStyle);
        if (loadingDialog != null) {
            if (show) {
                if (StringUtil.isEmpty(msg)) msg = "加载中...";
                loadingDialog.show("" + msg);
            } else {
                loadingDialog.dismiss();
            }
        }
    }

}
