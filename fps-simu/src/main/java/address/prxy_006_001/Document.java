
package address.prxy_006_001;

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
 *         &lt;element name="PrxyNqryRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryResponseV01"/&gt;
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
    "prxyNqryRspn"
})
public class Document {

    @XmlElement(name = "PrxyNqryRspn", required = true)
    protected ProxyEnquiryResponseV01 prxyNqryRspn;

    /**
     * Gets the value of the prxyNqryRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryResponseV01 }
     *     
     */
    public ProxyEnquiryResponseV01 getPrxyNqryRspn() {
        return prxyNqryRspn;
    }

    /**
     * Sets the value of the prxyNqryRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryResponseV01 }
     *     
     */
    public void setPrxyNqryRspn(ProxyEnquiryResponseV01 value) {
        this.prxyNqryRspn = value;
    }

}
