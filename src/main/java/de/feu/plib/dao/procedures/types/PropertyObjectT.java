package de.feu.plib.dao.procedures.types;

/**
 * Interface PropertyObjectT.
 * Contract for PropertyObjects that declare what is needed to be a usable property.
 */
public interface PropertyObjectT {
    String getIrdi();

    Long getId();

    String getValue();
}
