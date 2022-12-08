@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
                @XmlNs(prefix = "ct", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02"),
                @XmlNs(prefix = "schemaLocation", namespaceURI = "urn:bcsis G3.Real-Time.Credit.Transfer.01.xsd"),
        },
        namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package iso.pacs_008_001;

import javax.xml.bind.annotation.XmlNs;