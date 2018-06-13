package com.lightheart.sphr.patient.ui.serve;

import android.view.View;

import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.base.BaseFragment;

/**
 * Created by fucp on 2018-6-13.
 * Description :
 */

public class ServeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_serve;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

    public static ServeFragment newInstance() {
        return new ServeFragment();
    }
}
