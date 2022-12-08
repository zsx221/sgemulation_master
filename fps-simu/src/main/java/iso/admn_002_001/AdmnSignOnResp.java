
package iso.admn_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmnSignOnResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmnSignOnResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.002.001.01}GrpHdr"/&gt;
 *         &lt;element name="SignOnResp" type="{urn:iso:std:iso:20022:tech:xsd:admn.002.001.01}SignOnResp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdmnSignOnResp", propOrder = {
    "grpHdr",
    "signOnResp"
})
public class AdmnSignOnResp {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "SignOnResp", required = true)
    protected SignOnResp signOnResp;

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
     * Gets the value of the signOnResp property.
     * 
     * @return
     *     possible object is
     *     {@link SignOnResp }
     *     
     */
    public SignOnResp getSignOnResp() {
        return signOnResp;
    }

    /**
     * Sets the value of the signOnResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOnResp }
     *     
     */
    public void setSignOnResp(SignOnResp value) {
        this.signOnResp = value;
    }

}
