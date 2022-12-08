
package bcsis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bcsis package. 
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

    private final static QName _Message_QNAME = new QName("urn:bcsis", "Message");


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bcsis
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HdrAndData }
     * 
     */
    public HdrAndData createHdrAndData() {
        return new HdrAndData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HdrAndData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HdrAndData }{@code >}
     */
    @XmlElementDecl(namespace = "urn:bcsis", name = "Message")
    public JAXBElement<HdrAndData> createMessage(HdrAndData value) {
        return new JAXBElement<HdrAndData>(_Message_QNAME, HdrAndData.class, null, value);
    }

}
