package com.dream.demo;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;


public class PaperTest {
    @Test
    public void test() {
        // 构造请求
        int number = 485000;
        IntStream.range(1, 1000).parallel().forEach(i -> {
            HttpRequest request = HttpUtil.createGet("https://thesis-new.xnjd.cn/downloadThesis?id=" + (number + i));
            String savePath = "F:\\paperFile\\2023\\";
            request.cookie("JSESSIONID=9056DD5C6E3B417ABA30254829E16F38");
            HttpResponse response = request.execute();

            // 获取响应头中的文件名
            String fileName = response.header("Content-Disposition").split(";")[1].split("=")[1].replace("\"", "");
            String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

            // 创建文件并写入响应内容
            response.writeBody(savePath + decodedFileName);
            // 创建文件并写入响应内容
            response.bodyStream();

            // 关闭响应
            response.close();
        });
    }
}
