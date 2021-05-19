package com.icecola.simplemq.queue;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.19 23:52
 */
public abstract class AbstractBasicMessageQueue {

    abstract void enqueue();

    abstract void dequeue();
}
