
package address.prxy_007_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParticipantReportRequestV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParticipantReportRequestV01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}GroupHeader59"/&gt;
 *         &lt;element name="RptPrd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}DateAndMonthChoice"/&gt;
 *         &lt;element name="Ptcpt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;element name="RptTp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.007.001.01}ProxyReportType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantReportRequestV01", propOrder = {
    "grpHdr",
    "rptPrd",
    "ptcpt",
    "rptTp"
})
public class ParticipantReportRequestV01 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader59 grpHdr;
    @XmlElement(name = "RptPrd", required = true)
    protected DateAndMonthChoice rptPrd;
    @XmlElement(name = "Ptcpt", required = true)
    protected BranchAndFinancialInstitutionIdentification5 ptcpt;
    @XmlElement(name = "RptTp", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyReportType rptTp;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader59 }
     *     
     */
    public GroupHeader59 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader59 }
     *     
     */
    public void setGrpHdr(GroupHeader59 value) {
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
     * Gets the value of the rptTp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyReportType }
     *     
     */
    public ProxyReportType getRptTp() {
        return rptTp;
    }

    /**
     * Sets the value of the rptTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyReportType }
     *     
     */
    public void setRptTp(ProxyReportType value) {
        this.rptTp = value;
    }

}
