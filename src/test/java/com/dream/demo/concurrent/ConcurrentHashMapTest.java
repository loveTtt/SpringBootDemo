package com.dream.demo.concurrent;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * concurrentHashMap容易引发的并发问题
 * concurrentHashMap只能保证提供的原子性读写操作是线程安全的
 */
@SpringBootTest
public class ConcurrentHashMapTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    //线程个数
    private static int THREAD_COUNT = 10;
    //总元素数量
    private static int ITEM_COUNT = 1000;

    private ConcurrentHashMap<String,Long> getData(int count){
        return LongStream.rangeClosed(1,count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i-> IdUtil.getSnowflakeNextIdStr(), Function.identity(),
                        (o1,o2)->o1,ConcurrentHashMap::new));
    }

    /**
     * 错误的示例
     * @throws InterruptedException
     */
    @Test
    public void wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        logger.info("init size:{}", concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        //使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            //查询还需要补充多少个元素
            int gap = ITEM_COUNT - concurrentHashMap.size();
            logger.info("gap size:{}", gap);
            //补充元素
            concurrentHashMap.putAll(getData(gap));
        }));
        //等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //最后元素个数会是1000吗？
        logger.info("finish size:{}", concurrentHashMap.size());
    }

    /**
     * 正确的示例
     * 对putAll的地方进行加锁
     */
    @Test
    public void right() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        logger.info("init size:{}", concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        //使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            //查询还需要补充多少个元素
            synchronized (concurrentHashMap){
                int gap = ITEM_COUNT - concurrentHashMap.size();
                logger.info("gap size:{}", gap);
                //补充元素
                concurrentHashMap.putAll(getData(gap));
            }
        }));
        //等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //最后元素个数会是1000吗？
        logger.info("finish size:{}", concurrentHashMap.size());
    }
}
