package com.myweb.framework.utils;

import com.myweb.framework.FrameworkConstant;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.RuntimeErrorException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rola
 */
public class WebUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    public static void writeJSON(HttpServletResponse response,Object data) {
        try {
            //设置响应头
            response.setContentType("application/json");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            //向响应中写入数据
            PrintWriter writer = null;
            writer = response.getWriter();
            writer.write(JsonUtil.toJson(data));
            writer.flush();
            writer.close();
        } catch(Exception e) {
            logger.error("在响应中写入数据出错！",e);
            throw new RuntimeException(e);
        }
    }

    public static void writeHTML(HttpServletResponse response,Object data) {
        try {
            //设置响应头
            response.setContentType("text/html");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJson(data));
            writer.flush();
            writer.close();
        }catch (Exception e) {
            logger.error("在响应中写入数据错误！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从请求中获取所有参数， 这里有个问题：解析的时候url也一起解析了，貌似达不到预期的效果来的。 最后要自己测试一下
     *
     * 还要测试在请求中的参数新的值是否会覆盖旧的的值
     *
     * request.getParametersNames()   测试这个方法获取到的值时候咋样的
     */
    public static Map<String,Object> getRequestParamMap(HttpServletRequest request) {
        Map<String,Object> paramMap = new LinkedHashMap<String, Object>();
        try {
            String method = request.getMethod();
            if(method.equalsIgnoreCase("put") || method.equalsIgnoreCase("delete")) {
                //处理 put delete 请求处理
                String queryString = CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
                if(StringUtil.isNotEmpty(queryString)) {
                    String[] qsArray = StringUtil.splitString(queryString,"&");
                    if(ArrayUtil.isNotEmpty(qsArray)) {
                        for(String qs : qsArray) {
                            String[] array = StringUtil.splitString(qs,"=");
                            if(ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                String paramName = array[0];
                                String paramValue = array[1];
                                if(checkParamName(paramName)) {
                                    if(paramMap.containsKey(paramName)) {
                                        paramValue = paramMap.get(paramName) + StringUtil.SEPARATOR + paramValue;  // 这里很奇怪，新的值会覆盖之前的值吗？
                                    }
                                    paramMap.put(paramName,paramValue);
                                }
                            }
                        }
                    }
                }

            } else {
                Enumeration<String> paramNames = request.getParameterNames(); // 这个方法获取到的信息是咋样的？
                while(paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    if(checkParamName(paramName)) {
                        String[] paramValues = request.getParameterValues(paramName);
                        if(ArrayUtil.isNotEmpty(paramValues)) {
                            if(paramValues.length==1) {
                                paramMap.put(paramName,paramValues[0]);
                            } else {
                                StringBuilder paramValue = new StringBuilder("");
                                for(int i=0;i<paramValues.length;i++) {
                                    paramValue.append(paramValues[i]);
                                    if(i != paramValues.length-1) {
                                        paramValue.append(StringUtil.SEPARATOR);
                                    }
                                }
                                paramMap.put(paramName,paramValue);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取请求参数出错！",e);
            throw new RuntimeException(e);
        }
        return paramMap;
    }

    /**
     * 忽略 Jquery 缓存
     * @param paramName
     * @return
     */
    private static boolean checkParamName(String paramName) {
        return !paramName.equals("_");
    }

    /**
     * 转发请求
     * @param path
     * @param request
     * @param response
     */
    public static void forwardRequest(String path,HttpServletRequest request,HttpServletResponse response) {
        try {
            request.getRequestDispatcher(request.getContextPath()+ path);
        }catch (Exception e) {
            logger.error("转发请求出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 重定向
     * @param path
     * @param request
     * @param response
     */
    public static void redirectRequest(String path,HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath()+path);
        }catch (Exception e) {
            logger.error("重定向出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送错误代码
     * @param code
     * @param message
     * @param response
     */
    public static void snedError(int code, String message, HttpServletResponse response) {
        try {
            response.sendError(code,message);
        }catch (Exception e) {
            logger.error("发送错误代码错误！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否是 ajax 请求
     * @param request
     * @return
     */
    public static boolean isAJAX(HttpServletRequest request) {
        return request.getHeader("X-Request-With") != null;
    }

    /**
     * 获取请求路径
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = StringUtil.defaultIfEmpty(request.getPathInfo(),"");
        return servletPath+pathInfo;
    }

    /**
     * 从 Cookies 中获取值
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        String value = "";
        try {
            Cookie[] cookieArray = request.getCookies();
            if(cookieArray != null) {
                for(Cookie cookie : cookieArray) {
                    if(StringUtil.isNotEmpty(name) && name.equals(cookie.getName())) {
                        value = CodeUtil.decodeURL(cookie.getValue());
                        break;
                    }
                }
            }
        }catch (Exception e) {
            logger.error("获取 Cookie 出错！",e);
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * 下载文件
     *
     */
    public static void downloadFile(HttpServletResponse response,String filePath) {
        try {
            String originalFileName = FilenameUtils.getName(filePath);
            String downloadFileName = new String(originalFileName.getBytes(),"utf-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition","attachment;filename=\""+ downloadFileName + "\"");

            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
        } catch (Exception e) {
            logger.error("下载文件出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置 Redirect URL 到 Session 中
     * @param request
     * @param sessionKey
     */
    public static void setRedirectUrl(HttpServletRequest request, String sessionKey) {
        if(!isAJAX(request)) {
            String requestPath = getRequestPath(request);
            request.getSession().setAttribute(sessionKey,requestPath);
        }
    }

    /**
     * 该方法暂时不去用
     * @param response
     * @return
     */
    public static String createCaptcha(HttpServletResponse response) {
        StringBuilder captacha = new StringBuilder();

        try {

        }catch (Exception e) {

        }
        return captacha.toString();
    }

    /**
     * 判断是否是 IE
     * @param request
     * @return
     */
    public static boolean isIE(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        return agent != null && agent.contains("MSIE");
    }

}




















