package com.lightheart.sphr.patient.ui.my;

import android.view.View;

import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.base.BaseFragment;

/**
 * Created by fucp on 2018-4-19.
 * Description :我的页面
 */

public class MyFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }


}
