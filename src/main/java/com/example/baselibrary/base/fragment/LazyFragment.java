package com.example.baselibrary.base.fragment;

public abstract class LazyFragment extends BaseFragment {

    protected abstract void lazy();

    public void stopPlay() {

    }
    private boolean mLoaded;

    @Override
    public void onResume() {
        super.onResume();
        if (mLoaded) return;
        if (!mLoaded) mLoaded = true;
        lazy();
    }

}
