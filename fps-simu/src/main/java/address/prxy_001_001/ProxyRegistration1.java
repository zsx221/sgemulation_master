
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyRegistration1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyRegistration1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RegnTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistrationType1Code"/&gt;
 *         &lt;element name="RegnSubTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistrationSubType1Code" minOccurs="0"/&gt;
 *         &lt;element name="Prxy" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyDefintion1" minOccurs="0"/&gt;
 *         &lt;element name="PrxyRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01}ProxyRegistrationAccount1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyRegistration1", propOrder = {
    "regnTp",
    "regnSubTp",
    "prxy",
    "prxyRegn"
})
public class ProxyRegistration1 {

    @XmlElement(name = "RegnTp", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationType1Code regnTp;
    @XmlElement(name = "RegnSubTp")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationSubType1Code regnSubTp;
    @XmlElement(name = "Prxy")
    protected ProxyDefintion1 prxy;
    @XmlElement(name = "PrxyRegn", required = true)
    protected ProxyRegistrationAccount1 prxyRegn;

    /**
     * Gets the value of the regnTp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationType1Code }
     *     
     */
    public ProxyRegistrationType1Code getRegnTp() {
        return regnTp;
    }

    /**
     * Sets the value of the regnTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationType1Code }
     *     
     */
    public void setRegnTp(ProxyRegistrationType1Code value) {
        this.regnTp = value;
    }

    /**
     * Gets the value of the regnSubTp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationSubType1Code }
     *     
     */
    public ProxyRegistrationSubType1Code getRegnSubTp() {
        return regnSubTp;
    }

    /**
     * Sets the value of the regnSubTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationSubType1Code }
     *     
     */
    public void setRegnSubTp(ProxyRegistrationSubType1Code value) {
        this.regnSubTp = value;
    }

    /**
     * Gets the value of the prxy property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getPrxy() {
        return prxy;
    }

    /**
     * Sets the value of the prxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setPrxy(ProxyDefintion1 value) {
        this.prxy = value;
    }

    /**
     * Gets the value of the prxyRegn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationAccount1 }
     *     
     */
    public ProxyRegistrationAccount1 getPrxyRegn() {
        return prxyRegn;
    }

    /**
     * Sets the value of the prxyRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationAccount1 }
     *     
     */
    public void setPrxyRegn(ProxyRegistrationAccount1 value) {
        this.prxyRegn = value;
    }

}
