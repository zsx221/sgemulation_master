
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryDefintion1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryDefintion1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Max12Text"/&gt;
 *         &lt;element name="Val" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Max140Text"/&gt;
 *         &lt;element name="Sts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryStatusCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryDefintion1", propOrder = {
    "tp",
    "val",
    "sts"
})
public class ProxyEnquiryDefintion1 {

    @XmlElement(name = "Tp", required = true)
    protected String tp;
    @XmlElement(name = "Val", required = true)
    protected String val;
    @XmlElement(name = "Sts")
    @XmlSchemaType(name = "string")
    protected ProxyEnquiryStatusCode sts;

    /**
     * Gets the value of the tp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTp() {
        return tp;
    }

    /**
     * Sets the value of the tp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTp(String value) {
        this.tp = value;
    }

    /**
     * Gets the value of the val property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVal() {
        return val;
    }

    /**
     * Sets the value of the val property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVal(String value) {
        this.val = value;
    }

    /**
     * Gets the value of the sts property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryStatusCode }
     *     
     */
    public ProxyEnquiryStatusCode getSts() {
        return sts;
    }

    /**
     * Sets the value of the sts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryStatusCode }
     *     
     */
    public void setSts(ProxyEnquiryStatusCode value) {
        this.sts = value;
    }

}