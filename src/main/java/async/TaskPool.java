package async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务线程池 Task thread pool
 *
 * @author anonymous
 */
public class TaskPool {

    /** 创建线程工厂 create a thread factory */
    public static final ThreadFactory factory =
            new ThreadFactoryBuilder().setNameFormat("Anonymous-Scanner-Pool")
                    .build();

    /** cpu 核心数 cores of cpu */
    public static final int cpuCores = Runtime.getRuntime().availableProcessors();

    /** 最大线程数 max number of threads */
    public static final int maxThreads = (cpuCores * 2 + 1) * 100;

    /** 核心线程池大小 corePoolSize */
    public static final int corePoolSize = (cpuCores * 2 + 1) * 10;

    /** 线程池 thread pool */
    public static final ThreadPoolExecutor pool =
            new ThreadPoolExecutor(corePoolSize, maxThreads, 0L,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(1024),
                    factory, new ThreadPoolExecutor.AbortPolicy());
}
