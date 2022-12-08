
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Party30Choice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Party30Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Org" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Organisation22"/&gt;
 *         &lt;element name="IndvPrsn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}IndividualPerson28"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party30Choice", propOrder = {
    "org",
    "indvPrsn"
})
public class Party30Choice {

    @XmlElement(name = "Org")
    protected Organisation22 org;
    @XmlElement(name = "IndvPrsn")
    protected IndividualPerson28 indvPrsn;

    /**
     * Gets the value of the org property.
     * 
     * @return
     *     possible object is
     *     {@link Organisation22 }
     *     
     */
    public Organisation22 getOrg() {
        return org;
    }

    /**
     * Sets the value of the org property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organisation22 }
     *     
     */
    public void setOrg(Organisation22 value) {
        this.org = value;
    }

    /**
     * Gets the value of the indvPrsn property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualPerson28 }
     *     
     */
    public IndividualPerson28 getIndvPrsn() {
        return indvPrsn;
    }

    /**
     * Sets the value of the indvPrsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualPerson28 }
     *     
     */
    public void setIndvPrsn(IndividualPerson28 value) {
        this.indvPrsn = value;
    }

}
