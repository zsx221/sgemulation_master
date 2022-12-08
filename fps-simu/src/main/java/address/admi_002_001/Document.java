
package address.admi_002_001;

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
 *         &lt;element name="admi.002.001.01" type="{urn:iso:std:iso:20022:tech:xsd:admi.002.001.01}MessageRejectV01"/&gt;
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
    "admi00200101"
})
public class Document {

    @XmlElement(name = "admi.002.001.01", required = true)
    protected MessageRejectV01 admi00200101;

    /**
     * Gets the value of the admi00200101 property.
     * 
     * @return
     *     possible object is
     *     {@link MessageRejectV01 }
     *     
     */
    public MessageRejectV01 getAdmi00200101() {
        return admi00200101;
    }

    /**
     * Sets the value of the admi00200101 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageRejectV01 }
     *     
     */
    public void setAdmi00200101(MessageRejectV01 value) {
        this.admi00200101 = value;
    }

}
