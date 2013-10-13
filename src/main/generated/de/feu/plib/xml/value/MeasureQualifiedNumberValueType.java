//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.02 at 09:55:09 AM CEST 
//


package de.feu.plib.xml.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for measure_qualified_number_value_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measure_qualified_number_value_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}measure_value_Type">
 *       &lt;sequence>
 *         &lt;element name="qualified_value" type="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}qualified_value_Type" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measure_qualified_number_value_Type", propOrder = {
    "qualifiedValue"
})
public class MeasureQualifiedNumberValueType
    extends MeasureValueType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "qualified_value", required = true)
    protected List<QualifiedValueType> qualifiedValue;

    /**
     * Gets the value of the qualifiedValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualifiedValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualifiedValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualifiedValueType }
     * 
     * 
     */
    public List<QualifiedValueType> getQualifiedValue() {
        if (qualifiedValue == null) {
            qualifiedValue = new ArrayList<QualifiedValueType>();
        }
        return this.qualifiedValue;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<QualifiedValueType> theQualifiedValue;
            theQualifiedValue = (((this.qualifiedValue!= null)&&(!this.qualifiedValue.isEmpty()))?this.getQualifiedValue():null);
            strategy.appendField(locator, this, "qualifiedValue", buffer, theQualifiedValue);
        }
        return buffer;
    }

}
