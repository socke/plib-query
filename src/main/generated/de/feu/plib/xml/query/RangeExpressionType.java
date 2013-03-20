//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.15 at 10:16:47 PM CET 
//


package de.feu.plib.xml.query;

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
 * <p>Java class for range_expression_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="range_expression_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}query_expression_Type">
 *       &lt;sequence>
 *         &lt;element name="min_value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="max_value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="UOM_ref" type="{urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier}IRDI_type" minOccurs="0"/>
 *         &lt;element name="UOM_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency_ref" type="{urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier}IRDI_type" minOccurs="0"/>
 *         &lt;element name="currency_code" type="{urn:iso:std:iso:ts:29002:-4:ed-1:tech:xml-schema:basic}ISO_currency_code_Type" minOccurs="0"/>
 *         &lt;element name="is_inclusive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "range_expression_Type", propOrder = {
    "minValue",
    "maxValue",
    "uomRef",
    "uomCode",
    "currencyRef",
    "currencyCode",
    "isInclusive"
})
public class RangeExpressionType
    extends QueryExpressionType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "min_value")
    protected Float minValue;
    @XmlElement(name = "max_value")
    protected Float maxValue;
    @XmlElement(name = "UOM_ref")
    protected String uomRef;
    @XmlElement(name = "UOM_code")
    protected String uomCode;
    @XmlElement(name = "currency_ref")
    protected String currencyRef;
    @XmlElement(name = "currency_code")
    protected String currencyCode;
    @XmlElement(name = "is_inclusive")
    protected boolean isInclusive;

    /**
     * Gets the value of the minValue property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMinValue(Float value) {
        this.minValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMaxValue(Float value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the uomRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOMRef() {
        return uomRef;
    }

    /**
     * Sets the value of the uomRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOMRef(String value) {
        this.uomRef = value;
    }

    /**
     * Gets the value of the uomCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOMCode() {
        return uomCode;
    }

    /**
     * Sets the value of the uomCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOMCode(String value) {
        this.uomCode = value;
    }

    /**
     * Gets the value of the currencyRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyRef() {
        return currencyRef;
    }

    /**
     * Sets the value of the currencyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyRef(String value) {
        this.currencyRef = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the isInclusive property.
     * 
     */
    public boolean isIsInclusive() {
        return isInclusive;
    }

    /**
     * Sets the value of the isInclusive property.
     * 
     */
    public void setIsInclusive(boolean value) {
        this.isInclusive = value;
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
            Float theMinValue;
            theMinValue = this.getMinValue();
            strategy.appendField(locator, this, "minValue", buffer, theMinValue);
        }
        {
            Float theMaxValue;
            theMaxValue = this.getMaxValue();
            strategy.appendField(locator, this, "maxValue", buffer, theMaxValue);
        }
        {
            String theUOMRef;
            theUOMRef = this.getUOMRef();
            strategy.appendField(locator, this, "uomRef", buffer, theUOMRef);
        }
        {
            String theUOMCode;
            theUOMCode = this.getUOMCode();
            strategy.appendField(locator, this, "uomCode", buffer, theUOMCode);
        }
        {
            String theCurrencyRef;
            theCurrencyRef = this.getCurrencyRef();
            strategy.appendField(locator, this, "currencyRef", buffer, theCurrencyRef);
        }
        {
            String theCurrencyCode;
            theCurrencyCode = this.getCurrencyCode();
            strategy.appendField(locator, this, "currencyCode", buffer, theCurrencyCode);
        }
        {
            boolean theIsInclusive;
            theIsInclusive = (true?this.isIsInclusive():false);
            strategy.appendField(locator, this, "isInclusive", buffer, theIsInclusive);
        }
        return buffer;
    }

}
