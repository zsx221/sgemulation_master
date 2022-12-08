
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyRegistrationSubType1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProxyRegistrationSubType1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACCT"/&gt;
 *     &lt;enumeration value="PRAC"/&gt;
 *     &lt;enumeration value="PRXY"/&gt;
 *     &lt;enumeration value="RGID"/&gt;
 *     &lt;minLength value="1"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProxyRegistrationSubType1Code")
@XmlEnum
public enum ProxyRegistrationSubType1Code {

    ACCT,
    PRAC,
    PRXY,
    RGID;

    public String value() {
        return name();
    }

    public static ProxyRegistrationSubType1Code fromValue(String v) {
        return valueOf(v);
    }

}
