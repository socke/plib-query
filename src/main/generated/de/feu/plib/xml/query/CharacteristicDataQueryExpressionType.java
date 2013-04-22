//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.21 at 10:39:11 PM CEST 
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
 * <p>Java class for characteristic_data_query_expression_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="characteristic_data_query_expression_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="cardinality" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}cardinality_expression_Type"/>
 *         &lt;element name="range" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}range_expression_Type"/>
 *         &lt;element name="string_pattern" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}string_pattern_expression_Type"/>
 *         &lt;element name="string_size" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}string_size_expression_Type"/>
 *         &lt;element name="subset" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}subset_expression_Type"/>
 *         &lt;element name="data_environment" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}data_environment_expression_Type"/>
 *         &lt;element name="or" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}or_expression_Type"/>
 *         &lt;element name="and" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}and_expression_Type"/>
 *         &lt;element name="not" type="{urn:iso:std:iso:ts:29002:-31:ed-1:tech:xml-schema:query}not_expression_Type"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "characteristic_data_query_expression_Type", propOrder = {
    "cardinality",
    "range",
    "stringPattern",
    "stringSize",
    "subset",
    "dataEnvironment",
    "or",
    "and",
    "not"
})
public class CharacteristicDataQueryExpressionType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    protected CardinalityExpressionType cardinality;
    protected RangeExpressionType range;
    @XmlElement(name = "string_pattern")
    protected StringPatternExpressionType stringPattern;
    @XmlElement(name = "string_size")
    protected StringSizeExpressionType stringSize;
    protected SubsetExpressionType subset;
    @XmlElement(name = "data_environment")
    protected DataEnvironmentExpressionType dataEnvironment;
    protected OrExpressionType or;
    protected AndExpressionType and;
    protected NotExpressionType not;

    /**
     * Gets the value of the cardinality property.
     * 
     * @return
     *     possible object is
     *     {@link CardinalityExpressionType }
     *     
     */
    public CardinalityExpressionType getCardinality() {
        return cardinality;
    }

    /**
     * Sets the value of the cardinality property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardinalityExpressionType }
     *     
     */
    public void setCardinality(CardinalityExpressionType value) {
        this.cardinality = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link RangeExpressionType }
     *     
     */
    public RangeExpressionType getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeExpressionType }
     *     
     */
    public void setRange(RangeExpressionType value) {
        this.range = value;
    }

    /**
     * Gets the value of the stringPattern property.
     * 
     * @return
     *     possible object is
     *     {@link StringPatternExpressionType }
     *     
     */
    public StringPatternExpressionType getStringPattern() {
        return stringPattern;
    }

    /**
     * Sets the value of the stringPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPatternExpressionType }
     *     
     */
    public void setStringPattern(StringPatternExpressionType value) {
        this.stringPattern = value;
    }

    /**
     * Gets the value of the stringSize property.
     * 
     * @return
     *     possible object is
     *     {@link StringSizeExpressionType }
     *     
     */
    public StringSizeExpressionType getStringSize() {
        return stringSize;
    }

    /**
     * Sets the value of the stringSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringSizeExpressionType }
     *     
     */
    public void setStringSize(StringSizeExpressionType value) {
        this.stringSize = value;
    }

    /**
     * Gets the value of the subset property.
     * 
     * @return
     *     possible object is
     *     {@link SubsetExpressionType }
     *     
     */
    public SubsetExpressionType getSubset() {
        return subset;
    }

    /**
     * Sets the value of the subset property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubsetExpressionType }
     *     
     */
    public void setSubset(SubsetExpressionType value) {
        this.subset = value;
    }

    /**
     * Gets the value of the dataEnvironment property.
     * 
     * @return
     *     possible object is
     *     {@link DataEnvironmentExpressionType }
     *     
     */
    public DataEnvironmentExpressionType getDataEnvironment() {
        return dataEnvironment;
    }

    /**
     * Sets the value of the dataEnvironment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataEnvironmentExpressionType }
     *     
     */
    public void setDataEnvironment(DataEnvironmentExpressionType value) {
        this.dataEnvironment = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link OrExpressionType }
     *     
     */
    public OrExpressionType getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrExpressionType }
     *     
     */
    public void setOr(OrExpressionType value) {
        this.or = value;
    }

    /**
     * Gets the value of the and property.
     * 
     * @return
     *     possible object is
     *     {@link AndExpressionType }
     *     
     */
    public AndExpressionType getAnd() {
        return and;
    }

    /**
     * Sets the value of the and property.
     * 
     * @param value
     *     allowed object is
     *     {@link AndExpressionType }
     *     
     */
    public void setAnd(AndExpressionType value) {
        this.and = value;
    }

    /**
     * Gets the value of the not property.
     * 
     * @return
     *     possible object is
     *     {@link NotExpressionType }
     *     
     */
    public NotExpressionType getNot() {
        return not;
    }

    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotExpressionType }
     *     
     */
    public void setNot(NotExpressionType value) {
        this.not = value;
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
            CardinalityExpressionType theCardinality;
            theCardinality = this.getCardinality();
            strategy.appendField(locator, this, "cardinality", buffer, theCardinality);
        }
        {
            RangeExpressionType theRange;
            theRange = this.getRange();
            strategy.appendField(locator, this, "range", buffer, theRange);
        }
        {
            StringPatternExpressionType theStringPattern;
            theStringPattern = this.getStringPattern();
            strategy.appendField(locator, this, "stringPattern", buffer, theStringPattern);
        }
        {
            StringSizeExpressionType theStringSize;
            theStringSize = this.getStringSize();
            strategy.appendField(locator, this, "stringSize", buffer, theStringSize);
        }
        {
            SubsetExpressionType theSubset;
            theSubset = this.getSubset();
            strategy.appendField(locator, this, "subset", buffer, theSubset);
        }
        {
            DataEnvironmentExpressionType theDataEnvironment;
            theDataEnvironment = this.getDataEnvironment();
            strategy.appendField(locator, this, "dataEnvironment", buffer, theDataEnvironment);
        }
        {
            OrExpressionType theOr;
            theOr = this.getOr();
            strategy.appendField(locator, this, "or", buffer, theOr);
        }
        {
            AndExpressionType theAnd;
            theAnd = this.getAnd();
            strategy.appendField(locator, this, "and", buffer, theAnd);
        }
        {
            NotExpressionType theNot;
            theNot = this.getNot();
            strategy.appendField(locator, this, "not", buffer, theNot);
        }
        return buffer;
    }

}
