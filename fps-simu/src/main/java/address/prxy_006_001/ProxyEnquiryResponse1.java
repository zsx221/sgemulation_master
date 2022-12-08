
package address.prxy_006_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryResponse1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryResponse1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxRspnSts" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyStatusCode"/&gt;
 *         &lt;element name="StsRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyStatusChoice" minOccurs="0"/&gt;
 *         &lt;element name="Rspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryInformation1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryResponse1", propOrder = {
    "prxRspnSts",
    "stsRsnInf",
    "rspn"
})
public class ProxyEnquiryResponse1 {

    @XmlElement(name = "PrxRspnSts", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyStatusCode prxRspnSts;
    @XmlElement(name = "StsRsnInf")
    protected ProxyStatusChoice stsRsnInf;
    @XmlElement(name = "Rspn")
    protected List<ProxyEnquiryInformation1> rspn;

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
     * Gets the value of the rspn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rspn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRspn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProxyEnquiryInformation1 }
     * 
     * 
     */
    public List<ProxyEnquiryInformation1> getRspn() {
        if (rspn == null) {
            rspn = new ArrayList<ProxyEnquiryInformation1>();
        }
        return this.rspn;
    }

}
