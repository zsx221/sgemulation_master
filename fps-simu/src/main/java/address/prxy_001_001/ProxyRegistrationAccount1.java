
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyRegistrationAccount1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyRegistrationAccount1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RegnId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="DsplNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}Max140Text" minOccurs="0"/&gt;
 *         &lt;element name="Agt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}BranchAndFinancialInstitutionIdentification5" minOccurs="0"/&gt;
 *         &lt;element name="Acct" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}CashAccount40" minOccurs="0"/&gt;
 *         &lt;element name="AcctHldr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}Party30Choice" minOccurs="0"/&gt;
 *         &lt;element name="RegnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistrationStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="PreAuthrsd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}YesNoIndicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyRegistrationAccount1", propOrder = {
    "regnId",
    "dsplNm",
    "agt",
    "acct",
    "acctHldr",
    "regnSts",
    "preAuthrsd"
})
public class ProxyRegistrationAccount1 {

    @XmlElement(name = "RegnId")
    protected String regnId;
    @XmlElement(name = "DsplNm")
    protected String dsplNm;
    @XmlElement(name = "Agt")
    protected BranchAndFinancialInstitutionIdentification5 agt;
    @XmlElement(name = "Acct")
    protected CashAccount40 acct;
    @XmlElement(name = "AcctHldr")
    protected Party30Choice acctHldr;
    @XmlElement(name = "RegnSts")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationStatusCode regnSts;
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
     * Gets the value of the agt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification5 getAgt() {
        return agt;
    }

    /**
     * Sets the value of the agt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public void setAgt(BranchAndFinancialInstitutionIdentification5 value) {
        this.agt = value;
    }

    /**
     * Gets the value of the acct property.
     * 
     * @return
     *     possible object is
     *     {@link CashAccount40 }
     *     
     */
    public CashAccount40 getAcct() {
        return acct;
    }

    /**
     * Sets the value of the acct property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashAccount40 }
     *     
     */
    public void setAcct(CashAccount40 value) {
        this.acct = value;
    }

    /**
     * Gets the value of the acctHldr property.
     * 
     * @return
     *     possible object is
     *     {@link Party30Choice }
     *     
     */
    public Party30Choice getAcctHldr() {
        return acctHldr;
    }

    /**
     * Sets the value of the acctHldr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party30Choice }
     *     
     */
    public void setAcctHldr(Party30Choice value) {
        this.acctHldr = value;
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
