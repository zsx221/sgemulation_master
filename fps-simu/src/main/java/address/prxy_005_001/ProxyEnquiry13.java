
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiry13 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiry13"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxyNqry" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyDefintion1"/&gt;
 *         &lt;element name="Agt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;element name="Acct" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}CashAccount40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiry13", propOrder = {
    "prxyNqry",
    "agt",
    "acct"
})
public class ProxyEnquiry13 {

    @XmlElement(name = "PrxyNqry", required = true)
    protected ProxyDefintion1 prxyNqry;
    @XmlElement(name = "Agt", required = true)
    protected BranchAndFinancialInstitutionIdentification5 agt;
    @XmlElement(name = "Acct", required = true)
    protected CashAccount40 acct;

    /**
     * Gets the value of the prxyNqry property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getPrxyNqry() {
        return prxyNqry;
    }

    /**
     * Sets the value of the prxyNqry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setPrxyNqry(ProxyDefintion1 value) {
        this.prxyNqry = value;
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

}
