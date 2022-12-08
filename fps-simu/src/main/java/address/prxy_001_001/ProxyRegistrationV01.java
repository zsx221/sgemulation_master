
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Registration, Override, Amendment and Deactivation of a proxy and account registration in CAS.
 * 
 * <p>Java class for ProxyRegistrationV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyRegistrationV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}GroupHeader59"/&gt;
 *         &lt;element name="Regn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistration1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyRegistrationV01", propOrder = {
    "grpHdr",
    "regn"
})
public class ProxyRegistrationV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader59 grpHdr;
    @XmlElement(name = "Regn", required = true)
    protected ProxyRegistration1 regn;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader59 }
     *     
     */
    public GroupHeader59 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader59 }
     *     
     */
    public void setGrpHdr(GroupHeader59 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the regn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistration1 }
     *     
     */
    public ProxyRegistration1 getRegn() {
        return regn;
    }

    /**
     * Sets the value of the regn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistration1 }
     *     
     */
    public void setRegn(ProxyRegistration1 value) {
        this.regn = value;
    }

}
