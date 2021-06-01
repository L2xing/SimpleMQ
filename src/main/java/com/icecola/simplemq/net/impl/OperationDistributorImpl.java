package com.icecola.simplemq.net.impl;

import com.icecola.simplemq.net.IOperationDistributor;
import com.icecola.simplemq.net.bean.OperateEnum;
import com.icecola.simplemq.net.bean.Protocol;
import com.icecola.simplemq.queue.AbstractArrayMessageQueue;
import com.icecola.simplemq.queue.ArrayMessageQueue;
import com.icecola.simplemq.queue.Message;
import com.icecola.simplemq.queue.QueueMap;

/**
 * description:
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/23 19:23
 */
public class OperationDistributorImpl implements IOperationDistributor {

    private static QueueMap queueMap = new QueueMap();

    @Override
    public Protocol handle(Protocol<Message> protocol) {
        String header = protocol.getHeader();
        OperateEnum operateEnum = OperateEnum.getEnum(header);
        Protocol reProtocol = null;
        switch (operateEnum) {
            case CAT:
                return handleCat(protocol);
            case DEQUEUE:
                return handleDequeue(protocol);
            case ENQUEUE:
                return handleEnqueue(protocol);
            default:
                break;
        }
        return reProtocol;
    }

    /**
     * Description:
     * 查看主题
     *
     * @param protocol:
     * @return com.icecola.simplemq.net.bean.Protocol
     * @author liuxingxing
     * @date 2021-05-24 22:29
     **/
    private Protocol handleCat(Protocol protocol) {
        Integer messageInTopic = queueMap.getMessageInTopic(protocol.getTopic());
        return Protocol.buildResponse(protocol.getTopic(), messageInTopic);
    }

    /**
     * Description:
     * 出队
     *
     * @param protocol:
     * @return com.icecola.simplemq.net.bean.Protocol
     * @author liuxingxing
     * @date 2021-05-24 22:29
     **/
    private Protocol handleDequeue(Protocol protocol) {
        Message consume = queueMap.consume(protocol.getTopic());
        return Protocol.buildResponse(protocol.getTopic(), consume);
    }

    /**
     * Description:
     * 入队
     *
     * @param protocol:
     * @return com.icecola.simplemq.net.bean.Protocol
     * @author liuxingxing
     * @date 2021-05-24 22:29
     **/
    private Protocol handleEnqueue(Protocol<Message> protocol) {
        queueMap.produce(protocol.getData(), protocol.getTopic());
        return Protocol.buildResponse(protocol.getTopic(), "200");
    }

}
