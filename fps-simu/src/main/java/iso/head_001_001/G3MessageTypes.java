
package iso.head_001_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for G3MessageTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="G3MessageTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="admi.002.001.01"/&gt;
 *     &lt;enumeration value="admi.004.001.01"/&gt;
 *     &lt;enumeration value="admn.001.001.01"/&gt;
 *     &lt;enumeration value="admn.002.001.01"/&gt;
 *     &lt;enumeration value="admn.003.001.01"/&gt;
 *     &lt;enumeration value="admn.004.001.01"/&gt;
 *     &lt;enumeration value="admn.005.001.01"/&gt;
 *     &lt;enumeration value="admn.006.001.01"/&gt;
 *     &lt;enumeration value="pacs.002.001.03"/&gt;
 *     &lt;enumeration value="pacs.003.001.02"/&gt;
 *     &lt;enumeration value="pacs.007.001.02"/&gt;
 *     &lt;enumeration value="pacs.008.001.02"/&gt;
 *     &lt;enumeration value="psla.001.001.01"/&gt;
 *     &lt;enumeration value="ctrl.001.001.01"/&gt;
 *     &lt;enumeration value="camt.053.001.02"/&gt;
 *     &lt;enumeration value="camt.056.001.01"/&gt;
 *     &lt;enumeration value="camt.029.001.03"/&gt;
 *     &lt;enumeration value="stmt.001.001.01"/&gt;
 *     &lt;enumeration value="stmt.002.001.01"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "G3MessageTypes")
@XmlEnum
public enum G3MessageTypes {

    @XmlEnumValue("admi.002.001.01")
    ADMI_002_001_01("admi.002.001.01"),
    @XmlEnumValue("admi.004.001.01")
    ADMI_004_001_01("admi.004.001.01"),
    @XmlEnumValue("admn.001.001.01")
    ADMN_001_001_01("admn.001.001.01"),
    @XmlEnumValue("admn.002.001.01")
    ADMN_002_001_01("admn.002.001.01"),
    @XmlEnumValue("admn.003.001.01")
    ADMN_003_001_01("admn.003.001.01"),
    @XmlEnumValue("admn.004.001.01")
    ADMN_004_001_01("admn.004.001.01"),
    @XmlEnumValue("admn.005.001.01")
    ADMN_005_001_01("admn.005.001.01"),
    @XmlEnumValue("admn.006.001.01")
    ADMN_006_001_01("admn.006.001.01"),
    @XmlEnumValue("pacs.002.001.03")
    PACS_002_001_03("pacs.002.001.03"),
    @XmlEnumValue("pacs.003.001.02")
    PACS_003_001_02("pacs.003.001.02"),
    @XmlEnumValue("pacs.007.001.02")
    PACS_007_001_02("pacs.007.001.02"),
    @XmlEnumValue("pacs.008.001.02")
    PACS_008_001_02("pacs.008.001.02"),
    @XmlEnumValue("psla.001.001.01")
    PSLA_001_001_01("psla.001.001.01"),
    @XmlEnumValue("ctrl.001.001.01")
    CTRL_001_001_01("ctrl.001.001.01"),
    @XmlEnumValue("camt.053.001.02")
    CAMT_053_001_02("camt.053.001.02"),
    @XmlEnumValue("camt.056.001.01")
    CAMT_056_001_01("camt.056.001.01"),
    @XmlEnumValue("camt.029.001.03")
    CAMT_029_001_03("camt.029.001.03"),
    @XmlEnumValue("stmt.001.001.01")
    STMT_001_001_01("stmt.001.001.01"),
    @XmlEnumValue("stmt.002.001.01")
    STMT_002_001_01("stmt.002.001.01");
    private final String value;

    G3MessageTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static G3MessageTypes fromValue(String v) {
        for (G3MessageTypes c: G3MessageTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
