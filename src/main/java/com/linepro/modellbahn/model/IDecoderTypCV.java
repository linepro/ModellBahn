package com.linepro.modellbahn.model;

public interface IDecoderTypCV {

    IDecoderTyp getDecoderTyp();

    void setDecoderTyp(IDecoderTyp decoderTyp);

    Integer getCv();

    void setCv(Integer cv);

    String getBezeichnung();

    void setBezeichnung(String bezeichnung);

    Integer getMinimal();

    void setMinimal(Integer minimal);

    Integer getMaximal();

    void setMaximal(Integer maximal);

    Integer getWerkseinstellung();

    void setWerkseinstellung(Integer werkseinstellung);

}