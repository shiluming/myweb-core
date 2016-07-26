package com.myweb.framework.utils;

import jdk.jfr.events.ExceptionThrownEvent;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by rola
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File createDir(String dirPath) {
        File dir;

        try {
            dir = new File(dirPath);
            if(!dir.exists()) {
                FileUtils.forceMkdir(dir);
            }
        } catch (IOException e) {
            logger.error(" 创建目录出错！ ",e);
            throw new RuntimeException(e);
        }
        return dir;
    }

}
