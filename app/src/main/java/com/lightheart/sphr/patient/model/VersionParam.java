package com.lightheart.sphr.patient.model;

import java.io.Serializable;

/**
 * Created by fucp on 2018-6-7.
 * Description :
 */

public class VersionParam implements Serializable {

    /**
     * appType : PT
     * osType : ADR
     */

    public String appType = "PT";
    public String osType = "ADR";


    /**
     * id : 85
     * appVersion : 3.8.0
     * versionCode : 1701121919
     * appNotes : 阳光商城移至阳光欣晴服务号
     * appUrl : https://www.lightheart.com.cn/app/com.lightheart.dima.patient.3.8.0.apk
     * createTime : 1503482262000
     * isForcedUpdate : N
     * isPassed : N
     */

    private int id;
    private String appVersion;
    private int versionCode;
    private String appNotes;
    private String appUrl;
    private long createTime;
    private String isForcedUpdate;
    private String isPassed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getAppNotes() {
        return appNotes;
    }

    public void setAppNotes(String appNotes) {
        this.appNotes = appNotes;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getIsForcedUpdate() {
        return isForcedUpdate;
    }

    public void setIsForcedUpdate(String isForcedUpdate) {
        this.isForcedUpdate = isForcedUpdate;
    }

    public String getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(String isPassed) {
        this.isPassed = isPassed;
    }
}
