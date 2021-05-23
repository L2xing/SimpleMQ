package com.icecola.simplemq;

import cn.hutool.core.util.RandomUtil;
import com.icecola.simplemq.net.Server;
import com.icecola.simplemq.queue.ArrayMessageQueue;
import com.icecola.simplemq.queue.Message;
import com.icecola.simplemq.queue.QueueMap;
import com.icecola.simplemq.utils.ThreadPoolUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 * 简单的MQ
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/17 20:14
 */
public class Bootstrap {

    public static void main(String[] args) {
        // 一个服务线程
        Server.start();
    }


}
