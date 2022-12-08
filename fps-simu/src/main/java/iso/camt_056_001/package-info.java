@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
                @XmlNs(prefix = "pc", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:camt.056.001.01"),
                @XmlNs(prefix = "schemaLocation", namespaceURI = "urn:bcsis G3.Real-Time.Payment.Cancellation.01.xsd"),
        },
        namespace = "urn:iso:std:iso:20022:tech:xsd:camt.056.001.01",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package iso.camt_056_001;

import javax.xml.bind.annotation.XmlNs;