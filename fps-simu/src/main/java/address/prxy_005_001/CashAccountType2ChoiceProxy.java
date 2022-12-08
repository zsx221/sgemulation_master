
package address.prxy_005_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CashAccountType2ChoiceProxy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CashAccountType2ChoiceProxy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Prtry" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyAccountType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CashAccountType2ChoiceProxy", propOrder = {
    "prtry"
})
public class CashAccountType2ChoiceProxy {

    @XmlElement(name = "Prtry")
    @XmlSchemaType(name = "string")
    protected ProxyAccountType prtry;

    /**
     * Gets the value of the prtry property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyAccountType }
     *     
     */
    public ProxyAccountType getPrtry() {
        return prtry;
    }

    /**
     * Sets the value of the prtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyAccountType }
     *     
     */
    public void setPrtry(ProxyAccountType value) {
        this.prtry = value;
    }

}
