package com.macrochina.net.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的配置
 */
@Configuration
public class AsyncConfig {

    private static final int MAX_POOL_SIZE = 1000;

    private static final int CORE_POOL_SIZE = 100;

    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务 CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }


    @Bean("asyncAckTaskExecutor")
    public AsyncTaskExecutor asyncAckTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);//最大线程数
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);//核心池大小
        asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务 CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
