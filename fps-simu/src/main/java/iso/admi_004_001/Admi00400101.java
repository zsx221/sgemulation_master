
package iso.admi_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for admi.004.001.01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="admi.004.001.01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EvtInf" type="{urn:iso:std:iso:20022:tech:xsd:admi.004.001.01}Event1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "admi.004.001.01", propOrder = {
    "evtInf"
})
public class Admi00400101 {

    @XmlElement(name = "EvtInf", required = true)
    protected Event1 evtInf;

    /**
     * Gets the value of the evtInf property.
     * 
     * @return
     *     possible object is
     *     {@link Event1 }
     *     
     */
    public Event1 getEvtInf() {
        return evtInf;
    }

    /**
     * Sets the value of the evtInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Event1 }
     *     
     */
    public void setEvtInf(Event1 value) {
        this.evtInf = value;
    }

}
