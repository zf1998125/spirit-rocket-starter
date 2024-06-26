package com.spirit.rocket.base.state;

/**
 * 消息模型状态
 *
 * @author 张帆
 * @date 2021/08/19 00:24
 */
public interface MessageModelState {

    /**
     * 广播消费模式
     */
    String BROADCASTING = "BROADCASTING";

    /**
     * 集群消费模式
     */
    String CLUSTERING = "CLUSTERING";
}
