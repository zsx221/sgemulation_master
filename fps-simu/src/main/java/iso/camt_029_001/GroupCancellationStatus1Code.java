
package iso.camt_029_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupCancellationStatus1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupCancellationStatus1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PACR"/&gt;
 *     &lt;enumeration value="RJCR"/&gt;
 *     &lt;enumeration value="ACCR"/&gt;
 *     &lt;enumeration value="PDCR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GroupCancellationStatus1Code")
@XmlEnum
public enum GroupCancellationStatus1Code {

    PACR,
    RJCR,
    ACCR,
    PDCR;

    public String value() {
        return name();
    }

    public static GroupCancellationStatus1Code fromValue(String v) {
        return valueOf(v);
    }

}
