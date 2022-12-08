
package address.admn_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EchoResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EchoResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InstgAgt" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}BranchAndFinancialInstitutionIdentification4"/&gt;
 *         &lt;element name="InstdAgt" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}BranchAndFinancialInstitutionIdentification4"/&gt;
 *         &lt;element name="OrgnlInstrId" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}Max35Text"/&gt;
 *         &lt;element name="FnctnCd" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}EchoCode"/&gt;
 *         &lt;element name="TxSts" type="{urn:iso:std:iso:20022:tech:xsd:admn.006.001.01}TransactionIndividualStatus3Code_Echo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EchoResp", propOrder = {
    "instgAgt",
    "instdAgt",
    "orgnlInstrId",
    "fnctnCd",
    "txSts"
})
public class EchoResp {

    @XmlElement(name = "InstgAgt", required = true)
    protected BranchAndFinancialInstitutionIdentification4 instgAgt;
    @XmlElement(name = "InstdAgt", required = true)
    protected BranchAndFinancialInstitutionIdentification4 instdAgt;
    @XmlElement(name = "OrgnlInstrId", required = true)
    protected String orgnlInstrId;
    @XmlElement(name = "FnctnCd", required = true)
    protected String fnctnCd;
    @XmlElement(name = "TxSts", required = true)
    @XmlSchemaType(name = "string")
    protected TransactionIndividualStatus3CodeEcho txSts;

    /**
     * Gets the value of the instgAgt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getInstgAgt() {
        return instgAgt;
    }

    /**
     * Sets the value of the instgAgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setInstgAgt(BranchAndFinancialInstitutionIdentification4 value) {
        this.instgAgt = value;
    }

    /**
     * Gets the value of the instdAgt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification4 getInstdAgt() {
        return instdAgt;
    }

    /**
     * Sets the value of the instdAgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setInstdAgt(BranchAndFinancialInstitutionIdentification4 value) {
        this.instdAgt = value;
    }

    /**
     * Gets the value of the orgnlInstrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlInstrId() {
        return orgnlInstrId;
    }

    /**
     * Sets the value of the orgnlInstrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlInstrId(String value) {
        this.orgnlInstrId = value;
    }

    /**
     * Gets the value of the fnctnCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFnctnCd() {
        return fnctnCd;
    }

    /**
     * Sets the value of the fnctnCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFnctnCd(String value) {
        this.fnctnCd = value;
    }

    /**
     * Gets the value of the txSts property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionIndividualStatus3CodeEcho }
     *     
     */
    public TransactionIndividualStatus3CodeEcho getTxSts() {
        return txSts;
    }

    /**
     * Sets the value of the txSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionIndividualStatus3CodeEcho }
     *     
     */
    public void setTxSts(TransactionIndividualStatus3CodeEcho value) {
        this.txSts = value;
    }

}
