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
public class CreditTipocreditoJpaController implements Serializable {

    public CreditTipocreditoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditTipocredito creditTipocredito) {
        if (creditTipocredito.getCreditFinanciamientoList() == null) {
            creditTipocredito.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        if (creditTipocredito.getCreditRequisitoList() == null) {
            creditTipocredito.setCreditRequisitoList(new ArrayList<CreditRequisito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditTipocredito.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditTipocredito.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            List<CreditRequisito> attachedCreditRequisitoList = new ArrayList<CreditRequisito>();
            for (CreditRequisito creditRequisitoListCreditRequisitoToAttach : creditTipocredito.getCreditRequisitoList()) {
                creditRequisitoListCreditRequisitoToAttach = em.getReference(creditRequisitoListCreditRequisitoToAttach.getClass(), creditRequisitoListCreditRequisitoToAttach.getIdrequisito());
                attachedCreditRequisitoList.add(creditRequisitoListCreditRequisitoToAttach);
            }
            creditTipocredito.setCreditRequisitoList(attachedCreditRequisitoList);
            em.persist(creditTipocredito);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditTipocredito.getCreditFinanciamientoList()) {
                CreditTipocredito oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditTipocreditoIdtipocredito();
                creditFinanciamientoListCreditFinanciamiento.setCreditTipocreditoIdtipocredito(creditTipocredito);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            for (CreditRequisito creditRequisitoListCreditRequisito : creditTipocredito.getCreditRequisitoList()) {
                CreditTipocredito oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListCreditRequisito = creditRequisitoListCreditRequisito.getCreditTipocreditoIdtipocredito();
                creditRequisitoListCreditRequisito.setCreditTipocreditoIdtipocredito(creditTipocredito);
                creditRequisitoListCreditRequisito = em.merge(creditRequisitoListCreditRequisito);
                if (oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListCreditRequisito != null) {
                    oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListCreditRequisito.getCreditRequisitoList().remove(creditRequisitoListCreditRequisito);
                    oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListCreditRequisito = em.merge(oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListCreditRequisito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditTipocredito creditTipocredito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditTipocredito persistentCreditTipocredito = em.find(CreditTipocredito.class, creditTipocredito.getIdtipocredito());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditTipocredito.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditTipocredito.getCreditFinanciamientoList();
            List<CreditRequisito> creditRequisitoListOld = persistentCreditTipocredito.getCreditRequisitoList();
            List<CreditRequisito> creditRequisitoListNew = creditTipocredito.getCreditRequisitoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditTipocreditoIdtipocredito field is not nullable.");
                }
            }
            for (CreditRequisito creditRequisitoListOldCreditRequisito : creditRequisitoListOld) {
                if (!creditRequisitoListNew.contains(creditRequisitoListOldCreditRequisito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditRequisito " + creditRequisitoListOldCreditRequisito + " since its creditTipocreditoIdtipocredito field is not nullable.");
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
            creditTipocredito.setCreditFinanciamientoList(creditFinanciamientoListNew);
            List<CreditRequisito> attachedCreditRequisitoListNew = new ArrayList<CreditRequisito>();
            for (CreditRequisito creditRequisitoListNewCreditRequisitoToAttach : creditRequisitoListNew) {
                creditRequisitoListNewCreditRequisitoToAttach = em.getReference(creditRequisitoListNewCreditRequisitoToAttach.getClass(), creditRequisitoListNewCreditRequisitoToAttach.getIdrequisito());
                attachedCreditRequisitoListNew.add(creditRequisitoListNewCreditRequisitoToAttach);
            }
            creditRequisitoListNew = attachedCreditRequisitoListNew;
            creditTipocredito.setCreditRequisitoList(creditRequisitoListNew);
            creditTipocredito = em.merge(creditTipocredito);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditTipocredito oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditTipocreditoIdtipocredito();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditTipocreditoIdtipocredito(creditTipocredito);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditTipocredito)) {
                        oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditTipocreditoIdtipocreditoOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            for (CreditRequisito creditRequisitoListNewCreditRequisito : creditRequisitoListNew) {
                if (!creditRequisitoListOld.contains(creditRequisitoListNewCreditRequisito)) {
                    CreditTipocredito oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito = creditRequisitoListNewCreditRequisito.getCreditTipocreditoIdtipocredito();
                    creditRequisitoListNewCreditRequisito.setCreditTipocreditoIdtipocredito(creditTipocredito);
                    creditRequisitoListNewCreditRequisito = em.merge(creditRequisitoListNewCreditRequisito);
                    if (oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito != null && !oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito.equals(creditTipocredito)) {
                        oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito.getCreditRequisitoList().remove(creditRequisitoListNewCreditRequisito);
                        oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito = em.merge(oldCreditTipocreditoIdtipocreditoOfCreditRequisitoListNewCreditRequisito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditTipocredito.getIdtipocredito();
                if (findCreditTipocredito(id) == null) {
                    throw new NonexistentEntityException("The creditTipocredito with id " + id + " no longer exists.");
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
            CreditTipocredito creditTipocredito;
            try {
                creditTipocredito = em.getReference(CreditTipocredito.class, id);
                creditTipocredito.getIdtipocredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditTipocredito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditTipocredito.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditTipocredito (" + creditTipocredito + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditTipocreditoIdtipocredito field.");
            }
            List<CreditRequisito> creditRequisitoListOrphanCheck = creditTipocredito.getCreditRequisitoList();
            for (CreditRequisito creditRequisitoListOrphanCheckCreditRequisito : creditRequisitoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditTipocredito (" + creditTipocredito + ") cannot be destroyed since the CreditRequisito " + creditRequisitoListOrphanCheckCreditRequisito + " in its creditRequisitoList field has a non-nullable creditTipocreditoIdtipocredito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditTipocredito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditTipocredito> findCreditTipocreditoEntities() {
        return findCreditTipocreditoEntities(true, -1, -1);
    }

    public List<CreditTipocredito> findCreditTipocreditoEntities(int maxResults, int firstResult) {
        return findCreditTipocreditoEntities(false, maxResults, firstResult);
    }

    private List<CreditTipocredito> findCreditTipocreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditTipocredito.class));
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

    public CreditTipocredito findCreditTipocredito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditTipocredito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditTipocreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditTipocredito> rt = cq.from(CreditTipocredito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
