
package address.prxy_008_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParticipantReportResponseV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParticipantReportResponseV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}GroupHeader60"/&gt;
 *         &lt;element name="RptPrd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}DateAndMonthChoice"/&gt;
 *         &lt;element name="Ptcpt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;element name="PrtryData" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}ProprietaryReportData4"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantReportResponseV01", propOrder = {
    "grpHdr",
    "rptPrd",
    "ptcpt",
    "prtryData"
})
public class ParticipantReportResponseV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader60 grpHdr;
    @XmlElement(name = "RptPrd", required = true)
    protected DateAndMonthChoice rptPrd;
    @XmlElement(name = "Ptcpt", required = true)
    protected BranchAndFinancialInstitutionIdentification5 ptcpt;
    @XmlElement(name = "PrtryData", required = true)
    protected ProprietaryReportData4 prtryData;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader60 }
     *     
     */
    public GroupHeader60 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader60 }
     *     
     */
    public void setGrpHdr(GroupHeader60 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the rptPrd property.
     * 
     * @return
     *     possible object is
     *     {@link DateAndMonthChoice }
     *     
     */
    public DateAndMonthChoice getRptPrd() {
        return rptPrd;
    }

    /**
     * Sets the value of the rptPrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateAndMonthChoice }
     *     
     */
    public void setRptPrd(DateAndMonthChoice value) {
        this.rptPrd = value;
    }

    /**
     * Gets the value of the ptcpt property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification5 getPtcpt() {
        return ptcpt;
    }

    /**
     * Sets the value of the ptcpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public void setPtcpt(BranchAndFinancialInstitutionIdentification5 value) {
        this.ptcpt = value;
    }

    /**
     * Gets the value of the prtryData property.
     * 
     * @return
     *     possible object is
     *     {@link ProprietaryReportData4 }
     *     
     */
    public ProprietaryReportData4 getPrtryData() {
        return prtryData;
    }

    /**
     * Sets the value of the prtryData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProprietaryReportData4 }
     *     
     */
    public void setPrtryData(ProprietaryReportData4 value) {
        this.prtryData = value;
    }

}
