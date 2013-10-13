package de.feu.plib.processor.analyser;

/**
 * Defines the receivability of an IRDI representation.
 *
 * IRDI means: International Registration Data Identifier.
 *
 * @see  http://wiki.eclass.de/wiki/IRDI for more information
 *
 */
public interface Irdi {

    /**
     * Returns the IRDI, from an object implementing the interface. Mainly will be used by QueryTypes.
     * If the IRDI is not available or not set, then an empty string must be returned.
     *
     * Note that using this method does not mean that it is a correct IRDI like specified.
     *
     * If you implement that interface and your receive the value from a {@link de.feu.plib.xml.query.QueryType}
     * then it was already validated!
     */
    public String getIrdi();
}
