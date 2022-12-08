
package iso.admn_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmnEchoReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmnEchoReq"&gt;
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
@XmlType(name = "AdmnEchoReq", propOrder = {
    "grpHdr",
    "echoTxInf"
})
public class AdmnEchoReq {

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
