
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Organisation22 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organisation22"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Nm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}Max350Text"/&gt;
 *         &lt;element name="RegnDt" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}ISODate"/&gt;
 *         &lt;element name="TpOfOrg" type="{urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01}OrganisationType1Choice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organisation22", propOrder = {
    "nm",
    "regnDt",
    "tpOfOrg"
})
public class Organisation22 {

    @XmlElement(name = "Nm", required = true)
    protected String nm;
    @XmlElement(name = "RegnDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar regnDt;
    @XmlElement(name = "TpOfOrg")
    protected OrganisationType1Choice tpOfOrg;

    /**
     * Gets the value of the nm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNm() {
        return nm;
    }

    /**
     * Sets the value of the nm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNm(String value) {
        this.nm = value;
    }

    /**
     * Gets the value of the regnDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegnDt() {
        return regnDt;
    }

    /**
     * Sets the value of the regnDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegnDt(XMLGregorianCalendar value) {
        this.regnDt = value;
    }

    /**
     * Gets the value of the tpOfOrg property.
     * 
     * @return
     *     possible object is
     *     {@link OrganisationType1Choice }
     *     
     */
    public OrganisationType1Choice getTpOfOrg() {
        return tpOfOrg;
    }

    /**
     * Sets the value of the tpOfOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationType1Choice }
     *     
     */
    public void setTpOfOrg(OrganisationType1Choice value) {
        this.tpOfOrg = value;
    }

}
