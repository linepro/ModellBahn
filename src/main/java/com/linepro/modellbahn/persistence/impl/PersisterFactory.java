package com.linepro.modellbahn.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.ILoggerFactory;

import com.linepro.modellbahn.guice.ISessionManagerFactory;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILand;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.model.impl.Land;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.impl.ZugTyp;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.IPersisterFactory;

/**
 * A factory for creating Persister objects.
 */
public class PersisterFactory implements IPersisterFactory {

    /** The entity manager. */
    private final ISessionManagerFactory sessionManagerFactory;

    /** The log manager. */
    private final ILoggerFactory logManager;

    /** The persisters. */
    private final Map<Class<? extends IItem<?>>,IPersister<? extends IItem<?>>> persisters = new HashMap<>();
    
    /**
     * Instantiates a new persister factory.
     *
     * @param sessionManagerFactory the session manager
     * @param logManager the log manager
     */
    @Inject
    public PersisterFactory(final ISessionManagerFactory sessionManagerFactory, final ILoggerFactory logManager) {
        this.sessionManagerFactory = sessionManagerFactory;
        this.logManager = logManager;
    }

    @Override
    public synchronized <I extends IItem<?>> IPersister<I> createPersister(Class<I> interfaceClass) {
        @SuppressWarnings("unchecked")
        IPersister<I> persister = (IPersister<I>) persisters.get(interfaceClass);
        
        if (persister == null) {
            Class<?> mappedEntity = mappedEntity(interfaceClass);

            persister = new ItemPersister<>(sessionManagerFactory, logManager, mappedEntity);
            
            registerConverter(persister, interfaceClass);

            persisters.put(interfaceClass, persister);
        }
        
        return persister;
    }
    
    private Class<?> mappedEntity(Class<?> interfaceClass) {
        if (IAchsfolg.class.equals(interfaceClass)) {
            return Achsfolg.class;
        } else if (IAntrieb.class.equals(interfaceClass)) {
            return Antrieb.class;
        } else if (IArtikel.class.equals(interfaceClass)) {
            return Artikel.class;
        } else if (IAufbau.class.equals(interfaceClass)) {
            return Aufbau.class;
        } else if (IBahnverwaltung.class.equals(interfaceClass)) {
            return Bahnverwaltung.class;
        } else if (IDecoder.class.equals(interfaceClass)) {
            return Decoder.class;
        } else if (IDecoderAdress.class.equals(interfaceClass)) {
            return DecoderAdress.class;
        } else if (IDecoderCV.class.equals(interfaceClass)) {
            return DecoderCV.class;
        } else if (IDecoderFunktion.class.equals(interfaceClass)) {
            return DecoderFunktion.class;
        } else if (IDecoderTyp.class.equals(interfaceClass)) {
            return DecoderTyp.class;
        } else if (IDecoderTypAdress.class.equals(interfaceClass)) {
            return DecoderTypAdress.class;
        } else if (IDecoderTypCV.class.equals(interfaceClass)) {
            return DecoderTypCV.class;
        } else if (IDecoderTypFunktion.class.equals(interfaceClass)) {
            return DecoderTypFunktion.class;
        } else if (IEpoch.class.equals(interfaceClass)) {
            return Epoch.class;
        } else if (IGattung.class.equals(interfaceClass)) {
            return Gattung.class;
        } else if (IHersteller.class.equals(interfaceClass)) {
            return Hersteller.class;
        } else if (IKategorie.class.equals(interfaceClass)) {
            return Kategorie.class;
        } else if (IKupplung.class.equals(interfaceClass)) {
            return Kupplung.class;
        } else if (ILand.class.equals(interfaceClass)) {
            return Land.class;
        } else if (ILicht.class.equals(interfaceClass)) {
            return Licht.class;
        } else if (IMassstab.class.equals(interfaceClass)) {
            return Massstab.class;
        } else if (IMotorTyp.class.equals(interfaceClass)) {
            return MotorTyp.class;
        } else if (IProdukt.class.equals(interfaceClass)) {
            return Produkt.class;
        } else if (IProduktTeil.class.equals(interfaceClass)) {
            return ProduktTeil.class;
        } else if (IProtokoll.class.equals(interfaceClass)) {
            return Protokoll.class;
        } else if (ISonderModell.class.equals(interfaceClass)) {
            return SonderModell.class;
        } else if (ISpurweite.class.equals(interfaceClass)) {
            return Spurweite.class;
        } else if (ISteuerung.class.equals(interfaceClass)) {
            return Steuerung.class;
        } else if (IUnterKategorie.class.equals(interfaceClass)) {
            return UnterKategorie.class;
        } else if (IVorbild.class.equals(interfaceClass)) {
            return Vorbild.class;
        } else if (IWahrung.class.equals(interfaceClass)) {
            return Wahrung.class;
        } else if (IZug.class.equals(interfaceClass)) {
            return Zug.class;
        } else if (IZugConsist.class.equals(interfaceClass)) {
            return ZugConsist.class;
        } else if (IZugTyp.class.equals(interfaceClass)) {
            return ZugTyp.class;
        }

        return interfaceClass;
    }

    private void registerConverter(IPersister<?> persister, Class<?> entityClass) {
        if (INamedItem.class.isAssignableFrom(entityClass)) {
            ConvertUtils.register(new NamedItemConverter(persister), entityClass);
        }
    }
}
