package com.lightheart.sphr.patient.ui.main.contract;

import com.lightheart.sphr.patient.base.BaseContract;
import com.lightheart.sphr.patient.model.VersionParam;

/**
 * Created by fucp on 2018-6-7.
 * Description :
 */

public interface MainContract {

    interface View extends BaseContract.BaseView {

        void compareVersion(VersionParam param);

    }

    interface Presenter extends BaseContract.BasePresenter<MainContract.View> {

        void checkAppVersion();

    }

}
