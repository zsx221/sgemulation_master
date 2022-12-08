
package address.prxy_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyLookUpAccount1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyLookUpAccount1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RegnId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Max35Text"/&gt;
 *         &lt;element name="DsplNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Max140Text"/&gt;
 *         &lt;element name="Agt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;element name="Acct" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}CashAccount40"/&gt;
 *         &lt;element name="PreAuthrsd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}YesNoIndicator"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyLookUpAccount1", propOrder = {
    "regnId",
    "dsplNm",
    "agt",
    "acct",
    "preAuthrsd"
})
public class ProxyLookUpAccount1 {

    @XmlElement(name = "RegnId", required = true)
    protected String regnId;
    @XmlElement(name = "DsplNm", required = true)
    protected String dsplNm;
    @XmlElement(name = "Agt", required = true)
    protected BranchAndFinancialInstitutionIdentification5 agt;
    @XmlElement(name = "Acct", required = true)
    protected CashAccount40 acct;
    @XmlElement(name = "PreAuthrsd")
    protected boolean preAuthrsd;

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
     * Gets the value of the preAuthrsd property.
     * 
     */
    public boolean isPreAuthrsd() {
        return preAuthrsd;
    }

    /**
     * Sets the value of the preAuthrsd property.
     * 
     */
    public void setPreAuthrsd(boolean value) {
        this.preAuthrsd = value;
    }

}
