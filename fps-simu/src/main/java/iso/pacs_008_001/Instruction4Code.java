
package iso.pacs_008_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Instruction4Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Instruction4Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PHOA"/&gt;
 *     &lt;enumeration value="TELA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Instruction4Code")
@XmlEnum
public enum Instruction4Code {

    PHOA,
    TELA;

    public String value() {
        return name();
    }

    public static Instruction4Code fromValue(String v) {
        return valueOf(v);
    }

}
