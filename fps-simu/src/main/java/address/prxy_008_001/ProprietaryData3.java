
package address.prxy_008_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProprietaryData3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProprietaryData3"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ActvtySummryRpt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}ActivitySummaryReport01"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProprietaryData3", propOrder = {
    "actvtySummryRpt"
})
public class ProprietaryData3 {

    @XmlElement(name = "ActvtySummryRpt")
    protected ActivitySummaryReport01 actvtySummryRpt;

    /**
     * Gets the value of the actvtySummryRpt property.
     * 
     * @return
     *     possible object is
     *     {@link ActivitySummaryReport01 }
     *     
     */
    public ActivitySummaryReport01 getActvtySummryRpt() {
        return actvtySummryRpt;
    }

    /**
     * Sets the value of the actvtySummryRpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivitySummaryReport01 }
     *     
     */
    public void setActvtySummryRpt(ActivitySummaryReport01 value) {
        this.actvtySummryRpt = value;
    }

}
