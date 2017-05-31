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
public class CreditPagointeresJpaController implements Serializable {

    public CreditPagointeresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditPagointeres creditPagointeres) {
        if (creditPagointeres.getCreditFinanciamientoList() == null) {
            creditPagointeres.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditPagointeres.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditPagointeres.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditPagointeres);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditPagointeres.getCreditFinanciamientoList()) {
                CreditPagointeres oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditPagointeresIdpagointeres();
                creditFinanciamientoListCreditFinanciamiento.setCreditPagointeresIdpagointeres(creditPagointeres);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditPagointeres creditPagointeres) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditPagointeres persistentCreditPagointeres = em.find(CreditPagointeres.class, creditPagointeres.getIdpagointeres());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditPagointeres.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditPagointeres.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditPagointeresIdpagointeres field is not nullable.");
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
            creditPagointeres.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditPagointeres = em.merge(creditPagointeres);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditPagointeres oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditPagointeresIdpagointeres();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditPagointeresIdpagointeres(creditPagointeres);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditPagointeres)) {
                        oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditPagointeresIdpagointeresOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditPagointeres.getIdpagointeres();
                if (findCreditPagointeres(id) == null) {
                    throw new NonexistentEntityException("The creditPagointeres with id " + id + " no longer exists.");
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
            CreditPagointeres creditPagointeres;
            try {
                creditPagointeres = em.getReference(CreditPagointeres.class, id);
                creditPagointeres.getIdpagointeres();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditPagointeres with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditPagointeres.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditPagointeres (" + creditPagointeres + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditPagointeresIdpagointeres field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditPagointeres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditPagointeres> findCreditPagointeresEntities() {
        return findCreditPagointeresEntities(true, -1, -1);
    }

    public List<CreditPagointeres> findCreditPagointeresEntities(int maxResults, int firstResult) {
        return findCreditPagointeresEntities(false, maxResults, firstResult);
    }

    private List<CreditPagointeres> findCreditPagointeresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditPagointeres.class));
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

    public CreditPagointeres findCreditPagointeres(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditPagointeres.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditPagointeresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditPagointeres> rt = cq.from(CreditPagointeres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
