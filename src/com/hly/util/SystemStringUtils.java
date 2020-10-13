package com.hly.util;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc
 * @create: 2020-09-09 11:30
 **/
public class SystemStringUtils {

    /**
     * 判断字符串是否为空，为空返回null，不为空返回去掉空格的字符串
     * @param str
     * @return
     */
    public static String strNotNullTrim(String str){
        return str == null ? null : str.trim();
    }
}
