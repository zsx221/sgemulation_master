
package iso.pacs_008_001;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GroupHeader33 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupHeader33"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MsgId" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}Max35Text"/&gt;
 *         &lt;element name="CreDtTm" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}ISODateTime"/&gt;
 *         &lt;element name="BtchBookg" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}BatchBookingIndicator" minOccurs="0"/&gt;
 *         &lt;element name="NbOfTxs" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}Max15NumericText"/&gt;
 *         &lt;element name="CtrlSum" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}DecimalNumber" minOccurs="0"/&gt;
 *         &lt;element name="TtlIntrBkSttlmAmt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}ActiveCurrencyAndAmount" minOccurs="0"/&gt;
 *         &lt;element name="IntrBkSttlmDt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}ISODate" minOccurs="0"/&gt;
 *         &lt;element name="SttlmInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}SettlementInformation13"/&gt;
 *         &lt;element name="PmtTpInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}PaymentTypeInformation21" minOccurs="0"/&gt;
 *         &lt;element name="InstgAgt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}BranchAndFinancialInstitutionIdentification4" minOccurs="0"/&gt;
 *         &lt;element name="InstdAgt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}BranchAndFinancialInstitutionIdentification4" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupHeader33", propOrder = {
    "msgId",
    "creDtTm",
    "btchBookg",
    "nbOfTxs",
    "ctrlSum",
    "ttlIntrBkSttlmAmt",
    "intrBkSttlmDt",
    "sttlmInf",
    "pmtTpInf",
    "instgAgt",
    "instdAgt"
})
public class GroupHeader33 {

    @XmlElement(name = "MsgId", required = true)
    protected String msgId;
    @XmlElement(name = "CreDtTm", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creDtTm;
    @XmlElement(name = "BtchBookg")
    protected Boolean btchBookg;
    @XmlElement(name = "NbOfTxs", required = true)
    protected String nbOfTxs;
    @XmlElement(name = "CtrlSum")
    protected BigDecimal ctrlSum;
    @XmlElement(name = "TtlIntrBkSttlmAmt")
    protected ActiveCurrencyAndAmount ttlIntrBkSttlmAmt;
    @XmlElement(name = "IntrBkSttlmDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar intrBkSttlmDt;
    @XmlElement(name = "SttlmInf", required = true)
    protected SettlementInformation13 sttlmInf;
    @XmlElement(name = "PmtTpInf")
    protected PaymentTypeInformation21 pmtTpInf;
    @XmlElement(name = "InstgAgt")
    protected BranchAndFinancialInstitutionIdentification4 instgAgt;
    @XmlElement(name = "InstdAgt")
    protected BranchAndFinancialInstitutionIdentification4 instdAgt;

    /**
     * Gets the value of the msgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
    }

    /**
     * Gets the value of the creDtTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreDtTm() {
        return creDtTm;
    }

    /**
     * Sets the value of the creDtTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreDtTm(XMLGregorianCalendar value) {
        this.creDtTm = value;
    }

    /**
     * Gets the value of the btchBookg property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBtchBookg() {
        return btchBookg;
    }

    /**
     * Sets the value of the btchBookg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBtchBookg(Boolean value) {
        this.btchBookg = value;
    }

    /**
     * Gets the value of the nbOfTxs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbOfTxs() {
        return nbOfTxs;
    }

    /**
     * Sets the value of the nbOfTxs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbOfTxs(String value) {
        this.nbOfTxs = value;
    }

    /**
     * Gets the value of the ctrlSum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCtrlSum() {
        return ctrlSum;
    }

    /**
     * Sets the value of the ctrlSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCtrlSum(BigDecimal value) {
        this.ctrlSum = value;
    }

    /**
     * Gets the value of the ttlIntrBkSttlmAmt property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveCurrencyAndAmount }
     *     
     */
    public ActiveCurrencyAndAmount getTtlIntrBkSttlmAmt() {
        return ttlIntrBkSttlmAmt;
    }

    /**
     * Sets the value of the ttlIntrBkSttlmAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveCurrencyAndAmount }
     *     
     */
    public void setTtlIntrBkSttlmAmt(ActiveCurrencyAndAmount value) {
        this.ttlIntrBkSttlmAmt = value;
    }

    /**
     * Gets the value of the intrBkSttlmDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIntrBkSttlmDt() {
        return intrBkSttlmDt;
    }

    /**
     * Sets the value of the intrBkSttlmDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIntrBkSttlmDt(XMLGregorianCalendar value) {
        this.intrBkSttlmDt = value;
    }

    /**
     * Gets the value of the sttlmInf property.
     * 
     * @return
     *     possible object is
     *     {@link SettlementInformation13 }
     *     
     */
    public SettlementInformation13 getSttlmInf() {
        return sttlmInf;
    }

    /**
     * Sets the value of the sttlmInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link SettlementInformation13 }
     *     
     */
    public void setSttlmInf(SettlementInformation13 value) {
        this.sttlmInf = value;
    }

    /**
     * Gets the value of the pmtTpInf property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeInformation21 }
     *     
     */
    public PaymentTypeInformation21 getPmtTpInf() {
        return pmtTpInf;
    }

    /**
     * Sets the value of the pmtTpInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeInformation21 }
     *     
     */
    public void setPmtTpInf(PaymentTypeInformation21 value) {
        this.pmtTpInf = value;
    }

    /**
     * Gets the value of the instgAgt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getInstgAgt() {
        return instgAgt;
    }

    /**
     * Sets the value of the instgAgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setInstgAgt(BranchAndFinancialInstitutionIdentification4 value) {
        this.instgAgt = value;
    }

    /**
     * Gets the value of the instdAgt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getInstdAgt() {
        return instdAgt;
    }

    /**
     * Sets the value of the instdAgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setInstdAgt(BranchAndFinancialInstitutionIdentification4 value) {
        this.instdAgt = value;
    }

}
