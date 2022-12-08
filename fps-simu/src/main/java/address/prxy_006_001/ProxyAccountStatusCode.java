
package address.prxy_006_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyAccountStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProxyAccountStatusCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;minLength value="1"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *     &lt;enumeration value="ACTV"/&gt;
 *     &lt;enumeration value="ICTV"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProxyAccountStatusCode")
@XmlEnum
public enum ProxyAccountStatusCode {

    ACTV,
    ICTV;

    public String value() {
        return name();
    }

    public static ProxyAccountStatusCode fromValue(String v) {
        return valueOf(v);
    }

}
