package com.linepro.modellbahn.validation;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.persistence.DBNames;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, Item> {

    private final EntityManager entityManager;

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(item.getClass());
    
            Root<?> root = criteriaQuery.from(item.getClass());

            if (item instanceof UnterKategorie) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                    criteriaBuilder.equal(root.get(DBNames.KATEGORIE), ((UnterKategorie) item).getKategorie()),
                    criteriaBuilder.equal(root.get(DBNames.NAME), ((UnterKategorie) item).getName())
                    });
            } else if (item instanceof DecoderTyp) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(DBNames.HERSTELLER), ((DecoderTyp) item).getHersteller()),
                                criteriaBuilder.equal(root.get(DBNames.BESTELL_NR), ((DecoderTyp) item).getBestellNr()) 
                                });
            } else if (item instanceof DecoderTypAdress) {
                // TODO:
            } else if (item instanceof DecoderTypCv) {
                // TODO:
            } else if (item instanceof DecoderTypFunktion) {
                // TODO:
            } else if (item instanceof Produkt) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(DBNames.HERSTELLER), ((Produkt) item).getHersteller()),
                                criteriaBuilder.equal(root.get(DBNames.BESTELL_NR), ((Produkt) item).getBestellNr()) });
            } else if (item instanceof ProduktTeil) {
                // TODO:
            } else if (item instanceof Vorbild) {
                criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get(DBNames.GATTUNG), ((Vorbild) item).getGattung()));
            } else if (item instanceof ZugConsist) {
                // TODO:
            } else if (item instanceof NamedItem) {
                criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get(DBNames.NAME), ((NamedItem) item).getName()));
            }

            TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);

            try {
                typedQuery.getSingleResult();
                
                return false;
            } catch (NoResultException e) {
                return true;
            } catch (NonUniqueResultException e) {
                log.error(e.getMessage(), e);
                return false;
            }
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }

        return true;
   }
}
