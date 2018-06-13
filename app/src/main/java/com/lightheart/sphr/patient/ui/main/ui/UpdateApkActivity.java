package com.lightheart.sphr.patient.ui.main.ui;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightheart.sphr.patient.R;
import com.lightheart.sphr.patient.base.BaseActivity;
import com.lightheart.sphr.patient.model.VersionParam;
import com.lightheart.sphr.patient.view.DownloadProgressButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.lightheart.sphr.patient.app.Constant.RC_CANCEL_UPDATE;
import static com.lightheart.sphr.patient.app.Constant.RC_READ_EXTERNAL_STORAGE;
import static com.lightheart.sphr.patient.app.Constant.RC_UPDATE;

/**
 * Created by hezc on 2017-1-6.
 * 版本更新对话框
 */
public class UpdateApkActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ivCancel)
    ImageView ivCancel;
    @BindView(R.id.tvVersionCode)
    TextView tvVersionCode;
    @BindView(R.id.tvDetail)
    TextView tvDetail;
    @BindView(R.id.dpbUpdate)
    DownloadProgressButton dpbUpdate;

    // 外部存储权限
    private static final String[] READ_AND_WRITE_PERMISSION =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog_update_apk;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        VersionParam mAppInfo = (VersionParam) getIntent().getSerializableExtra("appInfo");

        if (mAppInfo != null) {
            tvVersionCode.setText(TextUtils.isEmpty(mAppInfo.getAppVersion()) ? "" : " V " + mAppInfo.getAppVersion());
            String s = mAppInfo.getAppNotes().replaceAll(";", "\n");
            tvDetail.setText(TextUtils.isEmpty(s) ? "" : s);
            if (TextUtils.equals(mAppInfo.getIsForcedUpdate(), "Y"))
                ivCancel.setVisibility(View.GONE);
            else ivCancel.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.ivCancel, R.id.dpbUpdate})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCancel:
                setResult(RC_CANCEL_UPDATE, getIntent());
                finish();
                break;
            case R.id.dpbUpdate:
                questNeedPermission();
                break;
        }
    }

    @AfterPermissionGranted(RC_READ_EXTERNAL_STORAGE)
    private void questNeedPermission() {
        if (hasReadStoragePermission()) {
            // Have permission, do the thing!
            setResult(RC_UPDATE, new Intent());
            finish();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_camera),
                    RC_READ_EXTERNAL_STORAGE,
                    READ_AND_WRITE_PERMISSION);
        }
    }

    private boolean hasReadStoragePermission() {
        return EasyPermissions.hasPermissions(this, READ_AND_WRITE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @android.support.annotation.NonNull String[] permissions,
                                           @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @android.support.annotation.NonNull List<String> perms) {
        // 不论获得权限还是被拒绝，一律进入倒计时，并进入主页面或者登陆页面
        Intent intent = getIntent();
        setResult(RC_UPDATE, intent);
        finish();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @android.support.annotation.NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RC_CANCEL_UPDATE, getIntent());
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
