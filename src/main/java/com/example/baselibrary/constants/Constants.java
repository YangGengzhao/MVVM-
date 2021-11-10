package com.example.baselibrary.constants;


import java.util.List;

public class Constants {


    public static final String KEY_ACTION = "action";

    public static final String KEY_NUM = "num";

    public static final String KEY_TOKEN = "token";

    public static final int ACTION_LOGIN = 0;

    public static final int ACTION_REGISTER = 1;

    public static final int ACTION_PAY_SUCCESS = 2;

    public static final int ACTION_VERIFY_SUCCESS = 3;


    public static final int ACTION_REGISTER_SUCCESS = 4;

    public static final int ACTION_LOGIN_SUCCESS = 5;

    public static final int ACTION_LOGIN_FAILED = 6;

    public static final int ACTION_NATIVE_VERIFY_SUCCESS = 7;

    public static final int ACTION_VERIFY_FAILED = 8;


    public static final int ACTION_THIRD_AUTHORIZED_SUCCESS = 9;

    public static final int ACTION_THIRD_AUTHORIZED_FAILED = 10;

    public static String verifyUrl = "此处填写您自己服务端实现的获取手机号码API接口地址";

    public static String consistUrl = "此处填写您自己服务端实现的手机号验证API接口地址";

    //-----------------------------------------认证sdk错误码-----------------------------------------

    public static final int VERIFY_CONSISTENT = 9000;//手机号验证一致
    public static final int FETCH_TOKEN_SUCCESS = 2000;//获取token成功
    public static final int CODE_LOGIN_SUCCESS = 6000;
    public static final int CODE_LOGIN_FAILED = 6001;
    public static final int CODE_LOGIN_CANCELD = 6002;

    public static final String KEY_ERORRO_MSG = "error_msg";
    public static final String KEY_ERROR_CODE = "error_code";


    public static String ACTIVITY_TITLE = null;
    public static List<String> ACTIVITY_TOPIC_NAME = null;
    public static int ACTIVITY_ID = -1;
    public static Integer ACTIVITY_GROUP_ID = null;
    public static int IS_ACTIVITY_VIEDO = 0;//是否来自活动  1是 2来自话题创建
    public static int CurrentPostion = 2;//是在推荐页面

    //-----------
    public static final int HTTP_TIME_OUT = 15 * 1000;

    //前后两次的间隔,默认30秒
    public static long INTERVAL_TIME = 1000 * 30;

    //以下是本地错误码
    public static final int NET_ERROR_CODE = 2998;//网络错误
    public static final int NET_TIMEOUT_CODE = 3001;//网络超时
    public static final int NET_UNKNOW_HOST = 3003;//域名无效
    public static final int NET_MALTFORMED_ERROR = 3004;//Malformed异常


    public static int USER_AGREEMENT = 1;

    public static int USER_ID;

    public static boolean PAUSE = false;//是否暂停播放

    public static boolean LOGIN = false;//登录

    public static int POSITION = 1;//1是推荐 2是关注 3我校

    public static int CURRENT_MODULE = 0;

    public static int HAVE_ATTENTION = 0;//0没有关注的人 1有关注的人

    public static boolean IS_DOWNLOAD = false;

}
