
package address.admn_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Switch and a Bank will use the Echo Response message to reply to the Echo Request message.
 * 
 * <p>Java class for EchoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EchoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}GrpHdr"/&gt;
 *         &lt;element name="EchoResponse" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}EchoResp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EchoResponse", propOrder = {
    "grpHdr",
    "echoResponse"
})
public class EchoResponse {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "re:EchoResponse", required = true)
    protected EchoResp echoResponse;

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
     * Gets the value of the echoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link EchoResp }
     *     
     */
    public EchoResp getEchoResponse() {
        return echoResponse;
    }

    /**
     * Sets the value of the echoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link EchoResp }
     *     
     */
    public void setEchoResponse(EchoResp value) {
        this.echoResponse = value;
    }

}
