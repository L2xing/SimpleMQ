package com.icecola.simplemq.queue;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 * 消息队列容器
 *
 * @author liuxingxing
 * @date 2021.05.21 23:25
 */
@Slf4j
public class QueueMap {

    private final Map<String, ArrayMessageQueue> map = new HashMap<>();

    private List<String> topics = new ArrayList<>();

    /**
     * Description:
     * 生产
     *
     * @param message: 消息
     * @param topic:   生产主题
     * @return void
     * @author liuxingxing
     * @date 2021-05-21 23:34
     **/
    public synchronized void produce(Message message, String topic) {
        ArrayMessageQueue arrayMessageQueue = map.get(topic);
        if (ObjectUtil.isNotNull(message)) {
            if (ObjectUtil.isNotNull(arrayMessageQueue)) {
                arrayMessageQueue.enqueue(message);
            } else {
                arrayMessageQueue = new ArrayMessageQueue(topic);
                arrayMessageQueue.enqueue(message);
                map.put(topic, arrayMessageQueue);
                topics.add(topic);
            }
        } else {
            log.error("【入队失败， 消息为空】：{}", message);
        }

    }

    /**
     * Description:
     * 消费
     *
     * @param topic: 消费主题
     * @return com.icecola.simplemq.queue.Message
     * @author liuxingxing
     * @date 2021-05-21 23:34
     **/
    public synchronized Message consume(String topic) {
        ArrayMessageQueue arrayMessageQueue = map.get(topic);
        if (ObjectUtil.isNotNull(arrayMessageQueue) &&
                arrayMessageQueue.getQueueSize() > 0) {
            return arrayMessageQueue.dequeue();
        } else {
            log.error("[出队失败，topic中无消息]：{}", topic);
            return null;
        }
    }

    /**
     * Description:
     * 获取topics
     *
     * @return java.lang.String[]
     * @author liuxingxing
     * @date 2021-05-21 23:51
     **/
    public synchronized List<String> getTopics() {
        return topics;
    }

    /**
     * Description:
     * 获取topic下的消息数量
     * @param topic
     * @return java.lang.Integer
     * @author liuxingxing23@jd.com
     * @date 2021/5/23 19:41
     **/
    public synchronized Integer getMessageInTopic(String topic) {
        ArrayMessageQueue arrayMessageQueue = map.get(topic);
        if (ObjectUtil.isNotNull(arrayMessageQueue)) {
            return arrayMessageQueue.getQueueSize();
        } else {
            log.error("【QueueMap 获取队列失败】：topic:{} 不存在。", topic);
            return 0;
        }
    }
}
