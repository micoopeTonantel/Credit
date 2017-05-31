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
public class CreditIngresoJpaController implements Serializable {

    public CreditIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditIngreso creditIngreso) {
        if (creditIngreso.getCreditCotizacionList() == null) {
            creditIngreso.setCreditCotizacionList(new ArrayList<CreditCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditCotizacion> attachedCreditCotizacionList = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListCreditCotizacionToAttach : creditIngreso.getCreditCotizacionList()) {
                creditCotizacionListCreditCotizacionToAttach = em.getReference(creditCotizacionListCreditCotizacionToAttach.getClass(), creditCotizacionListCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionList.add(creditCotizacionListCreditCotizacionToAttach);
            }
            creditIngreso.setCreditCotizacionList(attachedCreditCotizacionList);
            em.persist(creditIngreso);
            for (CreditCotizacion creditCotizacionListCreditCotizacion : creditIngreso.getCreditCotizacionList()) {
                CreditIngreso oldCreditIngresoIdingresoOfCreditCotizacionListCreditCotizacion = creditCotizacionListCreditCotizacion.getCreditIngresoIdingreso();
                creditCotizacionListCreditCotizacion.setCreditIngresoIdingreso(creditIngreso);
                creditCotizacionListCreditCotizacion = em.merge(creditCotizacionListCreditCotizacion);
                if (oldCreditIngresoIdingresoOfCreditCotizacionListCreditCotizacion != null) {
                    oldCreditIngresoIdingresoOfCreditCotizacionListCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListCreditCotizacion);
                    oldCreditIngresoIdingresoOfCreditCotizacionListCreditCotizacion = em.merge(oldCreditIngresoIdingresoOfCreditCotizacionListCreditCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditIngreso creditIngreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditIngreso persistentCreditIngreso = em.find(CreditIngreso.class, creditIngreso.getIdingreso());
            List<CreditCotizacion> creditCotizacionListOld = persistentCreditIngreso.getCreditCotizacionList();
            List<CreditCotizacion> creditCotizacionListNew = creditIngreso.getCreditCotizacionList();
            List<String> illegalOrphanMessages = null;
            for (CreditCotizacion creditCotizacionListOldCreditCotizacion : creditCotizacionListOld) {
                if (!creditCotizacionListNew.contains(creditCotizacionListOldCreditCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditCotizacion " + creditCotizacionListOldCreditCotizacion + " since its creditIngresoIdingreso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CreditCotizacion> attachedCreditCotizacionListNew = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListNewCreditCotizacionToAttach : creditCotizacionListNew) {
                creditCotizacionListNewCreditCotizacionToAttach = em.getReference(creditCotizacionListNewCreditCotizacionToAttach.getClass(), creditCotizacionListNewCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionListNew.add(creditCotizacionListNewCreditCotizacionToAttach);
            }
            creditCotizacionListNew = attachedCreditCotizacionListNew;
            creditIngreso.setCreditCotizacionList(creditCotizacionListNew);
            creditIngreso = em.merge(creditIngreso);
            for (CreditCotizacion creditCotizacionListNewCreditCotizacion : creditCotizacionListNew) {
                if (!creditCotizacionListOld.contains(creditCotizacionListNewCreditCotizacion)) {
                    CreditIngreso oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion = creditCotizacionListNewCreditCotizacion.getCreditIngresoIdingreso();
                    creditCotizacionListNewCreditCotizacion.setCreditIngresoIdingreso(creditIngreso);
                    creditCotizacionListNewCreditCotizacion = em.merge(creditCotizacionListNewCreditCotizacion);
                    if (oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion != null && !oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion.equals(creditIngreso)) {
                        oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListNewCreditCotizacion);
                        oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion = em.merge(oldCreditIngresoIdingresoOfCreditCotizacionListNewCreditCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditIngreso.getIdingreso();
                if (findCreditIngreso(id) == null) {
                    throw new NonexistentEntityException("The creditIngreso with id " + id + " no longer exists.");
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
            CreditIngreso creditIngreso;
            try {
                creditIngreso = em.getReference(CreditIngreso.class, id);
                creditIngreso.getIdingreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditIngreso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditCotizacion> creditCotizacionListOrphanCheck = creditIngreso.getCreditCotizacionList();
            for (CreditCotizacion creditCotizacionListOrphanCheckCreditCotizacion : creditCotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditIngreso (" + creditIngreso + ") cannot be destroyed since the CreditCotizacion " + creditCotizacionListOrphanCheckCreditCotizacion + " in its creditCotizacionList field has a non-nullable creditIngresoIdingreso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditIngreso> findCreditIngresoEntities() {
        return findCreditIngresoEntities(true, -1, -1);
    }

    public List<CreditIngreso> findCreditIngresoEntities(int maxResults, int firstResult) {
        return findCreditIngresoEntities(false, maxResults, firstResult);
    }

    private List<CreditIngreso> findCreditIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditIngreso.class));
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

    public CreditIngreso findCreditIngreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditIngreso> rt = cq.from(CreditIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
