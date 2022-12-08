
package address.admn_005_001;

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
 *         &lt;element name="AdmnEchoReq" type="{urn:iso:std:iso:20022:tech:xsd:admn.005.001.01}EchoRequest"/&gt;
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
    "admnEchoReq"
})
public class Document {

    @XmlElement(name = "AdmnEchoReq", required = true)
    protected EchoRequest admnEchoReq;

    /**
     * Gets the value of the admnEchoReq property.
     * 
     * @return
     *     possible object is
     *     {@link EchoRequest }
     *     
     */
    public EchoRequest getAdmnEchoReq() {
        return admnEchoReq;
    }

    /**
     * Sets the value of the admnEchoReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link EchoRequest }
     *     
     */
    public void setAdmnEchoReq(EchoRequest value) {
        this.admnEchoReq = value;
    }

}
