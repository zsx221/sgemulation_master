package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dao.DbToXmlRepository;
import com.macrochina.net.entity.DbToXml;
import com.macrochina.net.util.ConvertUtils;
import com.macrochina.net.util.XmlIsoDateUtil;
import com.macrochina.net.utils.BeanUtils;
import iso.head_001_001.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Service
public class DbToXmlService {
    private static Log logger = LogFactory.getLog(DbToXmlService.class);
    @Autowired
    private DbToXmlRepository dbToXmlRepository;

    /**
     * 根据targetClassName查询DbtoXml
     *
     * @param targetClassName
     * @return
     */
    public List<DbToXml> findByTargetClassName(String targetClassName) {
        List<DbToXml> rtn = null;
        if (StringUtils.isEmpty(targetClassName)) {
            return rtn;
        }
        rtn = this.dbToXmlRepository.findByTargetClassName(targetClassName);
        if (rtn == null) {
            logger.error("查询错误!targetClassName:" + targetClassName);
        }
        return rtn;
    }


    /**
     *
     * INSERT INTO `dbtoxml`(`id`, `srcClassName`, `srcField`, `targetClassField`, `targetClassName`) VALUES (1, 'java.util.HashMap', 'sysName', 'fiToFIPmtStsRpt.grpHdr.msgId', 'com.macrochina.net.common.iso.pacs002.Document');
     * INSERT INTO `dbtoxml`(`id`, `srcClassName`, `srcField`, `targetClassField`, `targetClassName`) VALUES (2, 'java.util.HashMap', 'sysCode', 'fiToFIPmtStsRpt.orgnlGrpInfAndSts.orgnlMsgId', 'com.macrochina.net.common.iso.pacs002.Document');
     *
     * @param map 程序中准备好的map数据，所有节点都必须对应到数据库中的字段
     * @param targetXmlEntity  将要组包的xml entity对象
     * @return
     */
    public Object DbToXml(Map map, Object targetXmlEntity) {
        Object res = targetXmlEntity;
        if (map == null || targetXmlEntity == null) {
            logger.error("targetXmlEntity or map is null.");
            return null;
        }
        logger.debug("find DbToXml with " + targetXmlEntity.getClass().getName());
        List<DbToXml> list = this.findByTargetClassName(targetXmlEntity.getClass().getName());
        if (list == null || list.size() < 1) {
            logger.debug("find DbToXml no data. ");
            return null;
        }

        for (DbToXml dtx : list) {
            if(!map.containsKey(dtx.getSrcField())){
                continue;
            }
            String srcClassName = dtx.getSrcClassName();
            String srcField = dtx.getSrcField();
            String targetClassName = dtx.getTargetClassName();
            String targetClassField = dtx.getTargetClassField();
            if (!srcClassName.equals(map.getClass().getName())) {
                logger.warn("dtx id:" + dtx.getId() + ",SrcClassName is not equals xmlEntity class!");
                continue;
            }
            if (!targetClassName.equals(targetXmlEntity.getClass().getName())) {
                logger.warn("dtx id:" + dtx.getId() + ",dtx.getTargetClassName() config error!");
                continue;
            }

            if (targetClassField.indexOf(".") > 0) {
                Map tMap=new HashMap();
                tMap.put("tObj",targetXmlEntity);
                tMap.put("tFieldName",targetClassField);
                tMap.put("tField",targetXmlEntity);
                getReflectValue(tMap, targetClassField);
                if(tMap!=null&&tMap.keySet().size()>0){
                    BeanUtils.setObjFieldValueByField(tMap.get("tObj"), tMap.get("tFieldName").toString(), map.get(srcField));
                }
            } else {
                logger.warn("dtx id" + dtx.getId() + "," + targetClassField + "配置错误.");
                continue;
            }
            logger.debug(targetXmlEntity);
        }

        return res;
    }


    public static void getReflectValue(Map xmlEntity, String srcClassName) {
        //String[] srcs = srcClassName.split(".");
        logger.debug("DbToXml srcClassName="+srcClassName);
        if(xmlEntity==null){
            return ;
        }
        int index = srcClassName.indexOf(".");
        if(index<1){
            //已经到具体的属性了
            BeanUtils.newObjField(xmlEntity, srcClassName);
            logger.debug("DbToXml match final field:"+srcClassName+",value:"+xmlEntity+">>>>>>>>>>>>>>>>>>>>>");
            return ;
        }
        String srcField = srcClassName.substring(0, index);
        String endField = srcClassName.substring(index, srcClassName.length());
        logger.debug("DbToXml srcField="+srcField+",endField="+endField);
        BeanUtils.newObjField(xmlEntity, srcField);
        getReflectValue(xmlEntity, endField.substring(1,endField.length()));
    }


    public static void main(String[] args)throws Exception {//把输入的数据转换成xml格式    200

        //fpsevnp.setPaymentStatus(document);
        iso.pacs_008_001.Document document = new iso.pacs_008_001.Document();

        List<DbToXml> list=new ArrayList<>();
        DbToXml a=new DbToXml();
        a.setId(1l);
        a.setSrcClassName("java.util.HashMap");
        a.setSrcField("sysName");
        a.setTargetClassName("iso.pacs_008_001.Document");
        a.setTargetClassField("fiToFICstmrCdtTrf.grpHdr.msgId");


        DbToXml b=new DbToXml();
        b.setId(2l);
        b.setSrcClassName("java.util.HashMap");
        b.setSrcField("sysCode");
        b.setTargetClassName("iso.pacs_008_001.Document");
        b.setTargetClassField("fiToFICstmrCdtTrf.grpHdr.btchBookg");

        DbToXml c=new DbToXml();
        c.setId(3l);
        c.setSrcClassName("java.util.HashMap");
        c.setSrcField("instrId");
        c.setTargetClassName("iso.pacs_008_001.Document");
        c.setTargetClassField("fiToFICstmrCdtTrf.cdtTrfTxInf.intrBkSttlmDt");

        DbToXml d=new DbToXml();
        d.setId(4l);
        d.setSrcClassName("java.util.HashMap");
        d.setSrcField("instrPrty");
        d.setTargetClassName("iso.pacs_008_001.Document");
        d.setTargetClassField("fiToFICstmrCdtTrf.cdtTrfTxInf.pmtTpInf.instrPrty");

        DbToXml e=new DbToXml();
        e.setId(5l);
        e.setSrcClassName("java.util.HashMap");
        e.setSrcField("instrInf");
        e.setTargetClassName("iso.pacs_008_001.Document");
        e.setTargetClassField("fiToFICstmrCdtTrf.cdtTrfTxInf.instrForCdtrAgt.instrInf");

        DbToXml f=new DbToXml();
        f.setId(5l);
        f.setSrcClassName("java.util.HashMap");
        f.setSrcField("cd");
        f.setTargetClassName("iso.pacs_008_001.Document");
        f.setTargetClassField("fiToFICstmrCdtTrf.cdtTrfTxInf.instrForCdtrAgt.cd");

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        Object targetXmlEntity=document;
        Map map=new HashMap();
        map.put("sysName","lllxxxxkkkk");
        map.put("sysCode",true);
        map.put("instrId", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("instrPrty","HIGH");
        map.put("instrInf","11111111111");
        map.put("cd","CHQB");


        for (DbToXml dtx : list) {
            String srcClassName = dtx.getSrcClassName();
            String srcField = dtx.getSrcField();
            String targetClassName = dtx.getTargetClassName();
            String targetClassField = dtx.getTargetClassField();
            if (!srcClassName.equals(map.getClass().getName())) {
                logger.debug(map.getClass().getName());
                logger.warn("dtx id:" + dtx.getId() + ",SrcClassName is not equals xmlEntity class!");
                continue;
            }
            if (!targetClassName.equals(targetXmlEntity.getClass().getName())) {
                logger.warn("dtx id:" + dtx.getId() + ",dtx.getTargetClassName() config error!");
                continue;
            }

            if (targetClassField.indexOf(".") > 0) {
                Map tMap=new HashMap();
                tMap.put("tObj",targetXmlEntity);
                tMap.put("tFieldName",targetClassField);
                tMap.put("tField",targetXmlEntity);
                getReflectValue(tMap, targetClassField);
                if(tMap!=null&&tMap.keySet().size()>0){
                    BeanUtils.setObjFieldValueByField(tMap.get("tObj"), tMap.get("tFieldName").toString(), map.get(srcField));
                }
            } else {
                logger.warn("dtx id" + dtx.getId() + "," + targetClassField + "配置错误.");
                continue;
            }

            logger.debug(targetXmlEntity);

        }

        HdrAndData fpsevnp = new HdrAndData();//之后开始往报文格式里面添加数据
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();//头部格式
        appHdr.setBizMsgIdr("msgdefidr00432");
        appHdr.setMsgDefIdr(G3MessageTypes.fromValue("admi.002.001.01"));
        appHdr.setCreDt(convertToXMLGregorianCalendar(Calendar.getInstance()));
        Party9Choice fr = new Party9Choice();
        fillHeadFrom(fr);
        fpsevnp.setAppHdr(appHdr);
        fpsevnp.setCreditTransfer(document);//把CreditTransfer里面的内容充满
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
        String xml = ConvertUtils.marshal(fpsevnp, true);//将HdrAndData转化为string对象
        System.out.println(xml);

    }
    private static void fillHeadFrom(Party9Choice fr) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        PartyIdentification42 orgId = new PartyIdentification42();
        Party10Choice id = new Party10Choice();
        OrganisationIdentification7 orgId1 = new OrganisationIdentification7();
        orgId1.setAnyBIC("ss");
        id.setOrgId(orgId1);
        orgId.setId(id);
        fr.setFIId(fiid);
    }
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Calendar  c) {
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                    c.get(Calendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }
}
