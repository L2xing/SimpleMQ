package com.icecola.simplemq.utils;

import cn.hutool.core.getter.BasicTypeGetter;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * description:
 * 属性读取工具类
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/18 20:28
 */
public class PropsUtils {

    private static String propsDir = "classpath:simplemq.properties";

    private volatile static Props props = null;

    /**
     * Description:
     * 属性文件工具类
     *
     * @return cn.hutool.setting.dialect.Props
     * @author liuxingxing
     * @date 2021-05-19 23:12
     **/
    private static Props getPropsUtil() {
        if (props == null) {
            synchronized (PropsUtils.class) {
                if (props == null) {
                    props = PropsUtil.get(propsDir);
                }
            }
        }
        return props;
    }

    public static Object getObj(String key) {
        return getPropsUtil().getObj(key);
    }

    public static String getStr(String key) {
        return getPropsUtil().getStr(key);
    }

    public static Integer getInt(String key) {
        return getPropsUtil().getInt(key);
    }

    public static Short getShort(String key) {
        return getPropsUtil().getShort(key);
    }

    public static Boolean getBool(String key) {
        return getPropsUtil().getBool(key);
    }

    public static Long getLong(String key) {
        return getPropsUtil().getLong(key);
    }

    public static Character getChar(String key) {
        return getPropsUtil().getChar(key);
    }

    public static Float getFloat(String key) {
        return getPropsUtil().getFloat(key);
    }

    public static Double getDouble(String key) {
        return getPropsUtil().getDouble(key);
    }

    public static Byte getByte(String key) {
        return getPropsUtil().getByte(key);
    }

    public static BigDecimal getBigDecimal(String key) {
        return getPropsUtil().getBigDecimal(key);
    }

    public static BigInteger getBigInteger(String key) {
        return getPropsUtil().getBigInteger(key);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
        return getPropsUtil().getEnum(clazz, key);
    }

    public static Date getDate(String key) {
        return getPropsUtil().getDate(key);
    }
}
