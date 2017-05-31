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
public class CreditTipocuotaJpaController implements Serializable {

    public CreditTipocuotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditTipocuota creditTipocuota) {
        if (creditTipocuota.getCreditFinanciamientoList() == null) {
            creditTipocuota.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditTipocuota.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditTipocuota.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditTipocuota);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditTipocuota.getCreditFinanciamientoList()) {
                CreditTipocuota oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditTipocuotaIdtipocuota();
                creditFinanciamientoListCreditFinanciamiento.setCreditTipocuotaIdtipocuota(creditTipocuota);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditTipocuota creditTipocuota) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditTipocuota persistentCreditTipocuota = em.find(CreditTipocuota.class, creditTipocuota.getIdtipocuota());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditTipocuota.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditTipocuota.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditTipocuotaIdtipocuota field is not nullable.");
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
            creditTipocuota.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditTipocuota = em.merge(creditTipocuota);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditTipocuota oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditTipocuotaIdtipocuota();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditTipocuotaIdtipocuota(creditTipocuota);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditTipocuota)) {
                        oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditTipocuotaIdtipocuotaOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditTipocuota.getIdtipocuota();
                if (findCreditTipocuota(id) == null) {
                    throw new NonexistentEntityException("The creditTipocuota with id " + id + " no longer exists.");
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
            CreditTipocuota creditTipocuota;
            try {
                creditTipocuota = em.getReference(CreditTipocuota.class, id);
                creditTipocuota.getIdtipocuota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditTipocuota with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditTipocuota.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditTipocuota (" + creditTipocuota + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditTipocuotaIdtipocuota field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditTipocuota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditTipocuota> findCreditTipocuotaEntities() {
        return findCreditTipocuotaEntities(true, -1, -1);
    }

    public List<CreditTipocuota> findCreditTipocuotaEntities(int maxResults, int firstResult) {
        return findCreditTipocuotaEntities(false, maxResults, firstResult);
    }

    private List<CreditTipocuota> findCreditTipocuotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditTipocuota.class));
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

    public CreditTipocuota findCreditTipocuota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditTipocuota.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditTipocuotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditTipocuota> rt = cq.from(CreditTipocuota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
