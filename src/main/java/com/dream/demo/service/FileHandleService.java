package com.dream.demo.service;

import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.file.StorageStrategyService;
import com.dream.demo.service.user.UserTypeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FileHandleService {

    private final LinkedHashMap<Long, StorageStrategyService> storageHashMap = new LinkedHashMap<>(4);

    /**
     * 注册文件存储策略，会把{@link StorageStrategyService}所有的实现类注册到storageHashMap中
     * @param strategyServices {@link StorageStrategyService}所有的实现类
     */
    public FileHandleService(List<StorageStrategyService> strategyServices) {
        //根据每种存储方式规定的大小范围排序后进行注入
        strategyServices.stream()
                .sorted(Comparator.comparing(StorageStrategyService::getFileSize))
                .forEach(strategyService -> storageHashMap.put(strategyService.getFileSize(), strategyService));
    }

    /**
     * 文件上传
     * @param file 需要上传的文件
     * @return 上传后的地址
     */
    public String uploadFile(File file){
        for (Map.Entry<Long, StorageStrategyService> entry : storageHashMap.entrySet()) {
            //根据文件大小选择文件上传的方式
            if (file.length() <= entry.getKey()) {
                return entry.getValue().uploadFile(file);
            }
        }
        return null;
    }


}
