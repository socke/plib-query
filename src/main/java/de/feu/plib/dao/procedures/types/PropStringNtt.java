package de.feu.plib.dao.procedures.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * The model of the string results from the procedure.
 * Type definition in oracle:
 *
 * TYPE PROP_STRING_T IS RECORD (PROP_IRDI VARCHAR2(4000), VAL VARCHAR2(4000),
 * UNIT VARCHAR2(30), PREFIX VARCHAR2(30),
 * TOLERANCE NUMBER, value_id  NUMBER);
 * TYPE PROP_STRING_NTT IS TABLE OF PROP_STRING_T;
 */
public class PropStringNtt implements SQLData {

    /** PROP_IRDI VARCHAR2(4000) */
    private String irdi;

    /** VAL VARCHAR2(4000) */
    private String value;

    /** UNIT VARCHAR2(30) */
    private String unit;

    /** PREFIX VARCHAR2(30) */
    private String prefix;

    /** OLERANCE NUMBER */
    private Long tolerance;

    /** value_id  NUMBER */
    private Long valueId;

    public String getIrdi() {
        return irdi;
    }

    public void setIrdi(String irdi) {
        this.irdi = irdi;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getTolerance() {
        return tolerance;
    }

    public void setTolerance(Long tolerance) {
        this.tolerance = tolerance;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropStringNtt that = (PropStringNtt) o;

        if (irdi != null ? !irdi.equals(that.irdi) : that.irdi != null) return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
        if (tolerance != null ? !tolerance.equals(that.tolerance) : that.tolerance != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (valueId != null ? !valueId.equals(that.valueId) : that.valueId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = irdi != null ? irdi.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (tolerance != null ? tolerance.hashCode() : 0);
        result = 31 * result + (valueId != null ? valueId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PropStringNtt{" +
                "irdi='" + irdi + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                ", prefix='" + prefix + '\'' +
                ", tolerance=" + tolerance +
                ", valueId=" + valueId +
                '}';
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return "";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void readSQL(SQLInput sqlInput, String s) throws SQLException {
        irdi = sqlInput.readString();
        value = sqlInput.readString();
    }

    @Override
    public void writeSQL(SQLOutput sqlOutput) throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
