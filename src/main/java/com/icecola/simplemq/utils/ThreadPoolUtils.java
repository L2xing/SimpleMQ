package com.icecola.simplemq.utils;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import lombok.Data;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.17 22:44
 */
public class ThreadPoolUtils {

    /**
     * 常驻线程数量
     */
    private final static Integer corePoolSize;

    /**
     * 最大线程数
     */
    private final static Integer maximumPoolSize;

    /**
     * 保持活跃时间
     */
    private final static Integer keepAliveTime;

    /**
     * 阻塞队列长度
     */
    private final static Integer blockQueueSize;

    /**
     * 线程池
     */
    private volatile static ThreadPoolExecutor threadPool;

    static {
        Props props = PropsUtil.get("classpath:simplemq.yml");
        corePoolSize = props.getInt("corePoolSize");
        maximumPoolSize = props.getInt("maximumPoolSize");
        keepAliveTime = props.getInt("keepAliveTime");
        blockQueueSize = props.getInt("blockQueueSize");
    }

    public ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            synchronized (ThreadPoolUtils.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(
                            corePoolSize,
                            maximumPoolSize,
                            keepAliveTime,
                            TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue<>(blockQueueSize),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return threadPool;
    }

}
