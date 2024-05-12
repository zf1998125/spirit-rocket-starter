package com.spirit.rocket.base.support;


import com.spirit.rocket.base.annotation.listener.RocketMessageListener;
import com.spirit.rocket.base.config.RocketProperties;
import com.spirit.rocket.base.core.consumer.RocketConsumerListener;
import com.spirit.rocket.base.serializer.base.RocketSerializer;
import com.spirit.rocket.base.support.container.AbstractConsumerContainer;

/**
 * mq容器实现支持者
 *
 * @author 张帆
 * @date 2021/08/19 00:32
 **/
public abstract class AbstractConsumerContainerSupport implements Comparable<AbstractConsumerContainerSupport> {

    /**
     * 判断是否可处理
     *
     * @param rocketProperties 火箭的属性
     * @param annotation       注释
     * @param serializer       序列化器
     * @param groupId          组id
     * @param topic            主题
     * @param tag              标签
     * @param clazz            clazz
     * @param messageType      消息类型
     * @param returnType       返回类型
     * @param actingListener   代理侦听器
     * @return boolean
     */
    public boolean isSupport(RocketProperties rocketProperties,
                             RocketMessageListener annotation,
                             RocketSerializer serializer,
                             Class<? extends RocketConsumerListener<?, ?>> clazz,
                             Class<?> messageType,
                             Class<?> returnType,
                             RocketConsumerListener<?, ?> actingListener,
                             String groupId,
                             String topic,
                             String tag) {
        return true;
    }

    /**
     * 生成容器
     *
     * @param rocketProperties 火箭的属性
     * @param annotation       注释
     * @param serializer       序列化器
     * @param groupId          组id
     * @param topic            主题
     * @param tag              标签
     * @param clazz            clazz
     * @param messageType      消息类型
     * @param returnType       返回类型
     * @param actingListener   代理侦听器
     * @return {@link AbstractConsumerContainer}
     */
    public abstract AbstractConsumerContainer generateContainer(RocketProperties rocketProperties,
                                                                RocketMessageListener annotation,
                                                                RocketSerializer serializer,
                                                                Class<? extends RocketConsumerListener<?, ?>> clazz,
                                                                Class<?> messageType,
                                                                Class<?> returnType,
                                                                RocketConsumerListener<?, ?> actingListener,
                                                                String groupId,
                                                                String topic,
                                                                String tag);

    /**
     * 排序序号
     *
     * @return int
     */
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(AbstractConsumerContainerSupport otherSupport) {
        return otherSupport.getOrder() - getOrder();
    }
}
