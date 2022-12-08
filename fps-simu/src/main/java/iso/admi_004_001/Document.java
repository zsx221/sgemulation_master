
package iso.admi_004_001;

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
 *         &lt;element name="admi.004.001.01" type="{urn:iso:std:iso:20022:tech:xsd:admi.004.001.01}admi.004.001.01"/&gt;
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
    "admi00400101"
})
public class Document {

    @XmlElement(name = "admi.004.001.01", required = true)
    protected Admi00400101 admi00400101;

    /**
     * Gets the value of the admi00400101 property.
     * 
     * @return
     *     possible object is
     *     {@link Admi00400101 }
     *     
     */
    public Admi00400101 getAdmi00400101() {
        return admi00400101;
    }

    /**
     * Sets the value of the admi00400101 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Admi00400101 }
     *     
     */
    public void setAdmi00400101(Admi00400101 value) {
        this.admi00400101 = value;
    }

}
