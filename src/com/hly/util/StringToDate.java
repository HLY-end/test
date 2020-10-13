package com.hly.util;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springmvc_day01_01_start
 * Author HLY
 * @DESC 字符串转日期的自定义类型转换器类
 * @create: 2020-08-02 22:49
 **/                                    //左边是传进来的，右边是执行代码转换后返回的
public class StringToDate implements Converter<String, Date> {

    /**
     *
     * @param s 传入的字符串
     * @return
     */
    @Override
    public Date convert(String s) {
        if(s == null){
            throw new RuntimeException("请传入一个日期参数！！！");
        }
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        try {
            return df.parse(s);
        } catch (ParseException e) {
            throw  new RuntimeException("转换时出错！！！");
        }
    }

}
