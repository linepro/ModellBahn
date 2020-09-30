package com.linepro.modellbahn.validation;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
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

import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.UnterKategorie;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("UniqueValidator")
@NoArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, Item>, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {
        EntityManager entityManager = applicationContext.getBean(EntityManager.class);
        FlushModeType flushModeType = entityManager.getFlushMode();

        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);

            Session session = entityManager.unwrap(Session.class);

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(Long.class);

            Root<?> root = criteriaQuery.from(item.getClass());

            if (item instanceof UnterKategorie) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.KATEGORIE), ((UnterKategorie) item).getKategorie()),
                                criteriaBuilder.equal(root.get(ApiNames.NAMEN), ((UnterKategorie) item).getName())
                                });
            } else if (item instanceof DecoderTyp) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.HERSTELLER), ((DecoderTyp) item).getHersteller()),
                                criteriaBuilder.equal(root.get(ApiNames.BESTELL_NR), ((DecoderTyp) item).getBestellNr()) 
                                });
            } else if (item instanceof DecoderTypAdress) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.DECODER_TYP), ((DecoderTypAdress) item).getDecoderTyp()),
                                criteriaBuilder.equal(root.get(ApiNames.POSITION), ((DecoderTypAdress) item).getPosition())
                                });
            } else if (item instanceof DecoderTypCv) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.DECODER_TYP), ((DecoderTypCv) item).getDecoderTyp()),
                                criteriaBuilder.equal(root.get(ApiNames.CV), ((DecoderTypCv) item).getCv())
                                });
            } else if (item instanceof DecoderTypFunktion) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.DECODER_TYP), ((DecoderTypFunktion) item).getDecoderTyp()),
                                criteriaBuilder.equal(root.get(ApiNames.REIHE), ((DecoderTypFunktion) item).getReihe()),
                                criteriaBuilder.equal(root.get(ApiNames.FUNKTION), ((DecoderTypFunktion) item).getFunktion())
                                });
            } else if (item instanceof Produkt) {
                criteriaQuery = criteriaQuery.where(new Predicate[] { 
                                criteriaBuilder.equal(root.get(ApiNames.HERSTELLER), ((Produkt) item).getHersteller()),
                                criteriaBuilder.equal(root.get(ApiNames.BESTELL_NR), ((Produkt) item).getBestellNr())
                                });
            } else if (item instanceof NamedItem) {
                criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get(ApiNames.NAMEN), ((NamedItem) item).getName()));
            }

            criteriaQuery.select(root.get(ApiNames.ID));
            TypedQuery<?> typedQuery = session.createQuery(criteriaQuery);

            try {
                Long actual = (Long) typedQuery.getSingleResult();

                return (item.getId() != null && item.getId().equals(actual));
            } catch (NoResultException e) {
                return true;
            } catch (NonUniqueResultException e) {
                return false;
            }
        } catch (PersistenceException e) {
            log.error("Error checking for duplicate {}: {}", item, e.getMessage(), e);
        } finally {
            entityManager.setFlushMode(flushModeType);
        }

        return true;
   }
}
