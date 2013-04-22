//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.21 at 10:39:11 PM CEST 
//


package de.feu.plib.xml.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for combination_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="combination_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}value_Group" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "combination_Type", propOrder = {
    "valueGroup"
})
public class CombinationType implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElements({
        @XmlElement(name = "string_value", type = StringValueType.class),
        @XmlElement(name = "bag_value", type = BagValueType.class),
        @XmlElement(name = "boolean_value", type = BooleanValueType.class),
        @XmlElement(name = "complex_value", type = ComplexValueType.class),
        @XmlElement(name = "composite_value", type = CompositeValueType.class),
        @XmlElement(name = "controlled_value", type = ControlledValueType.class),
        @XmlElement(name = "currency_value", type = CurrencyValueType.class),
        @XmlElement(name = "date_value", type = DateValueType.class),
        @XmlElement(name = "date_time_value", type = DateTimeValueType.class),
        @XmlElement(name = "file_value", type = FileValueType.class),
        @XmlElement(name = "integer_value", type = IntegerValueType.class),
        @XmlElement(name = "item_reference_value", type = ItemReferenceValueType.class),
        @XmlElement(name = "localized_text_value", type = LocalizedTextValueType.class),
        @XmlElement(name = "measure_qualified_number_value", type = MeasureQualifiedNumberValueType.class),
        @XmlElement(name = "measure_range_value", type = MeasureRangeValueType.class),
        @XmlElement(name = "measure_single_number_value", type = MeasureSingleNumberValueType.class),
        @XmlElement(name = "null_value", type = NullValueType.class),
        @XmlElement(name = "rational_value", type = RationalValueType.class),
        @XmlElement(name = "real_value", type = RealValueType.class),
        @XmlElement(name = "sequence_value", type = SequenceValueType.class),
        @XmlElement(name = "set_value", type = SetValueType.class),
        @XmlElement(name = "time_value", type = TimeValueType.class),
        @XmlElement(name = "year_month_value", type = YearMonthValueType.class),
        @XmlElement(name = "year_value", type = YearValueType.class)
    })
    protected List<Serializable> valueGroup;

    /**
     * Gets the value of the valueGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringValueType }
     * {@link BagValueType }
     * {@link BooleanValueType }
     * {@link ComplexValueType }
     * {@link CompositeValueType }
     * {@link ControlledValueType }
     * {@link CurrencyValueType }
     * {@link DateValueType }
     * {@link DateTimeValueType }
     * {@link FileValueType }
     * {@link IntegerValueType }
     * {@link ItemReferenceValueType }
     * {@link LocalizedTextValueType }
     * {@link MeasureQualifiedNumberValueType }
     * {@link MeasureRangeValueType }
     * {@link MeasureSingleNumberValueType }
     * {@link NullValueType }
     * {@link RationalValueType }
     * {@link RealValueType }
     * {@link SequenceValueType }
     * {@link SetValueType }
     * {@link TimeValueType }
     * {@link YearMonthValueType }
     * {@link YearValueType }
     * 
     * 
     */
    public List<Serializable> getValueGroup() {
        if (valueGroup == null) {
            valueGroup = new ArrayList<Serializable>();
        }
        return this.valueGroup;
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
            List<Serializable> theValueGroup;
            theValueGroup = (((this.valueGroup!= null)&&(!this.valueGroup.isEmpty()))?this.getValueGroup():null);
            strategy.appendField(locator, this, "valueGroup", buffer, theValueGroup);
        }
        return buffer;
    }

}
