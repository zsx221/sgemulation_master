
package iso.camt_029_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvestigationExecutionConfirmation3Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvestigationExecutionConfirmation3Code"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CNCL"/&gt;
 *     &lt;enumeration value="MODI"/&gt;
 *     &lt;enumeration value="IPAY"/&gt;
 *     &lt;enumeration value="ICOV"/&gt;
 *     &lt;enumeration value="MCOV"/&gt;
 *     &lt;enumeration value="INFO"/&gt;
 *     &lt;enumeration value="CONF"/&gt;
 *     &lt;enumeration value="CWFW"/&gt;
 *     &lt;enumeration value="MWFW"/&gt;
 *     &lt;enumeration value="UWFW"/&gt;
 *     &lt;enumeration value="PECR"/&gt;
 *     &lt;enumeration value="PDCR"/&gt;
 *     &lt;enumeration value="RJCR"/&gt;
 *     &lt;enumeration value="SMTC"/&gt;
 *     &lt;enumeration value="SMTI"/&gt;
 *     &lt;enumeration value="CHRG"/&gt;
 *     &lt;enumeration value="PURP"/&gt;
 *     &lt;enumeration value="IDUP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "InvestigationExecutionConfirmation3Code")
@XmlEnum
public enum InvestigationExecutionConfirmation3Code {

    CNCL,
    MODI,
    IPAY,
    ICOV,
    MCOV,
    INFO,
    CONF,
    CWFW,
    MWFW,
    UWFW,
    PECR,
    PDCR,
    RJCR,
    SMTC,
    SMTI,
    CHRG,
    PURP,
    IDUP;

    public String value() {
        return name();
    }

    public static InvestigationExecutionConfirmation3Code fromValue(String v) {
        return valueOf(v);
    }

}
