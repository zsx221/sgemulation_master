
package iso.admn_003_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmnSignOffReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmnSignOffReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.003.001.01}GrpHdr"/&gt;
 *         &lt;element name="SignOffReq" type="{urn:iso:std:iso:20022:tech:xsd:admn.003.001.01}SignOffReq"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdmnSignOffReq", propOrder = {
    "grpHdr",
    "signOffReq"
})
public class AdmnSignOffReq {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "SignOffReq", required = true)
    protected SignOffReq signOffReq;

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
     * Gets the value of the signOffReq property.
     * 
     * @return
     *     possible object is
     *     {@link SignOffReq }
     *     
     */
    public SignOffReq getSignOffReq() {
        return signOffReq;
    }

    /**
     * Sets the value of the signOffReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOffReq }
     *     
     */
    public void setSignOffReq(SignOffReq value) {
        this.signOffReq = value;
    }

}
