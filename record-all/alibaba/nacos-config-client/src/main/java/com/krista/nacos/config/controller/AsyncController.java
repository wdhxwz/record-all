package com.krista.nacos.config.controller;

import com.krista.nacos.config.longpolling.HandlePollingTask;
import com.krista.nacos.config.servlet.LongPollingAsyncServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AsyncController
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/8 16:41
 */
@Controller
public class AsyncController {
    @RequestMapping("async")
    public void async(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext asyncContext = request.startAsync();
        LongPollingAsyncServlet.executor.execute(new HandlePollingTask(10, asyncContext));
    }
}
