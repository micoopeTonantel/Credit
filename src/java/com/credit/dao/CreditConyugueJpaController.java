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
public class CreditConyugueJpaController implements Serializable {

    public CreditConyugueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditConyugue creditConyugue) {
        if (creditConyugue.getCreditAsociadoList() == null) {
            creditConyugue.setCreditAsociadoList(new ArrayList<CreditAsociado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditPersona creditPersonaDpi = creditConyugue.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi = em.getReference(creditPersonaDpi.getClass(), creditPersonaDpi.getDpi());
                creditConyugue.setCreditPersonaDpi(creditPersonaDpi);
            }
            List<CreditAsociado> attachedCreditAsociadoList = new ArrayList<CreditAsociado>();
            for (CreditAsociado creditAsociadoListCreditAsociadoToAttach : creditConyugue.getCreditAsociadoList()) {
                creditAsociadoListCreditAsociadoToAttach = em.getReference(creditAsociadoListCreditAsociadoToAttach.getClass(), creditAsociadoListCreditAsociadoToAttach.getCif());
                attachedCreditAsociadoList.add(creditAsociadoListCreditAsociadoToAttach);
            }
            creditConyugue.setCreditAsociadoList(attachedCreditAsociadoList);
            em.persist(creditConyugue);
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditConyugueList().add(creditConyugue);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            for (CreditAsociado creditAsociadoListCreditAsociado : creditConyugue.getCreditAsociadoList()) {
                CreditConyugue oldCreditConyugueIdconyugueOfCreditAsociadoListCreditAsociado = creditAsociadoListCreditAsociado.getCreditConyugueIdconyugue();
                creditAsociadoListCreditAsociado.setCreditConyugueIdconyugue(creditConyugue);
                creditAsociadoListCreditAsociado = em.merge(creditAsociadoListCreditAsociado);
                if (oldCreditConyugueIdconyugueOfCreditAsociadoListCreditAsociado != null) {
                    oldCreditConyugueIdconyugueOfCreditAsociadoListCreditAsociado.getCreditAsociadoList().remove(creditAsociadoListCreditAsociado);
                    oldCreditConyugueIdconyugueOfCreditAsociadoListCreditAsociado = em.merge(oldCreditConyugueIdconyugueOfCreditAsociadoListCreditAsociado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditConyugue creditConyugue) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditConyugue persistentCreditConyugue = em.find(CreditConyugue.class, creditConyugue.getIdconyugue());
            CreditPersona creditPersonaDpiOld = persistentCreditConyugue.getCreditPersonaDpi();
            CreditPersona creditPersonaDpiNew = creditConyugue.getCreditPersonaDpi();
            List<CreditAsociado> creditAsociadoListOld = persistentCreditConyugue.getCreditAsociadoList();
            List<CreditAsociado> creditAsociadoListNew = creditConyugue.getCreditAsociadoList();
            if (creditPersonaDpiNew != null) {
                creditPersonaDpiNew = em.getReference(creditPersonaDpiNew.getClass(), creditPersonaDpiNew.getDpi());
                creditConyugue.setCreditPersonaDpi(creditPersonaDpiNew);
            }
            List<CreditAsociado> attachedCreditAsociadoListNew = new ArrayList<CreditAsociado>();
            for (CreditAsociado creditAsociadoListNewCreditAsociadoToAttach : creditAsociadoListNew) {
                creditAsociadoListNewCreditAsociadoToAttach = em.getReference(creditAsociadoListNewCreditAsociadoToAttach.getClass(), creditAsociadoListNewCreditAsociadoToAttach.getCif());
                attachedCreditAsociadoListNew.add(creditAsociadoListNewCreditAsociadoToAttach);
            }
            creditAsociadoListNew = attachedCreditAsociadoListNew;
            creditConyugue.setCreditAsociadoList(creditAsociadoListNew);
            creditConyugue = em.merge(creditConyugue);
            if (creditPersonaDpiOld != null && !creditPersonaDpiOld.equals(creditPersonaDpiNew)) {
                creditPersonaDpiOld.getCreditConyugueList().remove(creditConyugue);
                creditPersonaDpiOld = em.merge(creditPersonaDpiOld);
            }
            if (creditPersonaDpiNew != null && !creditPersonaDpiNew.equals(creditPersonaDpiOld)) {
                creditPersonaDpiNew.getCreditConyugueList().add(creditConyugue);
                creditPersonaDpiNew = em.merge(creditPersonaDpiNew);
            }
            for (CreditAsociado creditAsociadoListOldCreditAsociado : creditAsociadoListOld) {
                if (!creditAsociadoListNew.contains(creditAsociadoListOldCreditAsociado)) {
                    creditAsociadoListOldCreditAsociado.setCreditConyugueIdconyugue(null);
                    creditAsociadoListOldCreditAsociado = em.merge(creditAsociadoListOldCreditAsociado);
                }
            }
            for (CreditAsociado creditAsociadoListNewCreditAsociado : creditAsociadoListNew) {
                if (!creditAsociadoListOld.contains(creditAsociadoListNewCreditAsociado)) {
                    CreditConyugue oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado = creditAsociadoListNewCreditAsociado.getCreditConyugueIdconyugue();
                    creditAsociadoListNewCreditAsociado.setCreditConyugueIdconyugue(creditConyugue);
                    creditAsociadoListNewCreditAsociado = em.merge(creditAsociadoListNewCreditAsociado);
                    if (oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado != null && !oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado.equals(creditConyugue)) {
                        oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado.getCreditAsociadoList().remove(creditAsociadoListNewCreditAsociado);
                        oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado = em.merge(oldCreditConyugueIdconyugueOfCreditAsociadoListNewCreditAsociado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditConyugue.getIdconyugue();
                if (findCreditConyugue(id) == null) {
                    throw new NonexistentEntityException("The creditConyugue with id " + id + " no longer exists.");
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
            CreditConyugue creditConyugue;
            try {
                creditConyugue = em.getReference(CreditConyugue.class, id);
                creditConyugue.getIdconyugue();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditConyugue with id " + id + " no longer exists.", enfe);
            }
            CreditPersona creditPersonaDpi = creditConyugue.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditConyugueList().remove(creditConyugue);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            List<CreditAsociado> creditAsociadoList = creditConyugue.getCreditAsociadoList();
            for (CreditAsociado creditAsociadoListCreditAsociado : creditAsociadoList) {
                creditAsociadoListCreditAsociado.setCreditConyugueIdconyugue(null);
                creditAsociadoListCreditAsociado = em.merge(creditAsociadoListCreditAsociado);
            }
            em.remove(creditConyugue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditConyugue> findCreditConyugueEntities() {
        return findCreditConyugueEntities(true, -1, -1);
    }

    public List<CreditConyugue> findCreditConyugueEntities(int maxResults, int firstResult) {
        return findCreditConyugueEntities(false, maxResults, firstResult);
    }

    private List<CreditConyugue> findCreditConyugueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditConyugue.class));
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

    public CreditConyugue findCreditConyugue(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditConyugue.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditConyugueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditConyugue> rt = cq.from(CreditConyugue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
