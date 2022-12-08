
package iso.admn_002_001;

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
 *         &lt;element name="AdmnSignOnResp" type="{urn:iso:std:iso:20022:tech:xsd:admn.002.001.01}AdmnSignOnResp"/&gt;
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
    "admnSignOnResp"
})
public class Document {

    @XmlElement(name = "AdmnSignOnResp", required = true)
    protected AdmnSignOnResp admnSignOnResp;

    /**
     * Gets the value of the admnSignOnResp property.
     * 
     * @return
     *     possible object is
     *     {@link AdmnSignOnResp }
     *     
     */
    public AdmnSignOnResp getAdmnSignOnResp() {
        return admnSignOnResp;
    }

    /**
     * Sets the value of the admnSignOnResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdmnSignOnResp }
     *     
     */
    public void setAdmnSignOnResp(AdmnSignOnResp value) {
        this.admnSignOnResp = value;
    }

}
