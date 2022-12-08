
package iso.pacs_003_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SequenceType1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SequenceType1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FRST"/&gt;
 *     &lt;enumeration value="RCUR"/&gt;
 *     &lt;enumeration value="FNAL"/&gt;
 *     &lt;enumeration value="OOFF"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SequenceType1Code")
@XmlEnum
public enum SequenceType1Code {

    FRST,
    RCUR,
    FNAL,
    OOFF;

    public String value() {
        return name();
    }

    public static SequenceType1Code fromValue(String v) {
        return valueOf(v);
    }

}
