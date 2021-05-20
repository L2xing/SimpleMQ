package com.icecola.test;

import com.icecola.simplemq.queue.ArrayMessageQueue;
import com.icecola.simplemq.queue.Message;
import org.junit.jupiter.api.Test;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.21 00:02
 */
public class QueueTest {

    @Test
    public void add() {
        ArrayMessageQueue arrayMessageQueue = new ArrayMessageQueue("topic");
        arrayMessageQueue.enqueue(new Message("123123"));
        Message dequeue = arrayMessageQueue.dequeue();
        System.out.println(dequeue.getText());
    }

}
