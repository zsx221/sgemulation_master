
package iso.camt_029_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CorrectiveTransaction1Choice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorrectiveTransaction1Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Initn" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}CorrectivePaymentInitiation1"/&gt;
 *           &lt;element name="IntrBk" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}CorrectiveInterbankTransaction1"/&gt;
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
@XmlType(name = "CorrectiveTransaction1Choice", propOrder = {
    "initn",
    "intrBk"
})
public class CorrectiveTransaction1Choice {

    @XmlElement(name = "Initn")
    protected CorrectivePaymentInitiation1 initn;
    @XmlElement(name = "IntrBk")
    protected CorrectiveInterbankTransaction1 intrBk;

    /**
     * Gets the value of the initn property.
     * 
     * @return
     *     possible object is
     *     {@link CorrectivePaymentInitiation1 }
     *     
     */
    public CorrectivePaymentInitiation1 getInitn() {
        return initn;
    }

    /**
     * Sets the value of the initn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrectivePaymentInitiation1 }
     *     
     */
    public void setInitn(CorrectivePaymentInitiation1 value) {
        this.initn = value;
    }

    /**
     * Gets the value of the intrBk property.
     * 
     * @return
     *     possible object is
     *     {@link CorrectiveInterbankTransaction1 }
     *     
     */
    public CorrectiveInterbankTransaction1 getIntrBk() {
        return intrBk;
    }

    /**
     * Sets the value of the intrBk property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrectiveInterbankTransaction1 }
     *     
     */
    public void setIntrBk(CorrectiveInterbankTransaction1 value) {
        this.intrBk = value;
    }

}
