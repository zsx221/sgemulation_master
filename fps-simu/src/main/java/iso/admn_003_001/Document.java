
package iso.admn_003_001;

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
 *         &lt;element name="AdmnSignOffReq" type="{urn:iso:std:iso:20022:tech:xsd:admn.003.001.01}AdmnSignOffReq"/&gt;
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
    "admnSignOffReq"
})
public class Document {

    @XmlElement(name = "AdmnSignOffReq", required = true)
    protected AdmnSignOffReq admnSignOffReq;

    /**
     * Gets the value of the admnSignOffReq property.
     * 
     * @return
     *     possible object is
     *     {@link AdmnSignOffReq }
     *     
     */
    public AdmnSignOffReq getAdmnSignOffReq() {
        return admnSignOffReq;
    }

    /**
     * Sets the value of the admnSignOffReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdmnSignOffReq }
     *     
     */
    public void setAdmnSignOffReq(AdmnSignOffReq value) {
        this.admnSignOffReq = value;
    }

}
