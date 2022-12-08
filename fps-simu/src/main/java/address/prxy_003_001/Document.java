
package address.prxy_003_001;

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
 *         &lt;element name="PrxyLookUp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}ProxyLookUpV01"/&gt;
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
    "prxyLookUp"
})
public class Document {

    @XmlElement(name = "PrxyLookUp", required = true)
    protected ProxyLookUpV01 prxyLookUp;

    /**
     * Gets the value of the prxyLookUp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyLookUpV01 }
     *     
     */
    public ProxyLookUpV01 getPrxyLookUp() {
        return prxyLookUp;
    }

    /**
     * Sets the value of the prxyLookUp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyLookUpV01 }
     *     
     */
    public void setPrxyLookUp(ProxyLookUpV01 value) {
        this.prxyLookUp = value;
    }

}
