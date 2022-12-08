
package address.prxy_002_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalStatusReason1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExternalStatusReason1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;minLength value="1"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *     &lt;enumeration value="AG03"/&gt;
 *     &lt;enumeration value="RC02"/&gt;
 *     &lt;enumeration value="RC03"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExternalStatusReason1Code")
@XmlEnum
public enum ExternalStatusReason1Code {

    @XmlEnumValue("AG03")
    AG_03("AG03"),
    @XmlEnumValue("RC02")
    RC_02("RC02"),
    @XmlEnumValue("RC03")
    RC_03("RC03");
    private final String value;

    ExternalStatusReason1Code(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExternalStatusReason1Code fromValue(String v) {
        for (ExternalStatusReason1Code c: ExternalStatusReason1Code.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
