package com.krista.nacos.config.servlet;

import com.krista.extend.utils.ThreadPoolUtil;
import com.krista.nacos.config.longpolling.HandlePollingTask;
import com.krista.nacos.config.longpolling.LongPollingAsyncListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * LongPollingAsyncServlet 长轮询异步servlet
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/8 9:58
 */
@WebServlet(urlPatterns = "/longPolling", asyncSupported = true)
public class LongPollingAsyncServlet extends HttpServlet {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LongPollingAsyncServlet.class);
    public static ExecutorService executor = ThreadPoolUtil.newSingleThreadExecutor("longPolling-%d");
    private final AtomicLong sequence = new AtomicLong();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final long currentSequence = sequence.incrementAndGet();
        LOGGER.info("第" + (currentSequence) + "次 longpolling async");
        // 设置request异步处理
        AsyncContext asyncContext = request.startAsync();

        // 异步处理超时时间，这里需要注意，jetty容器默认的这个值设置的是30s，
        // 如果超时，异步处理没有完成（通过是否asyncContext.complete()来进行判断），将会重试（会再次调用doGet方法）。
        // 这里由于客户端long polling设置的是50s，所以这里如果小于50，会导致重试。
        asyncContext.setTimeout(51000);
        asyncContext.addListener(new LongPollingAsyncListener());

        // 提交线程池异步写回结果
        // 具体场景中可以有具体的策略进行操作
        executor.submit(new HandlePollingTask(currentSequence, asyncContext));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
