package com.icecola.simplemq.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description:
 * 抽象数组队列
 *
 * @author liuxingxing
 * @date 2021.05.20 23:04
 */
@Slf4j
public abstract class AbstractArrayMessageQueue extends AbstractBasicMessageQueue {

    /**
     * 队列名称
     */
    private final String topic;

    public AbstractArrayMessageQueue(String topic) {
        this.topic = topic;
    }

    /**
     * 消息容器
     */
    private ArrayList<Message> messages = new ArrayList<>();

    /**
     * 队列大小
     */
    private int size = 0;

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private final Lock readLock = readWriteLock.readLock();

    /**
     * 写锁
     */
    private final Lock writeLock = readWriteLock.writeLock();


    @Override
    public final String getTopic() {
        return topic;
    }

    @Override
    public final void enqueue(Message msg) {
        writeLock.lock();
        try {
            beforeEnQueue(msg);
            messages.add(msg);
            size++;
            afterDeQueue(msg);
        } catch (Exception e) {
            log.error("【消息队列 出队错误】：", e);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public final Message dequeue() {
        Message message = null;
        writeLock.lock();
        try {
            if (size > 0) {
                beforeDeQueue(message);
                message = messages.remove(0);
                afterDeQueue(message);
                size--;
            }
        } catch (Exception e) {
            log.error("【消息队列 出队错误】：", e);
        } finally {
            writeLock.unlock();
        }
        return message;
    }

    /**
     * Description:
     * 获取队列长度
     *
     * @return int
     * @author liuxingxing
     * @date 2021-05-20 23:41
     **/
    public final int getQueueSize() {
        int num = 0;
        readLock.lock();
        try {
            num = size;
        } catch (Exception e) {
            log.error("【消息队列 获取队列大小错误】：", e);
        } finally {
            readLock.unlock();
        }
        return num;
    }

    @Override
    protected void beforeEnQueue(Message msg) {

    }

    @Override
    protected void afterEnQueue(Message msg) {

    }

    @Override
    protected void beforeDeQueue(Message msg) {

    }

    @Override
    protected void afterDeQueue(Message msg) {

    }
}
