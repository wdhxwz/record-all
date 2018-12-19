package com.krista.eagle.admin.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * EagleSessionManager 自定义sessionId获取
 * 默认是从cookie中读取，JSESSIONID
 * <p>
 * 修改成：从header中读取，读取不到，才使用默认方式
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/19 19:23
 */
public class EagleSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public EagleSessionManager() {
        super();
    }

    @Override
    public Serializable getSessionId(SessionKey key) {
        ServletRequest request = WebUtils.getRequest(key);
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        // 如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            // 否则按默认规则从cookie取sessionId
            return super.getSessionId(key);
        }
    }
}
