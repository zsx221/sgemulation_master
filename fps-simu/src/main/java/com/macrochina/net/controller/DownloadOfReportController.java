package com.macrochina.net.controller;

import com.macrochina.net.service.DownloadOfReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Api(tags = "fast")
@RestController
@CrossOrigin
@RequestMapping("/fast/v1")
public class DownloadOfReportController {//文件下载，是pdf还是zip
    @Autowired
    private DownloadOfReportService downloadOfReportService;
    @ApiOperation(value = "/report/download/{reportId}")
    @ResponseBody
    @GetMapping("/report/download/{reportId}")
    public void fastReport(@PathVariable String reportId, @RequestParam(name = "gen-date") String gen_date,
                           @RequestParam(name = "sttlmt-cycle") String sttlmt_cycle,
                           @RequestParam(name = "file-name", required = false) String file_name,
                           HttpServletRequest request, HttpServletResponse response) {
        File file = null;
        switch (reportId) {
            case "RTSGDSTM421":
                file = downloadOfReportService.get421(file_name,gen_date,sttlmt_cycle);
                break;
            case "RTSGDSTM453":
                file = downloadOfReportService.get453(gen_date,sttlmt_cycle);
                break;
            case "RTSGDSTM454":
                file = downloadOfReportService.get454(gen_date,sttlmt_cycle);
                break;
            case "RTSGDsta492":
                file = downloadOfReportService.get492(gen_date,sttlmt_cycle);
                break;
        }
        byte[] bytes = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int length = (int) file.length();
        bytes = new byte[length];
        int offset = 0;
        int numRead = 0;
        while (true) {
            try {
                if (!(offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            offset += numRead;
        }

                response.setContentType("application/binary;charset=UTF-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));

            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
