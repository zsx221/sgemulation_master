
package address.prxy_003_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubmitterType1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubmitterType1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BANK"/&gt;
 *     &lt;enumeration value="CNTR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SubmitterType1Code")
@XmlEnum
public enum SubmitterType1Code {

    BANK,
    CNTR;

    public String value() {
        return name();
    }

    public static SubmitterType1Code fromValue(String v) {
        return valueOf(v);
    }

}
