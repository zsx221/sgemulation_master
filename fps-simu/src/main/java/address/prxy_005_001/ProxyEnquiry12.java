
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiry12 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiry12"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Agt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;element name="Acct" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}CashAccount40"/&gt;
 *         &lt;element name="RegnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyRegistrationStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="NbOfItm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}Max3NumericText" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiry12", propOrder = {
    "agt",
    "acct",
    "regnSts",
    "nbOfItm"
})
public class ProxyEnquiry12 {

    @XmlElement(name = "Agt", required = true)
    protected BranchAndFinancialInstitutionIdentification5 agt;
    @XmlElement(name = "Acct", required = true)
    protected CashAccount40 acct;
    @XmlElement(name = "RegnSts")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationStatusCode regnSts;
    @XmlElement(name = "NbOfItm")
    protected String nbOfItm;

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
     * Gets the value of the nbOfItm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbOfItm() {
        return nbOfItm;
    }

    /**
     * Sets the value of the nbOfItm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbOfItm(String value) {
        this.nbOfItm = value;
    }

}
