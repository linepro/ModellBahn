package com.linepro.modellbahn.model;

public interface IDecoderTypCV {

    IDecoderTyp getDecoderTyp();

    void setDecoderTyp(IDecoderTyp decoderTyp);

    Integer getCv();

    void setCv(Integer cv);

    String getBezeichnung();

    void setBezeichnung(String bezeichnung);

    Long getMinimal();

    void setMinimal(Long minimal);

    Long getMaximal();

    void setMaximal(Long maximal);

    Long getWerkseinstellung();

    void setWerkseinstellung(Long werkseinstellung);

}