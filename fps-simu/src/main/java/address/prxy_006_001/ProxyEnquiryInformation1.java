
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ProxyEnquiryInformation1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryInformation1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RegnId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="DsplNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Max140Text" minOccurs="0"/&gt;
 *         &lt;element name="Ptcpt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}BranchAndFinancialInstitutionIdentification5" minOccurs="0"/&gt;
 *         &lt;element name="PrxyInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryDefintion1"/&gt;
 *         &lt;element name="AcctInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryAccount1" minOccurs="0"/&gt;
 *         &lt;element name="RegnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyRegistrationStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="RegnDtTm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ISODateTime" minOccurs="0"/&gt;
 *         &lt;element name="PreAuthrsd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}YesNoIndicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryInformation1", propOrder = {
    "regnId",
    "dsplNm",
    "ptcpt",
    "prxyInf",
    "acctInf",
    "regnSts",
    "regnDtTm",
    "preAuthrsd"
})
public class ProxyEnquiryInformation1 {

    @XmlElement(name = "RegnId")
    protected String regnId;
    @XmlElement(name = "DsplNm")
    protected String dsplNm;
    @XmlElement(name = "Ptcpt")
    protected BranchAndFinancialInstitutionIdentification5 ptcpt;
    @XmlElement(name = "PrxyInf", required = true)
    protected ProxyEnquiryDefintion1 prxyInf;
    @XmlElement(name = "AcctInf")
    protected ProxyEnquiryAccount1 acctInf;
    @XmlElement(name = "RegnSts")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationStatusCode regnSts;
    @XmlElement(name = "RegnDtTm")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar regnDtTm;
    @XmlElement(name = "PreAuthrsd")
    protected Boolean preAuthrsd;

    /**
     * Gets the value of the regnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegnId() {
        return regnId;
    }

    /**
     * Sets the value of the regnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegnId(String value) {
        this.regnId = value;
    }

    /**
     * Gets the value of the dsplNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsplNm() {
        return dsplNm;
    }

    /**
     * Sets the value of the dsplNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsplNm(String value) {
        this.dsplNm = value;
    }

    /**
     * Gets the value of the ptcpt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification5 getPtcpt() {
        return ptcpt;
    }

    /**
     * Sets the value of the ptcpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public void setPtcpt(BranchAndFinancialInstitutionIdentification5 value) {
        this.ptcpt = value;
    }

    /**
     * Gets the value of the prxyInf property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryDefintion1 }
     *     
     */
    public ProxyEnquiryDefintion1 getPrxyInf() {
        return prxyInf;
    }

    /**
     * Sets the value of the prxyInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryDefintion1 }
     *     
     */
    public void setPrxyInf(ProxyEnquiryDefintion1 value) {
        this.prxyInf = value;
    }

    /**
     * Gets the value of the acctInf property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryAccount1 }
     *     
     */
    public ProxyEnquiryAccount1 getAcctInf() {
        return acctInf;
    }

    /**
     * Sets the value of the acctInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryAccount1 }
     *     
     */
    public void setAcctInf(ProxyEnquiryAccount1 value) {
        this.acctInf = value;
    }

    /**
     * Gets the value of the regnSts property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationStatusCode }
     *     
     */
    public ProxyRegistrationStatusCode getRegnSts() {
        return regnSts;
    }

    /**
     * Sets the value of the regnSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationStatusCode }
     *     
     */
    public void setRegnSts(ProxyRegistrationStatusCode value) {
        this.regnSts = value;
    }

    /**
     * Gets the value of the regnDtTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegnDtTm() {
        return regnDtTm;
    }

    /**
     * Sets the value of the regnDtTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegnDtTm(XMLGregorianCalendar value) {
        this.regnDtTm = value;
    }

    /**
     * Gets the value of the preAuthrsd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreAuthrsd() {
        return preAuthrsd;
    }

    /**
     * Sets the value of the preAuthrsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreAuthrsd(Boolean value) {
        this.preAuthrsd = value;
    }

}
