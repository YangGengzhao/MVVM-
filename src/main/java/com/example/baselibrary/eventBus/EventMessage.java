package com.example.baselibrary.eventBus;

public class EventMessage {
    public final static String TOKEN_FAILURE = "TOKEN_FAILURE";
    public final static String LOGIN = "LOGIN";
    public final static String STUDENT_DELETE = "STUDENT_DELETE";
    public final static String LIKE = "LIKE";
    public final static String NET = "NET";
    public final static String LOGIN_OUT = "LOGIN_OUT";
    public final static String USER_INFO_REFRESH = "USER_INFO_REFRESH";
    public final static String Class_Joined = "Class_Joined";

    public final static String SYSTEM_MESSAGE = "System_Message";
    public final static String POP_DISMISS = "POP_DISMISS";
    public final static String WX_CODE = "WX_CODE";//微信code

    public final static String BOTTOM_STYLE = "BOTTOM_STYLE";//data为true时白底黑字，false时为透明

    public final static String INDEX_TITLE = "INDEX_TITLE";//首页标题栏目,刷新标题样式
    public final static String IS_PAUSE = "IS_PAUSE";//课程详情暂停
    public final static String PROGRESS = "PROGRESS";//课程详情进度


    public final static String IS_SCROLL = "IS_SCROLL";//是否可以滚动

    public final static String IS_PLAY = "IS_PLAY";//播放视频

    public final static String USER_REFRESH = "USER_REFRESH";//用户主页刷新 true:整个页面刷新，false:只刷新顶部信息

    public final static String USER_HOME_CLOSE = "USER_HOME_CLOSE";//关闭用户主页

    public final static String USER_INFO = "USER_INFO";//用户信息

    public final static String USER_HOME_OPEN = "USER_HOME_OPEN";//打开用户主页

    public final static String USER_ATTENTION_REFRESH = "USER_ATTENTION_REFRESH";//刷新用户关注状态

    public final static String LIVE_REFRESH = "LIVE_REFRESH";

    public final static String REFRESH = "REFRESH";
    public final static String LOADMORE = "LOADMORE";


    public final static String CHANGE_TYPE = "CHANGE_TYPE";///**CHANGE_TYPE:之前用于SetUpCourseFragment刷新顶部内容*/

    public final static String DLSuccess = "DLSuccess";

    public final static String DLFail = "DLFail";

    public final static String DLProgress = "DLProgress";

    /**
     * 0无网络 1移动网络 2wifi
     */
    public final static String NetWorkType = "NetWorkType";


    public final static String REMOVE_VIDEO = "REMOVE_VIDEO";

    public final static String REFRESH_TASK_BY_ID = "REFRESH_TASK_BY_ID";

    public final static String FINISH_TEAM_DETAIL_ACTIVITY = "FINISH_TEAM_DETAIL_ACTIVITY";

    public final static String REFRESH_USER_BASIC_INFO = "REFRESH_USER_BASIC_INFO";

    public final static String VIDEO_CLOSE = "VIDEO_CLOSE";

    public final static String KEY_WORD = "KEY_WORD";

    public final static String FULL_SCREEN = "FULL_SCREEN";

    public final static String CLOSE_FULL_SCREEN = "CLOSE_FULL_SCREEN";

    public String TAG;
    public Object data;

    public EventMessage(String tag, Object data) {
        this.TAG = tag;
        this.data = data;
    }


}
