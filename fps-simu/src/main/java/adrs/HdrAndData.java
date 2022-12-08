
package adrs;

import address.head_001_001.BusinessApplicationHeaderV01;
import address.prxy_002_001.Document;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for HdrAndData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HdrAndData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AppHdr" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.01}BusinessApplicationHeaderV01"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="ProxyRegistration" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}Document"/&gt;
 *           &lt;element name="ProxyRegistrationResponse" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}Document"/&gt;
 *           &lt;element name="ProxyLookUp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}Document"/&gt;
 *           &lt;element name="ProxyLookUpResponse" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Document"/&gt;
 *           &lt;element name="ProxyEnquiryRequest" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}Document"/&gt;
 *           &lt;element name="ProxyEnquiryResponse" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Document"/&gt;
 *           &lt;element name="ParticipantReportRequest" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}Document"/&gt;
 *           &lt;element name="ParticipantReportResponse" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Document"/&gt;
 *           &lt;element name="EchoRequest" type="{urn:iso:std:iso:20022:tech:xsd:admn.005.001.01}Document"/&gt;
 *           &lt;element name="EchoResponse" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}Document"/&gt;
 *           &lt;element name="MessageReject" type="{urn:iso:std:iso:20022:tech:xsd:admi.002.001.01}Document"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="Message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HdrAndData", propOrder = {
    "appHdr",
    "proxyRegistration",
    "proxyRegistrationResponse",
    "proxyLookUp",
    "proxyLookUpResponse",
    "proxyEnquiryRequest",
    "proxyEnquiryResponse",
    "participantReportRequest",
    "participantReportResponse",
    "echoRequest",
    "echoResponse",
    "messageReject"
})
public class HdrAndData {

    @XmlElement(name = "AppHdr", required = true)
    protected BusinessApplicationHeaderV01 appHdr;
    @XmlElement(name = "ProxyRegistration")
    protected address.prxy_001_001.Document proxyRegistration;
    @XmlElement(name = "ProxyRegistrationResponse")
    protected Document proxyRegistrationResponse;
    @XmlElement(name = "ProxyLookUp")
    protected address.prxy_003_001.Document proxyLookUp;
    @XmlElement(name = "ProxyLookUpResponse")
    protected address.prxy_004_001.Document proxyLookUpResponse;
    @XmlElement(name = "ProxyEnquiryRequest")
    protected address.prxy_005_001.Document proxyEnquiryRequest;
    @XmlElement(name = "ProxyEnquiryResponse")
    protected address.prxy_006_001.Document proxyEnquiryResponse;
    @XmlElement(name = "ParticipantReportRequest")
    protected address.prxy_007_001.Document participantReportRequest;
    @XmlElement(name = "ParticipantReportResponse")
    protected address.prxy_008_001.Document participantReportResponse;
    @XmlElement(name = "EchoRequest")
    protected address.admn_005_001.Document echoRequest;
    @XmlElement(name = "EchoResponse")
    protected address.admn_006_001.Document echoResponse;
    @XmlElement(name = "MessageReject")
    protected address.admi_002_001.Document messageReject;

    /**
     * Gets the value of the appHdr property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessApplicationHeaderV01 }
     *     
     */
    public BusinessApplicationHeaderV01 getAppHdr() {
        return appHdr;
    }

    /**
     * Sets the value of the appHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessApplicationHeaderV01 }
     *     
     */
    public void setAppHdr(BusinessApplicationHeaderV01 value) {
        this.appHdr = value;
    }

    /**
     * Gets the value of the proxyRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_001_001.Document }
     *     
     */
    public address.prxy_001_001.Document getProxyRegistration() {
        return proxyRegistration;
    }

    /**
     * Sets the value of the proxyRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_001_001.Document }
     *     
     */
    public void setProxyRegistration(address.prxy_001_001.Document value) {
        this.proxyRegistration = value;
    }

    /**
     * Gets the value of the proxyRegistrationResponse property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getProxyRegistrationResponse() {
        return proxyRegistrationResponse;
    }

    /**
     * Sets the value of the proxyRegistrationResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setProxyRegistrationResponse(Document value) {
        this.proxyRegistrationResponse = value;
    }

    /**
     * Gets the value of the proxyLookUp property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_003_001.Document }
     *     
     */
    public address.prxy_003_001.Document getProxyLookUp() {
        return proxyLookUp;
    }

    /**
     * Sets the value of the proxyLookUp property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_003_001.Document }
     *     
     */
    public void setProxyLookUp(address.prxy_003_001.Document value) {
        this.proxyLookUp = value;
    }

    /**
     * Gets the value of the proxyLookUpResponse property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_004_001.Document }
     *     
     */
    public address.prxy_004_001.Document getProxyLookUpResponse() {
        return proxyLookUpResponse;
    }

    /**
     * Sets the value of the proxyLookUpResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_004_001.Document }
     *     
     */
    public void setProxyLookUpResponse(address.prxy_004_001.Document value) {
        this.proxyLookUpResponse = value;
    }

    /**
     * Gets the value of the proxyEnquiryRequest property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_005_001.Document }
     *     
     */
    public address.prxy_005_001.Document getProxyEnquiryRequest() {
        return proxyEnquiryRequest;
    }

    /**
     * Sets the value of the proxyEnquiryRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_005_001.Document }
     *     
     */
    public void setProxyEnquiryRequest(address.prxy_005_001.Document value) {
        this.proxyEnquiryRequest = value;
    }

    /**
     * Gets the value of the proxyEnquiryResponse property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_006_001.Document }
     *     
     */
    public address.prxy_006_001.Document getProxyEnquiryResponse() {
        return proxyEnquiryResponse;
    }

    /**
     * Sets the value of the proxyEnquiryResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_006_001.Document }
     *     
     */
    public void setProxyEnquiryResponse(address.prxy_006_001.Document value) {
        this.proxyEnquiryResponse = value;
    }

    /**
     * Gets the value of the participantReportRequest property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_007_001.Document }
     *     
     */
    public address.prxy_007_001.Document getParticipantReportRequest() {
        return participantReportRequest;
    }

    /**
     * Sets the value of the participantReportRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_007_001.Document }
     *     
     */
    public void setParticipantReportRequest(address.prxy_007_001.Document value) {
        this.participantReportRequest = value;
    }

    /**
     * Gets the value of the participantReportResponse property.
     * 
     * @return
     *     possible object is
     *     {@link address.prxy_008_001.Document }
     *     
     */
    public address.prxy_008_001.Document getParticipantReportResponse() {
        return participantReportResponse;
    }

    /**
     * Sets the value of the participantReportResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.prxy_008_001.Document }
     *     
     */
    public void setParticipantReportResponse(address.prxy_008_001.Document value) {
        this.participantReportResponse = value;
    }

    /**
     * Gets the value of the echoRequest property.
     * 
     * @return
     *     possible object is
     *     {@link address.admn_005_001.Document }
     *     
     */
    public address.admn_005_001.Document getEchoRequest() {
        return echoRequest;
    }

    /**
     * Sets the value of the echoRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.admn_005_001.Document }
     *     
     */
    public void setEchoRequest(address.admn_005_001.Document value) {
        this.echoRequest = value;
    }

    /**
     * Gets the value of the echoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link address.admn_006_001.Document }
     *     
     */
    public address.admn_006_001.Document getEchoResponse() {
        return echoResponse;
    }

    /**
     * Sets the value of the echoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.admn_006_001.Document }
     *     
     */
    public void setEchoResponse(address.admn_006_001.Document value) {
        this.echoResponse = value;
    }

    /**
     * Gets the value of the messageReject property.
     * 
     * @return
     *     possible object is
     *     {@link address.admi_002_001.Document }
     *     
     */
    public address.admi_002_001.Document getMessageReject() {
        return messageReject;
    }

    /**
     * Sets the value of the messageReject property.
     * 
     * @param value
     *     allowed object is
     *     {@link address.admi_002_001.Document }
     *     
     */
    public void setMessageReject(address.admi_002_001.Document value) {
        this.messageReject = value;
    }

}
