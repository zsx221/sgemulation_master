
package iso.pacs_002_001;

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
 *         &lt;element name="FIToFIPmtStsRpt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}FIToFIPaymentStatusReportV03"/&gt;
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
    "fiToFIPmtStsRpt"
})
public class Document {

    @XmlElement(name = "FIToFIPmtStsRpt", required = true)
    protected FIToFIPaymentStatusReportV03 fiToFIPmtStsRpt;

    /**
     * Gets the value of the fiToFIPmtStsRpt property.
     * 
     * @return
     *     possible object is
     *     {@link FIToFIPaymentStatusReportV03 }
     *     
     */
    public FIToFIPaymentStatusReportV03 getFIToFIPmtStsRpt() {
        return fiToFIPmtStsRpt;
    }

    /**
     * Sets the value of the fiToFIPmtStsRpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link FIToFIPaymentStatusReportV03 }
     *     
     */
    public void setFIToFIPmtStsRpt(FIToFIPaymentStatusReportV03 value) {
        this.fiToFIPmtStsRpt = value;
    }

}
