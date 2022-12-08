
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiry11 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiry11"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxyRtrvl" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyDefintion1"/&gt;
 *         &lt;element name="RegnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyRegistrationStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="NbOfItm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}Max3NumericText" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiry11", propOrder = {
    "prxyRtrvl",
    "regnSts",
    "nbOfItm"
})
public class ProxyEnquiry11 {

    @XmlElement(name = "PrxyRtrvl", required = true)
    protected ProxyDefintion1 prxyRtrvl;
    @XmlElement(name = "RegnSts")
    @XmlSchemaType(name = "string")
    protected ProxyRegistrationStatusCode regnSts;
    @XmlElement(name = "NbOfItm")
    protected String nbOfItm;

    /**
     * Gets the value of the prxyRtrvl property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getPrxyRtrvl() {
        return prxyRtrvl;
    }

    /**
     * Sets the value of the prxyRtrvl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setPrxyRtrvl(ProxyDefintion1 value) {
        this.prxyRtrvl = value;
    }

    /**
     * Gets the value of the regnSts property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationStatusCode }
     *     
     */
    public ProxyRegistrationStatusCode getRegnSts() {
        return regnSts;
    }

    /**
     * Sets the value of the regnSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationStatusCode }
     *     
     */
    public void setRegnSts(ProxyRegistrationStatusCode value) {
        this.regnSts = value;
    }

    /**
     * Gets the value of the nbOfItm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbOfItm() {
        return nbOfItm;
    }

    /**
     * Sets the value of the nbOfItm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbOfItm(String value) {
        this.nbOfItm = value;
    }

}
