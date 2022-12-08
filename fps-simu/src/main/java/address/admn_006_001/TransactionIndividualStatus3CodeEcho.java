
package address.admn_006_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionIndividualStatus3Code_Echo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionIndividualStatus3Code_Echo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACTC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TransactionIndividualStatus3Code_Echo")
@XmlEnum
public enum TransactionIndividualStatus3CodeEcho {

    ACTC;

    public String value() {
        return name();
    }

    public static TransactionIndividualStatus3CodeEcho fromValue(String v) {
        return valueOf(v);
    }

}
