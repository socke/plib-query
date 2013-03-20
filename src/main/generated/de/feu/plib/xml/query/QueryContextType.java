//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.18 at 04:57:09 PM CET 
//


package de.feu.plib.xml.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for query_context_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="query_context_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requesting_organization_ref" type="{urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier}IRDI_type"/>
 *         &lt;element name="request_date_time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="response_due_date_time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="response_email_address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="point_of_contact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}query" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "query_context_Type", propOrder = {
    "requestingOrganizationRef",
    "requestDateTime",
    "responseDueDateTime",
    "responseEmailAddress",
    "pointOfContact",
    "query"
})
public class QueryContextType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "requesting_organization_ref", required = true)
    protected String requestingOrganizationRef;
    @XmlElement(name = "request_date_time", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDateTime;
    @XmlElement(name = "response_due_date_time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseDueDateTime;
    @XmlElement(name = "response_email_address", required = true)
    protected String responseEmailAddress;
    @XmlElement(name = "point_of_contact")
    protected String pointOfContact;
    protected List<QueryType> query;

    /**
     * Gets the value of the requestingOrganizationRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestingOrganizationRef() {
        return requestingOrganizationRef;
    }

    /**
     * Sets the value of the requestingOrganizationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestingOrganizationRef(String value) {
        this.requestingOrganizationRef = value;
    }

    /**
     * Gets the value of the requestDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Sets the value of the requestDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDateTime(XMLGregorianCalendar value) {
        this.requestDateTime = value;
    }

    /**
     * Gets the value of the responseDueDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseDueDateTime() {
        return responseDueDateTime;
    }

    /**
     * Sets the value of the responseDueDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseDueDateTime(XMLGregorianCalendar value) {
        this.responseDueDateTime = value;
    }

    /**
     * Gets the value of the responseEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseEmailAddress() {
        return responseEmailAddress;
    }

    /**
     * Sets the value of the responseEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseEmailAddress(String value) {
        this.responseEmailAddress = value;
    }

    /**
     * Gets the value of the pointOfContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointOfContact() {
        return pointOfContact;
    }

    /**
     * Sets the value of the pointOfContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointOfContact(String value) {
        this.pointOfContact = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the query property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryType }
     * 
     * 
     */
    public List<QueryType> getQuery() {
        if (query == null) {
            query = new ArrayList<QueryType>();
        }
        return this.query;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theRequestingOrganizationRef;
            theRequestingOrganizationRef = this.getRequestingOrganizationRef();
            strategy.appendField(locator, this, "requestingOrganizationRef", buffer, theRequestingOrganizationRef);
        }
        {
            XMLGregorianCalendar theRequestDateTime;
            theRequestDateTime = this.getRequestDateTime();
            strategy.appendField(locator, this, "requestDateTime", buffer, theRequestDateTime);
        }
        {
            XMLGregorianCalendar theResponseDueDateTime;
            theResponseDueDateTime = this.getResponseDueDateTime();
            strategy.appendField(locator, this, "responseDueDateTime", buffer, theResponseDueDateTime);
        }
        {
            String theResponseEmailAddress;
            theResponseEmailAddress = this.getResponseEmailAddress();
            strategy.appendField(locator, this, "responseEmailAddress", buffer, theResponseEmailAddress);
        }
        {
            String thePointOfContact;
            thePointOfContact = this.getPointOfContact();
            strategy.appendField(locator, this, "pointOfContact", buffer, thePointOfContact);
        }
        {
            List<QueryType> theQuery;
            theQuery = (((this.query!= null)&&(!this.query.isEmpty()))?this.getQuery():null);
            strategy.appendField(locator, this, "query", buffer, theQuery);
        }
        return buffer;
    }

}