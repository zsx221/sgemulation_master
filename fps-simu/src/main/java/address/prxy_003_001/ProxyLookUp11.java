
package address.prxy_003_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyLookUp11 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyLookUp11"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}Max35Text"/&gt;
 *         &lt;element name="PrxyRtrvl" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}ProxyDefintion1"/&gt;
 *         &lt;element name="PrxyRqstr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}ProxyDefintion1" minOccurs="0"/&gt;
 *         &lt;element name="DsplNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}Max140Text" minOccurs="0"/&gt;
 *         &lt;element name="AcctTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}CashAccountType2ChoiceProxy" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyLookUp11", propOrder = {
    "id",
    "prxyRtrvl",
    "prxyRqstr",
    "dsplNm",
    "acctTp"
})
public class ProxyLookUp11 {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "PrxyRtrvl", required = true)
    protected ProxyDefintion1 prxyRtrvl;
    @XmlElement(name = "PrxyRqstr")
    protected ProxyDefintion1 prxyRqstr;
    @XmlElement(name = "DsplNm")
    protected String dsplNm;
    @XmlElement(name = "AcctTp")
    protected CashAccountType2ChoiceProxy acctTp;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

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
     * Gets the value of the prxyRqstr property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public ProxyDefintion1 getPrxyRqstr() {
        return prxyRqstr;
    }

    /**
     * Sets the value of the prxyRqstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefintion1 }
     *     
     */
    public void setPrxyRqstr(ProxyDefintion1 value) {
        this.prxyRqstr = value;
    }

    /**
     * Gets the value of the dsplNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsplNm() {
        return dsplNm;
    }

    /**
     * Sets the value of the dsplNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsplNm(String value) {
        this.dsplNm = value;
    }

    /**
     * Gets the value of the acctTp property.
     * 
     * @return
     *     possible object is
     *     {@link CashAccountType2ChoiceProxy }
     *     
     */
    public CashAccountType2ChoiceProxy getAcctTp() {
        return acctTp;
    }

    /**
     * Sets the value of the acctTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashAccountType2ChoiceProxy }
     *     
     */
    public void setAcctTp(CashAccountType2ChoiceProxy value) {
        this.acctTp = value;
    }

}
