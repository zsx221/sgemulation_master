
package address.admi_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RejectionReason2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RejectionReason2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RjctgPtyRsn" type="{urn:iso:std:iso:20022:tech:xsd:admi.002.001.01}Max35Text"/&gt;
 *         &lt;element name="AddtlData" type="{urn:iso:std:iso:20022:tech:xsd:admi.002.001.01}Max20000Text" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RejectionReason2", propOrder = {
    "rjctgPtyRsn",
    "addtlData"
})
public class RejectionReason2 {

    @XmlElement(name = "RjctgPtyRsn", required = true)
    protected String rjctgPtyRsn;
    @XmlElement(name = "AddtlData")
    protected String addtlData;

    /**
     * Gets the value of the rjctgPtyRsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRjctgPtyRsn() {
        return rjctgPtyRsn;
    }

    /**
     * Sets the value of the rjctgPtyRsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRjctgPtyRsn(String value) {
        this.rjctgPtyRsn = value;
    }

    /**
     * Gets the value of the addtlData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddtlData() {
        return addtlData;
    }

    /**
     * Sets the value of the addtlData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddtlData(String value) {
        this.addtlData = value;
    }

}
