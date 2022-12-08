
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryResponseV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryResponseV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}GroupHeader60"/&gt;
 *         &lt;element name="OrgnlGrpInf" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}OriginalGroupInformation3"/&gt;
 *         &lt;element name="NqryRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ProxyEnquiryResponse1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryResponseV01", propOrder = {
    "grpHdr",
    "orgnlGrpInf",
    "nqryRspn"
})
public class ProxyEnquiryResponseV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader60 grpHdr;
    @XmlElement(name = "OrgnlGrpInf", required = true)
    protected OriginalGroupInformation3 orgnlGrpInf;
    @XmlElement(name = "NqryRspn", required = true)
    protected ProxyEnquiryResponse1 nqryRspn;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader60 }
     *     
     */
    public GroupHeader60 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader60 }
     *     
     */
    public void setGrpHdr(GroupHeader60 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the orgnlGrpInf property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalGroupInformation3 }
     *     
     */
    public OriginalGroupInformation3 getOrgnlGrpInf() {
        return orgnlGrpInf;
    }

    /**
     * Sets the value of the orgnlGrpInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalGroupInformation3 }
     *     
     */
    public void setOrgnlGrpInf(OriginalGroupInformation3 value) {
        this.orgnlGrpInf = value;
    }

    /**
     * Gets the value of the nqryRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryResponse1 }
     *     
     */
    public ProxyEnquiryResponse1 getNqryRspn() {
        return nqryRspn;
    }

    /**
     * Sets the value of the nqryRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryResponse1 }
     *     
     */
    public void setNqryRspn(ProxyEnquiryResponse1 value) {
        this.nqryRspn = value;
    }

}
