package com.spirit.rocket.aliyun.support.consumer;


import com.spirit.rocket.aliyun.core.consumer.AliyunBatchConsumerListener;
import com.spirit.rocket.aliyun.core.consumer.AliyunCommonConsumerListener;
import com.spirit.rocket.aliyun.core.consumer.AliyunOrderConsumerListener;
import com.spirit.rocket.aliyun.support.consumer.container.BatchRocketConsumerContainer;
import com.spirit.rocket.aliyun.support.consumer.container.CommonRocketConsumerContainer;
import com.spirit.rocket.aliyun.support.consumer.container.OrderRocketConsumerContainer;
import com.spirit.rocket.base.annotation.listener.RocketMessageListener;
import com.spirit.rocket.base.config.RocketProperties;
import com.spirit.rocket.base.core.consumer.RocketConsumerListener;
import com.spirit.rocket.base.serializer.base.RocketSerializer;
import com.spirit.rocket.base.state.MessageModelState;
import com.spirit.rocket.base.support.AbstractConsumerContainerSupport;
import com.spirit.rocket.base.support.container.AbstractConsumerContainer;
import com.spirit.rocket.base.utils.AspectUtils;

/**
 * 通用支持类
 *
 * @author 张帆
 * @date 2021/08/19 01:38
 **/
public class DefaultConsumerContainerSupport extends AbstractConsumerContainerSupport {

    /**
     * 判断是否受支持
     *
     * @param rocketProperties 火箭的属性
     * @param annotation       注释
     * @param serializer       序列化器
     * @param clazz            clazz
     * @param messageType      消息类型
     * @param returnType       返回类型
     * @param actingListener   代理侦听器
     * @param groupId          组id
     * @param topic            主题
     * @param tag              标签
     * @return boolean
     */
    @Override
    public boolean isSupport(RocketProperties rocketProperties, RocketMessageListener annotation, RocketSerializer serializer, Class<? extends RocketConsumerListener<?, ?>> clazz, Class<?> messageType, Class<?> returnType, RocketConsumerListener<?, ?> actingListener, String groupId, String topic, String tag) {
        if (annotation == null) {
            return false;
        }
        if (!MessageModelState.BROADCASTING.equals(annotation.messageModel())
                && !MessageModelState.CLUSTERING.equals(annotation.messageModel())) {
            return false;
        }
        return AspectUtils.checkClassAnySubOfInterface(clazz,
                AliyunCommonConsumerListener.class,
                AliyunBatchConsumerListener.class,
                AliyunOrderConsumerListener.class);
    }

    /**
     * 生成容器 仅支持{@link AliyunCommonConsumerListener}、{@link AliyunOrderConsumerListener}、{@link AliyunBatchConsumerListener}子类生成
     *
     * @param rocketProperties 火箭的属性
     * @param annotation       注释
     * @param serializer       序列化器
     * @param clazz            clazz
     * @param messageType      消息类型
     * @param returnType       返回类型
     * @param actingListener   代理侦听器
     * @param groupId          组id
     * @param topic            主题
     * @param tag              标签
     * @return {@link AbstractConsumerContainer}
     */
    @Override
    public AbstractConsumerContainer generateContainer(RocketProperties rocketProperties, RocketMessageListener annotation, RocketSerializer serializer, Class<? extends RocketConsumerListener<?, ?>> clazz, Class<?> messageType, Class<?> returnType, RocketConsumerListener<?, ?> actingListener, String groupId, String topic, String tag) {
        if (AspectUtils.checkClassAnySubOfInterface(clazz, AliyunCommonConsumerListener.class)) {
            return new CommonRocketConsumerContainer(rocketProperties, serializer, (AliyunCommonConsumerListener<?>) actingListener, messageType, null, groupId, topic, tag, annotation.messageModel());
        }
        if (AspectUtils.checkClassAnySubOfInterface(clazz, AliyunOrderConsumerListener.class)) {
            return new OrderRocketConsumerContainer(rocketProperties, serializer, (AliyunOrderConsumerListener<?>) actingListener, messageType, null, groupId, topic, tag, annotation.messageModel());
        }
        if (AspectUtils.checkClassAnySubOfInterface(clazz, AliyunBatchConsumerListener.class)) {
            return new BatchRocketConsumerContainer(rocketProperties, serializer, (AliyunBatchConsumerListener<?>) actingListener, messageType, null, groupId, topic, tag, annotation.messageModel());
        }
        throw new IllegalStateException("无法实例化的类型");
    }
}
