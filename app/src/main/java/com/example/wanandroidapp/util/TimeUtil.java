package com.example.wanandroidapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间类
 *
 * @author: ODM
 * @date: 2019/7/17
 */
public class TimeUtil {


    /**
     * 展示当前系统时间
     *
     * @param currentTime the current time:System.currentTimeMillis()
     * @return the string
     */
    public static String showCurrentTime (long currentTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(currentTime);
        String time = simpleDateFormat.format(date);
        return time;
    }


}
