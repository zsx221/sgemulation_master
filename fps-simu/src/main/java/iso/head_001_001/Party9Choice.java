
package iso.head_001_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Party9Choice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Party9Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="OrgId" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.01}PartyIdentification42"/&gt;
 *           &lt;element name="FIId" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.01}BranchAndFinancialInstitutionIdentification5"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party9Choice", propOrder = {
    "orgId",
    "fiId"
})
public class Party9Choice {

    @XmlElement(name = "OrgId")
    protected PartyIdentification42 orgId;
    @XmlElement(name = "FIId")
    protected BranchAndFinancialInstitutionIdentification5 fiId;

    /**
     * Gets the value of the orgId property.
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentification42 }
     *     
     */
    public PartyIdentification42 getOrgId() {
        return orgId;
    }

    /**
     * Sets the value of the orgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentification42 }
     *     
     */
    public void setOrgId(PartyIdentification42 value) {
        this.orgId = value;
    }

    /**
     * Gets the value of the fiId property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification5 getFIId() {
        return fiId;
    }

    /**
     * Sets the value of the fiId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification5 }
     *     
     */
    public void setFIId(BranchAndFinancialInstitutionIdentification5 value) {
        this.fiId = value;
    }

}
