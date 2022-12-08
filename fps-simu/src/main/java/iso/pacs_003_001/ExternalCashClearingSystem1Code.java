
package iso.pacs_003_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalCashClearingSystem1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExternalCashClearingSystem1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MEP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExternalCashClearingSystem1Code")
@XmlEnum
public enum ExternalCashClearingSystem1Code {

    MEP;

    public String value() {
        return name();
    }

    public static ExternalCashClearingSystem1Code fromValue(String v) {
        return valueOf(v);
    }

}