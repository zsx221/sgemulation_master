
package iso.camt_029_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MandateRelatedInformation6 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MandateRelatedInformation6"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MndtId" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="DtOfSgntr" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}ISODate" minOccurs="0"/&gt;
 *         &lt;element name="AmdmntInd" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}TrueFalseIndicator" minOccurs="0"/&gt;
 *         &lt;element name="AmdmntInfDtls" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}AmendmentInformationDetails6" minOccurs="0"/&gt;
 *         &lt;element name="ElctrncSgntr" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Max1025Text" minOccurs="0"/&gt;
 *         &lt;element name="FrstColltnDt" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}ISODate" minOccurs="0"/&gt;
 *         &lt;element name="FnlColltnDt" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}ISODate" minOccurs="0"/&gt;
 *         &lt;element name="Frqcy" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Frequency1Code" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandateRelatedInformation6", propOrder = {
    "mndtId",
    "dtOfSgntr",
    "amdmntInd",
    "amdmntInfDtls",
    "elctrncSgntr",
    "frstColltnDt",
    "fnlColltnDt",
    "frqcy"
})
public class MandateRelatedInformation6 {

    @XmlElement(name = "MndtId")
    protected String mndtId;
    @XmlElement(name = "DtOfSgntr")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dtOfSgntr;
    @XmlElement(name = "AmdmntInd")
    protected Boolean amdmntInd;
    @XmlElement(name = "AmdmntInfDtls")
    protected AmendmentInformationDetails6 amdmntInfDtls;
    @XmlElement(name = "ElctrncSgntr")
    protected String elctrncSgntr;
    @XmlElement(name = "FrstColltnDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar frstColltnDt;
    @XmlElement(name = "FnlColltnDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fnlColltnDt;
    @XmlElement(name = "Frqcy")
    @XmlSchemaType(name = "string")
    protected Frequency1Code frqcy;

    /**
     * Gets the value of the mndtId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMndtId() {
        return mndtId;
    }

    /**
     * Sets the value of the mndtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMndtId(String value) {
        this.mndtId = value;
    }

    /**
     * Gets the value of the dtOfSgntr property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtOfSgntr() {
        return dtOfSgntr;
    }

    /**
     * Sets the value of the dtOfSgntr property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtOfSgntr(XMLGregorianCalendar value) {
        this.dtOfSgntr = value;
    }

    /**
     * Gets the value of the amdmntInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAmdmntInd() {
        return amdmntInd;
    }

    /**
     * Sets the value of the amdmntInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAmdmntInd(Boolean value) {
        this.amdmntInd = value;
    }

    /**
     * Gets the value of the amdmntInfDtls property.
     * 
     * @return
     *     possible object is
     *     {@link AmendmentInformationDetails6 }
     *     
     */
    public AmendmentInformationDetails6 getAmdmntInfDtls() {
        return amdmntInfDtls;
    }

    /**
     * Sets the value of the amdmntInfDtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmendmentInformationDetails6 }
     *     
     */
    public void setAmdmntInfDtls(AmendmentInformationDetails6 value) {
        this.amdmntInfDtls = value;
    }

    /**
     * Gets the value of the elctrncSgntr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElctrncSgntr() {
        return elctrncSgntr;
    }

    /**
     * Sets the value of the elctrncSgntr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElctrncSgntr(String value) {
        this.elctrncSgntr = value;
    }

    /**
     * Gets the value of the frstColltnDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrstColltnDt() {
        return frstColltnDt;
    }

    /**
     * Sets the value of the frstColltnDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrstColltnDt(XMLGregorianCalendar value) {
        this.frstColltnDt = value;
    }

    /**
     * Gets the value of the fnlColltnDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFnlColltnDt() {
        return fnlColltnDt;
    }

    /**
     * Sets the value of the fnlColltnDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFnlColltnDt(XMLGregorianCalendar value) {
        this.fnlColltnDt = value;
    }

    /**
     * Gets the value of the frqcy property.
     * 
     * @return
     *     possible object is
     *     {@link Frequency1Code }
     *     
     */
    public Frequency1Code getFrqcy() {
        return frqcy;
    }

    /**
     * Sets the value of the frqcy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Frequency1Code }
     *     
     */
    public void setFrqcy(Frequency1Code value) {
        this.frqcy = value;
    }

}
