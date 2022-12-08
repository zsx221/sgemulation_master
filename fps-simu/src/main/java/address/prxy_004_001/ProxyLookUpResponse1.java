
package address.prxy_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyLookUpResponse1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyLookUpResponse1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrgnlId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Max35Text"/&gt;
 *         &lt;element name="OrgnlPrxyRtrvl" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyDefintion1"/&gt;
 *         &lt;element name="OrgnlPrxyRqstr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyDefintion1" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlDsplNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}Max140Text" minOccurs="0"/&gt;
 *         &lt;element name="OrgnlAcctTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}CashAccountType2ChoiceProxy" minOccurs="0"/&gt;
 *         &lt;element name="RegnRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}ProxyLookUpRegistration1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyLookUpResponse1", propOrder = {
    "orgnlId",
    "orgnlPrxyRtrvl",
    "orgnlPrxyRqstr",
    "orgnlDsplNm",
    "orgnlAcctTp",
    "regnRspn"
})
public class ProxyLookUpResponse1 {

    @XmlElement(name = "OrgnlId", required = true)
    protected String orgnlId;
    @XmlElement(name = "OrgnlPrxyRtrvl", required = true)
    protected ProxyDefintion1 orgnlPrxyRtrvl;
    @XmlElement(name = "OrgnlPrxyRqstr")
    protected ProxyDefintion1 orgnlPrxyRqstr;
    @XmlElement(name = "OrgnlDsplNm")
    protected String orgnlDsplNm;
    @XmlElement(name = "OrgnlAcctTp")
    protected CashAccountType2ChoiceProxy orgnlAcctTp;
    @XmlElement(name = "RegnRspn", required = true)
    protected ProxyLookUpRegistration1 regnRspn;

    /**
     * Gets the value of the orgnlId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlId() {
        return orgnlId;
    }

    /**
     * Sets the value of the orgnlId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlId(String value) {
        this.orgnlId = value;
    }

    /**
     * Gets the value of the orgnlPrxyRtrvl property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getOrgnlPrxyRtrvl() {
        return orgnlPrxyRtrvl;
    }

    /**
     * Sets the value of the orgnlPrxyRtrvl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setOrgnlPrxyRtrvl(ProxyDefintion1 value) {
        this.orgnlPrxyRtrvl = value;
    }

    /**
     * Gets the value of the orgnlPrxyRqstr property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getOrgnlPrxyRqstr() {
        return orgnlPrxyRqstr;
    }

    /**
     * Sets the value of the orgnlPrxyRqstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setOrgnlPrxyRqstr(ProxyDefintion1 value) {
        this.orgnlPrxyRqstr = value;
    }

    /**
     * Gets the value of the orgnlDsplNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlDsplNm() {
        return orgnlDsplNm;
    }

    /**
     * Sets the value of the orgnlDsplNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlDsplNm(String value) {
        this.orgnlDsplNm = value;
    }

    /**
     * Gets the value of the orgnlAcctTp property.
     * 
     * @return
     *     possible object is
     *     {@link CashAccountType2ChoiceProxy }
     *     
     */
    public CashAccountType2ChoiceProxy getOrgnlAcctTp() {
        return orgnlAcctTp;
    }

    /**
     * Sets the value of the orgnlAcctTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashAccountType2ChoiceProxy }
     *     
     */
    public void setOrgnlAcctTp(CashAccountType2ChoiceProxy value) {
        this.orgnlAcctTp = value;
    }

    /**
     * Gets the value of the regnRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyLookUpRegistration1 }
     *     
     */
    public ProxyLookUpRegistration1 getRegnRspn() {
        return regnRspn;
    }

    /**
     * Sets the value of the regnRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyLookUpRegistration1 }
     *     
     */
    public void setRegnRspn(ProxyLookUpRegistration1 value) {
        this.regnRspn = value;
    }

}
