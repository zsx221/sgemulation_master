
package iso.admn_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmnSignOffResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmnSignOffResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.004.001.01}GrpHdr"/&gt;
 *         &lt;element name="SignOffResp" type="{urn:iso:std:iso:20022:tech:xsd:admn.004.001.01}SignOffResp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdmnSignOffResp", propOrder = {
    "grpHdr",
    "signOffResp"
})
public class AdmnSignOffResp {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "SignOffResp", required = true)
    protected SignOffResp signOffResp;

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
     * Gets the value of the signOffResp property.
     * 
     * @return
     *     possible object is
     *     {@link SignOffResp }
     *     
     */
    public SignOffResp getSignOffResp() {
        return signOffResp;
    }

    /**
     * Sets the value of the signOffResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOffResp }
     *     
     */
    public void setSignOffResp(SignOffResp value) {
        this.signOffResp = value;
    }

}
