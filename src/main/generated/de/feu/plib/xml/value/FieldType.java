//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.18 at 04:57:09 PM CET 
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
 * <p>Java class for field_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="field_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:iso:std:iso:ts:29002:-10:ed-1:tech:xml-schema:value}value_Group"/>
 *       &lt;/sequence>
 *       &lt;attribute name="property_ref" type="{urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier}IRDI_type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "field_Type", propOrder = {
    "stringValue",
    "bagValue",
    "booleanValue",
    "complexValue",
    "compositeValue",
    "controlledValue",
    "currencyValue",
    "dateValue",
    "dateTimeValue",
    "fileValue",
    "integerValue",
    "itemReferenceValue",
    "localizedTextValue",
    "measureQualifiedNumberValue",
    "measureRangeValue",
    "measureSingleNumberValue",
    "nullValue",
    "rationalValue",
    "realValue",
    "sequenceValue",
    "setValue",
    "timeValue",
    "yearMonthValue",
    "yearValue"
})
public class FieldType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "string_value")
    protected StringValueType stringValue;
    @XmlElement(name = "bag_value")
    protected BagValueType bagValue;
    @XmlElement(name = "boolean_value")
    protected BooleanValueType booleanValue;
    @XmlElement(name = "complex_value")
    protected ComplexValueType complexValue;
    @XmlElement(name = "composite_value")
    protected CompositeValueType compositeValue;
    @XmlElement(name = "controlled_value")
    protected ControlledValueType controlledValue;
    @XmlElement(name = "currency_value")
    protected CurrencyValueType currencyValue;
    @XmlElement(name = "date_value")
    protected DateValueType dateValue;
    @XmlElement(name = "date_time_value")
    protected DateTimeValueType dateTimeValue;
    @XmlElement(name = "file_value")
    protected FileValueType fileValue;
    @XmlElement(name = "integer_value")
    protected IntegerValueType integerValue;
    @XmlElement(name = "item_reference_value")
    protected ItemReferenceValueType itemReferenceValue;
    @XmlElement(name = "localized_text_value")
    protected LocalizedTextValueType localizedTextValue;
    @XmlElement(name = "measure_qualified_number_value")
    protected MeasureQualifiedNumberValueType measureQualifiedNumberValue;
    @XmlElement(name = "measure_range_value")
    protected MeasureRangeValueType measureRangeValue;
    @XmlElement(name = "measure_single_number_value")
    protected MeasureSingleNumberValueType measureSingleNumberValue;
    @XmlElement(name = "null_value")
    protected NullValueType nullValue;
    @XmlElement(name = "rational_value")
    protected RationalValueType rationalValue;
    @XmlElement(name = "real_value")
    protected RealValueType realValue;
    @XmlElement(name = "sequence_value")
    protected SequenceValueType sequenceValue;
    @XmlElement(name = "set_value")
    protected SetValueType setValue;
    @XmlElement(name = "time_value")
    protected TimeValueType timeValue;
    @XmlElement(name = "year_month_value")
    protected YearMonthValueType yearMonthValue;
    @XmlElement(name = "year_value")
    protected YearValueType yearValue;
    @XmlAttribute(name = "property_ref")
    protected String propertyRef;

    /**
     * Gets the value of the stringValue property.
     * 
     * @return
     *     possible object is
     *     {@link StringValueType }
     *     
     */
    public StringValueType getStringValue() {
        return stringValue;
    }

    /**
     * Sets the value of the stringValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringValueType }
     *     
     */
    public void setStringValue(StringValueType value) {
        this.stringValue = value;
    }

    /**
     * Gets the value of the bagValue property.
     * 
     * @return
     *     possible object is
     *     {@link BagValueType }
     *     
     */
    public BagValueType getBagValue() {
        return bagValue;
    }

    /**
     * Sets the value of the bagValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BagValueType }
     *     
     */
    public void setBagValue(BagValueType value) {
        this.bagValue = value;
    }

    /**
     * Gets the value of the booleanValue property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanValueType }
     *     
     */
    public BooleanValueType getBooleanValue() {
        return booleanValue;
    }

    /**
     * Sets the value of the booleanValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanValueType }
     *     
     */
    public void setBooleanValue(BooleanValueType value) {
        this.booleanValue = value;
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
     * Gets the value of the compositeValue property.
     * 
     * @return
     *     possible object is
     *     {@link CompositeValueType }
     *     
     */
    public CompositeValueType getCompositeValue() {
        return compositeValue;
    }

    /**
     * Sets the value of the compositeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompositeValueType }
     *     
     */
    public void setCompositeValue(CompositeValueType value) {
        this.compositeValue = value;
    }

    /**
     * Gets the value of the controlledValue property.
     * 
     * @return
     *     possible object is
     *     {@link ControlledValueType }
     *     
     */
    public ControlledValueType getControlledValue() {
        return controlledValue;
    }

    /**
     * Sets the value of the controlledValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ControlledValueType }
     *     
     */
    public void setControlledValue(ControlledValueType value) {
        this.controlledValue = value;
    }

    /**
     * Gets the value of the currencyValue property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyValueType }
     *     
     */
    public CurrencyValueType getCurrencyValue() {
        return currencyValue;
    }

    /**
     * Sets the value of the currencyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyValueType }
     *     
     */
    public void setCurrencyValue(CurrencyValueType value) {
        this.currencyValue = value;
    }

    /**
     * Gets the value of the dateValue property.
     * 
     * @return
     *     possible object is
     *     {@link DateValueType }
     *     
     */
    public DateValueType getDateValue() {
        return dateValue;
    }

    /**
     * Sets the value of the dateValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateValueType }
     *     
     */
    public void setDateValue(DateValueType value) {
        this.dateValue = value;
    }

    /**
     * Gets the value of the dateTimeValue property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeValueType }
     *     
     */
    public DateTimeValueType getDateTimeValue() {
        return dateTimeValue;
    }

    /**
     * Sets the value of the dateTimeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeValueType }
     *     
     */
    public void setDateTimeValue(DateTimeValueType value) {
        this.dateTimeValue = value;
    }

    /**
     * Gets the value of the fileValue property.
     * 
     * @return
     *     possible object is
     *     {@link FileValueType }
     *     
     */
    public FileValueType getFileValue() {
        return fileValue;
    }

    /**
     * Sets the value of the fileValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileValueType }
     *     
     */
    public void setFileValue(FileValueType value) {
        this.fileValue = value;
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
     * Gets the value of the itemReferenceValue property.
     * 
     * @return
     *     possible object is
     *     {@link ItemReferenceValueType }
     *     
     */
    public ItemReferenceValueType getItemReferenceValue() {
        return itemReferenceValue;
    }

    /**
     * Sets the value of the itemReferenceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemReferenceValueType }
     *     
     */
    public void setItemReferenceValue(ItemReferenceValueType value) {
        this.itemReferenceValue = value;
    }

    /**
     * Gets the value of the localizedTextValue property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedTextValueType }
     *     
     */
    public LocalizedTextValueType getLocalizedTextValue() {
        return localizedTextValue;
    }

    /**
     * Sets the value of the localizedTextValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedTextValueType }
     *     
     */
    public void setLocalizedTextValue(LocalizedTextValueType value) {
        this.localizedTextValue = value;
    }

    /**
     * Gets the value of the measureQualifiedNumberValue property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureQualifiedNumberValueType }
     *     
     */
    public MeasureQualifiedNumberValueType getMeasureQualifiedNumberValue() {
        return measureQualifiedNumberValue;
    }

    /**
     * Sets the value of the measureQualifiedNumberValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureQualifiedNumberValueType }
     *     
     */
    public void setMeasureQualifiedNumberValue(MeasureQualifiedNumberValueType value) {
        this.measureQualifiedNumberValue = value;
    }

    /**
     * Gets the value of the measureRangeValue property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureRangeValueType }
     *     
     */
    public MeasureRangeValueType getMeasureRangeValue() {
        return measureRangeValue;
    }

    /**
     * Sets the value of the measureRangeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureRangeValueType }
     *     
     */
    public void setMeasureRangeValue(MeasureRangeValueType value) {
        this.measureRangeValue = value;
    }

    /**
     * Gets the value of the measureSingleNumberValue property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureSingleNumberValueType }
     *     
     */
    public MeasureSingleNumberValueType getMeasureSingleNumberValue() {
        return measureSingleNumberValue;
    }

    /**
     * Sets the value of the measureSingleNumberValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureSingleNumberValueType }
     *     
     */
    public void setMeasureSingleNumberValue(MeasureSingleNumberValueType value) {
        this.measureSingleNumberValue = value;
    }

    /**
     * Gets the value of the nullValue property.
     * 
     * @return
     *     possible object is
     *     {@link NullValueType }
     *     
     */
    public NullValueType getNullValue() {
        return nullValue;
    }

    /**
     * Sets the value of the nullValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link NullValueType }
     *     
     */
    public void setNullValue(NullValueType value) {
        this.nullValue = value;
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
     * Gets the value of the sequenceValue property.
     * 
     * @return
     *     possible object is
     *     {@link SequenceValueType }
     *     
     */
    public SequenceValueType getSequenceValue() {
        return sequenceValue;
    }

    /**
     * Sets the value of the sequenceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceValueType }
     *     
     */
    public void setSequenceValue(SequenceValueType value) {
        this.sequenceValue = value;
    }

    /**
     * Gets the value of the setValue property.
     * 
     * @return
     *     possible object is
     *     {@link SetValueType }
     *     
     */
    public SetValueType getSetValue() {
        return setValue;
    }

    /**
     * Sets the value of the setValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetValueType }
     *     
     */
    public void setSetValue(SetValueType value) {
        this.setValue = value;
    }

    /**
     * Gets the value of the timeValue property.
     * 
     * @return
     *     possible object is
     *     {@link TimeValueType }
     *     
     */
    public TimeValueType getTimeValue() {
        return timeValue;
    }

    /**
     * Sets the value of the timeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeValueType }
     *     
     */
    public void setTimeValue(TimeValueType value) {
        this.timeValue = value;
    }

    /**
     * Gets the value of the yearMonthValue property.
     * 
     * @return
     *     possible object is
     *     {@link YearMonthValueType }
     *     
     */
    public YearMonthValueType getYearMonthValue() {
        return yearMonthValue;
    }

    /**
     * Sets the value of the yearMonthValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link YearMonthValueType }
     *     
     */
    public void setYearMonthValue(YearMonthValueType value) {
        this.yearMonthValue = value;
    }

    /**
     * Gets the value of the yearValue property.
     * 
     * @return
     *     possible object is
     *     {@link YearValueType }
     *     
     */
    public YearValueType getYearValue() {
        return yearValue;
    }

    /**
     * Sets the value of the yearValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link YearValueType }
     *     
     */
    public void setYearValue(YearValueType value) {
        this.yearValue = value;
    }

    /**
     * Gets the value of the propertyRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyRef() {
        return propertyRef;
    }

    /**
     * Sets the value of the propertyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyRef(String value) {
        this.propertyRef = value;
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
            StringValueType theStringValue;
            theStringValue = this.getStringValue();
            strategy.appendField(locator, this, "stringValue", buffer, theStringValue);
        }
        {
            BagValueType theBagValue;
            theBagValue = this.getBagValue();
            strategy.appendField(locator, this, "bagValue", buffer, theBagValue);
        }
        {
            BooleanValueType theBooleanValue;
            theBooleanValue = this.getBooleanValue();
            strategy.appendField(locator, this, "booleanValue", buffer, theBooleanValue);
        }
        {
            ComplexValueType theComplexValue;
            theComplexValue = this.getComplexValue();
            strategy.appendField(locator, this, "complexValue", buffer, theComplexValue);
        }
        {
            CompositeValueType theCompositeValue;
            theCompositeValue = this.getCompositeValue();
            strategy.appendField(locator, this, "compositeValue", buffer, theCompositeValue);
        }
        {
            ControlledValueType theControlledValue;
            theControlledValue = this.getControlledValue();
            strategy.appendField(locator, this, "controlledValue", buffer, theControlledValue);
        }
        {
            CurrencyValueType theCurrencyValue;
            theCurrencyValue = this.getCurrencyValue();
            strategy.appendField(locator, this, "currencyValue", buffer, theCurrencyValue);
        }
        {
            DateValueType theDateValue;
            theDateValue = this.getDateValue();
            strategy.appendField(locator, this, "dateValue", buffer, theDateValue);
        }
        {
            DateTimeValueType theDateTimeValue;
            theDateTimeValue = this.getDateTimeValue();
            strategy.appendField(locator, this, "dateTimeValue", buffer, theDateTimeValue);
        }
        {
            FileValueType theFileValue;
            theFileValue = this.getFileValue();
            strategy.appendField(locator, this, "fileValue", buffer, theFileValue);
        }
        {
            IntegerValueType theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            strategy.appendField(locator, this, "integerValue", buffer, theIntegerValue);
        }
        {
            ItemReferenceValueType theItemReferenceValue;
            theItemReferenceValue = this.getItemReferenceValue();
            strategy.appendField(locator, this, "itemReferenceValue", buffer, theItemReferenceValue);
        }
        {
            LocalizedTextValueType theLocalizedTextValue;
            theLocalizedTextValue = this.getLocalizedTextValue();
            strategy.appendField(locator, this, "localizedTextValue", buffer, theLocalizedTextValue);
        }
        {
            MeasureQualifiedNumberValueType theMeasureQualifiedNumberValue;
            theMeasureQualifiedNumberValue = this.getMeasureQualifiedNumberValue();
            strategy.appendField(locator, this, "measureQualifiedNumberValue", buffer, theMeasureQualifiedNumberValue);
        }
        {
            MeasureRangeValueType theMeasureRangeValue;
            theMeasureRangeValue = this.getMeasureRangeValue();
            strategy.appendField(locator, this, "measureRangeValue", buffer, theMeasureRangeValue);
        }
        {
            MeasureSingleNumberValueType theMeasureSingleNumberValue;
            theMeasureSingleNumberValue = this.getMeasureSingleNumberValue();
            strategy.appendField(locator, this, "measureSingleNumberValue", buffer, theMeasureSingleNumberValue);
        }
        {
            NullValueType theNullValue;
            theNullValue = this.getNullValue();
            strategy.appendField(locator, this, "nullValue", buffer, theNullValue);
        }
        {
            RationalValueType theRationalValue;
            theRationalValue = this.getRationalValue();
            strategy.appendField(locator, this, "rationalValue", buffer, theRationalValue);
        }
        {
            RealValueType theRealValue;
            theRealValue = this.getRealValue();
            strategy.appendField(locator, this, "realValue", buffer, theRealValue);
        }
        {
            SequenceValueType theSequenceValue;
            theSequenceValue = this.getSequenceValue();
            strategy.appendField(locator, this, "sequenceValue", buffer, theSequenceValue);
        }
        {
            SetValueType theSetValue;
            theSetValue = this.getSetValue();
            strategy.appendField(locator, this, "setValue", buffer, theSetValue);
        }
        {
            TimeValueType theTimeValue;
            theTimeValue = this.getTimeValue();
            strategy.appendField(locator, this, "timeValue", buffer, theTimeValue);
        }
        {
            YearMonthValueType theYearMonthValue;
            theYearMonthValue = this.getYearMonthValue();
            strategy.appendField(locator, this, "yearMonthValue", buffer, theYearMonthValue);
        }
        {
            YearValueType theYearValue;
            theYearValue = this.getYearValue();
            strategy.appendField(locator, this, "yearValue", buffer, theYearValue);
        }
        {
            String thePropertyRef;
            thePropertyRef = this.getPropertyRef();
            strategy.appendField(locator, this, "propertyRef", buffer, thePropertyRef);
        }
        return buffer;
    }

}
