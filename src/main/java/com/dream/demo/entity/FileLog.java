package com.dream.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文件日志表
 * @author hys
 */
@Data
public class FileLog {
    /**
     * 日志id
     */
    private String id;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 类型，上传/下载
     */
    private String type;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 状态
     */
    private String status;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
}
