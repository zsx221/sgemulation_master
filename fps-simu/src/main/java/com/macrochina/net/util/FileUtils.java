package com.macrochina.net.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

@Slf4j
public class FileUtils {

    public final static String OUTWARD_RT_ACK = "/data/fps/outward/rt/ack/";
    public final static String OUTWARD_RT_PRC = "/data/fps/outward/rt/prc/";
    public final static String OUTWARD_RT_REQ = "/data/fps/outward/rt/req/";
    public final static String INWARD_RT_ACK = "/data/fps/inward/rt/ack/";
    public final static String INWARD_RT_PRC = "/data/fps/inward/rt/prc/";
    public final static String INWARD_RT_REQ = "/data/fps/inward/rt/req/";
    public final static String FPS_SCHEMA = "/schema/" ;
    public final static String ADRS_SCHEMA = "/adrsSchema/" ;
    public final static String REPORT_SOURCE = "/data/fps/report/source/";
    public final static String REPORT_TARGET = "/data/fps/report/target/";

    /*
     * 为 xml文本添加 CDATA标记
     */
    public static String addCdata(String xml) {
        return StringEscapeUtils.unescapeXml("<![CDATA[" + xml + "]]>");
    }

    /**
     * 保存报文到本地
     * @param xml  报文内容
     * @param url  保存文件地址
     */
    public static void write(String xml,String url) {
        File file = new File(url);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        Path rf = Paths.get(url);
        try (BufferedWriter wr = Files.newBufferedWriter(rf, Charset.forName("UTF-8"))){
            wr.write(xml);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("write message file error!");
        }
    }

    public static String loadFile(String fileUrl) {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader in = Files.newBufferedReader(Paths.get(fileUrl));
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("write message file error!");
        }
        return buffer.toString();
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     */
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (File file : srcFiles){
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(file);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
