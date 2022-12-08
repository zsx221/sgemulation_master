
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyRegistrationType1Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProxyRegistrationType1Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AMND"/&gt;
 *     &lt;enumeration value="DEAC"/&gt;
 *     &lt;enumeration value="NEWR"/&gt;
 *     &lt;minLength value="1"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProxyRegistrationType1Code")
@XmlEnum
public enum ProxyRegistrationType1Code {

    AMND,
    DEAC,
    NEWR;

    public String value() {
        return name();
    }

    public static ProxyRegistrationType1Code fromValue(String v) {
        return valueOf(v);
    }

}
