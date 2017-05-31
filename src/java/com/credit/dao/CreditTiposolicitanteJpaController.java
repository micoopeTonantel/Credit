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
public class CreditTiposolicitanteJpaController implements Serializable {

    public CreditTiposolicitanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditTiposolicitante creditTiposolicitante) {
        if (creditTiposolicitante.getCreditFinanciamientoList() == null) {
            creditTiposolicitante.setCreditFinanciamientoList(new ArrayList<CreditFinanciamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditFinanciamiento> attachedCreditFinanciamientoList = new ArrayList<CreditFinanciamiento>();
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamientoToAttach : creditTiposolicitante.getCreditFinanciamientoList()) {
                creditFinanciamientoListCreditFinanciamientoToAttach = em.getReference(creditFinanciamientoListCreditFinanciamientoToAttach.getClass(), creditFinanciamientoListCreditFinanciamientoToAttach.getIdfinanciamiento());
                attachedCreditFinanciamientoList.add(creditFinanciamientoListCreditFinanciamientoToAttach);
            }
            creditTiposolicitante.setCreditFinanciamientoList(attachedCreditFinanciamientoList);
            em.persist(creditTiposolicitante);
            for (CreditFinanciamiento creditFinanciamientoListCreditFinanciamiento : creditTiposolicitante.getCreditFinanciamientoList()) {
                CreditTiposolicitante oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListCreditFinanciamiento = creditFinanciamientoListCreditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
                creditFinanciamientoListCreditFinanciamiento.setCreditTiposolicitanteIdtiposolicitante(creditTiposolicitante);
                creditFinanciamientoListCreditFinanciamiento = em.merge(creditFinanciamientoListCreditFinanciamiento);
                if (oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListCreditFinanciamiento != null) {
                    oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListCreditFinanciamiento);
                    oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListCreditFinanciamiento = em.merge(oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListCreditFinanciamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditTiposolicitante creditTiposolicitante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditTiposolicitante persistentCreditTiposolicitante = em.find(CreditTiposolicitante.class, creditTiposolicitante.getIdtiposolicitante());
            List<CreditFinanciamiento> creditFinanciamientoListOld = persistentCreditTiposolicitante.getCreditFinanciamientoList();
            List<CreditFinanciamiento> creditFinanciamientoListNew = creditTiposolicitante.getCreditFinanciamientoList();
            List<String> illegalOrphanMessages = null;
            for (CreditFinanciamiento creditFinanciamientoListOldCreditFinanciamiento : creditFinanciamientoListOld) {
                if (!creditFinanciamientoListNew.contains(creditFinanciamientoListOldCreditFinanciamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditFinanciamiento " + creditFinanciamientoListOldCreditFinanciamiento + " since its creditTiposolicitanteIdtiposolicitante field is not nullable.");
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
            creditTiposolicitante.setCreditFinanciamientoList(creditFinanciamientoListNew);
            creditTiposolicitante = em.merge(creditTiposolicitante);
            for (CreditFinanciamiento creditFinanciamientoListNewCreditFinanciamiento : creditFinanciamientoListNew) {
                if (!creditFinanciamientoListOld.contains(creditFinanciamientoListNewCreditFinanciamiento)) {
                    CreditTiposolicitante oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento = creditFinanciamientoListNewCreditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
                    creditFinanciamientoListNewCreditFinanciamiento.setCreditTiposolicitanteIdtiposolicitante(creditTiposolicitante);
                    creditFinanciamientoListNewCreditFinanciamiento = em.merge(creditFinanciamientoListNewCreditFinanciamiento);
                    if (oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento != null && !oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento.equals(creditTiposolicitante)) {
                        oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento.getCreditFinanciamientoList().remove(creditFinanciamientoListNewCreditFinanciamiento);
                        oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento = em.merge(oldCreditTiposolicitanteIdtiposolicitanteOfCreditFinanciamientoListNewCreditFinanciamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditTiposolicitante.getIdtiposolicitante();
                if (findCreditTiposolicitante(id) == null) {
                    throw new NonexistentEntityException("The creditTiposolicitante with id " + id + " no longer exists.");
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
            CreditTiposolicitante creditTiposolicitante;
            try {
                creditTiposolicitante = em.getReference(CreditTiposolicitante.class, id);
                creditTiposolicitante.getIdtiposolicitante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditTiposolicitante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditFinanciamiento> creditFinanciamientoListOrphanCheck = creditTiposolicitante.getCreditFinanciamientoList();
            for (CreditFinanciamiento creditFinanciamientoListOrphanCheckCreditFinanciamiento : creditFinanciamientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditTiposolicitante (" + creditTiposolicitante + ") cannot be destroyed since the CreditFinanciamiento " + creditFinanciamientoListOrphanCheckCreditFinanciamiento + " in its creditFinanciamientoList field has a non-nullable creditTiposolicitanteIdtiposolicitante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditTiposolicitante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditTiposolicitante> findCreditTiposolicitanteEntities() {
        return findCreditTiposolicitanteEntities(true, -1, -1);
    }

    public List<CreditTiposolicitante> findCreditTiposolicitanteEntities(int maxResults, int firstResult) {
        return findCreditTiposolicitanteEntities(false, maxResults, firstResult);
    }

    private List<CreditTiposolicitante> findCreditTiposolicitanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditTiposolicitante.class));
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

    public CreditTiposolicitante findCreditTiposolicitante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditTiposolicitante.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditTiposolicitanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditTiposolicitante> rt = cq.from(CreditTiposolicitante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
