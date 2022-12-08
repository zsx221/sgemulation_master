package com.macrochina.net.task;

import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.util.HttpClientUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class HeartbeatTask {
    @Autowired
    FpsParamRepository fpsParamRepository;
    private static Log logger = LogFactory.getLog(HeartbeatTask.class);
    @Scheduled(cron = "0 0 0/1 * * ?")
    //或直接指定时间间隔，例如：5秒
  //  @Scheduled(fixedRate=5000)
    private void paynowV1HealthTask() throws JAXBException {
        logger.info("HeartbeatTask paynowV1HealthTask start");
        String ip = fpsParamRepository.findById(1).get().getPaynowHost();
        String url = "http://localhost:8080/sgfps/paynow/v1/health/heartbeat";
        HttpClientUtils.doGet(ip+"/paynow/v1/health/heartbeat");
        logger.info("HeartbeatTask paynowV1HealthTask end");
    }


    @Scheduled(cron = "0 0 0/1 * * ?")
    //或直接指定时间间隔，例如：5秒
   // @Scheduled(fixedRate=5000)
    private void fastV1HealthTask() throws JAXBException {
        logger.info("HeartbeatTask fastV1HealthTask start");
        String ip = fpsParamRepository.findById(1).get().getHostAdr();
        String url = "http://localhost:8080/sgfps/fast/v1/health/heartbeat";
        HttpClientUtils.doGet(ip+"/fast/v1/health/heartbeat");
        logger.info("HeartbeatTask fastV1HealthTask end");
    }
}
