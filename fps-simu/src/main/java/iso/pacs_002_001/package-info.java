@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
                @XmlNs(prefix = "ps", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03"),
                @XmlNs(prefix = "schemaLocation", namespaceURI = "urn:bcsis G3.Real-Time.Payment.Status.01.xsd"),
        },
        namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package iso.pacs_002_001;

import javax.xml.bind.annotation.XmlNs;