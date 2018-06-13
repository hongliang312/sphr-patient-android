package com.lightheart.sphr.patient.ui.home;

import android.view.View;

import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.base.BaseFragment;

/**
 * Created by fucp on 2018-4-19.
 * Description :首页
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}
