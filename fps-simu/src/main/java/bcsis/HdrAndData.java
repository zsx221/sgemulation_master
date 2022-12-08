package bcsis;

import javax.xml.bind.annotation.*;

import iso.pacs_002_001.Document;
import iso.head_001_001.BusinessApplicationHeaderV01;


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
 *           &lt;element name="CreditTransfer" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}Document"/&gt;
 *           &lt;element name="DirectDebit" type="{urn:iso:std:iso:20022:tech:xsd:pacs.003.001.02}Document"/&gt;
 *           &lt;element name="PaymentStatus" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}Document"/&gt;
 *           &lt;element name="PaymentCancellation" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Document"/&gt;
 *           &lt;element name="PaymentReversal" type="{urn:iso:std:iso:20022:tech:xsd:pacs.007.001.02}Document"/&gt;
 *           &lt;element name="ResolutionOfInvestigation" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Document"/&gt;
 *           &lt;element name="EchoRequest" type="{urn:iso:std:iso:20022:tech:xsd:admn.005.001.01}Document"/&gt;
 *           &lt;element name="EchoResponse" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}Document"/&gt;
 *           &lt;element name="SignOffRequest" type="{urn:iso:std:iso:20022:tech:xsd:admn.003.001.01}Document"/&gt;
 *           &lt;element name="SignOffResponse" type="{urn:iso:std:iso:20022:tech:xsd:admn.004.001.01}Document"/&gt;
 *           &lt;element name="SignOnRequest" type="{urn:iso:std:iso:20022:tech:xsd:admn.001.001.01}Document"/&gt;
 *           &lt;element name="SignOnResponse" type="{urn:iso:std:iso:20022:tech:xsd:admn.002.001.01}Document"/&gt;
 *           &lt;element name="SystemNotificationEvent" type="{urn:iso:std:iso:20022:tech:xsd:admi.004.001.01}Document"/&gt;
 *           &lt;element name="MessageReject" type="{urn:iso:std:iso:20022:tech:xsd:admi.002.001.01}Document"/&gt;
 *           &lt;element name="BankStatementRequest" type="{urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01}Document"/&gt;
 *           &lt;element name="BankStatementResponse" type="{urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01}Document"/&gt;
 *           &lt;element name="BankStatement" type="{urn:iso:std:iso:20022:tech:xsd:camt.053.001.02}Document"/&gt;
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
    "creditTransfer",
    "directDebit",
    "paymentStatus",
    "paymentCancellation",
    "paymentReversal",
    "resolutionOfInvestigation",
    "echoRequest",
    "echoResponse",
    "signOffRequest",
    "signOffResponse",
    "signOnRequest",
    "signOnResponse",
    "systemNotificationEvent",
    "messageReject",
    "bankStatementRequest",
    "bankStatementResponse",
    "bankStatement"
})
public class HdrAndData {

    @XmlElement(name = "AppHdr", required = true)
    protected BusinessApplicationHeaderV01 appHdr;
    @XmlElement(name = "CreditTransfer")
    protected iso.pacs_008_001.Document creditTransfer;
    @XmlElement(name = "DirectDebit")
    protected iso.pacs_003_001.Document directDebit;
    @XmlElement(name = "PaymentStatus")
    protected Document paymentStatus;
    @XmlElement(name = "PaymentCancellation")
    protected iso.camt_056_001.Document paymentCancellation;
    @XmlElement(name = "PaymentReversal")
    protected iso.pacs_007_001.Document paymentReversal;
    @XmlElement(name = "ResolutionOfInvestigation")
    protected iso.camt_029_001.Document resolutionOfInvestigation;
    @XmlElement(name = "EchoRequest")
    protected iso.admn_005_001.Document echoRequest;
    @XmlElement(name = "EchoResponse")
    protected iso.admn_006_001.Document echoResponse;
    @XmlElement(name = "SignOffRequest")
    protected iso.admn_003_001.Document signOffRequest;
    @XmlElement(name = "SignOffResponse")
    protected iso.admn_004_001.Document signOffResponse;
    @XmlElement(name = "SignOnRequest")
    protected iso.admn_001_001.Document signOnRequest;
    @XmlElement(name = "SignOnResponse")
    protected iso.admn_002_001.Document signOnResponse;
    @XmlElement(name = "SystemNotificationEvent")
    protected iso.admi_004_001.Document systemNotificationEvent;
    @XmlElement(name = "MessageReject")
    protected iso.admi_002_001.Document messageReject;
    @XmlElement(name = "BankStatementRequest")
    protected iso.stmt_001_001.Document bankStatementRequest;
    @XmlElement(name = "BankStatementResponse")
    protected iso.stmt_002_001.Document bankStatementResponse;
    @XmlElement(name = "BankStatement")
    protected iso.camt_053_001.Document bankStatement;

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
     * Gets the value of the creditTransfer property.
     * 
     * @return
     *     possible object is
     *     {@link iso.pacs_008_001.Document }
     *     
     */
    public iso.pacs_008_001.Document getCreditTransfer() {
        return creditTransfer;
    }

    /**
     * Sets the value of the creditTransfer property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.pacs_008_001.Document }
     *     
     */
    public void setCreditTransfer(iso.pacs_008_001.Document value) {
        this.creditTransfer = value;
    }

    /**
     * Gets the value of the directDebit property.
     * 
     * @return
     *     possible object is
     *     {@link iso.pacs_003_001.Document }
     *     
     */
    public iso.pacs_003_001.Document getDirectDebit() {
        return directDebit;
    }

    /**
     * Sets the value of the directDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.pacs_003_001.Document }
     *     
     */
    public void setDirectDebit(iso.pacs_003_001.Document value) {
        this.directDebit = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setPaymentStatus(Document value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the paymentCancellation property.
     * 
     * @return
     *     possible object is
     *     {@link iso.camt_056_001.Document }
     *     
     */
    public iso.camt_056_001.Document getPaymentCancellation() {
        return paymentCancellation;
    }

    /**
     * Sets the value of the paymentCancellation property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.camt_056_001.Document }
     *     
     */
    public void setPaymentCancellation(iso.camt_056_001.Document value) {
        this.paymentCancellation = value;
    }

    /**
     * Gets the value of the paymentReversal property.
     * 
     * @return
     *     possible object is
     *     {@link iso.pacs_007_001.Document }
     *     
     */
    public iso.pacs_007_001.Document getPaymentReversal() {
        return paymentReversal;
    }

    /**
     * Sets the value of the paymentReversal property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.pacs_007_001.Document }
     *     
     */
    public void setPaymentReversal(iso.pacs_007_001.Document value) {
        this.paymentReversal = value;
    }

    /**
     * Gets the value of the resolutionOfInvestigation property.
     * 
     * @return
     *     possible object is
     *     {@link iso.camt_029_001.Document }
     *     
     */
    public iso.camt_029_001.Document getResolutionOfInvestigation() {
        return resolutionOfInvestigation;
    }

    /**
     * Sets the value of the resolutionOfInvestigation property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.camt_029_001.Document }
     *     
     */
    public void setResolutionOfInvestigation(iso.camt_029_001.Document value) {
        this.resolutionOfInvestigation = value;
    }

    /**
     * Gets the value of the echoRequest property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_005_001.Document }
     *     
     */
    public iso.admn_005_001.Document getEchoRequest() {
        return echoRequest;
    }

    /**
     * Sets the value of the echoRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_005_001.Document }
     *     
     */
    public void setEchoRequest(iso.admn_005_001.Document value) {
        this.echoRequest = value;
    }

    /**
     * Gets the value of the echoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_006_001.Document }
     *     
     */
    public iso.admn_006_001.Document getEchoResponse() {
        return echoResponse;
    }

    /**
     * Sets the value of the echoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_006_001.Document }
     *     
     */
    public void setEchoResponse(iso.admn_006_001.Document value) {
        this.echoResponse = value;
    }

    /**
     * Gets the value of the signOffRequest property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_003_001.Document }
     *     
     */
    public iso.admn_003_001.Document getSignOffRequest() {
        return signOffRequest;
    }

    /**
     * Sets the value of the signOffRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_003_001.Document }
     *     
     */
    public void setSignOffRequest(iso.admn_003_001.Document value) {
        this.signOffRequest = value;
    }

    /**
     * Gets the value of the signOffResponse property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_004_001.Document }
     *     
     */
    public iso.admn_004_001.Document getSignOffResponse() {
        return signOffResponse;
    }

    /**
     * Sets the value of the signOffResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_004_001.Document }
     *     
     */
    public void setSignOffResponse(iso.admn_004_001.Document value) {
        this.signOffResponse = value;
    }

    /**
     * Gets the value of the signOnRequest property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_001_001.Document }
     *     
     */
    public iso.admn_001_001.Document getSignOnRequest() {
        return signOnRequest;
    }

    /**
     * Sets the value of the signOnRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_001_001.Document }
     *     
     */
    public void setSignOnRequest(iso.admn_001_001.Document value) {
        this.signOnRequest = value;
    }

    /**
     * Gets the value of the signOnResponse property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admn_002_001.Document }
     *     
     */
    public iso.admn_002_001.Document getSignOnResponse() {
        return signOnResponse;
    }

    /**
     * Sets the value of the signOnResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admn_002_001.Document }
     *     
     */
    public void setSignOnResponse(iso.admn_002_001.Document value) {
        this.signOnResponse = value;
    }

    /**
     * Gets the value of the systemNotificationEvent property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admi_004_001.Document }
     *     
     */
    public iso.admi_004_001.Document getSystemNotificationEvent() {
        return systemNotificationEvent;
    }

    /**
     * Sets the value of the systemNotificationEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admi_004_001.Document }
     *     
     */
    public void setSystemNotificationEvent(iso.admi_004_001.Document value) {
        this.systemNotificationEvent = value;
    }

    /**
     * Gets the value of the messageReject property.
     * 
     * @return
     *     possible object is
     *     {@link iso.admi_002_001.Document }
     *     
     */
    public iso.admi_002_001.Document getMessageReject() {
        return messageReject;
    }

    /**
     * Sets the value of the messageReject property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.admi_002_001.Document }
     *     
     */
    public void setMessageReject(iso.admi_002_001.Document value) {
        this.messageReject = value;
    }

    /**
     * Gets the value of the bankStatementRequest property.
     * 
     * @return
     *     possible object is
     *     {@link iso.stmt_001_001.Document }
     *     
     */
    public iso.stmt_001_001.Document getBankStatementRequest() {
        return bankStatementRequest;
    }

    /**
     * Sets the value of the bankStatementRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.stmt_001_001.Document }
     *     
     */
    public void setBankStatementRequest(iso.stmt_001_001.Document value) {
        this.bankStatementRequest = value;
    }

    /**
     * Gets the value of the bankStatementResponse property.
     * 
     * @return
     *     possible object is
     *     {@link iso.stmt_002_001.Document }
     *     
     */
    public iso.stmt_002_001.Document getBankStatementResponse() {
        return bankStatementResponse;
    }

    /**
     * Sets the value of the bankStatementResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.stmt_002_001.Document }
     *     
     */
    public void setBankStatementResponse(iso.stmt_002_001.Document value) {
        this.bankStatementResponse = value;
    }

    /**
     * Gets the value of the bankStatement property.
     * 
     * @return
     *     possible object is
     *     {@link iso.camt_053_001.Document }
     *     
     */
    public iso.camt_053_001.Document getBankStatement() {
        return bankStatement;
    }

    /**
     * Sets the value of the bankStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link iso.camt_053_001.Document }
     *     
     */
    public void setBankStatement(iso.camt_053_001.Document value) {
        this.bankStatement = value;
    }

}
