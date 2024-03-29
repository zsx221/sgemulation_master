
package iso.pacs_003_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FIToFICustomerDirectDebitV02 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FIToFICustomerDirectDebitV02"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.003.001.02}GroupHeader34"/&gt;
 *         &lt;element name="DrctDbtTxInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.003.001.02}DirectDebitTransactionInformation10" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIToFICustomerDirectDebitV02", propOrder = {
    "grpHdr",
    "drctDbtTxInf"
})
public class FIToFICustomerDirectDebitV02 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader34 grpHdr;
    @XmlElement(name = "DrctDbtTxInf", required = true)
    protected List<DirectDebitTransactionInformation10> drctDbtTxInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader34 }
     *     
     */
    public GroupHeader34 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader34 }
     *     
     */
    public void setGrpHdr(GroupHeader34 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the drctDbtTxInf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drctDbtTxInf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrctDbtTxInf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectDebitTransactionInformation10 }
     * 
     * 
     */
    public List<DirectDebitTransactionInformation10> getDrctDbtTxInf() {
        if (drctDbtTxInf == null) {
            drctDbtTxInf = new ArrayList<DirectDebitTransactionInformation10>();
        }
        return this.drctDbtTxInf;
    }

}
