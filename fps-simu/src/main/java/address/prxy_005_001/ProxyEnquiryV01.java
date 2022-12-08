
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Enquiry of a proxy and account registration in CAS.
 * 
 * <p>Java class for ProxyEnquiryV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}GroupHeader59"/&gt;
 *         &lt;element name="Nqry" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiryChoice1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryV01", propOrder = {
    "grpHdr",
    "nqry"
})
public class ProxyEnquiryV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader59 grpHdr;
    @XmlElement(name = "Nqry", required = true)
    protected ProxyEnquiryChoice1 nqry;

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
     * Gets the value of the nqry property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiryChoice1 }
     *     
     */
    public ProxyEnquiryChoice1 getNqry() {
        return nqry;
    }

    /**
     * Sets the value of the nqry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiryChoice1 }
     *     
     */
    public void setNqry(ProxyEnquiryChoice1 value) {
        this.nqry = value;
    }

}
