package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

public class VorbildCriterion extends AbstractCriterion<Vorbild> implements Criterion {

    private final Optional<VorbildModel> vorbildModel;

    public VorbildCriterion(Optional<VorbildModel> vorbildModel) {
        this.vorbildModel = vorbildModel;
    }

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> vorbild) {
        List<Predicate> where = new ArrayList<>();
        if (vorbildModel.isPresent()) {
            VorbildModel model = vorbildModel.get();
            addJoinCondition(criteriaBuilder, vorbild, where, DBNames.GATTUNG, model.getGattung());
            addCondition(criteriaBuilder, vorbild, where, DBNames.BEZEICHNUNG, model.getBezeichnung());
            if (StringUtils.hasText(model.getKategorie()) || StringUtils.hasText(model.getUnterKategorie())) {
                Join<?,?> unterkategorie = vorbild.join(DBNames.UNTER_KATEGORIE);
                if (StringUtils.hasText(model.getKategorie())) {
                    addJoinCondition(criteriaBuilder, unterkategorie, where, DBNames.KATEGORIE, model.getKategorie());
                }
                addCondition(criteriaBuilder, unterkategorie, where, DBNames.NAME, model.getUnterKategorie());
            }
            addCondition(criteriaBuilder, vorbild, where, DBNames.BAHNVERWALTUNG, model.getBahnverwaltung());
            addCondition(criteriaBuilder, vorbild, where, DBNames.HERSTELLER, model.getHersteller());
            addCondition(criteriaBuilder, vorbild, where, DBNames.BAUZEIT, model.getBauzeit());
            addCondition(criteriaBuilder, vorbild, where, DBNames.ANZAHL, model.getAnzahl());
            addCondition(criteriaBuilder, vorbild, where, DBNames.BETREIBSNUMMER, model.getBetreibsNummer());
            addJoinCondition(criteriaBuilder, vorbild, where, DBNames.ANTRIEB, model.getAntrieb());
            addJoinCondition(criteriaBuilder, vorbild, where, DBNames.ACHSFOLG, model.getAchsfolg());
            addCondition(criteriaBuilder, vorbild, where, DBNames.ANFAHRZUGKRAFT, model.getAnfahrzugkraft());
            addCondition(criteriaBuilder, vorbild, where, DBNames.LEISTUNG, model.getLeistung());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DIENSTGEWICHT, model.getDienstgewicht());
            addCondition(criteriaBuilder, vorbild, where, DBNames.GESCHWINDIGKEIT, model.getGeschwindigkeit());
            addCondition(criteriaBuilder, vorbild, where, DBNames.LANGE, model.getLange());
            addCondition(criteriaBuilder, vorbild, where, DBNames.AUSSERDIENST, model.getAusserdienst());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DMTREIBRAD, model.getDmTreibrad());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DMLAUFRADVORN, model.getDmLaufradVorn());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DMLAUFRADHINTEN, model.getDmLaufradHinten());
            addCondition(criteriaBuilder, vorbild, where, DBNames.ZYLINDER, model.getZylinder());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DMZYLINDER, model.getDmZylinder());
            addCondition(criteriaBuilder, vorbild, where, DBNames.KOLBENHUB, model.getKolbenhub());
            addCondition(criteriaBuilder, vorbild, where, DBNames.KESSELUBERDRUCK, model.getKesseluberdruck());
            addCondition(criteriaBuilder, vorbild, where, DBNames.ROSTFLACHE, model.getRostflache());
            addCondition(criteriaBuilder, vorbild, where, DBNames.UBERHITZERFLACHE, model.getUberhitzerflache());
            addCondition(criteriaBuilder, vorbild, where, DBNames.WASSERVORRAT, model.getWasservorrat());
            addCondition(criteriaBuilder, vorbild, where, DBNames.VERDAMPFUNG, model.getVerdampfung());
            addCondition(criteriaBuilder, vorbild, where, DBNames.FAHRMOTOREN, model.getFahrmotoren());
            addCondition(criteriaBuilder, vorbild, where, DBNames.MOTORBAUART, model.getMotorbauart());
            addCondition(criteriaBuilder, vorbild, where, DBNames.LEISTUNGSUBERTRAGUNG, model.getLeistungsUbertragung());
            addCondition(criteriaBuilder, vorbild, where, DBNames.REICHWEITE, model.getReichweite());
            addCondition(criteriaBuilder, vorbild, where, DBNames.KAPAZITAT, model.getKapazitat());
            addCondition(criteriaBuilder, vorbild, where, DBNames.KLASSE, model.getKlasse());
            addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL1, model.getSitzplatzeKL1());
            addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL2, model.getSitzplatzeKL2());
            addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL3, model.getSitzplatzeKL3());
            addCondition(criteriaBuilder, vorbild, where, DBNames.SITZPLATZEKL4, model.getSitzplatzeKL4());
            addCondition(criteriaBuilder, vorbild, where, DBNames.AUFBAU, model.getAufbau());
            addCondition(criteriaBuilder, vorbild, where, DBNames.TRIEBKOPF, model.getTriebkopf());
            addCondition(criteriaBuilder, vorbild, where, DBNames.MITTELWAGEN, model.getMittelwagen());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DREHGESTELLBAUART, model.getDrehgestellBauart());
            addCondition(criteriaBuilder, vorbild, where, DBNames.DELETED, model.getDeleted());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "vorbild.lookup";
    }
}
