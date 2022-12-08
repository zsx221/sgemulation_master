@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
                @XmlNs(prefix = "br", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01"),
                @XmlNs(prefix = "schemaLocation", namespaceURI = "urn:bcsis G3.Real-Time.Bank.Statement.Request.01.xsd"),
        },
        namespace = "urn:iso:std:iso:20022:tech:xsd:stmt.001.001.01", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package iso.stmt_001_001;

import javax.xml.bind.annotation.XmlNs;