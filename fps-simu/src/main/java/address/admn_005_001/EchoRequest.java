
package address.admn_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * The Switch and a Bank will use the Echo Request message to perform the connectivity status check.
 * The Switch will attempt to send an Echo Request only when there is no activity from a Bank for a pre-determined length of time*.  The Bank will be marked as Unavailable if no response is received after a number of retries* or there are no further requests or responses from it.  This will prevent the Bank from receiving or sending payment requests.  A System Notification Message will be sent to all Banks to advise them of  this unavailability.
 * The Switch is required to send an Echo Request in order to ensure that the Bank is available to receive payment requests.  
 * It is optional for a Bank to send Echo Request to the Switch.
 * (*The number of and interval between retries is configurable).
 *       
 * 
 * <p>Java class for EchoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EchoRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:admn.005.001.01}GrpHdr"/&gt;
 *         &lt;element name="EchoTxInf" type="{urn:iso:std:iso:20022:tech:xsd:admn.005.001.01}EchoTxInf"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EchoRequest", propOrder = {
    "grpHdr",
    "echoTxInf"
})
public class EchoRequest {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "EchoTxInf", required = true)
    protected EchoTxInf echoTxInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GrpHdr }
     *     
     */
    public GrpHdr getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrpHdr }
     *     
     */
    public void setGrpHdr(GrpHdr value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the echoTxInf property.
     * 
     * @return
     *     possible object is
     *     {@link EchoTxInf }
     *     
     */
    public EchoTxInf getEchoTxInf() {
        return echoTxInf;
    }

    /**
     * Sets the value of the echoTxInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link EchoTxInf }
     *     
     */
    public void setEchoTxInf(EchoTxInf value) {
        this.echoTxInf = value;
    }

}
