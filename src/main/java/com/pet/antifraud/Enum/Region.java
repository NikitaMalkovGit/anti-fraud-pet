package com.pet.antifraud.Enum;

/**
 * Enum representing different regions.
 */
public enum Region {
    EAP("East Asia and Pacific"),
    ECA("Europe and Central Asia"),
    HIC("High-Income countries"),
    LAC("Latin America and the Caribbean"),
    MENA("The Middle East and North Africa"),
    SA("South Asia"),
    SSA("Sub-Saharan Africa");

    private final String region;

    /**
     * Constructor to initialize the region with its display name.
     *
     * @param region The display name of the region.
     */
    Region(String region) {
        this.region = region;
    }

    /**
     * Returns the display name of the region.
     *
     * @return The display name of the region.
     */
    @Override
    public String toString() {
        return this.region;
    }
}
