package com.lightheart.sphr.patient.ui.main.ui;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.app.Constant;
import com.lightheart.sphr.patient.base.BaseActivity;
import com.lightheart.sphr.patient.base.BaseFragment;
import com.lightheart.sphr.patient.model.EventModel;
import com.lightheart.sphr.patient.model.VersionParam;
import com.lightheart.sphr.patient.ui.home.HomeFragment;
import com.lightheart.sphr.patient.ui.main.contract.MainContract;
import com.lightheart.sphr.patient.ui.main.presenter.MainPresenter;
import com.lightheart.sphr.patient.ui.my.MyFragment;
import com.lightheart.sphr.patient.ui.serve.ServeFragment;
import com.lightheart.sphr.patient.utils.RxBus;
import com.lightheart.sphr.patient.view.ProgressUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.lightheart.sphr.patient.app.Constant.RC_CANCEL_UPDATE;
import static com.lightheart.sphr.patient.app.Constant.RC_READ_EXTERNAL_STORAGE;
import static com.lightheart.sphr.patient.app.Constant.RC_UPDATE;
import static com.lightheart.sphr.patient.app.Constant.REQUEST_VERSION;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    private int mLastFgIndex;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private long mExitTime;
    // 外部存储权限
    private static final String[] READ_AND_WRITE_PERMISSION =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private String appUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mNavigation.setOnNavigationItemSelectedListener(this);
        mNavigation.setItemIconTintList(null);
        initFragment();
        switchFragment(0);
        RxBus.getInstance().toFlowable(EventModel.class).subscribe(new Consumer<EventModel>() {
            @Override
            public void accept(EventModel event) throws Exception {
                if (event.isLogout) finish();
            }
        });

        assert mPresenter != null;
        ProgressUtil.show(getSupportFragmentManager());
        mPresenter.checkAppVersion();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ServeFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchFragment(0);
                break;
            case R.id.navigation_serve:
                switchFragment(1);
                break;
            case R.id.navigation_my:
                switchFragment(2);
                break;
        }
        return true;
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        MenuItem home = mNavigation.getMenu().findItem(R.id.navigation_home);
        MenuItem serve = mNavigation.getMenu().findItem(R.id.navigation_serve);
        MenuItem my = mNavigation.getMenu().findItem(R.id.navigation_my);
        if (position == 0) {
            home.setIcon(R.mipmap.ic_home);
            serve.setIcon(R.mipmap.ic_serve_unselected);
            my.setIcon(R.mipmap.ic_my_unselected);
        } else if (position == 1) {
            home.setIcon(R.mipmap.ic_home_unselected);
            serve.setIcon(R.mipmap.ic_serve);
            my.setIcon(R.mipmap.ic_my_unselected);
        } else if (position == 2) {
            home.setIcon(R.mipmap.ic_home_unselected);
            serve.setIcon(R.mipmap.ic_serve_unselected);
            my.setIcon(R.mipmap.ic_my);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded())
            ft.add(R.id.layout_fragment, targetFg);
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void compareVersion(VersionParam param) {
        int ignoreVersion = SPUtils.getInstance(Constant.SHARED_NAME).getInt("ignore_version", 0);
        if (param != null) {
            if (ignoreVersion != param.getVersionCode()) {
                if (param.getVersionCode() > AppUtils.getAppVersionCode()) {
                    // 暂时不用
//                startActivityForResult(new Intent(this, UpdateApkActivity.class).putExtra("appInfo", param), REQUEST_VERSION);
                    appUrl = param.getAppUrl();
                    if (!TextUtils.isEmpty(appUrl)) {
                        // TODO 检查并更新
                        questNeedPermission();
                    }
                }
            }
        }
    }

    @AfterPermissionGranted(RC_READ_EXTERNAL_STORAGE)
    private void questNeedPermission() {
        if (hasReadStoragePermission()) {

        } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_storage),
                    RC_READ_EXTERNAL_STORAGE,
                    READ_AND_WRITE_PERMISSION);
        }
    }

    private boolean hasReadStoragePermission() {
        return EasyPermissions.hasPermissions(this, READ_AND_WRITE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // 不论获得权限还是被拒绝，一律进入倒计时，并进入主页面或者登陆页面
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort(R.string.exit_system);
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 暂时不用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_VERSION) {
            switch (resultCode) {
                case RC_UPDATE:
                    break;
                case RC_CANCEL_UPDATE:
                    break;
            }
        }
    }

}
