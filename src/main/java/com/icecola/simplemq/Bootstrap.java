package com.icecola.simplemq;

import cn.hutool.core.util.RandomUtil;
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
//        ThreadPoolExecutor executor = new ThreadPoolExecutor();
        QueueMap map = new QueueMap();
        for (int i = 0; i < 10; i++) {
            String s = RandomUtil.randomString(6);
            map.produce(new Message(String.valueOf(RandomUtil.randomInt(0, 100))), s);
        }
        List<String> topics = map.getTopics();
        System.out.println(topics);
        Message consume = map.consume(topics.get(0));
        System.out.println(consume);
        Message consume1 = map.consume(topics.get(0));
        System.out.println(consume1);
    }


}
