package com.myweb.framework.utils;

import com.myweb.framework.FrameworkConstant;
import jdk.jfr.events.ExceptionThrownEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by rola
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建目录
     * @param dirPath
     * @return
     */
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

    /**
     * 创建文件
     * @param filePath
     * @return
     */
    public static File createFile(String filePath) {
        File file;
        try {

            file = new File(filePath);
            File parentDir = file.getParentFile();
            if(!parentDir.exists()) {
                FileUtils.forceMkdir(parentDir);
            }
        } catch (IOException e) {
            logger.error(" 创建文件失败 ", e);
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 复制目录(不会复制空目录)
     * @param srcPath
     * @param destPath
     */
    public static void copyDir(String srcPath,String destPath) {
        try {
            File srcDir = new File(srcPath);
            File destDir = new File(destPath);

            if(srcDir.exists() && srcDir.isDirectory()) {
                FileUtils.copyDirectoryToDirectory(srcDir,destDir);
            }
        } catch(Exception e) {
            logger.error(" 复制目录出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制文件
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath,String destPath) {
        try {
            File srcFile = new File(srcPath);
            File descFile = new File(destPath);
            if(srcFile.exists() && srcFile.isFile()) {
                FileUtils.copyFile(srcFile,descFile);
            }
        }catch (Exception e) {
            logger.error(" 复制文件出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除目录
     * @param dirPath
     */
    public static void deleteDir(String dirPath) {
        try {
            File file = new File(dirPath);
            if(file.exists() && file.isDirectory()) {
                FileUtils.deleteDirectory(file);
            }
        }catch (Exception e) {
            logger.error("删除目录出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文件
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if(file.exists() && file.isFile()) {
                FileUtils.forceDelete(file);
            }
        } catch(Exception e) {
            logger.error("删除文件出错",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 重命名文件
     * @param srcPath
     * @param destPath
     */
    public static void renameFile(String srcPath,String destPath) {
        File srcFile = new File(srcPath);
        if(srcFile.exists()) {
            File newFile = new File(destPath);
            boolean result = srcFile.renameTo(newFile);
            if(!result) {
                throw new RuntimeException("重命名文件出错！"+ newFile);
            }
        }
    }

    /**
     * 将字符串写入到文件中
     * @param filePath
     * @param fileContent
     */
    public static void writeFile(String filePath,String fileContent) {
        OutputStream out = null;
        Writer writer = null;
        FileUtil.createFile(filePath);
        try {
            out = new BufferedOutputStream(new FileOutputStream(filePath));
            writer = new OutputStreamWriter(out, FrameworkConstant.UTF_8);
            writer.write(fileContent);
            writer.flush();
        } catch (Exception e) {
            logger.error("写入文件失败！",e);
            throw new RuntimeException(e);
        } finally {
            try {
                if(out!=null) {
                    out.close();
                }
                if(writer!=null) {
                    writer.close();
                }
            }catch (Exception e) {
                logger.error("释放资源出错!",e);

            }
        }
    }

    /**
     * 获取真是文件名
     * @param fileName
     * @return
     */
    public static String getRealFileName(String fileName) {
        return FilenameUtils.getName(fileName);
    }

    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean checkFileExists(String filePath) {
        return new File(filePath).exists();
    }

}













