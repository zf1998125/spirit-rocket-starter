package com.spirit.rocket.base.annotation.other;

import java.lang.annotation.*;

/**
 * 自动生成UUID消息主键
 *
 * @author 张帆
 * @date 2021/10/13 11:42
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoMessageKey {
}
