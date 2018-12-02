package com.krista.spring.boot.admin.service.notifier;

import com.krista.extend.utils.JsonUtil;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.model.Application;
import de.codecentric.boot.admin.model.StatusInfo;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 应用状态告警
 */
@Component
public class MyNotifier extends AbstractStatusChangeNotifier {
    private static Logger logger = LoggerFactory.getLogger(MyNotifier.class);

    @Override
    protected void doNotify(ClientApplicationEvent clientApplicationEvent) throws Exception {
        String type = clientApplicationEvent.getType();
        logger.info("type = " + type);
        Application application = clientApplicationEvent.getApplication();
        logger.info(JsonUtil.toJson(application));

        StatusInfo statusInfo = application.getStatusInfo();
        Map detail = statusInfo.getDetails();

        // type = STATUS_CHANGE
        // {
        //	"id": "641ec01a",
        //	"name": "USER-SERVICE",
        //	"managementUrl": "http://169.254.20.216:8001/",
        //	"healthUrl": "http://169.254.20.216:8001/health",
        //	"serviceUrl": "http://169.254.20.216:8001/",
        //	"statusInfo": {
        //		"status": "OFFLINE",
        //		"timestamp": 1543658528264,
        //		"details": {
        //			"exception": "org.springframework.web.client.ResourceAccessException",
        //			"message": "I/O error on GET request for \"http://169.254.20.216:8001/health\": Connect to 169.254.20.216:8001 [/169.254.20.216] failed: Connection refused: connect; nested exception is org.apache.http.conn.HttpHostConnectException: Connect to 169.254.20.216:8001 [/169.254.20.216] failed: Connection refused: connect"
        //		}
        //	},
        //	"source": "discovery",
        //	"metadata": {
        //		"user.name": "krista",
        //		"management.port": "8001",
        //		"jmx.port": "62931",
        //		"user.password": "krista008"
        //	},
        //	"info": {
        //		"author": {
        //			"name": "krista",
        //			"email": "1366678737@qq.com",
        //			"url": "https://github.com/wdhxwz"
        //		},
        //		"applicationName": "springboot-1.x-test",
        //		"application": {
        //			"name": "springboot-1.x-test",
        //			"desc": "aaaa",
        //			"version": "V0.1"
        //		}
        //	}
        //}
        //
        //
        // type = STATUS_CHANGE
        // {
        //	"id": "641ec01a",
        //	"name": "USER-SERVICE",
        //	"managementUrl": "http://169.254.20.216:8001/",
        //	"healthUrl": "http://169.254.20.216:8001/health",
        //	"serviceUrl": "http://169.254.20.216:8001/",
        //	"statusInfo": {
        //		"status": "UP",
        //		"timestamp": 1543658647466,
        //		"details": {
        //			"hystrix": {
        //				"status": "UP"
        //			},
        //			"diskSpace": {
        //				"status": "UP",
        //				"total": 237962784768,
        //				"free": 174054432768,
        //				"threshold": 10485760
        //			},
        //			"discoveryComposite": {
        //				"description": "Composite Discovery Client",
        //				"status": "UP",
        //				"discoveryClient": {
        //					"description": "Composite Discovery Client",
        //					"status": "UP",
        //					"services": ["service-registry", "admin-service", "user-service"]
        //				},
        //				"eureka": {
        //					"description": "Remote status from Eureka server",
        //					"status": "UP",
        //					"applications": {
        //						"SERVICE-REGISTRY": 2,
        //						"ADMIN-SERVICE": 1,
        //						"USER-SERVICE": 1
        //					}
        //				}
        //			},
        //			"rabbit": {
        //				"status": "UP",
        //				"version": "3.7.7"
        //			},
        //			"description": "Composite Discovery Client",
        //			"db": {
        //				"status": "UP",
        //				"database": "H2",
        //				"hello": 1
        //			},
        //			"status": "UP"
        //		}
        //	},
        //	"source": "discovery",
        //	"metadata": {
        //		"user.name": "krista",
        //		"management.port": "8001",
        //		"jmx.port": "62931",
        //		"user.password": "krista008"
        //	},
        //	"info": {
        //		"author": {
        //			"name": "krista",
        //			"email": "1366678737@qq.com",
        //			"url": "https://github.com/wdhxwz"
        //		},
        //		"applicationName": "springboot-1.x-test",
        //		"application": {
        //			"name": "springboot-1.x-test",
        //			"desc": "aaaa",
        //			"version": "V0.1"
        //		}
        //	}
        //}
    }
}
