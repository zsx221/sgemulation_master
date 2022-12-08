
package iso.admn_002_001;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the iso.std.iso._20022.tech.xsd.admn_002_001 package. 
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

    private final static QName _SignOnResponse_QNAME = new QName("urn:iso:std:iso:20022:tech:xsd:admn.002.001.01", "SignOnResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: iso.std.iso._20022.tech.xsd.admn_002_001
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
     * Create an instance of {@link AdmnSignOnResp }
     * 
     */
    public AdmnSignOnResp createAdmnSignOnResp() {
        return new AdmnSignOnResp();
    }

    /**
     * Create an instance of {@link GrpHdr }
     * 
     */
    public GrpHdr createGrpHdr() {
        return new GrpHdr();
    }

    /**
     * Create an instance of {@link SignOnResp }
     * 
     */
    public SignOnResp createSignOnResp() {
        return new SignOnResp();
    }

    /**
     * Create an instance of {@link BranchAndFinancialInstitutionIdentification4 }
     * 
     */
    public BranchAndFinancialInstitutionIdentification4 createBranchAndFinancialInstitutionIdentification4() {
        return new BranchAndFinancialInstitutionIdentification4();
    }

    /**
     * Create an instance of {@link FinancialInstitutionIdentification7 }
     * 
     */
    public FinancialInstitutionIdentification7 createFinancialInstitutionIdentification7() {
        return new FinancialInstitutionIdentification7();
    }

    /**
     * Create an instance of {@link ClearingSystemMemberIdentification2 }
     * 
     */
    public ClearingSystemMemberIdentification2 createClearingSystemMemberIdentification2() {
        return new ClearingSystemMemberIdentification2();
    }

    /**
     * Create an instance of {@link StatusReasonInformation8 }
     * 
     */
    public StatusReasonInformation8 createStatusReasonInformation8() {
        return new StatusReasonInformation8();
    }

    /**
     * Create an instance of {@link StatusReason6Choice }
     * 
     */
    public StatusReason6Choice createStatusReason6Choice() {
        return new StatusReason6Choice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Document }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Document }{@code >}
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso:20022:tech:xsd:admn.002.001.01", name = "SignOnResponse")
    public JAXBElement<Document> createSignOnResponse(Document value) {
        return new JAXBElement<Document>(_SignOnResponse_QNAME, Document.class, null, value);
    }

}
