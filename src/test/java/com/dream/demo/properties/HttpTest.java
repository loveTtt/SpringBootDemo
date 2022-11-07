package com.dream.demo.properties;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

@SpringBootTest
public class HttpTest {

    @Test
    public void httpTest(){
        HttpRequest allCurriculumRequest = HttpUtil.createGet("https://study.xnjd.cn/study/CourseRelation_studentViewCurrentTermCourse.action");
        allCurriculumRequest.cookie("JSESSIONID=3E063AC3BE59B009E6A57A2837188BBB");
        String body = allCurriculumRequest.execute().body();
        Pattern pattern_a = compile("<a[^>]*href=(\\\"([^\\\"]*)\\\"|\\'([^\\']*)\\'|([^\\\\s>]*))[^>]*>(.*?)</a>");
        Matcher matcher_a = pattern_a.matcher(body);
        String urlPrefix = "http://cs.xnjd.cn/course/Course_index.action";
        String targetUrl = "https://cs.xnjd.cn/course/Course_video.action";
        String videoPrefix = "Course_newVideo.action?courseId";
        List<String> allCurriculumUrls = Lists.newArrayList();
        List<String> allVideoUrls = Lists.newArrayList();
        while (matcher_a.find()) {
            for (int i = 0; i < matcher_a.groupCount(); i++) {
                if(matcher_a.group(2).contains(urlPrefix)){
                    allCurriculumUrls.add(matcher_a.group(2));
                }
            }
        }
        allCurriculumUrls.stream().distinct().forEach(curriculumUrl->{
            curriculumUrl = curriculumUrl.replace(urlPrefix,targetUrl);
            HttpRequest allVideoRequest = HttpUtil.createGet(curriculumUrl);
            allVideoRequest.cookie("JSESSIONID=F40AAFE311E4D27DE91AFA5F494FCFFC");
            String videoBody = allVideoRequest.execute().body();
            Matcher matcher_b = pattern_a.matcher(videoBody);
            while (matcher_b.find()) {
                for (int i = 0; i < matcher_b.groupCount(); i++) {
                    if(StringUtils.isNotBlank(matcher_b.group(2)) && matcher_b.group(2).contains(videoPrefix)){
                        allVideoUrls.add("https://cs.xnjd.cn/course/"+matcher_b.group(2));
                    }
                }
            }
        });
        allVideoUrls.stream().distinct().forEach(videoUrl->{
            HttpRequest allVideoRequest = HttpUtil.createGet(videoUrl);
            allVideoRequest.cookie("JSESSIONID=F40AAFE311E4D27DE91AFA5F494FCFFC");
            allVideoRequest.execute();
        });

    }

}
