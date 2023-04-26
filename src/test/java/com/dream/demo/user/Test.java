package com.dream.demo.user;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.compress.CompressUtil;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.File;
import java.time.Duration;

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
