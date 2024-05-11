package com.spirit.rocket.aliyun.core.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.spirit.rocket.base.core.consumer.RocketConsumerListener;

/**
 * 封装阿里SDK 响应体的监听器
 *
 * @author 张帆
 * @date 2021/08/19 13:34
 **/
public interface AliyunCommonConsumerListener<T> extends RocketConsumerListener<T, Action> {
}
