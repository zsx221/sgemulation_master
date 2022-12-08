package com.macrochina.net.service;

import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.dao.MessagePacsRepository;
import com.macrochina.net.dao.MessageRepository;
import com.macrochina.net.dto.Admi002Dto;
import com.macrochina.net.dto.MessageDto;
import com.macrochina.net.entity.Message;
import com.macrochina.net.util.MessageUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class MessageService {
    @Autowired
    DbToXmlService dbToXmlService;

    @Autowired
    XmlSignService xmlSignService;

    @Autowired
    SchemaService schemaService;

    @Autowired
    MessagePacsService messagePacsService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    FpsParamRepository fpsParamRepository;

    @Autowired
    FpsParamService fpsParamService;

    @Autowired
    Admi002Service admi002Service;

    public void syntaxErrors(String msg, adrs.HdrAndData fpsMsg) {
        Admi002Dto dto = new Admi002Dto();
        dto.setRef(MessageUtils.getCTxnId(fpsParamService.findById().getSendBank()));
        dto.setRjctgPtyRsn("650");
        dto.setAddtlData(msg);
        admi002Service.receive(dto,"Paynow",fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI() ,fpsMsg.getAppHdr().getFr().getFIId().getFinInstnId().getBICFI());

    }

    public void signErrors(String msg,adrs.HdrAndData fpsMsg) {
        Admi002Dto dto = new Admi002Dto();
        dto.setRef(fpsMsg.getAppHdr().getBizMsgIdr());
        dto.setRjctgPtyRsn("690");
        dto.setAddtlData(msg);
        admi002Service.receive(dto,"Paynow",fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI() ,fpsMsg.getAppHdr().getFr().getFIId().getFinInstnId().getBICFI());
    }

    public void save(Message message) {
        messageRepository.save(message);
    }


    public Page<Message> findAllMessageByCdn(Integer page, Integer size, MessageDto molde) {
        Page<Message> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<Message> messages = new Specification<Message>() {

            @SneakyThrows
            @Override
            public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (!org.springframework.util.StringUtils.isEmpty(molde.getMsg_def())){
                    Predicate predicate = null;
                    Path<String> msgDef = root.get("msgDef");
                    predicate = criteriaBuilder.like(msgDef,"%" + molde.getMsg_def()+"%" );
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(molde.getDirct())){
                    Predicate predicate = null;
                    Path<String> dirct = root.get("dirct");
                    predicate = criteriaBuilder.equal(dirct,molde.getDirct());
                    predicates.add(predicate);
                }if (!org.springframework.util.StringUtils.isEmpty(molde.getCreated_date())){
                    Predicate predicate = null;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                    Date date = simpleDateFormat.parse(molde.getCreated_date());
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    Date enddate = c.getTime();
                    Path<Date> createdDate = root.get("createdDate");
                    predicate = criteriaBuilder.between(createdDate,date,enddate );
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(molde.getState())){
                    Predicate predicate = null;
                    Path<String> state = root.get("state");
                    predicate = criteriaBuilder.equal(state,molde.getState());
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(molde.getTo_instn())){
                    Predicate predicate = null;
                    Path<String> toInstn = root.get("toInstn");
                    predicate = criteriaBuilder.like(toInstn,"%" + molde.getTo_instn()+"%" );
                    predicates.add(predicate);
                }
                if(predicates!=null){
                    criteriaQuery.where(predicates.toArray(new Predicate[0]));
                }

                return criteriaQuery.getRestriction();
            }

        };

        rtn = messageRepository.findAll(messages, pageable);

        return rtn;
    }

    public Optional<Message> findById(int id) {
        return messageRepository.findById(id);
    }


    public void deleteMessage(String ids){
        List<Message> messageList = new ArrayList<Message>();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(ids)) {
            for(String id : ids.split(",")) {
                Message message = new Message();
                message.setId(Integer.valueOf(id));
                messageList.add(message);
            }
        }
        this.messageRepository.deleteInBatch(messageList);
    }

}
