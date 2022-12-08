
package iso.camt_056_001;

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
 *         &lt;element name="FIToFIPmtCxlReq" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.01}FIToFIPaymentCancellationRequestV01"/&gt;
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
    "fiToFIPmtCxlReq"
})
public class Document {

    @XmlElement(name = "FIToFIPmtCxlReq", required = true)
    protected FIToFIPaymentCancellationRequestV01 fiToFIPmtCxlReq;

    /**
     * Gets the value of the fiToFIPmtCxlReq property.
     * 
     * @return
     *     possible object is
     *     {@link FIToFIPaymentCancellationRequestV01 }
     *     
     */
    public FIToFIPaymentCancellationRequestV01 getFIToFIPmtCxlReq() {
        return fiToFIPmtCxlReq;
    }

    /**
     * Sets the value of the fiToFIPmtCxlReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link FIToFIPaymentCancellationRequestV01 }
     *     
     */
    public void setFIToFIPmtCxlReq(FIToFIPaymentCancellationRequestV01 value) {
        this.fiToFIPmtCxlReq = value;
    }

}
