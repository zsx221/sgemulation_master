
package address.prxy_003_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GroupHeader69 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupHeader69"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MsgId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}Max35Text"/&gt;
 *         &lt;element name="CreDtTm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}ISODateTime"/&gt;
 *         &lt;element name="MsgSndr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}Party12Choice"/&gt;
 *         &lt;element name="TpOfSubmitr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.003.001.01}SubmitterType1Choice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupHeader69", propOrder = {
    "msgId",
    "creDtTm",
    "msgSndr",
    "tpOfSubmitr"
})
public class GroupHeader69 {

    @XmlElement(name = "MsgId", required = true)
    protected String msgId;
    @XmlElement(name = "CreDtTm", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creDtTm;
    @XmlElement(name = "MsgSndr", required = true)
    protected Party12Choice msgSndr;
    @XmlElement(name = "TpOfSubmitr")
    protected SubmitterType1Choice tpOfSubmitr;

    /**
     * Gets the value of the msgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
    }

    /**
     * Gets the value of the creDtTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreDtTm() {
        return creDtTm;
    }

    /**
     * Sets the value of the creDtTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreDtTm(XMLGregorianCalendar value) {
        this.creDtTm = value;
    }

    /**
     * Gets the value of the msgSndr property.
     * 
     * @return
     *     possible object is
     *     {@link Party12Choice }
     *     
     */
    public Party12Choice getMsgSndr() {
        return msgSndr;
    }

    /**
     * Sets the value of the msgSndr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party12Choice }
     *     
     */
    public void setMsgSndr(Party12Choice value) {
        this.msgSndr = value;
    }

    /**
     * Gets the value of the tpOfSubmitr property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitterType1Choice }
     *     
     */
    public SubmitterType1Choice getTpOfSubmitr() {
        return tpOfSubmitr;
    }

    /**
     * Sets the value of the tpOfSubmitr property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitterType1Choice }
     *     
     */
    public void setTpOfSubmitr(SubmitterType1Choice value) {
        this.tpOfSubmitr = value;
    }

}
