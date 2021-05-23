package com.icecola.simplemq.net.config;

import com.icecola.simplemq.utils.PropsUtils;
import lombok.Getter;

/**
 * description:
 * net配置类
 *
 * @author liuxingxing
 * @date 2021.05.22 00:03
 */
public class NetConfig {

    /**
     * net配置文件属性前缀
     */
    private final static String PREFIX = "server.";

    /**
     * 服务端口
     */
    @Getter
    private static final Integer port;

    static {
        port = PropsUtils.getInt(PREFIX + "port");
    }


}
