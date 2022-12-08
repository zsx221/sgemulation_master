
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxyNqryReq" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiryV01"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "prxyNqryReq"
})
public class Document {

    @XmlElement(name = "PrxyNqryReq", required = true)
    protected ProxyEnquiryV01 prxyNqryReq;

    /**
     * Gets the value of the prxyNqryReq property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryV01 }
     *     
     */
    public ProxyEnquiryV01 getPrxyNqryReq() {
        return prxyNqryReq;
    }

    /**
     * Sets the value of the prxyNqryReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryV01 }
     *     
     */
    public void setPrxyNqryReq(ProxyEnquiryV01 value) {
        this.prxyNqryReq = value;
    }

}
