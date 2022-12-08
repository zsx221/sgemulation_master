
package iso.stmt_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankStmtResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankStmtResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01}GrpHdr"/&gt;
 *         &lt;element name="StmtResp" type="{urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01}StmtResp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankStmtResp", propOrder = {
    "grpHdr",
    "stmtResp"
})
public class BankStmtResp {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "StmtResp", required = true)
    protected StmtResp stmtResp;

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
     * Gets the value of the stmtResp property.
     * 
     * @return
     *     possible object is
     *     {@link StmtResp }
     *     
     */
    public StmtResp getStmtResp() {
        return stmtResp;
    }

    /**
     * Sets the value of the stmtResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link StmtResp }
     *     
     */
    public void setStmtResp(StmtResp value) {
        this.stmtResp = value;
    }

}
