package com.spirit.rocket.aliyun.aspect;


import com.spirit.rocket.base.aspect.RocketSendAspectCore;
import com.spirit.rocket.base.config.RocketProperties;
import com.spirit.rocket.base.config.init.RocketProducerContainerInitConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.StandardEnvironment;

/**
 * 切面
 *
 * @author 张帆
 * @date 2021/08/17 17:24
 **/
@Slf4j
@Aspect
public class AliyunRocketSendAspect extends RocketSendAspectCore {

    public AliyunRocketSendAspect(RocketProducerContainerInitConfig rocketProducerContainerInitConfig,
                                  RocketProperties rocketProperties, StandardEnvironment environment) {
        super(rocketProducerContainerInitConfig, rocketProperties, environment);
    }

    /**
     * 消息切入点
     */
    @Pointcut("@annotation(com.spirit.rocket.aliyun.annotation.send.CommonMessage)||" +
            "@annotation(com.spirit.rocket.aliyun.annotation.send.OrderMessage)||" +
            "@annotation(com.spirit.rocket.aliyun.annotation.send.TransactionMessage)")
    public void messagePointcut() {
    }

    @Override
    @Around("messagePointcut()")
    public Object rockerMessageSend(ProceedingJoinPoint point) throws Throwable {
        return super.rockerMessageSend(point);
    }
}
