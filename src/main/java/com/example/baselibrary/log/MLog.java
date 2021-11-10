package com.example.baselibrary.log;

public class MLog {
    //不同日志等级
    public static void v(String tagString , String explainString ,Throwable throwable, boolean isUpLoad){
        LogPrint.println(LogConfig.VERBOSE, tagString, explainString,throwable,isUpLoad);
    }

    public static void v(String tagString , String explainString){
        LogPrint.println(LogConfig.VERBOSE, tagString, explainString,false);
    }

    public static void v(String tagString , String explainString , boolean isUpLoad){
        LogPrint.println(LogConfig.VERBOSE, tagString, explainString,isUpLoad);
    }

    public static void v(String tagString , String explainString,Throwable throwable){
        LogPrint.println(LogConfig.VERBOSE, tagString, explainString,throwable,false);
    }
    public static void e(String tagString , String explainString ,Throwable throwable, boolean isUpLoad){
        LogPrint.println(LogConfig.ERROR, tagString, explainString,throwable,isUpLoad);
    }

    public static void e(String tagString , String explainString){
        LogPrint.println(LogConfig.ERROR, tagString, explainString,false);
    }

    public static void e(String tagString , String explainString , boolean isUpLoad){
        LogPrint.println(LogConfig.ERROR, tagString, explainString,isUpLoad);
    }

    public static void e(String tagString , String explainString,Throwable throwable){
        LogPrint.println(LogConfig.ERROR, tagString, explainString,throwable,false);
    }


    public static void d(String tagString , String explainString ,Throwable throwable, boolean isUpLoad){
        LogPrint.println(LogConfig.DEBUG, tagString, explainString,throwable,isUpLoad);
    }

    public static void d(String tagString , String explainString){
        LogPrint.println(LogConfig.DEBUG, tagString, explainString,false);
    }

    public static void d(String tagString , String explainString , boolean isUpLoad){
        LogPrint.println(LogConfig.DEBUG, tagString, explainString,isUpLoad);
    }

    public static void d(String tagString , String explainString,Throwable throwable){
        LogPrint.println(LogConfig.DEBUG, tagString, explainString,throwable,false);
    }
    public static void i(String tagString , String explainString ,Throwable throwable, boolean isUpLoad){
        LogPrint.println(LogConfig.DEBUG, tagString, explainString,throwable,isUpLoad);
    }

    public static void i(String tagString , String explainString){
        LogPrint.println(LogConfig.INFO, tagString, explainString,false);
    }

    public static void i(String tagString , String explainString , boolean isUpLoad){
        LogPrint.println(LogConfig.INFO, tagString, explainString,isUpLoad);
    }

    public static void i(String tagString , String explainString,Throwable throwable){
        LogPrint.println(LogConfig.INFO, tagString, explainString,throwable,false);
    }
    public static void w(String tagString , String explainString ,Throwable throwable, boolean isUpLoad){
        LogPrint.println(LogConfig.WARN, tagString, explainString,throwable,isUpLoad);
    }

    public static void w(String tagString , String explainString){
        LogPrint.println(LogConfig.WARN, tagString, explainString,false);
    }

    public static void w(String tagString , String explainString , boolean isUpLoad){
        LogPrint.println(LogConfig.WARN, tagString, explainString,isUpLoad);
    }

    public static void w(String tagString , String explainString,Throwable throwable){
        LogPrint.println(LogConfig.WARN, tagString, explainString,throwable,false);
    }
}
