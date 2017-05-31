/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rgalicia
 */
public class CreditRequisitoJpaController implements Serializable {

    public CreditRequisitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditRequisito creditRequisito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditTipocredito creditTipocreditoIdtipocredito = creditRequisito.getCreditTipocreditoIdtipocredito();
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito = em.getReference(creditTipocreditoIdtipocredito.getClass(), creditTipocreditoIdtipocredito.getIdtipocredito());
                creditRequisito.setCreditTipocreditoIdtipocredito(creditTipocreditoIdtipocredito);
            }
            em.persist(creditRequisito);
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito.getCreditRequisitoList().add(creditRequisito);
                creditTipocreditoIdtipocredito = em.merge(creditTipocreditoIdtipocredito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditRequisito creditRequisito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditRequisito persistentCreditRequisito = em.find(CreditRequisito.class, creditRequisito.getIdrequisito());
            CreditTipocredito creditTipocreditoIdtipocreditoOld = persistentCreditRequisito.getCreditTipocreditoIdtipocredito();
            CreditTipocredito creditTipocreditoIdtipocreditoNew = creditRequisito.getCreditTipocreditoIdtipocredito();
            if (creditTipocreditoIdtipocreditoNew != null) {
                creditTipocreditoIdtipocreditoNew = em.getReference(creditTipocreditoIdtipocreditoNew.getClass(), creditTipocreditoIdtipocreditoNew.getIdtipocredito());
                creditRequisito.setCreditTipocreditoIdtipocredito(creditTipocreditoIdtipocreditoNew);
            }
            creditRequisito = em.merge(creditRequisito);
            if (creditTipocreditoIdtipocreditoOld != null && !creditTipocreditoIdtipocreditoOld.equals(creditTipocreditoIdtipocreditoNew)) {
                creditTipocreditoIdtipocreditoOld.getCreditRequisitoList().remove(creditRequisito);
                creditTipocreditoIdtipocreditoOld = em.merge(creditTipocreditoIdtipocreditoOld);
            }
            if (creditTipocreditoIdtipocreditoNew != null && !creditTipocreditoIdtipocreditoNew.equals(creditTipocreditoIdtipocreditoOld)) {
                creditTipocreditoIdtipocreditoNew.getCreditRequisitoList().add(creditRequisito);
                creditTipocreditoIdtipocreditoNew = em.merge(creditTipocreditoIdtipocreditoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditRequisito.getIdrequisito();
                if (findCreditRequisito(id) == null) {
                    throw new NonexistentEntityException("The creditRequisito with id " + id + " no longer exists.");
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
            CreditRequisito creditRequisito;
            try {
                creditRequisito = em.getReference(CreditRequisito.class, id);
                creditRequisito.getIdrequisito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditRequisito with id " + id + " no longer exists.", enfe);
            }
            CreditTipocredito creditTipocreditoIdtipocredito = creditRequisito.getCreditTipocreditoIdtipocredito();
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito.getCreditRequisitoList().remove(creditRequisito);
                creditTipocreditoIdtipocredito = em.merge(creditTipocreditoIdtipocredito);
            }
            em.remove(creditRequisito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditRequisito> findCreditRequisitoEntities() {
        return findCreditRequisitoEntities(true, -1, -1);
    }

    public List<CreditRequisito> findCreditRequisitoEntities(int maxResults, int firstResult) {
        return findCreditRequisitoEntities(false, maxResults, firstResult);
    }

    private List<CreditRequisito> findCreditRequisitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditRequisito.class));
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

    public CreditRequisito findCreditRequisito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditRequisito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditRequisitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditRequisito> rt = cq.from(CreditRequisito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
