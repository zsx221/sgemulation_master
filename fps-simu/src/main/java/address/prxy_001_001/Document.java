
package address.prxy_001_001;

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
 *         &lt;element name="PrxyRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistrationV01"/&gt;
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
    "prxyRegn"
})
public class Document {

    @XmlElement(name = "PrxyRegn", required = true)
    protected ProxyRegistrationV01 prxyRegn;

    /**
     * Gets the value of the prxyRegn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationV01 }
     *     
     */
    public ProxyRegistrationV01 getPrxyRegn() {
        return prxyRegn;
    }

    /**
     * Sets the value of the prxyRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationV01 }
     *     
     */
    public void setPrxyRegn(ProxyRegistrationV01 value) {
        this.prxyRegn = value;
    }

}
