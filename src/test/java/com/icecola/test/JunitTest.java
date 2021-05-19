package com.icecola.test;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.junit.jupiter.api.Test;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.19 22:15
 */
public class JunitTest {

    @Test
    public void run() {
        System.out.println(123);
    }

    @Test
    public void readProps() {
        String props = "classpath:simplemq.properties";
        Props prop = Props.getProp(props);
        Set<String> strings = prop.stringPropertyNames();
        String str = prop.getStr("thread.poolName");

    }

}
