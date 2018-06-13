package com.lightheart.sphr.patient.ui.main.presenter;

import com.lightheart.sphr.patient.base.BasePresenter;
import com.lightheart.sphr.patient.model.DataResponse;
import com.lightheart.sphr.patient.model.VersionParam;
import com.lightheart.sphr.patient.ui.main.contract.MainContract;
import com.lightheart.sphr.patient.net.ApiService;
import com.lightheart.sphr.patient.net.RetrofitManager;
import com.lightheart.sphr.patient.utils.RxSchedulers;
import com.lightheart.sphr.patient.view.ProgressUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-6-7.
 * Description :
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private VersionParam param = new VersionParam();

    @Inject
    public MainPresenter() {
    }

    @Override
    public void checkAppVersion() {
        RetrofitManager.create(ApiService.class)
                .getAppInfo(param)
                .compose(RxSchedulers.<DataResponse<VersionParam>>applySchedulers())
                .compose(mView.<DataResponse<VersionParam>>bindToLife())
                .subscribe(new Consumer<DataResponse<VersionParam>>() {
                    @Override
                    public void accept(DataResponse<VersionParam> response) throws Exception {
                        ProgressUtil.dis();
                        if (response.getResultcode() == 200) {
                            mView.compareVersion(response.getContent());
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ProgressUtil.dis();
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }
}
