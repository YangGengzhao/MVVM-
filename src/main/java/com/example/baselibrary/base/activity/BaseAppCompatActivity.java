
package com.example.baselibrary.base.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.base.dialog.CommonDialog;
import com.example.baselibrary.base.dialog.LoadingDialog;
import com.example.baselibrary.base.dialog.TipsDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import com.example.baselibrary.eventBus.EventMessage;


import com.example.baselibrary.log.MLog;

//后期弃用
public class BaseAppCompatActivity extends BaseActivity {

    protected TipsDialog tipsDialog;
    protected CommonDialog commonDialog;
    protected LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipsDialog = new TipsDialog(this);
        commonDialog = new CommonDialog(this);
        loadingDialog = new LoadingDialog(this, R.style.MyDialogStyle);
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

    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(layoutResID, "");
    }

    public void setContentView(@LayoutRes int layoutResID, String title) {
        getDelegate().setContentView(layoutResID);
        setTitleText(title);
    }

    public void setTitleText(String title) {
        View titleView = findViewById(R.id.toolbar_layout);
        if (titleView == null) return;
//        if (StringUtil.isEmpty(title)) return;
        tvTitle = (TextView) findViewById(R.id.txt_title);
        imgBack = (ImageView) findViewById(R.id.btn_title_back);
        if (imgBack != null)
            imgBack.setOnClickListener(v -> {
                onBackPressed();
            });
        if (tvTitle != null)
            tvTitle.setText(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tipsDialog != null) {
            tipsDialog.dismiss();
        }
    }

    public void showLoadingDialog(String msg) {
        if (loadingDialog.isShowing()) return;
        loadingDialog.show(msg);
    }

    public void dismissLoadingDialog() {
        if (!loadingDialog.isShowing()) return;
        loadingDialog.dismiss();
    }

    private TextView tvTitle;
    private ImageView imgBack;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessage(EventMessage message) {
        if (EventMessage.TOKEN_FAILURE.equals(message.TAG)) {
            MLog.e("BaseAppCompatActivity", "TOKEN_FAILURE");
            loginOut();
        }
    }

    public void loginOut() {
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {

    }
}
