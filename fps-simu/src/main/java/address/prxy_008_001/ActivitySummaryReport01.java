
package address.prxy_008_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivitySummaryReport01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivitySummaryReport01"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LookUpReqd" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="LookUpOnOwnCstmr" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="PrxyWthZeroLookUp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="SucssfullyRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="UsucssfullyRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="DeactvdRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="ArchvdRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="LiveRegn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}Max10NumericText"/&gt;
 *         &lt;element name="StsRsn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}StatusReason1Choice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivitySummaryReport01", propOrder = {
    "lookUpReqd",
    "lookUpOnOwnCstmr",
    "prxyWthZeroLookUp",
    "sucssfullyRegn",
    "usucssfullyRegn",
    "deactvdRegn",
    "archvdRegn",
    "liveRegn",
    "stsRsn"
})
public class ActivitySummaryReport01 {

    @XmlElement(name = "LookUpReqd", required = true)
    protected String lookUpReqd;
    @XmlElement(name = "LookUpOnOwnCstmr", required = true)
    protected String lookUpOnOwnCstmr;
    @XmlElement(name = "PrxyWthZeroLookUp", required = true)
    protected String prxyWthZeroLookUp;
    @XmlElement(name = "SucssfullyRegn", required = true)
    protected String sucssfullyRegn;
    @XmlElement(name = "UsucssfullyRegn", required = true)
    protected String usucssfullyRegn;
    @XmlElement(name = "DeactvdRegn", required = true)
    protected String deactvdRegn;
    @XmlElement(name = "ArchvdRegn", required = true)
    protected String archvdRegn;
    @XmlElement(name = "LiveRegn", required = true)
    protected String liveRegn;
    @XmlElement(name = "StsRsn")
    protected StatusReason1Choice stsRsn;

    /**
     * Gets the value of the lookUpReqd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookUpReqd() {
        return lookUpReqd;
    }

    /**
     * Sets the value of the lookUpReqd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookUpReqd(String value) {
        this.lookUpReqd = value;
    }

    /**
     * Gets the value of the lookUpOnOwnCstmr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookUpOnOwnCstmr() {
        return lookUpOnOwnCstmr;
    }

    /**
     * Sets the value of the lookUpOnOwnCstmr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookUpOnOwnCstmr(String value) {
        this.lookUpOnOwnCstmr = value;
    }

    /**
     * Gets the value of the prxyWthZeroLookUp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrxyWthZeroLookUp() {
        return prxyWthZeroLookUp;
    }

    /**
     * Sets the value of the prxyWthZeroLookUp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrxyWthZeroLookUp(String value) {
        this.prxyWthZeroLookUp = value;
    }

    /**
     * Gets the value of the sucssfullyRegn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucssfullyRegn() {
        return sucssfullyRegn;
    }

    /**
     * Sets the value of the sucssfullyRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucssfullyRegn(String value) {
        this.sucssfullyRegn = value;
    }

    /**
     * Gets the value of the usucssfullyRegn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsucssfullyRegn() {
        return usucssfullyRegn;
    }

    /**
     * Sets the value of the usucssfullyRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsucssfullyRegn(String value) {
        this.usucssfullyRegn = value;
    }

    /**
     * Gets the value of the deactvdRegn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeactvdRegn() {
        return deactvdRegn;
    }

    /**
     * Sets the value of the deactvdRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeactvdRegn(String value) {
        this.deactvdRegn = value;
    }

    /**
     * Gets the value of the archvdRegn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchvdRegn() {
        return archvdRegn;
    }

    /**
     * Sets the value of the archvdRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchvdRegn(String value) {
        this.archvdRegn = value;
    }

    /**
     * Gets the value of the liveRegn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiveRegn() {
        return liveRegn;
    }

    /**
     * Sets the value of the liveRegn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiveRegn(String value) {
        this.liveRegn = value;
    }

    /**
     * Gets the value of the stsRsn property.
     * 
     * @return
     *     possible object is
     *     {@link StatusReason1Choice }
     *     
     */
    public StatusReason1Choice getStsRsn() {
        return stsRsn;
    }

    /**
     * Sets the value of the stsRsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusReason1Choice }
     *     
     */
    public void setStsRsn(StatusReason1Choice value) {
        this.stsRsn = value;
    }

}
