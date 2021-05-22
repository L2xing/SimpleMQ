package com.icecola.simplemq.utils;

import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

import java.util.concurrent.*;

/**
 * description:
 * 线程池工具类
 *
 * @author liuxingxing
 * @date 2021.05.17 22:44
 */
public class ThreadPoolUtils {

    /**
     * 线程池配置文件属性前缀
     */
    private final static String PREFIX = "thread.";

    /**
     * 常驻线程数量
     */
    private final static Integer CORE_POOL_SIZE;

    /**
     * 最大线程数
     */
    private final static Integer MAXIMUM_POOL_SIZE;

    /**
     * 保持活跃时间
     */
    private final static Integer KEEP_ALIVE_TIME;

    /**
     * 阻塞队列长度
     */
    private final static Integer BLOCK_QUEUE_SIZE;

    /**
     * 线程工厂名称
     */
    private final static String POOL_NAME;

    /**
     * 线程池
     */
    private volatile static ThreadPoolExecutor threadPool;

    static {
        CORE_POOL_SIZE = PropsUtils.getInt(PREFIX + "corePoolSize");
        MAXIMUM_POOL_SIZE = PropsUtils.getInt(PREFIX + "maximumPoolSize");
        KEEP_ALIVE_TIME = PropsUtils.getInt(PREFIX + "keepAliveTime");
        BLOCK_QUEUE_SIZE = PropsUtils.getInt(PREFIX + "blockQueueSize");
        POOL_NAME = PropsUtils.getStr(PREFIX + "poolName");
    }

    /**
     * Description:
     * 获取线程池
     *
     * @return java.util.concurrent.ThreadPoolExecutor
     * @author liuxingxing
     * @date 2021-05-19 22:50
     **/
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            synchronized (ThreadPoolUtils.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(
                            CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue<>(BLOCK_QUEUE_SIZE),
                            new NamedThreadFactory(POOL_NAME, false),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return threadPool;
    }

    /**
     * Description:
     * 异步提交
     *
     * @param r: 任务
     * @return void
     * @author liuxingxing
     * @date 2021-05-19 23:04
     **/
    public static void execute(Runnable r) {
        getThreadPool().execute(r);
    }

    /**
     * Description:
     * 同步提交
     *
     * @param callable: 任务
     * @return T 返回值
     * @author liuxingxing
     * @date 2021-05-19 23:04
     **/
    public static <T> T syncExecute(Callable<T> callable) {
        try {
            return getThreadPool().submit(callable).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
