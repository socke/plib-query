//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.21 at 10:39:11 PM CEST 
//


package de.feu.plib.xml.value;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for qualified_value_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qualified_value_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}numeric_value"/>
 *       &lt;/sequence>
 *       &lt;attribute name="qualifier_ref" type="{urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier}IRDI_type" />
 *       &lt;attribute name="qualifier_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qualified_value_Type", propOrder = {
    "realValue",
    "complexValue",
    "integerValue",
    "rationalValue"
})
public class QualifiedValueType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "real_value")
    protected RealValueType realValue;
    @XmlElement(name = "complex_value")
    protected ComplexValueType complexValue;
    @XmlElement(name = "integer_value")
    protected IntegerValueType integerValue;
    @XmlElement(name = "rational_value")
    protected RationalValueType rationalValue;
    @XmlAttribute(name = "qualifier_ref")
    protected String qualifierRef;
    @XmlAttribute(name = "qualifier_code")
    protected String qualifierCode;

    /**
     * Gets the value of the realValue property.
     * 
     * @return
     *     possible object is
     *     {@link RealValueType }
     *     
     */
    public RealValueType getRealValue() {
        return realValue;
    }

    /**
     * Sets the value of the realValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealValueType }
     *     
     */
    public void setRealValue(RealValueType value) {
        this.realValue = value;
    }

    /**
     * Gets the value of the complexValue property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexValueType }
     *     
     */
    public ComplexValueType getComplexValue() {
        return complexValue;
    }

    /**
     * Sets the value of the complexValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexValueType }
     *     
     */
    public void setComplexValue(ComplexValueType value) {
        this.complexValue = value;
    }

    /**
     * Gets the value of the integerValue property.
     * 
     * @return
     *     possible object is
     *     {@link IntegerValueType }
     *     
     */
    public IntegerValueType getIntegerValue() {
        return integerValue;
    }

    /**
     * Sets the value of the integerValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerValueType }
     *     
     */
    public void setIntegerValue(IntegerValueType value) {
        this.integerValue = value;
    }

    /**
     * Gets the value of the rationalValue property.
     * 
     * @return
     *     possible object is
     *     {@link RationalValueType }
     *     
     */
    public RationalValueType getRationalValue() {
        return rationalValue;
    }

    /**
     * Sets the value of the rationalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link RationalValueType }
     *     
     */
    public void setRationalValue(RationalValueType value) {
        this.rationalValue = value;
    }

    /**
     * Gets the value of the qualifierRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierRef() {
        return qualifierRef;
    }

    /**
     * Sets the value of the qualifierRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierRef(String value) {
        this.qualifierRef = value;
    }

    /**
     * Gets the value of the qualifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierCode() {
        return qualifierCode;
    }

    /**
     * Sets the value of the qualifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierCode(String value) {
        this.qualifierCode = value;
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
            RealValueType theRealValue;
            theRealValue = this.getRealValue();
            strategy.appendField(locator, this, "realValue", buffer, theRealValue);
        }
        {
            ComplexValueType theComplexValue;
            theComplexValue = this.getComplexValue();
            strategy.appendField(locator, this, "complexValue", buffer, theComplexValue);
        }
        {
            IntegerValueType theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            strategy.appendField(locator, this, "integerValue", buffer, theIntegerValue);
        }
        {
            RationalValueType theRationalValue;
            theRationalValue = this.getRationalValue();
            strategy.appendField(locator, this, "rationalValue", buffer, theRationalValue);
        }
        {
            String theQualifierRef;
            theQualifierRef = this.getQualifierRef();
            strategy.appendField(locator, this, "qualifierRef", buffer, theQualifierRef);
        }
        {
            String theQualifierCode;
            theQualifierCode = this.getQualifierCode();
            strategy.appendField(locator, this, "qualifierCode", buffer, theQualifierCode);
        }
        return buffer;
    }

}
