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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for rational_value_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rational_value_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="whole_part" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numerator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="denominator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rational_value_Type", propOrder = {
    "wholePart",
    "numerator",
    "denominator"
})
public class RationalValueType implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "whole_part")
    protected Integer wholePart;
    protected int numerator;
    protected int denominator;

    /**
     * Gets the value of the wholePart property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWholePart() {
        return wholePart;
    }

    /**
     * Sets the value of the wholePart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWholePart(Integer value) {
        this.wholePart = value;
    }

    /**
     * Gets the value of the numerator property.
     * 
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Sets the value of the numerator property.
     * 
     */
    public void setNumerator(int value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the denominator property.
     * 
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     * 
     */
    public void setDenominator(int value) {
        this.denominator = value;
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
            Integer theWholePart;
            theWholePart = this.getWholePart();
            strategy.appendField(locator, this, "wholePart", buffer, theWholePart);
        }
        {
            int theNumerator;
            theNumerator = (true?this.getNumerator(): 0);
            strategy.appendField(locator, this, "numerator", buffer, theNumerator);
        }
        {
            int theDenominator;
            theDenominator = (true?this.getDenominator(): 0);
            strategy.appendField(locator, this, "denominator", buffer, theDenominator);
        }
        return buffer;
    }

}
