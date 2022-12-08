package com.macrochina.net.jaxb;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

import java.util.HashMap;
import java.util.Map;

public class XmlNsprefixFilter extends XMLFilterImpl {
	private static final String EMPTY_STRING = "";
	private String nsprefix = null;
	private String convertNs = null;
	private String paymentNs=null;
	Map map = new HashMap<>();

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
//		System.out.println("uri= "+uri+"  localName= "+localName+"  qName= "+qName+"  atts= "+atts);
		String prefix = getPrefix(qName);
		if (nsprefix == null) {
			nsprefix = prefix;

			String ns=XmlNsConstnt.map.get(localName);
			if(ns!=null){
				convertNs=ns==""?null:ns;
				StringBuilder sbuilder = new StringBuilder(80);
				sbuilder.append(localName);
				qName = sbuilder.toString();


			}

		} else {
			String ns=XmlNsConstnt.map.get(localName);
			StringBuilder sb = new StringBuilder(30);
			if(qName.equals("head:Fr") || qName.equals("head:To") || qName.equals("head:BizMsgIdr") || qName.equals("head:MsgDefIdr") || qName.equals("head:CreDt") || qName.equals("head:Sgntr")){
				sb.append(qName).append(" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
				qName = sb.toString();
			}
			if(ns!=null){
				convertNs=ns==""?null:ns;
				StringBuilder sbuilder = new StringBuilder(80);
				if(convertNs!=null){
					String tag = (String) map.get("tag");
					if(StringUtils.isNotBlank(tag)){
						sbuilder.append(paymentNs+":").append(localName);
					}else {
						extracted(qName, sbuilder, tag);
					}
				}else{
					sbuilder.append(localName);
				}
				qName = sbuilder.toString();
			} else{
				if(convertNs!=null){
					StringBuilder sbuilder = new StringBuilder(30);
					sbuilder.append(paymentNs + ":").append(localName);
					qName = sbuilder.toString();
				}
				/*if (((nsprefix.equals("ns2")&&!prefix.equals("ns3"))||prefix.equals(""))) {
					StringBuilder sbuilder = new StringBuilder(30);
					sbuilder.append("head:").append(localName);
					qName = sbuilder.toString();
				}else if((nsprefix.equals("")&&prefix.equals("ns2"))){
					StringBuilder sbuilder = new StringBuilder(80);
					sbuilder.append(localName);
					qName = sbuilder.toString();
				} else if(nsprefix.equals("ns3")||(nsprefix.equals("ns2")&&prefix.equals("ns3"))){
					StringBuilder sbuilder = new StringBuilder(30);
					sbuilder.append("ct:").append(localName);
					qName = sbuilder.toString();
				}*/
			}
			nsprefix = prefix;

		}




			/*if (prefix != EMPTY_STRING && !nsprefix.equals(prefix)) {
				
				if (!PREFIX_AH.equals(prefix)) {
					StringBuilder sbuilder = new StringBuilder(80);
					sbuilder.append("head:").append(localName).append(" xmlns:").append("head=\"").append(uri).append("\"");
					qName = sbuilder.toString();
				}else {
					StringBuilder sbuilder = new StringBuilder(80);
					sbuilder.append(qName).append(" xmlns:").append(prefix).append("=\"").append(uri).append("\"");
					qName = sbuilder.toString();
				}
				//nsprefix = prefix;
			}
			if (nsprefix.equals(prefix)&& !PREFIX_AH.equals(prefix)) {
				if(localName.equals("AppHdr")){
					StringBuilder sbuilder = new StringBuilder(80);
					sbuilder.append(localName);
					qName = sbuilder.toString();
				}else{
					StringBuilder sbuilder = new StringBuilder(30);
					sbuilder.append("head:").append(localName);
					qName = sbuilder.toString();
				}
			}
			
			if (prefix == EMPTY_STRING && !nsprefix.equals(prefix)&& PREFIX_AH.equals(prefix)) {
				StringBuilder sbuilder = new StringBuilder(80);
				sbuilder.append(qName).append(" xmlns").append("=\"").append(uri).append("\"");
				qName = sbuilder.toString();
				
			}
			nsprefix = prefix;
		}*/
		super.startElement(uri, localName, qName, atts);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		String prefix = getPrefix(qName);

		/*if (prefix != EMPTY_STRING && nsprefix.equals(prefix) && !PREFIX_AH.equals(prefix) && !PREFIX_FPS.equals(prefix)) {
			StringBuilder sbuilder = new StringBuilder(30);
			sbuilder.append("head:").append(localName);
			qName = sbuilder.toString();
		}*/
		String ns=XmlNsConstnt.map.get(localName);
		if(ns!=null){
			convertNs=ns==""?null:ns;
			StringBuilder sbuilder = new StringBuilder(80);
			if(convertNs!=null){
				sbuilder.append(paymentNs+":").append(localName);
			}else{
				sbuilder.append(localName);
			}
			qName = sbuilder.toString();
		} else {
			if (convertNs != null) {
				StringBuilder sbuilder = new StringBuilder(30);
				sbuilder.append(paymentNs + ":").append(localName);
				qName = sbuilder.toString();
			}
		}

		/*if(localName.equals("AppHdr")){
			StringBuilder sbuilder = new StringBuilder(80);
			sbuilder.append(localName);
			qName = sbuilder.toString();
		}else if(uri.equals("http://www.w3.org/2000/09/xmldsig#")){
			StringBuilder sbuilder = new StringBuilder(80);
			sbuilder.append(localName);
			qName = sbuilder.toString();
		} else{
			if (((nsprefix.equals("ns2")&&!prefix.equals("ns3"))||prefix.equals(""))) {
				StringBuilder sbuilder = new StringBuilder(30);
				sbuilder.append("head:").append(localName);
				qName = sbuilder.toString();
			}else if((nsprefix.equals("")&&prefix.equals("ns2"))){
				StringBuilder sbuilder = new StringBuilder(80);
				sbuilder.append(localName);
				qName = sbuilder.toString();
			}else if(nsprefix.equals("ns3")&&prefix.equals("ns2")){
				StringBuilder sbuilder = new StringBuilder(80);
				sbuilder.append(localName);
				qName = sbuilder.toString();
			}else if((nsprefix.equals("ns3")&&!prefix.equals("ns2"))||(nsprefix.equals("ns2")&&prefix.equals("ns3"))){
				StringBuilder sbuilder = new StringBuilder(30);
				sbuilder.append("ct:").append(localName);
				qName = sbuilder.toString();
			}
		}*/

		super.endElement(uri, localName, qName);
	}

	@Override
	public void startPrefixMapping(String prefix, String url) throws SAXException {
		//if (PREFIX_XSI.equals(prefix) || PREFIX_FPS.equals(prefix)) {
//			System.out.println("url:::"+url+"---------prefix:"+prefix);
			if(url.equals("urn:bcsis")||prefix.equals("xsi")||prefix.equals("head")||prefix.equals("")||prefix.equals(paymentNs)){
				super.startPrefixMapping(prefix, url);
			}else{
				//XmlNsConstnt.prefix.get(prefix)!=null
				//super.startPrefixMapping(prefix, url);
			}


	}

	public void startPrefixMappingExtend(String paymentNs)  {
		if(!Strings.isEmpty(paymentNs)){
			this.paymentNs=paymentNs;
		}
	}
	
	private String getPrefix(String qname) {
        int idx = qname.indexOf(':');
        String prefix = (idx == -1) ? EMPTY_STRING : qname.substring(0, idx);
        return prefix;
    }

	private void extracted(String qName, StringBuilder sbuilder, String tag) {
		if(StringUtils.isBlank(tag)){
			if(qName.equals("ct:FIToFICstmrCdtTrf")){
				sbuilder.append(qName).append(" xmlns:ct=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02\"");
			}
			if(qName.equals("ps:FIToFIPmtStsRpt")){
				sbuilder.append(qName).append(" xmlns:ps=\"urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03\"");
			}
			if(qName.equals("p2:PrxyRegnRspn")){
				sbuilder.append(qName).append(" xmlns:p2=\"urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01\"");
			}
			if(qName.equals("p4:PrxyLookUpRspn")){
				sbuilder.append(qName).append(" xmlns:p4=\"urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01\"");
			}
			if(qName.equals("p6:PrxyNqryRspn")){
				sbuilder.append(qName).append(" xmlns:p6=\"urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01\"");
			}
			if(qName.equals("p8:PtcptRptRspn")){
				sbuilder.append(qName).append(" xmlns:p8=\"urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01\"");
			}

			if(qName.equals("pc:FIToFIPmtCxlReq")){
				sbuilder.append(qName).append(" xmlns:pc=\"urn:iso:std:iso:20022:tech:xsd:camt.056.001.01\"");
			}
			if(qName.equals("rs:AdmnSignOnResp")){
				sbuilder.append(qName).append(" xmlns:rs=\"urn:iso:std:iso:20022:tech:xsd:admn.002.001.01\"");
			}
			if(qName.equals("rf:AdmnSignOffResp")){
				sbuilder.append(qName).append(" xmlns:rf=\"urn:iso:std:iso:20022:tech:xsd:admn.004.001.01\"");
			}
			if(qName.equals("er:AdmnEchoReq")){
				sbuilder.append(qName).append(" xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\"");
			}
			if(qName.equals("re:AdmnEchoResp")){
				sbuilder.append(qName).append(" xmlns:re=\"urn:iso:std:iso:20022:tech:xsd:admn.006.001.01\"");
			}

			if(qName.equals("mr:admi.002.001.01")){
				sbuilder.append(qName).append(" xmlns:mr=\"urn:iso:std:iso:20022:tech:xsd:admi.002.001.01\"");
			}
			if(qName.equals("ne:admi.004.001.01")){
				sbuilder.append(qName).append(" xmlns:ne=\"urn:iso:std:iso:20022:tech:xsd:admi.004.001.01\"");
			}

			if(qName.equals("bp:BankStmtResp")){
				sbuilder.append(qName).append(" xmlns:bp=\"urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01\"");
			}
			if(qName.equals("bs:BkToCstmrStmt")){
				sbuilder.append(qName).append(" xmlns:bs=\"urn:iso:std:iso:20022:tech:xsd:camt.053.001.02\"");
			}

			if(qName.equals("bp:BankStmtResp")){
				sbuilder.append(qName).append(" xmlns:bp=\"urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01\"");
			}
			map.put("tag","tag");
		}
	}
}
