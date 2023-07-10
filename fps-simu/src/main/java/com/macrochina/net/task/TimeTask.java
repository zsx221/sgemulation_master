package com.macrochina.net.task;

/**
 * @author xhd
 * @date 2023/3/14
 */

import com.macrochina.net.service.TimeTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component //作用：扫描是注入spring IOC 交给spring 去管理
public class TimeTask {
    @Value("${spring.profiles.active}")
    private String envFlag;
    // 注入日志对象  TimeTask.class 为定时任务类
    private static final Logger logger = LoggerFactory.getLogger(TimeTask.class);

    // 注入sysOperLogService ：实现操作的对象（接口）
    @Autowired
    private TimeTaskService taskService;

    @PostConstruct
    @Scheduled(cron = "0 15 10 15 * ?") // 每月15日上午10:15触发
//    @Scheduled(cron = "0 * * * * ?") // 每1分钟触发一次
    public void clearDataByDesgin() {
        if (envFlag.equals("aaa")) {
            //日志
            logger.info("---------您好，清理transactions定时任务开始执行！---------" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            //调用删除数据库数据方法
            clearData();
            logger.info("---------真不错，定时任务清理transactions执行成功！---------" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    //删除数据库数据方法
    private void clearData() {
        try {
            //调用service层的方法 清理数据库数据
            taskService.removeAll();
        } catch (Exception e) {
            logger.error("清理数据失败，失败原因：" + e.getMessage());
        }
    }
}

