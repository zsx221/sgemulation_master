
package iso.camt_056_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PaymentTransactionInformation31 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTransactionInformation31"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CxlId" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="Case" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Case2" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlGrpInf" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}OriginalGroupInformation3" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlInstrId" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlEndToEndId" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlTxId" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlClrSysRef" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlIntrBkSttlmAmt" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}ActiveOrHistoricCurrencyAndAmount" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlIntrBkSttlmDt" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}ISODate" minOccurs="0"/&gt;
 *         &lt;element name="Assgnr" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}BranchAndFinancialInstitutionIdentification4" minOccurs="0"/&gt;
 *         &lt;element name="Assgne" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}BranchAndFinancialInstitutionIdentification4" minOccurs="0"/&gt;
 *         &lt;element name="CxlRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}CancellationReasonInformation3" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlTxRef" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}OriginalTransactionReference13" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTransactionInformation31", propOrder = {
    "cxlId",
    "_case",
    "orgnlGrpInf",
    "orgnlInstrId",
    "orgnlEndToEndId",
    "orgnlTxId",
    "orgnlClrSysRef",
    "orgnlIntrBkSttlmAmt",
    "orgnlIntrBkSttlmDt",
    "assgnr",
    "assgne",
    "cxlRsnInf",
    "orgnlTxRef"
})
public class PaymentTransactionInformation31 {

    @XmlElement(name = "CxlId")
    protected String cxlId;
    @XmlElement(name = "Case")
    protected Case2 _case;
    @XmlElement(name = "OrgnlGrpInf")
    protected OriginalGroupInformation3 orgnlGrpInf;
    @XmlElement(name = "OrgnlInstrId")
    protected String orgnlInstrId;
    @XmlElement(name = "OrgnlEndToEndId")
    protected String orgnlEndToEndId;
    @XmlElement(name = "OrgnlTxId")
    protected String orgnlTxId;
    @XmlElement(name = "OrgnlClrSysRef")
    protected String orgnlClrSysRef;
    @XmlElement(name = "OrgnlIntrBkSttlmAmt")
    protected ActiveOrHistoricCurrencyAndAmount orgnlIntrBkSttlmAmt;
    @XmlElement(name = "OrgnlIntrBkSttlmDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orgnlIntrBkSttlmDt;
    @XmlElement(name = "Assgnr")
    protected BranchAndFinancialInstitutionIdentification4 assgnr;
    @XmlElement(name = "Assgne")
    protected BranchAndFinancialInstitutionIdentification4 assgne;
    @XmlElement(name = "CxlRsnInf")
    protected List<CancellationReasonInformation3> cxlRsnInf;
    @XmlElement(name = "OrgnlTxRef")
    protected OriginalTransactionReference13 orgnlTxRef;

    /**
     * Gets the value of the cxlId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCxlId() {
        return cxlId;
    }

    /**
     * Sets the value of the cxlId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCxlId(String value) {
        this.cxlId = value;
    }

    /**
     * Gets the value of the case property.
     * 
     * @return
     *     possible object is
     *     {@link Case2 }
     *     
     */
    public Case2 getCase() {
        return _case;
    }

    /**
     * Sets the value of the case property.
     * 
     * @param value
     *     allowed object is
     *     {@link Case2 }
     *     
     */
    public void setCase(Case2 value) {
        this._case = value;
    }

    /**
     * Gets the value of the orgnlGrpInf property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalGroupInformation3 }
     *     
     */
    public OriginalGroupInformation3 getOrgnlGrpInf() {
        return orgnlGrpInf;
    }

    /**
     * Sets the value of the orgnlGrpInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalGroupInformation3 }
     *     
     */
    public void setOrgnlGrpInf(OriginalGroupInformation3 value) {
        this.orgnlGrpInf = value;
    }

    /**
     * Gets the value of the orgnlInstrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlInstrId() {
        return orgnlInstrId;
    }

    /**
     * Sets the value of the orgnlInstrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlInstrId(String value) {
        this.orgnlInstrId = value;
    }

    /**
     * Gets the value of the orgnlEndToEndId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlEndToEndId() {
        return orgnlEndToEndId;
    }

    /**
     * Sets the value of the orgnlEndToEndId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlEndToEndId(String value) {
        this.orgnlEndToEndId = value;
    }

    /**
     * Gets the value of the orgnlTxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlTxId() {
        return orgnlTxId;
    }

    /**
     * Sets the value of the orgnlTxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlTxId(String value) {
        this.orgnlTxId = value;
    }

    /**
     * Gets the value of the orgnlClrSysRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlClrSysRef() {
        return orgnlClrSysRef;
    }

    /**
     * Sets the value of the orgnlClrSysRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlClrSysRef(String value) {
        this.orgnlClrSysRef = value;
    }

    /**
     * Gets the value of the orgnlIntrBkSttlmAmt property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveOrHistoricCurrencyAndAmount }
     *     
     */
    public ActiveOrHistoricCurrencyAndAmount getOrgnlIntrBkSttlmAmt() {
        return orgnlIntrBkSttlmAmt;
    }

    /**
     * Sets the value of the orgnlIntrBkSttlmAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveOrHistoricCurrencyAndAmount }
     *     
     */
    public void setOrgnlIntrBkSttlmAmt(ActiveOrHistoricCurrencyAndAmount value) {
        this.orgnlIntrBkSttlmAmt = value;
    }

    /**
     * Gets the value of the orgnlIntrBkSttlmDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrgnlIntrBkSttlmDt() {
        return orgnlIntrBkSttlmDt;
    }

    /**
     * Sets the value of the orgnlIntrBkSttlmDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrgnlIntrBkSttlmDt(XMLGregorianCalendar value) {
        this.orgnlIntrBkSttlmDt = value;
    }

    /**
     * Gets the value of the assgnr property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getAssgnr() {
        return assgnr;
    }

    /**
     * Sets the value of the assgnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setAssgnr(BranchAndFinancialInstitutionIdentification4 value) {
        this.assgnr = value;
    }

    /**
     * Gets the value of the assgne property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getAssgne() {
        return assgne;
    }

    /**
     * Sets the value of the assgne property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setAssgne(BranchAndFinancialInstitutionIdentification4 value) {
        this.assgne = value;
    }

    /**
     * Gets the value of the cxlRsnInf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cxlRsnInf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCxlRsnInf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CancellationReasonInformation3 }
     * 
     * 
     */
    public List<CancellationReasonInformation3> getCxlRsnInf() {
        if (cxlRsnInf == null) {
            cxlRsnInf = new ArrayList<CancellationReasonInformation3>();
        }
        return this.cxlRsnInf;
    }

    /**
     * Gets the value of the orgnlTxRef property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalTransactionReference13 }
     *     
     */
    public OriginalTransactionReference13 getOrgnlTxRef() {
        return orgnlTxRef;
    }

    /**
     * Sets the value of the orgnlTxRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalTransactionReference13 }
     *     
     */
    public void setOrgnlTxRef(OriginalTransactionReference13 value) {
        this.orgnlTxRef = value;
    }

}
