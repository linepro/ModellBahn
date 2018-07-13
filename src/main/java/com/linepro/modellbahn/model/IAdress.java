package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.impl.AdressId;

public interface IAdress {

    /**
     * Gets the id.
     *
     * @return the id
     */
    AdressId getId();

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    void setId(AdressId id);

    /**
     * Gets the poles.
     *
     * @return the poles
     */
    Integer getPoles();

    /**
     * Sets the poles.
     *
     * @param poles the new poles
     */
    void setPoles(Integer poles);

    /**
     * Gets the switches.
     *
     * @return the switches
     */
    Long getSwitches();

    /**
     * Sets the switches.
     *
     * @param switches the new switches
     */
    void setSwitches(Long switches);

}