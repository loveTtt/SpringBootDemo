package com.dream.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

//指定基准测试类型 整体吞吐量 Throughput; AverageTime平均时间
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
//指定类中变量的作用范围
@State(Scope.Thread)
//预热配置、iterations:预热阶段的迭代数；time：每次预热的时间；batchSize:批处理大小，指定了每次操作调用几次方法
@Warmup(iterations = 3, time = 1)
//真正的迭代次数
@Measurement(iterations = 5, time = 1)
//表示使用几个进程进行测试
@Fork(1)
//指定几个线程进行测试
@Threads(2)
public class BenchmarkTest {

    @Param(value = {"50", "100", "1000"})
    private int length;

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        String a = "";
        for (int i = 0; i < length; i++) {
            a += i;
        }
        blackhole.consume(a);
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }


}
