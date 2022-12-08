
package iso.camt_029_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResolutionOfInvestigationV03 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResolutionOfInvestigationV03"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Assgnmt" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}CaseAssignment2"/&gt;
 *         &lt;element name="RslvdCase" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Case2" minOccurs="0"/&gt;
 *         &lt;element name="Sts" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}InvestigationStatus2Choice"/&gt;
 *         &lt;element name="CxlDtls" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}UnderlyingTransaction3" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="StmtDtls" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}StatementResolutionEntry1" minOccurs="0"/&gt;
 *         &lt;element name="CrrctnTx" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}CorrectiveTransaction1Choice" minOccurs="0"/&gt;
 *         &lt;element name="RsltnRltdInf" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}ResolutionInformation1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResolutionOfInvestigationV03", propOrder = {
    "assgnmt",
    "rslvdCase",
    "sts",
    "cxlDtls",
    "stmtDtls",
    "crrctnTx",
    "rsltnRltdInf"
})
public class ResolutionOfInvestigationV03 {

    @XmlElement(name = "Assgnmt", required = true)
    protected CaseAssignment2 assgnmt;
    @XmlElement(name = "RslvdCase")
    protected Case2 rslvdCase;
    @XmlElement(name = "Sts", required = true)
    protected InvestigationStatus2Choice sts;
    @XmlElement(name = "CxlDtls")
    protected List<UnderlyingTransaction3> cxlDtls;
    @XmlElement(name = "StmtDtls")
    protected StatementResolutionEntry1 stmtDtls;
    @XmlElement(name = "CrrctnTx")
    protected CorrectiveTransaction1Choice crrctnTx;
    @XmlElement(name = "RsltnRltdInf")
    protected ResolutionInformation1 rsltnRltdInf;

    /**
     * Gets the value of the assgnmt property.
     * 
     * @return
     *     possible object is
     *     {@link CaseAssignment2 }
     *     
     */
    public CaseAssignment2 getAssgnmt() {
        return assgnmt;
    }

    /**
     * Sets the value of the assgnmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CaseAssignment2 }
     *     
     */
    public void setAssgnmt(CaseAssignment2 value) {
        this.assgnmt = value;
    }

    /**
     * Gets the value of the rslvdCase property.
     * 
     * @return
     *     possible object is
     *     {@link Case2 }
     *     
     */
    public Case2 getRslvdCase() {
        return rslvdCase;
    }

    /**
     * Sets the value of the rslvdCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Case2 }
     *     
     */
    public void setRslvdCase(Case2 value) {
        this.rslvdCase = value;
    }

    /**
     * Gets the value of the sts property.
     * 
     * @return
     *     possible object is
     *     {@link InvestigationStatus2Choice }
     *     
     */
    public InvestigationStatus2Choice getSts() {
        return sts;
    }

    /**
     * Sets the value of the sts property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvestigationStatus2Choice }
     *     
     */
    public void setSts(InvestigationStatus2Choice value) {
        this.sts = value;
    }

    /**
     * Gets the value of the cxlDtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cxlDtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCxlDtls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnderlyingTransaction3 }
     * 
     * 
     */
    public List<UnderlyingTransaction3> getCxlDtls() {
        if (cxlDtls == null) {
            cxlDtls = new ArrayList<UnderlyingTransaction3>();
        }
        return this.cxlDtls;
    }

    /**
     * Gets the value of the stmtDtls property.
     * 
     * @return
     *     possible object is
     *     {@link StatementResolutionEntry1 }
     *     
     */
    public StatementResolutionEntry1 getStmtDtls() {
        return stmtDtls;
    }

    /**
     * Sets the value of the stmtDtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatementResolutionEntry1 }
     *     
     */
    public void setStmtDtls(StatementResolutionEntry1 value) {
        this.stmtDtls = value;
    }

    /**
     * Gets the value of the crrctnTx property.
     * 
     * @return
     *     possible object is
     *     {@link CorrectiveTransaction1Choice }
     *     
     */
    public CorrectiveTransaction1Choice getCrrctnTx() {
        return crrctnTx;
    }

    /**
     * Sets the value of the crrctnTx property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrectiveTransaction1Choice }
     *     
     */
    public void setCrrctnTx(CorrectiveTransaction1Choice value) {
        this.crrctnTx = value;
    }

    /**
     * Gets the value of the rsltnRltdInf property.
     * 
     * @return
     *     possible object is
     *     {@link ResolutionInformation1 }
     *     
     */
    public ResolutionInformation1 getRsltnRltdInf() {
        return rsltnRltdInf;
    }

    /**
     * Sets the value of the rsltnRltdInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResolutionInformation1 }
     *     
     */
    public void setRsltnRltdInf(ResolutionInformation1 value) {
        this.rsltnRltdInf = value;
    }

}
