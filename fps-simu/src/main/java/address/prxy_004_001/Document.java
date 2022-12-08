
package address.prxy_004_001;

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
 *         &lt;element name="PrxyLookUpRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyLookUpResponseV01"/&gt;
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
    "prxyLookUpRspn"
})
public class Document {

    @XmlElement(name = "PrxyLookUpRspn", required = true)
    protected ProxyLookUpResponseV01 prxyLookUpRspn;

    /**
     * Gets the value of the prxyLookUpRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyLookUpResponseV01 }
     *     
     */
    public ProxyLookUpResponseV01 getPrxyLookUpRspn() {
        return prxyLookUpRspn;
    }

    /**
     * Sets the value of the prxyLookUpRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyLookUpResponseV01 }
     *     
     */
    public void setPrxyLookUpRspn(ProxyLookUpResponseV01 value) {
        this.prxyLookUpRspn = value;
    }

}
