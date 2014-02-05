package de.feu.plib.processor.analyser;

/**
 * Irdi only class model
 */
public class BaseIrdi implements Irdi {

    private String irdi;

    public BaseIrdi(String irdi) {
        this.irdi = irdi;
    }

    @Override
    public String getIrdi() {
        return irdi;
    }
}
