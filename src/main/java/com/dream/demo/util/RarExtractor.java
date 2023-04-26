package com.dream.demo.util;


import com.github.junrar.Archive;
import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import com.github.junrar.volume.FileVolumeManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RarExtractor {

    public static void main(String[] args) throws IOException, RarException {
        File rarFile = new File("F:\\rar\\计算机、自动化写作参考.rar");
        File destDir = new File("F:\\unrar");
        Junrar.extract(rarFile,destDir);
    }
}
