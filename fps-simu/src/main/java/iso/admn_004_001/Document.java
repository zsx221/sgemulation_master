
package iso.admn_004_001;

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
 *         &lt;element name="AdmnSignOffResp" type="{urn:iso:std:iso:20022:tech:xsd:admn.004.001.01}AdmnSignOffResp"/&gt;
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
    "admnSignOffResp"
})
public class Document {

    @XmlElement(name = "AdmnSignOffResp", required = true)
    protected AdmnSignOffResp admnSignOffResp;

    /**
     * Gets the value of the admnSignOffResp property.
     * 
     * @return
     *     possible object is
     *     {@link AdmnSignOffResp }
     *     
     */
    public AdmnSignOffResp getAdmnSignOffResp() {
        return admnSignOffResp;
    }

    /**
     * Sets the value of the admnSignOffResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdmnSignOffResp }
     *     
     */
    public void setAdmnSignOffResp(AdmnSignOffResp value) {
        this.admnSignOffResp = value;
    }

}
