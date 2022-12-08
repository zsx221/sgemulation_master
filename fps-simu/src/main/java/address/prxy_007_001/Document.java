
package address.prxy_007_001;

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
 *         &lt;element name="PtcptRptReq" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}ParticipantReportRequestV01"/&gt;
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
    "ptcptRptReq"
})
public class Document {

    @XmlElement(name = "PtcptRptReq", required = true)
    protected ParticipantReportRequestV01 ptcptRptReq;

    /**
     * Gets the value of the ptcptRptReq property.
     * 
     * @return
     *     possible object is
     *     {@link ParticipantReportRequestV01 }
     *     
     */
    public ParticipantReportRequestV01 getPtcptRptReq() {
        return ptcptRptReq;
    }

    /**
     * Sets the value of the ptcptRptReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParticipantReportRequestV01 }
     *     
     */
    public void setPtcptRptReq(ParticipantReportRequestV01 value) {
        this.ptcptRptReq = value;
    }

}
