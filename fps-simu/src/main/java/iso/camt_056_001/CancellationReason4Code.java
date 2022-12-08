
package iso.camt_056_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CancellationReason4Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CancellationReason4Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CUST"/&gt;
 *     &lt;enumeration value="DUPL"/&gt;
 *     &lt;enumeration value="AGNT"/&gt;
 *     &lt;enumeration value="CURR"/&gt;
 *     &lt;enumeration value="UPAY"/&gt;
 *     &lt;enumeration value="CUTA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CancellationReason4Code")
@XmlEnum
public enum CancellationReason4Code {

    CUST,
    DUPL,
    AGNT,
    CURR,
    UPAY,
    CUTA;

    public String value() {
        return name();
    }

    public static CancellationReason4Code fromValue(String v) {
        return valueOf(v);
    }

}
