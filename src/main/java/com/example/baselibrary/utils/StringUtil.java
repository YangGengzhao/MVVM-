package com.example.baselibrary.utils;

import com.example.baselibrary.log.MLog;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getGapTime(long time) {//将毫秒差值转换为时分秒
        return getGapTime(time, true);
    }

    public static String getGapTime(long time, boolean isNeedSeconds) {//将毫秒差值转换为时分秒
        long hours = time / (1000 * 60 * 60);
        long minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60);
        long secound = (time - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
        String diffTime = "";
        if (hours < 10) {
            diffTime = "0" + hours;
        } else {
            diffTime = "" + hours;
        }
        if (minutes < 10) {
            diffTime = diffTime + ":0" + minutes;
        } else {
            diffTime = diffTime + ":" + minutes;
        }
        if (isNeedSeconds)
            if (secound < 10) {
                diffTime = diffTime + ":0" + secound;
            } else {
                diffTime = diffTime + ":" + secound;
            }
        return diffTime;
    }

    public static List<String> getNoticeTime(long time) {//将毫秒差值转换为时分秒
        long day = time / (1000 * 60 * 60 * 24);
        long hours = (time - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time - day * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        List<String> stringList = new ArrayList<>();
        stringList.add(day + "");
        stringList.add(hours + "");
        stringList.add(minutes + "");
        return stringList;
    }

    public static String getVideoTime(long time) {
        long mm = (time % 3600) / 60;
        long ss = (time % 3600) % 60;
        return (mm < 10 ? ("0" + mm) : mm) + ":" + (ss < 10 ? ("0" + ss) : ss);
    }

    public static String getMinTime(long time) {
        int minutes = (int) ((time / 1000) / 60);
        int seconds = (int) ((time / 1000) % 60);
        return (minutes < 10 ? ("0" + minutes) : minutes) + ":" + (seconds < 10 ? ("0" + seconds) : seconds);
    }

    public static String getMin(long time) {
        int minutes = (int) ((time / 1000) / 60);
        return (minutes < 10 ? (minutes) : minutes) + "";
    }

    public static String getDay(long time) {
        long minutes = ((time / 1000) / 60);
        long hour = minutes / 60;
        int day = (int) hour / 24;
        return (day < 10 ? (day) : day) + "";
    }

    public static String getHour(long time) {
        long minutes = ((time / 1000) / 60);
        int hour = (int) (minutes / 60);
        return (hour < 10 ? (hour) : hour) + "";
    }

    public static String formatDateStr(Long time) {
        try {
            Date date = new Date(time);
            SimpleDateFormat format;
            String hintDate = "";
            //先获取年份
            int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(date));
            //先获取月份
            int month = Integer.valueOf(new SimpleDateFormat("MM").format(date));
            //获取一年中的第几天
            int day = Integer.valueOf(new SimpleDateFormat("d").format(date));
            //获取当前年份 和 当前月份和一年中的第几天
            Date currentDate = new Date(System.currentTimeMillis());
            int currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(currentDate));
            int currentmonth = Integer.valueOf(new SimpleDateFormat("MM").format(currentDate));
            int currentDay = Integer.valueOf(new SimpleDateFormat("d").format(currentDate));
            //计算 如果是去年的
            if (currentYear - year == 1) {
                //如果当前正好是 1月1日 计算去年有多少天，指定时间是否是一年中的最后一天
                if (currentDay == 1) {
                    int yearDay;
                    if (year % 400 == 0) {
                        yearDay = 366;//世纪闰年
                    } else if (year % 4 == 0 && year % 100 != 0) {
                        yearDay = 366;//普通闰年
                    } else {
                        yearDay = 365;//平年
                    }
                    if (day == yearDay) {
                        hintDate = "昨天";
                    }
                }
            } else {
                if (currentDay - day == 1 && currentmonth == month && currentYear == year) {
                    hintDate = "昨天";
                }
                if (currentDay - day == 0 && currentmonth == month && currentYear == year) {
                    hintDate = "今天";
                }
            }
            if (StringUtil.isEmpty(hintDate)) {
                return dateFormat(time);
//                format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//                return format.format(date);
            } else {
                format = new SimpleDateFormat("HH:mm");
                return hintDate + "" + format.format(date);
            }

        } catch (Exception e) {
            return "";
        }
    }


    public static String formatDate(Date date, String format) {
        String timeStr = "";
        if (date != null && !StringUtil.isEmpty(format)) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            timeStr = sf.format(date);
        }
        return timeStr;
    }

    public static String formatDate(Long millisecond, String format) {
        if (millisecond == null) {
            return "";
        }
        Date date = new Date(millisecond);
        return formatDate(date, format);
    }

    public static String getTenThousand(int number) {
//        String tenThousand = "";
//        NumberFormat nf = NumberFormat.getNumberInstance();
//        nf.setMaximumFractionDigits(1);
//        nf.setMinimumFractionDigits(1);
//        if (number < 10000) {
//            tenThousand = number + "";
//        } else {
//            tenThousand = nf.format(number / 10000.0) + "w ";
//        }
        return getTenThousand2(number, 1, "w");
    }

    public static String getTenThousand2(int number, int digits, String unit) {
        String tenThousand = "";
        NumberFormat nf = NumberFormat.getNumberInstance();
        //保留小数点后1位
        nf.setMaximumFractionDigits(digits);//保留最大位数
        nf.setMinimumFractionDigits(digits);//保留最小位数
        if (number < 10000) {
            tenThousand = number + "";
        } else {
            tenThousand = nf.format(number / 10000.0) + unit;
        }
        return tenThousand;
    }

    //保留小数点后2位
    public static String getHundredThousand(int number) {
        String tenThousand = "";
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        if (number < 100000) {
            tenThousand = number + "";
        } else {
            tenThousand = nf.format(number / 100000.0) + "w ";
        }
        return tenThousand;
    }

    public static String getHundred(int number) {
        String hundred = "";
        if (number < 100) {
            hundred = number + "";
        } else {
            hundred = "99+";
        }
        return hundred;
    }

    public static String getThousand(int number) {
        String hundred = "";
        if (number < 1000) {
            hundred = number + "";
        } else {
            hundred = "999+";
        }
        return hundred;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }


    //将半角转为全角
    public static String ToDBC(String input) {

        char[] c = input.toCharArray();

        for (int i = 0; i < c.length; i++) {

            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }

            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);

    }

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String format(long time) {

        long delta = new Date().getTime() - time;
//        if (delta < 1L * ONE_MINUTE) {
//            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
//        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
//        if (delta < 48L * ONE_HOUR) {
//            return "昨天";
//        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


    /**
     * 计算各个值的变动
     */
    public static String startCount(long millisecond) {
        String s = "";
        long totalSeconds = millisecond / 1000;
        long day = 0;
        long hour = 0;
        long minute = 0;
        long second = 0;

        boolean dayNotAlready = false;
        boolean hourNotAlready = false;
        boolean minuteNotAlready = false;
        boolean secondNotAlready = false;

        try {
            if (totalSeconds > 0) {
                secondNotAlready = true;
                second = totalSeconds;
                if (second >= 60) {
                    minuteNotAlready = true;
                    minute = second / 60;
                    second = second % 60;
                    if (minute >= 60) {
                        hourNotAlready = true;
                        hour = minute / 60;
                        minute = minute % 60;
                        if (hour > 24) {
                            dayNotAlready = true;
                            day = hour / 24;
                            hour = hour % 24;
                        }
                    }
                }
            }
            if (secondNotAlready) {
                if (second > 0) {
                    second--;
                    if (second == 0 && !minuteNotAlready) {
                        secondNotAlready = false;
                    }
                } else {
                    if (minuteNotAlready) {
                        if (minute > 0) {
                            minute--;
                            second = 59;
                            if (minute == 0 && !hourNotAlready) {
                                minuteNotAlready = false;
                            }

                        } else {
                            if (hourNotAlready) {
                                if (hour > 0) {
                                    hour--;
                                    minute = 59;
                                    second = 59;
                                    if (hour == 0 && !dayNotAlready) {
                                        hourNotAlready = false;
                                    }

                                } else {
                                    if (dayNotAlready) {
                                        day--;
                                        hour = 23;
                                        minute = 59;
                                        second = 59;
                                        if (day == 0) {
                                            dayNotAlready = false;
                                        }

                                    }
                                }
                            }
                        }

                    }
                }

            }
            s = day + "天" + hour + "小时" + minute + "分钟";
        } catch (Exception e) {
            e.printStackTrace();
            s = "";
        }


        MLog.e("tag", "距离截止日期还有——>" + day + "天" + hour + "小时" + minute
                + "分钟" + second + "秒");

        return s;
    }

    public static String dateFormat(long time) {//判断是否在当年的时间，如果是在当年的时间格式化为MM/dd HH:mm 否则格式化为YYYY/MM/dd HH:mm
        String s = "";
        try {
            if (time != 0) {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(time);
                int year = instance.get(Calendar.YEAR);
                Calendar instance1 = Calendar.getInstance();
                int year1 = instance1.get(Calendar.YEAR);
                if (year == year1) {
                    s = formatDate(time, "MM/dd HH:mm");
                } else {
                    s = formatDate(time, "yyyy/MM/dd HH:mm");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            s = "";
        }
        return s;
    }

    public static String stringFilter(String str) throws PatternSyntaxException {

        // 只允许字母、数字和汉字
//        "[^a-zA-Z0-9\u4E00-\u9FA5]"
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();

    }

}

