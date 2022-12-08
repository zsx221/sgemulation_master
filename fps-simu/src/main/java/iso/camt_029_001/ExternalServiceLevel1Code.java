
package iso.camt_029_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalServiceLevel1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExternalServiceLevel1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SDVA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExternalServiceLevel1Code")
@XmlEnum
public enum ExternalServiceLevel1Code {

    SDVA;

    public String value() {
        return name();
    }

    public static ExternalServiceLevel1Code fromValue(String v) {
        return valueOf(v);
    }

}
