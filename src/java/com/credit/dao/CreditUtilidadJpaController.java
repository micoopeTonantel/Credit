/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.IllegalOrphanException;
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
public class CreditUtilidadJpaController implements Serializable {

    public CreditUtilidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditUtilidad creditUtilidad) {
        if (creditUtilidad.getCreditFinanciamientoList() == null) {
            creditUtilidad.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditUtilidad.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditUtilidad.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditUtilidad);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditUtilidad.getCreditFinanciamientoList()) {
                CreditUtilidad oldCreditUtilidadIdutilidadOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditUtilidadIdutilidad();
                creditFinanciamientoListCreditFinanciamiento.setCreditUtilidadIdutilidad(creditUtilidad);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditUtilidadIdutilidadOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditUtilidadIdutilidadOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditUtilidadIdutilidadOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditUtilidadIdutilidadOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditUtilidad creditUtilidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditUtilidad persistentCreditUtilidad = em.find(CreditUtilidad.class, creditUtilidad.getIdutilidad());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditUtilidad.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditUtilidad.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditUtilidadIdutilidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CreditFinanciamiento> attachedCreditFinanciamientoListNew = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamientoToAttach : creditFinanciamientoListNew) {
                creditFinanciamientoListNewCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListNewCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListNewCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoListNew.add(creditFinanciamientoListNewCreditFinanciamientoToAttach);
            }
            creditFinanciamientoListNew = attachedCreditFinanciamientoListNew;
            creditUtilidad.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditUtilidad = em.merge(creditUtilidad);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditUtilidad oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditUtilidadIdutilidad();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditUtilidadIdutilidad(creditUtilidad);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditUtilidad)) {
                        oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditUtilidadIdutilidadOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditUtilidad.getIdutilidad();
                if (findCreditUtilidad(id) == null) {
                    throw new NonexistentEntityException("The creditUtilidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditUtilidad creditUtilidad;
            try {
                creditUtilidad = em.getReference(CreditUtilidad.class, id);
                creditUtilidad.getIdutilidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditUtilidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditUtilidad.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditUtilidad (" + creditUtilidad + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditUtilidadIdutilidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditUtilidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditUtilidad> findCreditUtilidadEntities() {
        return findCreditUtilidadEntities(true, -1, -1);
    }

    public List<CreditUtilidad> findCreditUtilidadEntities(int maxResults, int firstResult) {
        return findCreditUtilidadEntities(false, maxResults, firstResult);
    }

    private List<CreditUtilidad> findCreditUtilidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditUtilidad.class));
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

    public CreditUtilidad findCreditUtilidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditUtilidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditUtilidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditUtilidad> rt = cq.from(CreditUtilidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
