package com.krista.base.eureka.server.listener;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * EurekaStateChangeListener
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 14:56
 */
@Component
public class EurekaStateChangeListener {
    private static Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.error("{} {} 服务下线", event.getServerId(), event.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        // InstanceInfo instanceInfo = event.getInstanceInfo();
        // logger.info("{} {} 服务注册", instanceInfo.getInstanceId(), instanceInfo.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        // logger.info("{} {} 服务进行续约", event.getServerId(), event.getAppName());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("注册中心 可用");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("Eureka Server 启动");
    }
}
