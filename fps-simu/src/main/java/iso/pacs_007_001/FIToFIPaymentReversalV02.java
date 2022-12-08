
package iso.pacs_007_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FIToFIPaymentReversalV02 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FIToFIPaymentReversalV02"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.007.001.02}GroupHeader41"/&gt;
 *         &lt;element name="OrgnlGrpInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.007.001.02}OriginalGroupInformation22"/&gt;
 *         &lt;element name="TxInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.007.001.02}PaymentTransactionInformation29" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIToFIPaymentReversalV02", propOrder = {
    "grpHdr",
    "orgnlGrpInf",
    "txInf"
})
public class FIToFIPaymentReversalV02 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader41 grpHdr;
    @XmlElement(name = "OrgnlGrpInf", required = true)
    protected OriginalGroupInformation22 orgnlGrpInf;
    @XmlElement(name = "TxInf")
    protected List<PaymentTransactionInformation29> txInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader41 }
     *     
     */
    public GroupHeader41 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader41 }
     *     
     */
    public void setGrpHdr(GroupHeader41 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the orgnlGrpInf property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalGroupInformation22 }
     *     
     */
    public OriginalGroupInformation22 getOrgnlGrpInf() {
        return orgnlGrpInf;
    }

    /**
     * Sets the value of the orgnlGrpInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalGroupInformation22 }
     *     
     */
    public void setOrgnlGrpInf(OriginalGroupInformation22 value) {
        this.orgnlGrpInf = value;
    }

    /**
     * Gets the value of the txInf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the txInf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTxInf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentTransactionInformation29 }
     * 
     * 
     */
    public List<PaymentTransactionInformation29> getTxInf() {
        if (txInf == null) {
            txInf = new ArrayList<PaymentTransactionInformation29>();
        }
        return this.txInf;
    }

}
