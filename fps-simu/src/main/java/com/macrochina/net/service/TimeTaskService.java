package com.macrochina.net.service;

import com.macrochina.net.dao.MessagePacsRepository;
import com.macrochina.net.dao.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xhd
 * @date 2023/3/14
 */
@Service
@Slf4j
public class TimeTaskService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessagePacsRepository messagePacsRepository;

    public void removeAll() {
        try {
            messageRepository.deleteAll();
            messagePacsRepository.deleteAll();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
