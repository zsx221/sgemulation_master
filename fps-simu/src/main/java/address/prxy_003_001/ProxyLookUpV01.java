
package address.prxy_003_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This message is use to request a Proxy Look-Up with CAS.
 * 
 * <p>Java class for ProxyLookUpV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyLookUpV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}GroupHeader69"/&gt;
 *         &lt;element name="LookUp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}ProxyLookUpChoice1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyLookUpV01", propOrder = {
    "grpHdr",
    "lookUp"
})
public class ProxyLookUpV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader69 grpHdr;
    @XmlElement(name = "LookUp", required = true)
    protected ProxyLookUpChoice1 lookUp;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader69 }
     *     
     */
    public GroupHeader69 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader69 }
     *     
     */
    public void setGrpHdr(GroupHeader69 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the lookUp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyLookUpChoice1 }
     *     
     */
    public ProxyLookUpChoice1 getLookUp() {
        return lookUp;
    }

    /**
     * Sets the value of the lookUp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyLookUpChoice1 }
     *     
     */
    public void setLookUp(ProxyLookUpChoice1 value) {
        this.lookUp = value;
    }

}
