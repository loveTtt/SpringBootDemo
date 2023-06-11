package com.dream.demo.user;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.compress.CompressUtil;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.File;
import java.time.Duration;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.dream.demo.entity.Company;
import com.google.common.util.concurrent.AtomicDouble;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author huangys2
 * @date 2022/9/5 10:42
 */
public class Test {

    public static void main(String[] args) {


    }

    @org.junit.Test
    public void mergeFluxes() {
        File srcFile = new File("/path/to/rar/file.rar");
        File destDir = new File("/path/to/destination/dir");
    }

}
