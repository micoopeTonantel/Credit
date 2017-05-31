/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Rgalicia
 */
public class CreditElectrodomesticoJpaController implements Serializable {

    public CreditElectrodomesticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditElectrodomestico creditElectrodomestico) {
        if (creditElectrodomestico.getCreditSolicitudList() == null) {
            creditElectrodomestico.setCreditSolicitudList(new ArrayList<CreditSolicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditSolicitud> attachedCreditSolicitudList = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListCreditSolicitudToAttach : creditElectrodomestico.getCreditSolicitudList()) {
                creditSolicitudListCreditSolicitudToAttach = em.getReference(creditSolicitudListCreditSolicitudToAttach.getClass(), creditSolicitudListCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudList.add(creditSolicitudListCreditSolicitudToAttach);
            }
            creditElectrodomestico.setCreditSolicitudList(attachedCreditSolicitudList);
            em.persist(creditElectrodomestico);
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditElectrodomestico.getCreditSolicitudList()) {
                CreditElectrodomestico oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListCreditSolicitud = creditSolicitudListCreditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
                creditSolicitudListCreditSolicitud.setCreditElectrodomesticoIdelectrodomestico(creditElectrodomestico);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
                if (oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListCreditSolicitud != null) {
                    oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListCreditSolicitud);
                    oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListCreditSolicitud = em.merge(oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListCreditSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditElectrodomestico creditElectrodomestico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditElectrodomestico persistentCreditElectrodomestico = em.find(CreditElectrodomestico.class, creditElectrodomestico.getIdelectrodomestico());
            List<CreditSolicitud> creditSolicitudListOld = persistentCreditElectrodomestico.getCreditSolicitudList();
            List<CreditSolicitud> creditSolicitudListNew = creditElectrodomestico.getCreditSolicitudList();
            List<CreditSolicitud> attachedCreditSolicitudListNew = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListNewCreditSolicitudToAttach : creditSolicitudListNew) {
                creditSolicitudListNewCreditSolicitudToAttach = em.getReference(creditSolicitudListNewCreditSolicitudToAttach.getClass(), creditSolicitudListNewCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudListNew.add(creditSolicitudListNewCreditSolicitudToAttach);
            }
            creditSolicitudListNew = attachedCreditSolicitudListNew;
            creditElectrodomestico.setCreditSolicitudList(creditSolicitudListNew);
            creditElectrodomestico = em.merge(creditElectrodomestico);
            for (CreditSolicitud creditSolicitudListOldCreditSolicitud : creditSolicitudListOld) {
                if (!creditSolicitudListNew.contains(creditSolicitudListOldCreditSolicitud)) {
                    creditSolicitudListOldCreditSolicitud.setCreditElectrodomesticoIdelectrodomestico(null);
                    creditSolicitudListOldCreditSolicitud = em.merge(creditSolicitudListOldCreditSolicitud);
                }
            }
            for (CreditSolicitud creditSolicitudListNewCreditSolicitud : creditSolicitudListNew) {
                if (!creditSolicitudListOld.contains(creditSolicitudListNewCreditSolicitud)) {
                    CreditElectrodomestico oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud = creditSolicitudListNewCreditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
                    creditSolicitudListNewCreditSolicitud.setCreditElectrodomesticoIdelectrodomestico(creditElectrodomestico);
                    creditSolicitudListNewCreditSolicitud = em.merge(creditSolicitudListNewCreditSolicitud);
                    if (oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud != null && !oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud.equals(creditElectrodomestico)) {
                        oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListNewCreditSolicitud);
                        oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud = em.merge(oldCreditElectrodomesticoIdelectrodomesticoOfCreditSolicitudListNewCreditSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditElectrodomestico.getIdelectrodomestico();
                if (findCreditElectrodomestico(id) == null) {
                    throw new NonexistentEntityException("The creditElectrodomestico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditElectrodomestico creditElectrodomestico;
            try {
                creditElectrodomestico = em.getReference(CreditElectrodomestico.class, id);
                creditElectrodomestico.getIdelectrodomestico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditElectrodomestico with id " + id + " no longer exists.", enfe);
            }
            List<CreditSolicitud> creditSolicitudList = creditElectrodomestico.getCreditSolicitudList();
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditSolicitudList) {
                creditSolicitudListCreditSolicitud.setCreditElectrodomesticoIdelectrodomestico(null);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
            }
            em.remove(creditElectrodomestico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditElectrodomestico> findCreditElectrodomesticoEntities() {
        return findCreditElectrodomesticoEntities(true, -1, -1);
    }

    public List<CreditElectrodomestico> findCreditElectrodomesticoEntities(int maxResults, int firstResult) {
        return findCreditElectrodomesticoEntities(false, maxResults, firstResult);
    }

    private List<CreditElectrodomestico> findCreditElectrodomesticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditElectrodomestico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CreditElectrodomestico findCreditElectrodomestico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditElectrodomestico.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditElectrodomesticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditElectrodomestico> rt = cq.from(CreditElectrodomestico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
