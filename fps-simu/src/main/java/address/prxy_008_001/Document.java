
package address.prxy_008_001;

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
 *         &lt;element name="PtcptRptRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}ParticipantReportResponseV01"/&gt;
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
    "ptcptRptRspn"
})
public class Document {

    @XmlElement(name = "PtcptRptRspn", required = true)
    protected ParticipantReportResponseV01 ptcptRptRspn;

    /**
     * Gets the value of the ptcptRptRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ParticipantReportResponseV01 }
     *     
     */
    public ParticipantReportResponseV01 getPtcptRptRspn() {
        return ptcptRptRspn;
    }

    /**
     * Sets the value of the ptcptRptRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParticipantReportResponseV01 }
     *     
     */
    public void setPtcptRptRspn(ParticipantReportResponseV01 value) {
        this.ptcptRptRspn = value;
    }

}
