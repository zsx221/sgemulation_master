
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryAccount1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryAccount1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Agt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}BranchAndFinancialInstitutionIdentification5" minOccurs="0"/&gt;
 *         &lt;element name="Acct" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}CashAccount40" minOccurs="0"/&gt;
 *         &lt;element name="Sts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyAccountStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="AcctHldr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Party30Choice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryAccount1", propOrder = {
    "agt",
    "acct",
    "sts",
    "acctHldr"
})
public class ProxyEnquiryAccount1 {

    @XmlElement(name = "Agt")
    protected BranchAndFinancialInstitutionIdentification5 agt;
    @XmlElement(name = "Acct")
    protected CashAccount40 acct;
    @XmlElement(name = "Sts")
    @XmlSchemaType(name = "string")
    protected ProxyAccountStatusCode sts;
    @XmlElement(name = "AcctHldr")
    protected Party30Choice acctHldr;

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
     * Gets the value of the sts property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyAccountStatusCode }
     *     
     */
    public ProxyAccountStatusCode getSts() {
        return sts;
    }

    /**
     * Sets the value of the sts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyAccountStatusCode }
     *     
     */
    public void setSts(ProxyAccountStatusCode value) {
        this.sts = value;
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

}
