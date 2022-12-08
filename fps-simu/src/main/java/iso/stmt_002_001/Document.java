
package iso.stmt_002_001;

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
 *         &lt;element name="BankStmtResp" type="{urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01}BankStmtResp"/&gt;
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
    "bankStmtResp"
})
public class Document {

    @XmlElement(name = "BankStmtResp", required = true)
    protected BankStmtResp bankStmtResp;

    /**
     * Gets the value of the bankStmtResp property.
     * 
     * @return
     *     possible object is
     *     {@link BankStmtResp }
     *     
     */
    public BankStmtResp getBankStmtResp() {
        return bankStmtResp;
    }

    /**
     * Sets the value of the bankStmtResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankStmtResp }
     *     
     */
    public void setBankStmtResp(BankStmtResp value) {
        this.bankStmtResp = value;
    }

}
