package com.linepro.modellbahn.model;

public interface IAdressId {

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    IAdressTyp getTyp();

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    void setTyp(IAdressTyp typ);

    /**
     * Gets the adress.
     *
     * @return the adress
     */
    Integer getAdress();

    /**
     * Sets the adress.
     *
     * @param adress the new adress
     */
    void setAdress(Integer adress);

}