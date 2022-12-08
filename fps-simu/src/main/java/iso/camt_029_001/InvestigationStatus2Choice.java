
package iso.camt_029_001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvestigationStatus2Choice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvestigationStatus2Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Conf" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}InvestigationExecutionConfirmation3Code"/&gt;
 *           &lt;element name="RjctdMod" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}ModificationRejection2Code" maxOccurs="unbounded"/&gt;
 *           &lt;element name="DplctOf" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}Case2"/&gt;
 *           &lt;element name="AssgnmtCxlConf" type="{urn:iso:std:iso:20022:tech:xsd:camt.029.001.03}YesNoIndicator"/&gt;
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
@XmlType(name = "InvestigationStatus2Choice", propOrder = {
    "conf",
    "rjctdMod",
    "dplctOf",
    "assgnmtCxlConf"
})
public class InvestigationStatus2Choice {

    @XmlElement(name = "Conf")
    @XmlSchemaType(name = "string")
    protected InvestigationExecutionConfirmation3Code conf;
    @XmlElement(name = "RjctdMod")
    @XmlSchemaType(name = "string")
    protected List<ModificationRejection2Code> rjctdMod;
    @XmlElement(name = "DplctOf")
    protected Case2 dplctOf;
    @XmlElement(name = "AssgnmtCxlConf")
    protected Boolean assgnmtCxlConf;

    /**
     * Gets the value of the conf property.
     * 
     * @return
     *     possible object is
     *     {@link InvestigationExecutionConfirmation3Code }
     *     
     */
    public InvestigationExecutionConfirmation3Code getConf() {
        return conf;
    }

    /**
     * Sets the value of the conf property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvestigationExecutionConfirmation3Code }
     *     
     */
    public void setConf(InvestigationExecutionConfirmation3Code value) {
        this.conf = value;
    }

    /**
     * Gets the value of the rjctdMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rjctdMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRjctdMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationRejection2Code }
     * 
     * 
     */
    public List<ModificationRejection2Code> getRjctdMod() {
        if (rjctdMod == null) {
            rjctdMod = new ArrayList<ModificationRejection2Code>();
        }
        return this.rjctdMod;
    }

    /**
     * Gets the value of the dplctOf property.
     * 
     * @return
     *     possible object is
     *     {@link Case2 }
     *     
     */
    public Case2 getDplctOf() {
        return dplctOf;
    }

    /**
     * Sets the value of the dplctOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Case2 }
     *     
     */
    public void setDplctOf(Case2 value) {
        this.dplctOf = value;
    }

    /**
     * Gets the value of the assgnmtCxlConf property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssgnmtCxlConf() {
        return assgnmtCxlConf;
    }

    /**
     * Sets the value of the assgnmtCxlConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssgnmtCxlConf(Boolean value) {
        this.assgnmtCxlConf = value;
    }

}
