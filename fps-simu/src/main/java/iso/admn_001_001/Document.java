
package iso.admn_001_001;

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
 *         &lt;element name="AdmnSignOnReq" type="{urn:iso:std:iso:20022:tech:xsd:admn.001.001.01}AdmnSignOnReq"/&gt;
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
    "admnSignOnReq"
})
public class Document {

    @XmlElement(name = "AdmnSignOnReq", required = true)
    protected AdmnSignOnReq admnSignOnReq;

    /**
     * Gets the value of the admnSignOnReq property.
     * 
     * @return
     *     possible object is
     *     {@link AdmnSignOnReq }
     *     
     */
    public AdmnSignOnReq getAdmnSignOnReq() {
        return admnSignOnReq;
    }

    /**
     * Sets the value of the admnSignOnReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdmnSignOnReq }
     *     
     */
    public void setAdmnSignOnReq(AdmnSignOnReq value) {
        this.admnSignOnReq = value;
    }

}
