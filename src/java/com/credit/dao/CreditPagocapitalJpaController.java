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
public class CreditPagocapitalJpaController implements Serializable {

    public CreditPagocapitalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditPagocapital creditPagocapital) {
        if (creditPagocapital.getCreditFinanciamientoList() == null) {
            creditPagocapital.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditPagocapital.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditPagocapital.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditPagocapital);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditPagocapital.getCreditFinanciamientoList()) {
                CreditPagocapital oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditPagocapitalIdpagocapital();
                creditFinanciamientoListCreditFinanciamiento.setCreditPagocapitalIdpagocapital(creditPagocapital);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditPagocapital creditPagocapital) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditPagocapital persistentCreditPagocapital = em.find(CreditPagocapital.class, creditPagocapital.getIdpagocapital());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditPagocapital.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditPagocapital.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditPagocapitalIdpagocapital field is not nullable.");
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
            creditPagocapital.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditPagocapital = em.merge(creditPagocapital);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditPagocapital oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditPagocapitalIdpagocapital();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditPagocapitalIdpagocapital(creditPagocapital);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditPagocapital)) {
                        oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditPagocapitalIdpagocapitalOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditPagocapital.getIdpagocapital();
                if (findCreditPagocapital(id) == null) {
                    throw new NonexistentEntityException("The creditPagocapital with id " + id + " no longer exists.");
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
            CreditPagocapital creditPagocapital;
            try {
                creditPagocapital = em.getReference(CreditPagocapital.class, id);
                creditPagocapital.getIdpagocapital();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditPagocapital with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditPagocapital.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditPagocapital (" + creditPagocapital + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditPagocapitalIdpagocapital field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditPagocapital);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditPagocapital> findCreditPagocapitalEntities() {
        return findCreditPagocapitalEntities(true, -1, -1);
    }

    public List<CreditPagocapital> findCreditPagocapitalEntities(int maxResults, int firstResult) {
        return findCreditPagocapitalEntities(false, maxResults, firstResult);
    }

    private List<CreditPagocapital> findCreditPagocapitalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditPagocapital.class));
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

    public CreditPagocapital findCreditPagocapital(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditPagocapital.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditPagocapitalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditPagocapital> rt = cq.from(CreditPagocapital.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
