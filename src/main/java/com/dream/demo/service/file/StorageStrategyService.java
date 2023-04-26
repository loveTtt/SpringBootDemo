package com.dream.demo.service.file;


import java.io.File;

public interface StorageStrategyService {

    long getFileSize();

    String uploadFile(File file);

    File downloadFile(String path);
}
