package com.spirit.rocket.base.annotation;

import java.lang.annotation.*;

/**
 * 全局启动
 *
 * @author 张帆
 * @date 2021/08/23 10:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableRocketMQ {

}
