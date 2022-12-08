
package iso.stmt_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankStmtReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankStmtReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01}GrpHdr"/&gt;
 *         &lt;element name="StmtReq" type="{urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01}StmtReq"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankStmtReq", propOrder = {
    "grpHdr",
    "stmtReq"
})
public class BankStmtReq {

    @XmlElement(name = "GrpHdr", required = true)
    protected GrpHdr grpHdr;
    @XmlElement(name = "StmtReq", required = true)
    protected StmtReq stmtReq;

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
     * Gets the value of the stmtReq property.
     * 
     * @return
     *     possible object is
     *     {@link StmtReq }
     *     
     */
    public StmtReq getStmtReq() {
        return stmtReq;
    }

    /**
     * Sets the value of the stmtReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link StmtReq }
     *     
     */
    public void setStmtReq(StmtReq value) {
        this.stmtReq = value;
    }

}
