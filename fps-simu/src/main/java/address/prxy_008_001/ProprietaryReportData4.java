
package address.prxy_008_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProprietaryReportData4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProprietaryReportData4"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}ProxyReportType"/&gt;
 *         &lt;element name="Data" type="{urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01}ProprietaryData3"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProprietaryReportData4", propOrder = {
    "tp",
    "data"
})
public class ProprietaryReportData4 {

    @XmlElement(name = "Tp", required = true)
    @XmlSchemaType(name = "string")
    protected ProxyReportType tp;
    @XmlElement(name = "Data", required = true)
    protected ProprietaryData3 data;

    /**
     * Gets the value of the tp property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyReportType }
     *     
     */
    public ProxyReportType getTp() {
        return tp;
    }

    /**
     * Sets the value of the tp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyReportType }
     *     
     */
    public void setTp(ProxyReportType value) {
        this.tp = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link ProprietaryData3 }
     *     
     */
    public ProprietaryData3 getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProprietaryData3 }
     *     
     */
    public void setData(ProprietaryData3 value) {
        this.data = value;
    }

}
