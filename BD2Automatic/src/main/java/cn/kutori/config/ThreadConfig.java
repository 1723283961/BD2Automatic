package cn.kutori.config;

import java.util.concurrent.*;

/**
 * 线程池(未启用)
 */
public class ThreadConfig {
    // 自定义线程池
    ThreadPoolExecutor customPool = new ThreadPoolExecutor(
            5, // 核心线程数
            10, // 最大线程数
            60L, // 空闲线程存活时间(秒)
            TimeUnit.SECONDS, // 时间单位
            new LinkedBlockingQueue<>(100), // 工作队列
            new ThreadFactory() { // 线程工厂
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setName("custom-pool-" + t.getId());
                    return t;
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
    );

    public ExecutorService getCustomExecutor() {
        return customPool;
    }
}
