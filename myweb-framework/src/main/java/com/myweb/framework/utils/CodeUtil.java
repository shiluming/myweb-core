package com.myweb.framework.utils;

import com.myweb.framework.FrameworkConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by rola
 */
public class CodeUtil {


    private static final Logger logger = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 将 url 编码
     */
    public static String encodeURL(String url) {
        String target;
        try {
            target = URLEncoder.encode(url, FrameworkConstant.UTF_8);
        } catch (Exception e) {
            logger.error(" 编码错误 ",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将url 解码
     * @param url
     * @return
     */
    public static String decodeURL(String url) {
        String target;
        try {
            target = URLDecoder.decode(url,FrameworkConstant.UTF_8);

        } catch (Exception e) {
            logger.error(" 解码错误 ！ ",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 编码
     * @param str
     * @return
     */
    public static String encodeBASE64(String str) {
        String target = null;
        try {
            target = Base64.encodeBase64String(str.getBytes(FrameworkConstant.UTF_8));

        } catch (UnsupportedEncodingException e) {
            logger.error("编码错误！",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 解码base64
     * @param str
     * @return
     */
    public static String decodeBASE64(String str) {
        String target = null;
        try {
            target = new String(Base64.decodeBase64(str.getBytes(FrameworkConstant.UTF_8)));
        } catch (UnsupportedEncodingException e) {
            logger.error("解码错误！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 md 加密
     * @param str
     * @return
     */
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将字符串 SHA 加密
     * @param str
     * @return
     */
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 创建随机数
     * @param count
     * @return
     */
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取uuid(32位)
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(CodeUtil.createUUID());
    }
}
