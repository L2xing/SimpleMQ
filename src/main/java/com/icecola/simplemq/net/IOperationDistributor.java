package com.icecola.simplemq.net;

import com.icecola.simplemq.net.bean.Protocol;
import com.icecola.simplemq.queue.Message;

/**
 * description:
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/23 19:19
 */
public interface IOperationDistributor {

    public Protocol handle(Protocol<Message> protocol);

}
