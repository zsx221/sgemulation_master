
package address.admi_002_001;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the iso.std.iso._20022.tech.xsd.admi_002_001 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: iso.std.iso._20022.tech.xsd.admi_002_001
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link MessageReference }
     * 
     */
    public MessageReference createMessageReference() {
        return new MessageReference();
    }

    /**
     * Create an instance of {@link MessageRejectV01 }
     * 
     */
    public MessageRejectV01 createMessageRejectV01() {
        return new MessageRejectV01();
    }

    /**
     * Create an instance of {@link RejectionReason2 }
     * 
     */
    public RejectionReason2 createRejectionReason2() {
        return new RejectionReason2();
    }

}
