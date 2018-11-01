package com.krista.microservice.simple.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型，决定了过滤器在请求的哪个生命周期执行
     * <br/> pre 代表请求被路由之前执行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否需要被执行,true为是
     * <br/> 该方法用于指定过滤器的执行范围
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null){
            logger.warn("access token is empty");
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseBody("access token is empty");
//            requestContext.setResponseStatusCode(401);

            return null;
        }

        logger.info("access token ok");

        return null;
    }
}
