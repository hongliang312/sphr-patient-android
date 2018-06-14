package com.lightheart.sphr.patient.ui.serve;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by fucp on 2018-6-13.
 * Description :
 */

public class ServeFragment extends BaseFragment {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRight;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_serve;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {
        initToolbar(mToolbar, mTitleTv, mRight, R.string.title_serve, false, 0);
    }

    public static ServeFragment newInstance() {
        return new ServeFragment();
    }
}
