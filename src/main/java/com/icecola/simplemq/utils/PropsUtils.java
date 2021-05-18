package com.icecola.simplemq.utils;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;

/**
 * description:
 * 属性读取工具类
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/18 20:28
 */
public class PropsUtils {

    private final String props = "classpath:simplemq.yml";

    private HashMap<String, Object> map = new HashMap<>();

    private void buildMap() {
        YAMLFactory.FORMAT_NAME_JSON
    }

    /**
     * Description:
     *
     * @param key:
     * @param type:
     * @return T
     * @author liuxingxing
     * @date 2021-05-18 23:41
     **/
    public <T> T getProp(String key, Class<T> type) {
        Object o = map.get(key);
        if (ObjectUtil.isNull(o)) {
            return null;
        }
        if (o.getClass() != type) {
            return null;
        }
        return (T) o;
    }

    /**
     * Description:
     *
     * @param key:
     * @return java.lang.Object
     * @author liuxingxing
     * @date 2021-05-18 23:41
     **/
    public Object getProp(String key) {
        return map.get(key);
    }


}
