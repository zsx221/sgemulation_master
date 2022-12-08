
package iso.admn_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmnSignOnReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmnSignOnReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.001.001.01}GrpHdr"/&gt;
 *         &lt;element name="SignOnReq" type="{urn:iso:std:iso:20022:tech:xsd:admn.001.001.01}SignOnReq"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdmnSignOnReq", propOrder = {
    "grpHdr",
    "signOnReq"
})
public class AdmnSignOnReq {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "SignOnReq", required = true)
    protected SignOnReq signOnReq;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GrpHdr }
     *     
     */
    public GrpHdr getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrpHdr }
     *     
     */
    public void setGrpHdr(GrpHdr value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the signOnReq property.
     * 
     * @return
     *     possible object is
     *     {@link SignOnReq }
     *     
     */
    public SignOnReq getSignOnReq() {
        return signOnReq;
    }

    /**
     * Sets the value of the signOnReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOnReq }
     *     
     */
    public void setSignOnReq(SignOnReq value) {
        this.signOnReq = value;
    }

}
