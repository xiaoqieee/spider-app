package com.hawcore.learning.spiderapp.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 *
 * @author 025665
 * @date 2017-07-25
 */
public class DelayThreadManager {

    private static final int CORE = Runtime.getRuntime().availableProcessors();
    // 线程池维护线程的最少数量,工作线程
    public static final int CORE_POOL_SIZE = CORE * 3;

    public static final ScheduledExecutorService delayThread = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    /**
     * 添加延迟任务
     *
     * @param task
     */
    public static void addTask(Runnable task, int delay, TimeUnit timeUnit) {
        delayThread.schedule(task, delay, timeUnit);
    }

    /**
     * 添加延迟任务(毫秒)
     *
     * @param task
     * @param delay
     */
    public static void addTask(Runnable task, int delay) {
        addTask(task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 添加延迟任务(30秒)
     *
     * @param task
     */
    public static void addTask(Runnable task) {
        addTask(task, 30, TimeUnit.SECONDS);
    }
}
