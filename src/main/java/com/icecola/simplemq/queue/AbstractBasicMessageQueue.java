package com.icecola.simplemq.queue;

/**
 * description:
 *
 * @author liuxingxing
 * @date 2021.05.19 23:52
 */
public abstract class AbstractBasicMessageQueue {

    /**
     * Description:
     * 获取队列名称
     *
     * @return java.lang.String
     * @author liuxingxing
     * @date 2021-05-20 23:08
     **/
    abstract String getTopic();

    /**
     * Description:
     * 消息入队
     *
     * @param msg:
     * @return void
     * @author liuxingxing
     * @date 2021-05-20 23:09
     **/
    abstract void enqueue(Message msg);

    /**
     * Description:
     * 消息出队
     *
     * @return com.icecola.simplemq.queue.Message
     * @author liuxingxing
     * @date 2021-05-20 23:09
     **/
    abstract Message dequeue();

    /**
     * Description:
     * 进队前
     *
     * @param msg:
     * @return void
     * @author liuxingxing
     * @date 2021-05-20 23:49
     **/
    protected abstract void beforeEnQueue(Message msg);

    /**
     * Description:
     * 进队后
     *
     * @param msg:
     * @return void
     * @author liuxingxing
     * @date 2021-05-20 23:50
     **/
    protected abstract void afterEnQueue(Message msg);

    /**
     * Description:
     * 出队前
     *
     * @param msg:
     * @return void
     * @author liuxingxing
     * @date 2021-05-20 23:50
     **/
    protected abstract void beforeDeQueue(Message msg);

    /**
     * Description:
     * 出队后
     *
     * @param msg:
     * @return void
     * @author liuxingxing
     * @date 2021-05-20 23:51
     **/
    protected abstract void afterDeQueue(Message msg);

}
