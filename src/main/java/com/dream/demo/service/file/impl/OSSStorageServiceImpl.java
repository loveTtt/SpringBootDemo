package com.dream.demo.service.file.impl;

import com.dream.demo.service.file.StorageStrategyService;

import java.io.File;

public class OSSStorageServiceImpl implements StorageStrategyService {
    @Override
    public long getFileSize() {
        return Long.MAX_VALUE;
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
