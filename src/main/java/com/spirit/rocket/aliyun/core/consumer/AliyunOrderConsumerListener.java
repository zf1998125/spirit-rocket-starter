package com.spirit.rocket.aliyun.core.consumer;

import com.aliyun.openservices.ons.api.order.OrderAction;
import com.spirit.rocket.base.core.consumer.RocketConsumerListener;

/**
 * 阿里云sdk 顺序消费
 *
 * @author 张帆
 * @date 2021/08/19 13:36
 **/
public interface AliyunOrderConsumerListener<T> extends RocketConsumerListener<T, OrderAction> {
}
