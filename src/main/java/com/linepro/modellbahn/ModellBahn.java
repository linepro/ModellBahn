package com.linepro.modellbahn;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.persistence.EntityManagerUtil;

//Hibernate JPA With H2 Example

public class ModellBahn {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public static void main(String[] args) {
        ModellBahn example = new ModellBahn();

        System.out.println("After Sucessfully insertion ");
        Achsfolg achsfolg1 = example.saveAchsfolg("Sumith");
        Achsfolg achsfolg2 = example.saveAchsfolg("Anoop");
        example.listAchsfolg();

        System.out.println("After Sucessfully modification ");
        example.updateAchsfolg(achsfolg1.getId(), "Sumith Honai");
        example.updateAchsfolg(achsfolg2.getId(), "Anoop Pavanai");
        example.listAchsfolg();

        System.out.println("After Sucessfully deletion ");
        example.deleteAchsfolg(achsfolg2.getId());
        example.listAchsfolg();
    }

    public Achsfolg saveAchsfolg(String achsfolgName) {
        Achsfolg achsfolg = new Achsfolg();

        try {
            entityManager.getTransaction().begin();

            achsfolg.setName(achsfolgName);
            achsfolg = entityManager.merge(achsfolg);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        return achsfolg;
    }

    public void listAchsfolg() {
        try {
            entityManager.getTransaction().begin();

            @SuppressWarnings("unchecked")
            List<Achsfolg> Achsfolgs = entityManager.createQuery("from Achsfolg").getResultList();

            for (Iterator<Achsfolg> iterator = Achsfolgs.iterator(); iterator.hasNext();) {
                Achsfolg achsfolg = (Achsfolg) iterator.next();
                System.out.println(achsfolg.getName());
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateAchsfolg(Long achsfolgId, String achsfolgName) {
        try {
            entityManager.getTransaction().begin();

            Achsfolg achsfolg = (Achsfolg) entityManager.find(Achsfolg.class, achsfolgId);
            achsfolg.setName(achsfolgName);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteAchsfolg(Long achsfolgId) {
        try {
            entityManager.getTransaction().begin();

            Achsfolg achsfolg = (Achsfolg) entityManager.find(Achsfolg.class, achsfolgId);

            entityManager.remove(achsfolg);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}