package com.myweb.framework.core.impl.support;

import com.myweb.framework.utils.ClassUtil;
import com.myweb.framework.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by rola
 */
public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public final List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace("/", "."));
            while(urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if(url != null) {
                    //获取协议名（分为 file 与 jar）
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")) {
                        String packagePath = url.getPath().replace("%20"," ");
                        addClass(classList,packagePath,packageName);
                    } else if(protocol.equals("jar")) {
                        // 若在 jar 包中，则解析jar包中的entry
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while(jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            // 判断该 entry 是否是class
                            if(jarEntryName.endsWith(".class")){
                                //获取类名
                                String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                addClass(classList,className);
                            }
                        }
                    }

                }
            }
        } catch(Exception e) {
            logger.error("获取类出错！",e);
        }
        return classList;
    }

    private void addClass(List<Class<?>> listClass,String packagePath, String packageName) {
        try {
            File[] files = new File(packagePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
                }
            });
            //遍历文件或者目录
            for(File file : files) {
                String fileName = file.getName();
                if(file.isFile()) {
                    String className = fileName.substring(0,fileName.lastIndexOf("."));
                    if(StringUtil.isNotEmpty(packageName)) {
                        className = packageName + "." + className;
                    }
                    addClass(listClass,className);
                } else {
                    // 获取子包操作
                    String subPackagePath = fileName;
                    if(StringUtil.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    // 子包名
                    String subPackageName = fileName;
                    if(StringUtil.isNotEmpty(packageName)) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    //递归调用
                    addClass(listClass,subPackagePath,subPackageName);
                }
            }
        }catch (Exception e) {
            logger.error(" 添加类出错！",e);
        }
    }

    /**
     * 添加类操作
     * @param listClass
     * @param className
     */
    private void addClass(List<Class<?>> listClass, String className) {
        Class<?> cls = ClassUtil.loadClass(className,false);
        if(checkAddClass(cls)) {
            listClass.add(cls);
        }
    }

    public abstract boolean checkAddClass(Class<?> cls);
}







