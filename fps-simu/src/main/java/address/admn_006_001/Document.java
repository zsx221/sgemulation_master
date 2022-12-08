
package address.admn_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdmnEchoResp" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}EchoResponse"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "admnEchoResp"
})
public class Document {

    @XmlElement(name = "AdmnEchoResp", required = true)
    protected EchoResponse admnEchoResp;

    /**
     * Gets the value of the admnEchoResp property.
     * 
     * @return
     *     possible object is
     *     {@link EchoResponse }
     *     
     */
    public EchoResponse getAdmnEchoResp() {
        return admnEchoResp;
    }

    /**
     * Sets the value of the admnEchoResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link EchoResponse }
     *     
     */
    public void setAdmnEchoResp(EchoResponse value) {
        this.admnEchoResp = value;
    }

}
