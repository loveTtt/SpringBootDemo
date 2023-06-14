package com.dream.demo.entity;

import lombok.Data;

/**
 * 用户文件
 * @author hys
 */
@Data
public class UserFile {
    /**
     * 文件id
     */
    private String id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件存储路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件存储方式
     */
    private String storageMethod;
    /**
     * 文件的md5值
     */
    private String fileMd5;
}
