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
public class CreditDestinofondoJpaController implements Serializable {

    public CreditDestinofondoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditDestinofondo creditDestinofondo) {
        if (creditDestinofondo.getCreditFinanciamientoList() == null) {
            creditDestinofondo.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditDestinofondo.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditDestinofondo.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditDestinofondo);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditDestinofondo.getCreditFinanciamientoList()) {
                CreditDestinofondo oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditDestinofondoIddestinofondo();
                creditFinanciamientoListCreditFinanciamiento.setCreditDestinofondoIddestinofondo(creditDestinofondo);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditDestinofondo creditDestinofondo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditDestinofondo persistentCreditDestinofondo = em.find(CreditDestinofondo.class, creditDestinofondo.getIddestinofondo());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditDestinofondo.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditDestinofondo.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditDestinofondoIddestinofondo field is not nullable.");
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
            creditDestinofondo.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditDestinofondo = em.merge(creditDestinofondo);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditDestinofondo oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditDestinofondoIddestinofondo();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditDestinofondoIddestinofondo(creditDestinofondo);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditDestinofondo)) {
                        oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditDestinofondoIddestinofondoOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditDestinofondo.getIddestinofondo();
                if (findCreditDestinofondo(id) == null) {
                    throw new NonexistentEntityException("The creditDestinofondo with id " + id + " no longer exists.");
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
            CreditDestinofondo creditDestinofondo;
            try {
                creditDestinofondo = em.getReference(CreditDestinofondo.class, id);
                creditDestinofondo.getIddestinofondo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditDestinofondo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditDestinofondo.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditDestinofondo (" + creditDestinofondo + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditDestinofondoIddestinofondo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditDestinofondo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditDestinofondo> findCreditDestinofondoEntities() {
        return findCreditDestinofondoEntities(true, -1, -1);
    }

    public List<CreditDestinofondo> findCreditDestinofondoEntities(int maxResults, int firstResult) {
        return findCreditDestinofondoEntities(false, maxResults, firstResult);
    }

    private List<CreditDestinofondo> findCreditDestinofondoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditDestinofondo.class));
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

    public CreditDestinofondo findCreditDestinofondo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditDestinofondo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditDestinofondoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditDestinofondo> rt = cq.from(CreditDestinofondo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
