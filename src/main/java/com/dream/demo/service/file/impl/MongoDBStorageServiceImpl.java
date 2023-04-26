package com.dream.demo.service.file.impl;

import com.dream.demo.service.file.StorageStrategyService;

import java.io.File;

public class MongoDBStorageServiceImpl implements StorageStrategyService {
    @Override
    public long getFileSize() {
        //30M以下使用mongoDb存储
        return 30 * 1024 * 1024;
    }

    @Override
    public String uploadFile(File file) {
        return null;
    }

    @Override
    public File downloadFile(String path) {
        return null;
    }
}
