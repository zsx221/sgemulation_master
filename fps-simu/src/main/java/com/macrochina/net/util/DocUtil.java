package com.macrochina.net.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author：Tseng
 * @description  获取临时文件路径
 * @since: JDK1.8
 * @version: 1.0
 * @date: 2021-09-23
 * 最后更新日期：
 * 修改人：
 * 复审人：
 * @Copyright © 2021
 */
@Slf4j
public class DocUtil {

    //此路径是其余方法进行调用，且只须要加载一次
    private static String sourceTemplatePath;
    // 模板文件名称 位于 resource/static/template下面
    private static String[] ftlArray = {"rental_agreement.pdf"};
    // resources下模板文件夹名称
    private static String temPath = "template_file/";

    static {
        //静态方法调用一次　
        sourceTemplatePath = createFtlFileByFtlArray();
    }
    // 获取临时文件路径
    public String getSourceTemplatePath(){
        return sourceTemplatePath;
    }

    //获取临时文件模板路径
    public String getRentalAgreementPath(){
        return sourceTemplatePath+ ftlArray[0];
    }

    private static String createFtlFileByFtlArray() {
        String path = "";
        for (int i = 0; i < ftlArray.length; i++) {
            path = createFtlFile(temPath, ftlArray[i]);
            if (null == path) {
                log.info("not copy success:" + ftlArray[i]);
            }
        }
        return path;
    }

    private static String createFtlFile(String ftlPath, String ftlName) {
        try {
            //获取当前项目所在的绝对路径

            String proFilePath = System.getProperty("user.dir");
            log.info("project run path：" + proFilePath);
            //获取模板下的路径　
            String newFilePath = proFilePath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + ftlPath;
            newFilePath = newFilePath.replace("/", File.separator);
            log.info("newFilePath:" + newFilePath);
            //检查项目运行时的src下的对应路径
            File newFile = new File(newFilePath + ftlName);
            if (newFile.isFile() && newFile.exists()) {
                return newFilePath;
            }
            //当项目打成jar包会运行下面的代码，而且复制一份到src路径下（具体结构看下面图片）
            InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(ftlPath + ftlName);
            byte[] certData = IOUtils.toByteArray(certStream);
            FileUtils.writeByteArrayToFile(newFile, certData);
            return newFilePath;
        } catch (IOException e) {
            log.error("复制文件失败--> 异常信息：" + e);
        }
        return null;
    }
}