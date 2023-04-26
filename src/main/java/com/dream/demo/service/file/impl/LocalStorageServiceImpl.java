package com.dream.demo.service.file.impl;

import com.dream.demo.service.file.StorageStrategyService;

import java.io.File;

public class LocalStorageServiceImpl implements StorageStrategyService {
    @Override
    public long getFileSize() {
        //10M以下使用本地存储
        return 10 * 1024 * 1024;
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
