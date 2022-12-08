
package address.head_001_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageTypes_CAS.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageTypes_CAS"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="admi.002.001.01"/&gt;
 *     &lt;enumeration value="admn.005.001.01"/&gt;
 *     &lt;enumeration value="admn.006.001.01"/&gt;
 *     &lt;enumeration value="prxy.001.001.01"/&gt;
 *     &lt;enumeration value="prxy.002.001.01"/&gt;
 *     &lt;enumeration value="prxy.003.001.01"/&gt;
 *     &lt;enumeration value="prxy.004.001.01"/&gt;
 *     &lt;enumeration value="prxy.005.001.01"/&gt;
 *     &lt;enumeration value="prxy.006.001.01"/&gt;
 *     &lt;enumeration value="prxy.007.001.01"/&gt;
 *     &lt;enumeration value="prxy.008.001.01"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageTypes_CAS")
@XmlEnum
public enum MessageTypesCAS {

    @XmlEnumValue("admi.002.001.01")
    ADMI_002_001_01("admi.002.001.01"),
    @XmlEnumValue("admn.005.001.01")
    ADMN_005_001_01("admn.005.001.01"),
    @XmlEnumValue("admn.006.001.01")
    ADMN_006_001_01("admn.006.001.01"),
    @XmlEnumValue("prxy.001.001.01")
    PRXY_001_001_01("prxy.001.001.01"),
    @XmlEnumValue("prxy.002.001.01")
    PRXY_002_001_01("prxy.002.001.01"),
    @XmlEnumValue("prxy.003.001.01")
    PRXY_003_001_01("prxy.003.001.01"),
    @XmlEnumValue("prxy.004.001.01")
    PRXY_004_001_01("prxy.004.001.01"),
    @XmlEnumValue("prxy.005.001.01")
    PRXY_005_001_01("prxy.005.001.01"),
    @XmlEnumValue("prxy.006.001.01")
    PRXY_006_001_01("prxy.006.001.01"),
    @XmlEnumValue("prxy.007.001.01")
    PRXY_007_001_01("prxy.007.001.01"),
    @XmlEnumValue("prxy.008.001.01")
    PRXY_008_001_01("prxy.008.001.01");
    private final String value;

    MessageTypesCAS(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageTypesCAS fromValue(String v) {
        for (MessageTypesCAS c: MessageTypesCAS.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
