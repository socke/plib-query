package de.feu.plib.dao.procedures.types;

/**
 * The model of the string results from the procedure.
 * Type definition in oracle:
 * <p/>
 * CREATE OR REPLACE
 * TYPE        "PROP_STRING_OBJ_T" AS OBJECT(
 * P_IRDI    VARCHAR2(4000),
 * "VALUE"   NUMBER,
 * unit      VARCHAR2(30),
 * prefix    VARCHAR2(30),
 * tolerance NUMBER,
 * VALUE_ID  NUMBER
 * );
 * <p/>
 * Note: the naming is not very well chosen, but we stick to the naming of the oracle types to make it recognizable.
 */
public class PropNumberObjT implements PropertyObjectT {

    /**
     * the id of the property
     */
    private Long id;

    /**
     * PROP_IRDI VARCHAR2(4000)
     */
    private String irdi;

    /**
     * VAL VARCHAR2(4000)
     */
    private Long value;

    /**
     * UNIT VARCHAR2(30)
     */
    private String unit;

    /**
     * PREFIX VARCHAR2(30)
     */
    private String prefix;

    /**
     * TOLERANCE NUMBER
     */
    private Long tolerance;

    /**
     * value_id  NUMBER
     */
    private Long valueId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getIrdi() {
        return irdi;
    }

    public void setIrdi(String irdi) {
        this.irdi = irdi;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    public void setValue(Long value) {
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

        PropNumberObjT that = (PropNumberObjT) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
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
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (irdi != null ? irdi.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (tolerance != null ? tolerance.hashCode() : 0);
        result = 31 * result + (valueId != null ? valueId.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "PropNumberObjT{" +
                "id=" + id +
                ", irdi='" + irdi + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                ", prefix='" + prefix + '\'' +
                ", tolerance=" + tolerance +
                ", valueId=" + valueId +
                '}';
    }

}
