
package address.prxy_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyLookUpRegistration1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyLookUpRegistration1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxRspnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyStatusCode"/&gt;
 *         &lt;element name="StsRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyStatusChoice" minOccurs="0"/&gt;
 *         &lt;element name="Prxy" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyDefintion1" minOccurs="0"/&gt;
 *         &lt;element name="LkUpRef" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Max35Text"/&gt;
 *         &lt;element name="Regn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyLookUpAccount1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyLookUpRegistration1", propOrder = {
    "prxRspnSts",
    "stsRsnInf",
    "prxy",
    "lkUpRef",
    "regn"
})
public class ProxyLookUpRegistration1 {

    @XmlElement(name = "PrxRspnSts", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyStatusCode prxRspnSts;
    @XmlElement(name = "StsRsnInf")
    protected ProxyStatusChoice stsRsnInf;
    @XmlElement(name = "Prxy")
    protected ProxyDefintion1 prxy;
    @XmlElement(name = "LkUpRef", required = true)
    protected String lkUpRef;
    @XmlElement(name = "Regn")
    protected ProxyLookUpAccount1 regn;

    /**
     * Gets the value of the prxRspnSts property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyStatusCode }
     *     
     */
    public ProxyStatusCode getPrxRspnSts() {
        return prxRspnSts;
    }

    /**
     * Sets the value of the prxRspnSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyStatusCode }
     *     
     */
    public void setPrxRspnSts(ProxyStatusCode value) {
        this.prxRspnSts = value;
    }

    /**
     * Gets the value of the stsRsnInf property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyStatusChoice }
     *     
     */
    public ProxyStatusChoice getStsRsnInf() {
        return stsRsnInf;
    }

    /**
     * Sets the value of the stsRsnInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyStatusChoice }
     *     
     */
    public void setStsRsnInf(ProxyStatusChoice value) {
        this.stsRsnInf = value;
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
     * Gets the value of the lkUpRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLkUpRef() {
        return lkUpRef;
    }

    /**
     * Sets the value of the lkUpRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLkUpRef(String value) {
        this.lkUpRef = value;
    }

    /**
     * Gets the value of the regn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyLookUpAccount1 }
     *     
     */
    public ProxyLookUpAccount1 getRegn() {
        return regn;
    }

    /**
     * Sets the value of the regn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyLookUpAccount1 }
     *     
     */
    public void setRegn(ProxyLookUpAccount1 value) {
        this.regn = value;
    }

}
