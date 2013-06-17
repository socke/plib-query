package de.feu.plib.dao;

/**
 * This enum holds all SQL queries used by the DAOs.
 * Extend this file with a new query to have them centrally located.
 */
public enum SQLQuery {

    /** just an example query */
    SELECT("SELECT name FROM DE_COMPANY"),

    /** Checks if there are instances of classes available with given IRDI */
    CHECK_IRDI("SELECT COUNT(*) FROM DE_CLASS c, DO_OBJECT o WHERE c.ID = o.C_ID AND c.IRDI = ?"),

    /** Get all dictionary instance ids of a given class IRDI */
    GET_EXT_ID_SQL("SELECT o.DI_ID FROM DE_CLASS c, DO_OBJECT o WHERE c.ID = o.C_ID AND c.IRDI = ?"),

    /**
     * <p>Get all string properties of a item with given DI_ID (dictionary instance).   </p>
     * <strong>Note:</strong> <p />
     * <p>We will find a unit here also in the table, the PLIB Standard says there could be a unit defined also
     * for an instance of an item which should have precedence.
     * Normally for a Property in DE_PROPERTY, a corresponding data type and unit is defined by joining the tables
     * DE_DATA_TYPE and then DE_UNIT.
     * However, we will read it here, depends on the client what to do with it. </p>
     */
    GET_STRING_PROPERTIES("SELECT P.ID, P.IRDI, LOJ.VALUE, LOJ.UNIT, LOJ.PREFIX, LOJ.TOLERANCE, LOJ.VALUE_ID " +
            "       FROM (SELECT DI_ID, P_ID,  VALUE, UNIT, PREFIX, TOLERANCE, VALUE_ID " +
            "             FROM DO_STRING LEFT OUTER JOIN (SELECT DI_ID, P_ID, UNIT, PREFIX, TOLERANCE, VALUE_ID" +
            "                                             FROM DO_ADDITIONAL_DATA " +
            "                                             WHERE ERR_CODE = 1) " +
            "             USING (DI_ID, P_ID) " +
            "             WHERE (DI_ID =?) AND (DO_STRING.ERR_CODE = 1))LOJ JOIN DE_PROPERTY P" +
            "                                                                       ON LOJ.P_ID = P.ID"),

    /** Get property type and unit information from a property with given property id */
    GET_PROPERTY_DATA_TYPE("SELECT " +
            // first all DE_PROPERTY attributes
            "p.id as property_id, p.irdi, p.preferred_name AS property_name, " +
            // then all DE_DATA_TYPE attributes
            "t.are_optional, t.bound1, t.bound2, t.cardinality, t.code, t.id as type_id, " +
            "t.is_multidim, t.length, t.list_type, t.list_type_dim, t.max, t.min, t.nom, t.pr_id, " +
            "t.preferred_name as TYPE_PREFERRED_NAME, t.short_name, t.sub_type, t.supplier_code, " +
            "t.supplier_coded_code, t.typ, t.u_id, t.uniqueness, t.version, " +
            // finally all DE_UNIT attributes
            "u.id as unit_id, u.SYMBOL, u.UNIT_TYPE, u.UNIT_NAME, u.UNIT_CATEGORY, u.FACTOR_TO_SI, u.CODE, u.SUPPLIER_CODE " +
            "FROM de_property p, de_unit u, de_data_type t " +
            "WHERE p.dt_id = t.id " +
            "AND u.id = t.u_id " +
            "AND p.id IN (?)"),

    /** Get a list of all available datatypes */
    GET_ALL_DATA_TYPES("SELECT DISTINCT t.sub_type FROM de_data_type ts");


    /** variable holding the sql query as string */
    private String SQL;

    SQLQuery(String sql) {
        this.SQL = sql;
    }

    public String getSql() {
        return this.SQL;
    }
}
