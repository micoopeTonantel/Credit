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
public class CreditExclusivoJpaController implements Serializable {

    public CreditExclusivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditExclusivo creditExclusivo) {
        if (creditExclusivo.getCreditSolicitudList() == null) {
            creditExclusivo.setCreditSolicitudList(new ArrayList<CreditSolicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditSolicitud> attachedCreditSolicitudList = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListCreditSolicitudToAttach : creditExclusivo.getCreditSolicitudList()) {
                creditSolicitudListCreditSolicitudToAttach = em.getReference(creditSolicitudListCreditSolicitudToAttach.getClass(), creditSolicitudListCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudList.add(creditSolicitudListCreditSolicitudToAttach);
            }
            creditExclusivo.setCreditSolicitudList(attachedCreditSolicitudList);
            em.persist(creditExclusivo);
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditExclusivo.getCreditSolicitudList()) {
                CreditExclusivo oldCreditExclusivoIdexclusivoOfCreditSolicitudListCreditSolicitud = creditSolicitudListCreditSolicitud.getCreditExclusivoIdexclusivo();
                creditSolicitudListCreditSolicitud.setCreditExclusivoIdexclusivo(creditExclusivo);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
                if (oldCreditExclusivoIdexclusivoOfCreditSolicitudListCreditSolicitud != null) {
                    oldCreditExclusivoIdexclusivoOfCreditSolicitudListCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListCreditSolicitud);
                    oldCreditExclusivoIdexclusivoOfCreditSolicitudListCreditSolicitud = em.merge(oldCreditExclusivoIdexclusivoOfCreditSolicitudListCreditSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditExclusivo creditExclusivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditExclusivo persistentCreditExclusivo = em.find(CreditExclusivo.class, creditExclusivo.getIdexclusivo());
            List<CreditSolicitud> creditSolicitudListOld = persistentCreditExclusivo.getCreditSolicitudList();
            List<CreditSolicitud> creditSolicitudListNew = creditExclusivo.getCreditSolicitudList();
            List<CreditSolicitud> attachedCreditSolicitudListNew = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListNewCreditSolicitudToAttach : creditSolicitudListNew) {
                creditSolicitudListNewCreditSolicitudToAttach = em.getReference(creditSolicitudListNewCreditSolicitudToAttach.getClass(), creditSolicitudListNewCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudListNew.add(creditSolicitudListNewCreditSolicitudToAttach);
            }
            creditSolicitudListNew = attachedCreditSolicitudListNew;
            creditExclusivo.setCreditSolicitudList(creditSolicitudListNew);
            creditExclusivo = em.merge(creditExclusivo);
            for (CreditSolicitud creditSolicitudListOldCreditSolicitud : creditSolicitudListOld) {
                if (!creditSolicitudListNew.contains(creditSolicitudListOldCreditSolicitud)) {
                    creditSolicitudListOldCreditSolicitud.setCreditExclusivoIdexclusivo(null);
                    creditSolicitudListOldCreditSolicitud = em.merge(creditSolicitudListOldCreditSolicitud);
                }
            }
            for (CreditSolicitud creditSolicitudListNewCreditSolicitud : creditSolicitudListNew) {
                if (!creditSolicitudListOld.contains(creditSolicitudListNewCreditSolicitud)) {
                    CreditExclusivo oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud = creditSolicitudListNewCreditSolicitud.getCreditExclusivoIdexclusivo();
                    creditSolicitudListNewCreditSolicitud.setCreditExclusivoIdexclusivo(creditExclusivo);
                    creditSolicitudListNewCreditSolicitud = em.merge(creditSolicitudListNewCreditSolicitud);
                    if (oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud != null && !oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud.equals(creditExclusivo)) {
                        oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListNewCreditSolicitud);
                        oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud = em.merge(oldCreditExclusivoIdexclusivoOfCreditSolicitudListNewCreditSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditExclusivo.getIdexclusivo();
                if (findCreditExclusivo(id) == null) {
                    throw new NonexistentEntityException("The creditExclusivo with id " + id + " no longer exists.");
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
            CreditExclusivo creditExclusivo;
            try {
                creditExclusivo = em.getReference(CreditExclusivo.class, id);
                creditExclusivo.getIdexclusivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditExclusivo with id " + id + " no longer exists.", enfe);
            }
            List<CreditSolicitud> creditSolicitudList = creditExclusivo.getCreditSolicitudList();
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditSolicitudList) {
                creditSolicitudListCreditSolicitud.setCreditExclusivoIdexclusivo(null);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
            }
            em.remove(creditExclusivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditExclusivo> findCreditExclusivoEntities() {
        return findCreditExclusivoEntities(true, -1, -1);
    }

    public List<CreditExclusivo> findCreditExclusivoEntities(int maxResults, int firstResult) {
        return findCreditExclusivoEntities(false, maxResults, firstResult);
    }

    private List<CreditExclusivo> findCreditExclusivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditExclusivo.class));
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

    public CreditExclusivo findCreditExclusivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditExclusivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditExclusivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditExclusivo> rt = cq.from(CreditExclusivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
