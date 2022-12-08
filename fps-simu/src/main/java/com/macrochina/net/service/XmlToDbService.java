package com.macrochina.net.service;


import com.macrochina.net.dao.XmlToDbRepository;
import com.macrochina.net.entity.SysParams;
import com.macrochina.net.entity.XmlToDb;
//import com.macrochina.net.iso.pacs002.OriginalGroupInformation20;
import com.macrochina.net.utils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class XmlToDbService {
    private static Log logger = LogFactory.getLog(XmlToDbService.class);
    @Autowired
    private XmlToDbRepository xmlToDbRepository;

    /**
     * 根据srcClassName查询XmlToDb
     *
     * @param srcClassName
     * @return
     */
    public List<XmlToDb> findBySrcClassName(String srcClassName) {
        List<XmlToDb> rtn = null;
        if (StringUtils.isEmpty(srcClassName)) {
            return rtn;
        }
        rtn = this.xmlToDbRepository.findBySrcClassName(srcClassName);
        if (rtn == null) {
            logger.error("查询错误!srcClassName:" + srcClassName);
        }
        return rtn;
    }

    /**
     *
     * INSERT INTO `xmltodb`(`id`, `srcClassName`, `srcField`, `targetClassField`, `targetClassName`) VALUES (1, 'com.macrochina.net.common.iso.pacs002.Document', 'fiToFIPmtStsRpt.grpHdr.msgId', 'sysName', 'com.itstudy.itgo.adminview.entity.SysParams');
     * INSERT INTO `xmltodb`(`id`, `srcClassName`, `srcField`, `targetClassField`, `targetClassName`) VALUES (2, 'com.macrochina.net.common.iso.pacs002.Document', 'fiToFIPmtStsRpt.orgnlGrpInfAndSts.orgnlMsgId', 'sysCode', 'com.itstudy.itgo.adminview.entity.SysParams');
     * @param xmlEntity 从xml解析来的entity对象
     * @param dbEntity  将要入库的entity对象
     * @return
     */
    public Object xmlToDb(Object xmlEntity, Object dbEntity) {
        Object res = dbEntity;
        if (xmlEntity == null || dbEntity == null) {
            logger.error("xmlEntity or dbEntity is null.");
            return null;
        }
        logger.info("find XmlToDb with " + xmlEntity.getClass().getName());
        List<XmlToDb> list = this.findBySrcClassName(xmlEntity.getClass().getName());
        if (list == null || list.size() < 1) {
            logger.info("find XmlToDb no data. ");
            return null;
        }

        for (XmlToDb xtd : list) {
            String srcClassName = xtd.getSrcClassName();
            String srcField = xtd.getSrcField();
            String targetClassName = xtd.getTargetClassName();
            String targetClassField = xtd.getTargetClassField();
            if (!xtd.getSrcClassName().equals(xmlEntity.getClass().getName())) {
                logger.warn("xtd id:" + xtd.getId() + ",SrcClassName is not equals xmlEntity class!");
                continue;
            }
            if (!xtd.getTargetClassName().equals(dbEntity.getClass().getName())) {
                logger.warn("xtd id:" + xtd.getId() + ",xtd.getTargetClassName() config error!");
                continue;
            }

            if (srcField.indexOf(".") > 0) {
                Object srcFieldValue = getReflectValue(xmlEntity, srcField);
                if(srcFieldValue!=null){
                    BeanUtils.setObjFieldValue(dbEntity, targetClassField, srcFieldValue);
                }
            } else {
                logger.warn("xtd id" + xtd.getId() + "," + srcField + "配置错误.");
                continue;
            }

            logger.info(dbEntity);

        }

        return res;
    }


    public static Object getReflectValue(Object xmlEntity, String srcClassName) {
        //String[] srcs = srcClassName.split(".");
        logger.info("srcClassName="+srcClassName);
        if(xmlEntity==null){
            return null;
        }
        int index = srcClassName.indexOf(".");
        if(index<1){
            //已经到具体的属性了
            Object obj = BeanUtils.getObjFieldValue(xmlEntity, srcClassName);
            logger.info("match final field:"+srcClassName+",value:"+obj+">>>>>>>>>>>>>>>>>>>>>");
            return obj;
        }
        String srcField = srcClassName.substring(0, index);
        String endField = srcClassName.substring(index, srcClassName.length());
        logger.info("srcField="+srcField+",endField="+endField);
        Object obj = BeanUtils.getObjFieldValue(xmlEntity, srcField);
        Object res=getReflectValue(obj, endField.substring(1,endField.length()));
        return res;
    }

    @Async("asyncTaskExecutor")
    public void aSyncProcess(){
        try{
            logger.info(Thread.currentThread().getName()+"开始处理..");
            Thread.sleep(Long.valueOf(5*1000));
            /*CustomerInfo ci=new CustomerInfo();
            ci.setCstName("lxk");
            ci.setCstPhone("13613086757");
            ci.setCstSex("0");
            ci.setCstWxId("13613086757");
            customerInfoService.addCustomerInfo(ci);*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }


   /* public static void main(String[] args) {

        //fpsevnp.setPaymentStatus(document);
        com.macrochina.net.iso.pacs002.Document document = new com.macrochina.net.iso.pacs002.Document();
        com.macrochina.net.iso.pacs002.FIToFIPaymentStatusReportV03 fiToFIPmtStsRpt = new com.macrochina.net.iso.pacs002.FIToFIPaymentStatusReportV03();
        com.macrochina.net.iso.pacs002.GroupHeader37 grpHdr = new com.macrochina.net.iso.pacs002.GroupHeader37();
        grpHdr.setMsgId("sdsdsdds");
//		BranchAndFinancialInstitutionIdentification4 instdAgt = new BranchAndFinancialInstitutionIdentification4();
//		instdAgt.setFinInstnId();
//		grpHdr.setInstdAgt(instdAgt);
        //fiToFIPmtStsRpt.setGrpHdr(grpHdr);
        OriginalGroupInformation20 orgnlGrpInfAndSts=new OriginalGroupInformation20();
        orgnlGrpInfAndSts.setOrgnlMsgId("111111111111");
        fiToFIPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);
        document.setFIToFIPmtStsRpt(fiToFIPmtStsRpt);

        SysParams sp=new SysParams();
        List<XmlToDb> list=new ArrayList<>();
        XmlToDb a=new XmlToDb();
        a.setId(1l);
        a.setSrcClassName("com.macrochina.net.common.iso.pacs002.Document");
        a.setSrcField("fiToFIPmtStsRpt.grpHdr.msgId");
        a.setTargetClassName("com.itstudy.itgo.adminview.entity.SysParams");
        a.setTargetClassField("sysName");

        XmlToDb b=new XmlToDb();
        b.setId(1l);
        b.setSrcClassName("com.macrochina.net.common.iso.pacs002.Document");
        b.setSrcField("fiToFIPmtStsRpt.orgnlGrpInfAndSts.orgnlMsgId");
        b.setTargetClassName("com.itstudy.itgo.adminview.entity.SysParams");
        b.setTargetClassField("sysCode");
        list.add(a);
        list.add(b);

        Object xmlEntity=document;
        Object dbEntity=sp;

        logger.info("=========================start==========================");
        logger.info(((SysParams)dbEntity).getSysName());
        logger.info(((SysParams)dbEntity).getSysCode());

        for (XmlToDb xtd : list) {
            String srcClassName = xtd.getSrcClassName();
            String srcField = xtd.getSrcField();
            String targetClassName = xtd.getTargetClassName();
            String targetClassField = xtd.getTargetClassField();
            if (!xtd.getSrcClassName().equals(xmlEntity.getClass().getName())) {
                logger.warn("xtd id:" + xtd.getId() + ",SrcClassName is not equals xmlEntity class!");
                continue;
            }
            if (!xtd.getTargetClassName().equals(dbEntity.getClass().getName())) {
                logger.warn("xtd id:" + xtd.getId() + ",xtd.getTargetClassName() config error!");
                continue;
            }

            if (srcField.indexOf(".") > 0) {
                Object srcFieldValue = getReflectValue(xmlEntity, srcField);
                logger.info("match final value:"+srcFieldValue);
                if(srcFieldValue!=null){
                    BeanUtils.setObjFieldValue(dbEntity, targetClassField, srcFieldValue);
                }
            } else {
                logger.warn("xtd id" + xtd.getId() + "," + srcField + "配置错误.");
                continue;
            }
        }
        logger.info("=========================end==========================");
        logger.info(((SysParams)dbEntity).getSysName());
        logger.info(((SysParams)dbEntity).getSysCode());
    }*/

}
