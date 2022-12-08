package com.macrochina.net.service;

import com.macrochina.net.util.FileUtils;
import com.macrochina.net.util.XmlIsoDateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DownloadOfReportService {
    private final  String url453S01 = FileUtils.REPORT_SOURCE + "RTSGDstm453/ControlFile/SGDCTLSTMYYYYMMDDTRBUSG50S01/";
    private final  String url453S02 = FileUtils.REPORT_SOURCE + "RTSGDstm453/ControlFile/SGDCTLSTMYYYYMMDDTRBUSG50S02/";

    private final  String url421S01 =  FileUtils.REPORT_SOURCE + "RTSGDstm421/SGDYYYYMMDDTRBUSG50NRT01/";
    private final  String url421S02 =  FileUtils.REPORT_SOURCE + "RTSGDstm421/SGDYYYYMMDDTRBUSG50NRT02/";

    private final  String url454S01 =  FileUtils.REPORT_SOURCE + "RTSGDstm454/SGDYYYYMMDDTRBUSG50HSTM454S01/";
    private final  String url454S02 =  FileUtils.REPORT_SOURCE + "RTSGDstm454/SGDYYYYMMDDTRBUSG50HSTM454S02/";

    private final  String url492S01 =  FileUtils.REPORT_SOURCE + "RTSGDsta492/SGDSLA20210927TRBUSG50S01/";

    /**
     * @Author fangyu
     * @Description //453接口
     * @Date 2021/10/2 22:47
     * @Param []
     * @return java.io.File
     **/
    public File get453(String dateYYYYMMdd, String sttlmt_cycle){
        String url = "";
        File dir = null;
        if("01".equals(sttlmt_cycle)){
            url = url453S01;
            dir = new File(url453S01);
        }else {
            url = url453S02;
            dir = new File(url453S02);
        }
        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
            return null;
        }

        String targetfileName = ""; //copy之后的文件名
        String zipFileName = "";
        String fileNameOld = ""; //copy之前的文件名,用于打包
        File sigFile = null; //copy之后的sig
        File datFile = null; //copy之后的dat
        String org = "SGDSTMYYYYMMDDTRBUSG50HYYYYYYYYS";
        String [] files = dir.list();// 读取目录下的所有目录文件信息
        for(String fileName : files){
            System.out.println(fileName);
            int i = fileName.indexOf(".DAT");
            if(i > 0){ //判断是否后缀为.DAT
                fileNameOld = fileName.substring(0,fileName.length()-4);
                //
                targetfileName = fileNameOld.substring(0,9)+dateYYYYMMdd+fileNameOld.substring(17);

                zipFileName =org.replace("YYYYMMDD",dateYYYYMMdd).replace("YYYYYYYY",RandomStringUtils.randomNumeric(8))+sttlmt_cycle;
            }else{
                try {
                    targetfileName = fileNameOld.substring(0,9)+dateYYYYMMdd+fileNameOld.substring(17);
                    zipFileName =org.replace("YYYYMMDD",dateYYYYMMdd).replace("YYYYYYYY",RandomStringUtils.randomNumeric(8))+sttlmt_cycle;
                    sigFile = new File(FileUtils.REPORT_TARGET +"/" +  targetfileName+ ".SIG");
                    FileCopyUtils.copy(new File(dir.getPath() +"/" + fileNameOld + ".SIG"), sigFile );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String datUrl =  url +fileNameOld+".DAT";
        String datSrcFile = FileUtils.loadFile(datUrl);
        Matcher m = Pattern.compile("<head:BizMsgIdr>.*</head:BizMsgIdr>")
                .matcher(datSrcFile);
        m.find();
        String oldMsgId = m.group().replace("</head:BizMsgIdr>", "").replace("<head:BizMsgIdr>", "");
        String msgId = oldMsgId.substring(0, 1) + dateYYYYMMdd + oldMsgId.substring(9, 20) + RandomStringUtils.randomNumeric(7);
        String data = datSrcFile.replaceAll("<head:BizMsgIdr>.*</head:BizMsgIdr>", "<head:BizMsgIdr>" + msgId + "</head:BizMsgIdr>")
                .replaceAll("<head:CreDt>.{20}</head:CreDt>","<head:CreDt>"
                        + XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())+"</head:CreDt>")
                .replaceAll("<cf:MsgId>.*</cf:MsgId>","<cf:MsgId>"+msgId+"</cf:MsgId>")
                .replaceAll("<cf:CreDtTm>.*</cf:CreDtTm>","<cf:CreDtTm>"+XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance())+"</cf:CreDtTm>")
                .replaceAll("<cf:FileNm>.*</cf:FileNm>","<cf:FileNm>"+zipFileName+".ZIP"+"</cf:FileNm>");
        try {
            datFile = new File(FileUtils.REPORT_TARGET +"/" + fileNameOld + ".DAT");
            FileCopyUtils.copy(data.getBytes("UTF-8"),datFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> fileList = new ArrayList<>();
        fileList.add(datFile);
        fileList.add(sigFile);
        File file = new File(FileUtils.REPORT_TARGET +"/" + zipFileName + ".ZIP");//下载zip格式
        FileUtils.zipFiles(fileList,file);
        return  file;
    }

    public File get421(String file_name ,String dateYYYYMMdd, String sttlmt_cycle){
        File zipFile = null;
        File dir = null;
        String url = "";
        String reportType = "" ; //sum403 或者stm421
        if("01".equals(sttlmt_cycle)){
            url = url421S01;
            dir = new File(url);
        }else {
            url = url421S02;
            dir = new File(url);
        }

       if(StringUtils.isEmpty(file_name)){
           reportType = "sum403";
        }else{
           reportType = "stm421";
        }
        String fileNameOld = "";
        String targetfileName = "";
        File pdfFile = null ;
        String [] files = dir.list();// 读取目录下的所有目录文件信息
        for(String fileName : files){
            int i = fileName.indexOf(reportType);
            if(i > 0 ){ //判断是否为全量报文
                targetfileName = fileName.substring(0,fileName.length()-12)+dateYYYYMMdd;
                pdfFile = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".pdf");//将拼接好的名字赋给file,并重命名为pdf格式
                try {
                    FileCopyUtils.copy(new File(dir.getPath() +"/" + fileName), pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        List<File> fileList = new ArrayList<>();
        fileList.add(pdfFile);
        zipFile = new File(FileUtils.REPORT_TARGET +"/" + url.substring(url.length()-25,url.length()-1).replace("YYYYMMDD",dateYYYYMMdd) + ".ZIP");
        FileUtils.zipFiles(fileList,zipFile);
        return zipFile;
    }
    public File get454(String dateYYYYMMdd, String sttlmt_cycle){
        String url = "";
        File dir = null;
        System.out.println(dateYYYYMMdd+sttlmt_cycle);
        if("01".equals(sttlmt_cycle)){
            url = url454S01;
            dir = new File(url454S01);
        }else {
            url = url454S02;
            dir = new File(url454S02);
        }
        System.out.println("---------------"+url);
        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录或者判断给定的字符串是否是目录
            return null;
        }

        String targetfileName = ""; //copy之后的文件名,用于打包
        String fileNameOld = ""; //copy之前的文件名,用于打包
        File sigFile = null; //copy之后的sig
        File datFile = null; //copy之后的dat
        String [] files = dir.list();// 读取目录下的所有目录文件信息
        for(String fileName : files){
            int i = fileName.indexOf(".DAT");
            if(i > 0){ //判断是否后缀为.DAT
                fileNameOld = fileName.substring(0,fileName.length()-4);
                targetfileName = fileNameOld.substring(0,3)+dateYYYYMMdd+fileNameOld.substring(12);
            }else{
                try {
                    sigFile = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".SIG");
                    FileCopyUtils.copy(new File(dir.getPath() +"/" + fileNameOld + ".SIG"), sigFile );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String datUrl =  url +fileNameOld+".DAT";
        String datSrcFile = FileUtils.loadFile(datUrl);
        Matcher m = Pattern.compile("<head:BizMsgIdr>.*</head:BizMsgIdr>")
                .matcher(datSrcFile);
        m.find();
       // String oldMsgId = m.group().replace("</head:BizMsgIdr>", "").replace("<head:BizMsgIdr>", "");
        String msgId = "AA"+ RandomStringUtils.randomNumeric(7);
        String data = datSrcFile.replaceAll("<head:BizMsgIdr>.*</head:BizMsgIdr>", "<head:BizMsgIdr>" + msgId + "</head:BizMsgIdr>")
                .replaceAll("<head:CreDt>.{20}</head:CreDt>","<head:CreDt>"
                        + XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())+"</head:CreDt>")
                .replaceAll("<cf:MsgId>.*</cf:MsgId>","<cf:MsgId>"+msgId+"</cf:MsgId>")
                .replaceAll("<cf:CreDtTm>.*</cf:CreDtTm>","<cf:CreDtTm>"+XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance())+"</cf:CreDtTm>");

        try {
            datFile = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".DAT");
            FileCopyUtils.copy(data.getBytes("UTF-8"),datFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(datFile.getPath()));
        fileList.add(new File(sigFile.getPath()));
        File file = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".ZIP");
        FileUtils.zipFiles(fileList,file);
        return  file;
    }

    public File get492(String dateYYYYMMdd, String sttlmt_cycle){
        String url = url492S01;
        File dir = new File(url492S01);

        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
            return null;
        }

        String targetfileName = ""; //copy之后的文件名,用于打包
        String fileNameOld = ""; //copy之前的文件名,用于打包
        File sigFile = null; //copy之后的sig
        File datFile = null; //copy之后的dat
        String [] files = dir.list();// 读取目录下的所有目录文件信息
        for(String fileName : files){
            int i = fileName.indexOf(".DAT");
            if(i > 0){ //判断是否后缀为.DAT
                fileNameOld = fileName.substring(0,fileName.length()-4);
                targetfileName = fileNameOld.substring(0,6)+dateYYYYMMdd+fileNameOld.substring(14);
            }else{
                try {
                    sigFile = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".SIG");
                    FileCopyUtils.copy(new File(dir.getPath() +"/" + fileNameOld + ".SIG"), sigFile );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String datUrl =  url +fileNameOld+".DAT";
        String datSrcFile = FileUtils.loadFile(datUrl);
        Matcher m = Pattern.compile("<head:BizMsgIdr>.*</head:BizMsgIdr>")
                .matcher(datSrcFile);
        m.find();
        String msgId = "AA"+ RandomStringUtils.randomNumeric(7);
        String data = datSrcFile.replaceAll("<head:BizMsgIdr>.*</head:BizMsgIdr>", "<head:BizMsgIdr>" + msgId + "</head:BizMsgIdr>")
                .replaceAll("<head:CreDt>.{20}</head:CreDt>","<head:CreDt>"
                        + XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())+"</head:CreDt>")
                .replaceAll("<cf:MsgId>.*</cf:MsgId>","<cf:MsgId>"+msgId+"</cf:MsgId>")
                .replaceAll("<cf:CreDtTm>.*</cf:CreDtTm>","<cf:CreDtTm>"+XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance())+"</cf:CreDtTm>");

        try {
            datFile = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".DAT");
            FileCopyUtils.copy(data.getBytes("UTF-8"),datFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(datFile.getPath()));
        fileList.add(new File(sigFile.getPath()));
        File file = new File(FileUtils.REPORT_TARGET +"/" + targetfileName + ".ZIP");
        FileUtils.zipFiles(fileList,file);
        return  file;
    }
}

