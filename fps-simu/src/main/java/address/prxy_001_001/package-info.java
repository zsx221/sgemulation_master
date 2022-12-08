@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
            @XmlNs(prefix = "p1", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01"),
            @XmlNs(prefix = "head", namespaceURI = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01"),
//            @XmlNs(prefix = "schemaLocation", namespaceURI = "urn:bcsis prxy.001.001.01.xsd"),
        },
        namespace = "urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package address.prxy_001_001;

import javax.xml.bind.annotation.XmlNs;