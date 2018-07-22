package com.linepro.modellbahn.model;

public interface IDecoderCV {

    public IDecoder getDecoder();

    public void setDecoder(IDecoder decoder);

    public IDecoderTypCV getCV();

    public void setCV(IDecoderTypCV cv);

    Integer getWert();

    void setWert(Integer wert);

    int hashCode();

}