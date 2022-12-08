
package iso.stmt_001_001;

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
 *         &lt;element name="BankStmtReq" type="{urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01}BankStmtReq"/&gt;
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
    "bankStmtReq"
})
public class Document {

    @XmlElement(name = "BankStmtReq", required = true)
    protected BankStmtReq bankStmtReq;

    /**
     * Gets the value of the bankStmtReq property.
     * 
     * @return
     *     possible object is
     *     {@link BankStmtReq }
     *     
     */
    public BankStmtReq getBankStmtReq() {
        return bankStmtReq;
    }

    /**
     * Sets the value of the bankStmtReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankStmtReq }
     *     
     */
    public void setBankStmtReq(BankStmtReq value) {
        this.bankStmtReq = value;
    }

}
