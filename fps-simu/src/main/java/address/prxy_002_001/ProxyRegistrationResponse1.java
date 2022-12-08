
package address.prxy_002_001;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ProxyRegistrationResponse1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyRegistrationResponse1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxRspnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyStatusCode"/&gt;
 *         &lt;element name="StsRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyStatusChoice" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlRegnTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyRegistrationType1Code"/&gt;
 *         &lt;element name="OrgnlRegnSubTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyRegistrationSubType1Code" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlPrxy" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyDefintion1" minOccurs="0"/&gt;
 *         &lt;element name="PrxyRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyRegistrationAccount1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyRegistrationResponse1", propOrder = {
    "prxRspnSts",
    "stsRsnInf",
    "orgnlRegnTp",
    "orgnlRegnSubTp",
    "orgnlPrxy",
    "prxyRegn"
})
public class ProxyRegistrationResponse1 {

    @XmlElement(name = "PrxRspnSts", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyStatusCode prxRspnSts;
    @XmlElement(name = "StsRsnInf")
    protected ProxyStatusChoice stsRsnInf;
    @XmlElement(name = "OrgnlRegnTp", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationType1Code orgnlRegnTp;
    @XmlElement(name = "OrgnlRegnSubTp")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationSubType1Code orgnlRegnSubTp;
    @XmlElement(name = "OrgnlPrxy")
    protected ProxyDefintion1 orgnlPrxy;
    @XmlElement(name = "PrxyRegn")
    protected List<ProxyRegistrationAccount1> prxyRegn;

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
     * Gets the value of the orgnlRegnTp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationType1Code }
     *     
     */
    public ProxyRegistrationType1Code getOrgnlRegnTp() {
        return orgnlRegnTp;
    }

    /**
     * Sets the value of the orgnlRegnTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationType1Code }
     *     
     */
    public void setOrgnlRegnTp(ProxyRegistrationType1Code value) {
        this.orgnlRegnTp = value;
    }

    /**
     * Gets the value of the orgnlRegnSubTp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationSubType1Code }
     *     
     */
    public ProxyRegistrationSubType1Code getOrgnlRegnSubTp() {
        return orgnlRegnSubTp;
    }

    /**
     * Sets the value of the orgnlRegnSubTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationSubType1Code }
     *     
     */
    public void setOrgnlRegnSubTp(ProxyRegistrationSubType1Code value) {
        this.orgnlRegnSubTp = value;
    }

    /**
     * Gets the value of the orgnlPrxy property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getOrgnlPrxy() {
        return orgnlPrxy;
    }

    /**
     * Sets the value of the orgnlPrxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setOrgnlPrxy(ProxyDefintion1 value) {
        this.orgnlPrxy = value;
    }

    /**
     * Gets the value of the prxyRegn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prxyRegn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrxyRegn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProxyRegistrationAccount1 }
     * 
     * 
     */
    public List<ProxyRegistrationAccount1> getPrxyRegn() {
        if (prxyRegn == null) {
            prxyRegn = new ArrayList<ProxyRegistrationAccount1>();
        }
        return this.prxyRegn;
    }

    public void setPrxyRegn(List<ProxyRegistrationAccount1> prxyRegn) {
        this.prxyRegn = prxyRegn;
    }
}
