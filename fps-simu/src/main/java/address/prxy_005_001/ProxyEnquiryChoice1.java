
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryChoice1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryChoice1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="RegnId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}Max35Text"/&gt;
 *         &lt;element name="PrxyOnly" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiry11"/&gt;
 *         &lt;element name="AccOnly" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiry12"/&gt;
 *         &lt;element name="PrxyAcc" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiry13"/&gt;
 *         &lt;element name="NonOwngPrxy" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiry14"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryChoice1", propOrder = {
    "regnId",
    "prxyOnly",
    "accOnly",
    "prxyAcc",
    "nonOwngPrxy"
})
public class ProxyEnquiryChoice1 {

    @XmlElement(name = "RegnId")
    protected String regnId;
    @XmlElement(name = "PrxyOnly")
    protected ProxyEnquiry11 prxyOnly;
    @XmlElement(name = "AccOnly")
    protected ProxyEnquiry12 accOnly;
    @XmlElement(name = "PrxyAcc")
    protected ProxyEnquiry13 prxyAcc;
    @XmlElement(name = "NonOwngPrxy")
    protected ProxyEnquiry14 nonOwngPrxy;

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
     * Gets the value of the prxyOnly property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiry11 }
     *     
     */
    public ProxyEnquiry11 getPrxyOnly() {
        return prxyOnly;
    }

    /**
     * Sets the value of the prxyOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiry11 }
     *     
     */
    public void setPrxyOnly(ProxyEnquiry11 value) {
        this.prxyOnly = value;
    }

    /**
     * Gets the value of the accOnly property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiry12 }
     *     
     */
    public ProxyEnquiry12 getAccOnly() {
        return accOnly;
    }

    /**
     * Sets the value of the accOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiry12 }
     *     
     */
    public void setAccOnly(ProxyEnquiry12 value) {
        this.accOnly = value;
    }

    /**
     * Gets the value of the prxyAcc property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiry13 }
     *     
     */
    public ProxyEnquiry13 getPrxyAcc() {
        return prxyAcc;
    }

    /**
     * Sets the value of the prxyAcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiry13 }
     *     
     */
    public void setPrxyAcc(ProxyEnquiry13 value) {
        this.prxyAcc = value;
    }

    /**
     * Gets the value of the nonOwngPrxy property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiry14 }
     *     
     */
    public ProxyEnquiry14 getNonOwngPrxy() {
        return nonOwngPrxy;
    }

    /**
     * Sets the value of the nonOwngPrxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiry14 }
     *     
     */
    public void setNonOwngPrxy(ProxyEnquiry14 value) {
        this.nonOwngPrxy = value;
    }

}
