package com.lightheart.sphr.patient.app;

/**
 * Created by fucp on 2018-4-10.
 * Description : 配置文件
 */

public class Constant {
    // 测试服务器
    public static final String BASE_URL = "http://172.61.1.63:8889/shdr-service-basic/";
    // 鲁有志
//    public static final String BASE_URL = "http://172.61.1.51:8889/shdr-service-basic/";
    // 宇清
//    public static final String BASE_URL = "http://172.61.1.85:8889/shdr-service-basic/";

    // 文件地址
    public static final String BASE_FILE_URL = "http://172.61.1.63:5085/shdr-file-boot/";

    // 每页数量
    public static final int PAGE_SIZE = 10;

    public static final String SHARED_NAME = "_preferences";
    public static final String MOBILE_KEY = "mobile";
    public static final String PASSWORD_KEY = "password";
    public static final String LOGIN_KEY = "login";
    public static final String USER_KEY = "user";
    public static final String IS_FIRST_LOGIN_KEY = "isFirstLogin";

    // 权限
    public static final int RC_CAMERA_PERM = 123;
    public static final int RC_READ_EXTERNAL_STORAGE = 124;
    public static final int RC_READ_AND_WRITE_AND_CAMERA = 125;

    //请求相机
    public static final int REQUEST_CAPTURE = 100;
    //请求相册
    public static final int REQUEST_PICK = 101;
    //请求截图
    public static final int REQUEST_CROP_PHOTO = 102;
    // 请求地区
    public static final int REQUEST_DISTRACT = 103;
    // 请求科室
    public static final int REQUEST_DEPARTMENT = 104;
    // 更新版本
    public static final int REQUEST_VERSION = 105;
    // 取消更新
    public static final int RC_CANCEL_UPDATE = 112;
    // 更新
    public static final int RC_UPDATE = 113;
}
