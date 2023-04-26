package com.dream.demo.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.multipart.UploadFile;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class FileTest {


    private static final int CHUNK_SIZE = 10 * 1024 * 1024; // 每个分片的大小为 10MB
    private static final String UPLOAD_DIR = "F:\\newFile"; // 上传目录
    private static final String FILE_PATH = "F:\\oldFile\\redditsave.mp4"; // 待上传文件的路径

    @Test
    public void fileShard() throws IOException {
        File file = new File(FILE_PATH);
        //计算出需要分多少片
        int numChunks = (int) Math.ceil((double) file.length() / CHUNK_SIZE);
        //定义一个和分片数一样大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(numChunks);

        for (int i = 0; i < numChunks; i++) {
            final int chunkIndex = i;
            executorService.submit(() -> {
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    //跳过已经处理过的文件流
                    inputStream.skip((long) chunkIndex * CHUNK_SIZE);
                    byte[] buffer = new byte[CHUNK_SIZE];
                    int bytesRead = inputStream.read(buffer);
                    //临时分片文件
                    Path chunkPath = Paths.get(UPLOAD_DIR, file.getName() + ".part" + chunkIndex);
                    OutputStream outputStream = new FileOutputStream(chunkPath.toFile());
                    outputStream.write(buffer, 0, bytesRead);
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mergeChunks(file.getName(), numChunks);
    }

    private static void mergeChunks(String fileName, int numChunks) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        OutputStream outputStream = new FileOutputStream(filePath.toFile());
        for (int i = 0; i < numChunks; i++) {
            Path chunkPath = Paths.get(UPLOAD_DIR, fileName + ".part" + i);
            InputStream inputStream = new FileInputStream(chunkPath.toFile());
            Files.copy(chunkPath, outputStream);
            inputStream.close();
            Files.delete(chunkPath);
        }
        outputStream.flush();
        outputStream.close();
    }
}
