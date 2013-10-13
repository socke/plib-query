//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.02 at 09:55:09 AM CEST 
//


package de.feu.plib.xml.value;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for measure_range_value_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measure_range_value_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}measure_value_Type">
 *       &lt;sequence>
 *         &lt;element name="lower_value" type="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}numeric_value_Type"/>
 *         &lt;element name="upper_value" type="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}numeric_value_Type"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measure_range_value_Type", propOrder = {
    "lowerValue",
    "upperValue"
})
public class MeasureRangeValueType
    extends MeasureValueType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "lower_value", required = true)
    protected NumericValueType lowerValue;
    @XmlElement(name = "upper_value", required = true)
    protected NumericValueType upperValue;

    /**
     * Gets the value of the lowerValue property.
     * 
     * @return
     *     possible object is
     *     {@link NumericValueType }
     *     
     */
    public NumericValueType getLowerValue() {
        return lowerValue;
    }

    /**
     * Sets the value of the lowerValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumericValueType }
     *     
     */
    public void setLowerValue(NumericValueType value) {
        this.lowerValue = value;
    }

    /**
     * Gets the value of the upperValue property.
     * 
     * @return
     *     possible object is
     *     {@link NumericValueType }
     *     
     */
    public NumericValueType getUpperValue() {
        return upperValue;
    }

    /**
     * Sets the value of the upperValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumericValueType }
     *     
     */
    public void setUpperValue(NumericValueType value) {
        this.upperValue = value;
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
            NumericValueType theLowerValue;
            theLowerValue = this.getLowerValue();
            strategy.appendField(locator, this, "lowerValue", buffer, theLowerValue);
        }
        {
            NumericValueType theUpperValue;
            theUpperValue = this.getUpperValue();
            strategy.appendField(locator, this, "upperValue", buffer, theUpperValue);
        }
        return buffer;
    }

}
