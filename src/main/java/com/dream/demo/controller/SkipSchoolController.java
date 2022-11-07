package com.dream.demo.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

@RestController
@RequestMapping("/skipSchool")
public class SkipSchoolController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String CURRICULUM_URL = "https://study.xnjd.cn/study/CourseRelation_studentViewCurrentTermCourse.action";
    private static final String OLD_URL_PREFIX = "http://cs.xnjd.cn/course/Course_index.action";
    private static final String TARGET_URL_PREFIX = "https://cs.xnjd.cn/course/Course_video.action";

    private static final String VIDEO_URL_PREFIX = "https://cs.xnjd.cn/course/Course_video.action";

    @GetMapping("/implement/{indexCookie}/{classCookie}")
    public String implement(@PathVariable String indexCookie, @PathVariable String classCookie) {
        logger.info("indexCookie:{},classCookie:{}",indexCookie,classCookie);
        HttpRequest allCurriculumRequest = HttpUtil.createGet(CURRICULUM_URL);
        allCurriculumRequest.cookie(indexCookie);
        String body = allCurriculumRequest.execute().body();
        Pattern pattern_a = compile("<a[^>]*href=(\\\"([^\\\"]*)\\\"|\\'([^\\']*)\\'|([^\\\\s>]*))[^>]*>(.*?)</a>");
        Matcher matcher_a = pattern_a.matcher(body);
        List<String> allCurriculumUrls = Lists.newArrayList();
        List<String> allVideoUrls = Lists.newArrayList();
        while (matcher_a.find()) {
            for (int i = 0; i < matcher_a.groupCount(); i++) {
                if (matcher_a.group(2).contains(OLD_URL_PREFIX)) {
                    allCurriculumUrls.add(matcher_a.group(2));
                }
            }
        }
        allCurriculumUrls.stream().distinct().forEach(curriculumUrl -> {
            curriculumUrl = curriculumUrl.replace(OLD_URL_PREFIX, TARGET_URL_PREFIX);
            HttpRequest allVideoRequest = HttpUtil.createGet(curriculumUrl);
            allVideoRequest.cookie(classCookie);
            String videoBody = allVideoRequest.execute().body();
            Matcher matcher_b = pattern_a.matcher(videoBody);
            while (matcher_b.find()) {
                for (int i = 0; i < matcher_b.groupCount(); i++) {
                    if (StringUtils.isNotBlank(matcher_b.group(2)) && matcher_b.group(2).contains(VIDEO_URL_PREFIX)) {
                        allVideoUrls.add("https://cs.xnjd.cn/course/" + matcher_b.group(2));
                    }
                }
            }
        });
        allVideoUrls.stream().distinct().forEach(videoUrl -> {
            HttpRequest allVideoRequest = HttpUtil.createGet(videoUrl);
            allVideoRequest.cookie(classCookie);
            allVideoRequest.execute();
        });
        return "已完成";
    }
}
