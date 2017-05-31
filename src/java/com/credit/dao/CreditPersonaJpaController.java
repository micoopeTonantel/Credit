/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.IllegalOrphanException;
import com.credit.dao.exceptions.NonexistentEntityException;
import com.credit.dao.exceptions.PreexistingEntityException;
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
public class CreditPersonaJpaController implements Serializable {

    public CreditPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditPersona creditPersona) throws PreexistingEntityException, Exception {
        if (creditPersona.getCreditAsociadoList() == null) {
            creditPersona.setCreditAsociadoList(new ArrayList<CreditAsociado>());
        }
        if (creditPersona.getCreditCotizacionList() == null) {
            creditPersona.setCreditCotizacionList(new ArrayList<CreditCotizacion>());
        }
        if (creditPersona.getCreditConyugueList() == null) {
            creditPersona.setCreditConyugueList(new ArrayList<CreditConyugue>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CreditAsociado> attachedCreditAsociadoList = new ArrayList<CreditAsociado>();
            for (CreditAsociado creditAsociadoListCreditAsociadoToAttach : creditPersona.getCreditAsociadoList()) {
                creditAsociadoListCreditAsociadoToAttach = em.getReference(creditAsociadoListCreditAsociadoToAttach.getClass(), creditAsociadoListCreditAsociadoToAttach.getCif());
                attachedCreditAsociadoList.add(creditAsociadoListCreditAsociadoToAttach);
            }
            creditPersona.setCreditAsociadoList(attachedCreditAsociadoList);
            List<CreditCotizacion> attachedCreditCotizacionList = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListCreditCotizacionToAttach : creditPersona.getCreditCotizacionList()) {
                creditCotizacionListCreditCotizacionToAttach = em.getReference(creditCotizacionListCreditCotizacionToAttach.getClass(), creditCotizacionListCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionList.add(creditCotizacionListCreditCotizacionToAttach);
            }
            creditPersona.setCreditCotizacionList(attachedCreditCotizacionList);
            List<CreditConyugue> attachedCreditConyugueList = new ArrayList<CreditConyugue>();
            for (CreditConyugue creditConyugueListCreditConyugueToAttach : creditPersona.getCreditConyugueList()) {
                creditConyugueListCreditConyugueToAttach = em.getReference(creditConyugueListCreditConyugueToAttach.getClass(), creditConyugueListCreditConyugueToAttach.getIdconyugue());
                attachedCreditConyugueList.add(creditConyugueListCreditConyugueToAttach);
            }
            creditPersona.setCreditConyugueList(attachedCreditConyugueList);
            em.persist(creditPersona);
            for (CreditAsociado creditAsociadoListCreditAsociado : creditPersona.getCreditAsociadoList()) {
                CreditPersona oldCreditPersonaDpiOfCreditAsociadoListCreditAsociado = creditAsociadoListCreditAsociado.getCreditPersonaDpi();
                creditAsociadoListCreditAsociado.setCreditPersonaDpi(creditPersona);
                creditAsociadoListCreditAsociado = em.merge(creditAsociadoListCreditAsociado);
                if (oldCreditPersonaDpiOfCreditAsociadoListCreditAsociado != null) {
                    oldCreditPersonaDpiOfCreditAsociadoListCreditAsociado.getCreditAsociadoList().remove(creditAsociadoListCreditAsociado);
                    oldCreditPersonaDpiOfCreditAsociadoListCreditAsociado = em.merge(oldCreditPersonaDpiOfCreditAsociadoListCreditAsociado);
                }
            }
            for (CreditCotizacion creditCotizacionListCreditCotizacion : creditPersona.getCreditCotizacionList()) {
                CreditPersona oldCreditPersonaDpiOfCreditCotizacionListCreditCotizacion = creditCotizacionListCreditCotizacion.getCreditPersonaDpi();
                creditCotizacionListCreditCotizacion.setCreditPersonaDpi(creditPersona);
                creditCotizacionListCreditCotizacion = em.merge(creditCotizacionListCreditCotizacion);
                if (oldCreditPersonaDpiOfCreditCotizacionListCreditCotizacion != null) {
                    oldCreditPersonaDpiOfCreditCotizacionListCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListCreditCotizacion);
                    oldCreditPersonaDpiOfCreditCotizacionListCreditCotizacion = em.merge(oldCreditPersonaDpiOfCreditCotizacionListCreditCotizacion);
                }
            }
            for (CreditConyugue creditConyugueListCreditConyugue : creditPersona.getCreditConyugueList()) {
                CreditPersona oldCreditPersonaDpiOfCreditConyugueListCreditConyugue = creditConyugueListCreditConyugue.getCreditPersonaDpi();
                creditConyugueListCreditConyugue.setCreditPersonaDpi(creditPersona);
                creditConyugueListCreditConyugue = em.merge(creditConyugueListCreditConyugue);
                if (oldCreditPersonaDpiOfCreditConyugueListCreditConyugue != null) {
                    oldCreditPersonaDpiOfCreditConyugueListCreditConyugue.getCreditConyugueList().remove(creditConyugueListCreditConyugue);
                    oldCreditPersonaDpiOfCreditConyugueListCreditConyugue = em.merge(oldCreditPersonaDpiOfCreditConyugueListCreditConyugue);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditPersona(creditPersona.getDpi()) != null) {
                throw new PreexistingEntityException("CreditPersona " + creditPersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditPersona creditPersona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditPersona persistentCreditPersona = em.find(CreditPersona.class, creditPersona.getDpi());
            List<CreditAsociado> creditAsociadoListOld = persistentCreditPersona.getCreditAsociadoList();
            List<CreditAsociado> creditAsociadoListNew = creditPersona.getCreditAsociadoList();
            List<CreditCotizacion> creditCotizacionListOld = persistentCreditPersona.getCreditCotizacionList();
            List<CreditCotizacion> creditCotizacionListNew = creditPersona.getCreditCotizacionList();
            List<CreditConyugue> creditConyugueListOld = persistentCreditPersona.getCreditConyugueList();
            List<CreditConyugue> creditConyugueListNew = creditPersona.getCreditConyugueList();
            List<String> illegalOrphanMessages = null;
            for (CreditAsociado creditAsociadoListOldCreditAsociado : creditAsociadoListOld) {
                if (!creditAsociadoListNew.contains(creditAsociadoListOldCreditAsociado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditAsociado " + creditAsociadoListOldCreditAsociado + " since its creditPersonaDpi field is not nullable.");
                }
            }
            for (CreditCotizacion creditCotizacionListOldCreditCotizacion : creditCotizacionListOld) {
                if (!creditCotizacionListNew.contains(creditCotizacionListOldCreditCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditCotizacion " + creditCotizacionListOldCreditCotizacion + " since its creditPersonaDpi field is not nullable.");
                }
            }
            for (CreditConyugue creditConyugueListOldCreditConyugue : creditConyugueListOld) {
                if (!creditConyugueListNew.contains(creditConyugueListOldCreditConyugue)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditConyugue " + creditConyugueListOldCreditConyugue + " since its creditPersonaDpi field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CreditAsociado> attachedCreditAsociadoListNew = new ArrayList<CreditAsociado>();
            for (CreditAsociado creditAsociadoListNewCreditAsociadoToAttach : creditAsociadoListNew) {
                creditAsociadoListNewCreditAsociadoToAttach = em.getReference(creditAsociadoListNewCreditAsociadoToAttach.getClass(), creditAsociadoListNewCreditAsociadoToAttach.getCif());
                attachedCreditAsociadoListNew.add(creditAsociadoListNewCreditAsociadoToAttach);
            }
            creditAsociadoListNew = attachedCreditAsociadoListNew;
            creditPersona.setCreditAsociadoList(creditAsociadoListNew);
            List<CreditCotizacion> attachedCreditCotizacionListNew = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListNewCreditCotizacionToAttach : creditCotizacionListNew) {
                creditCotizacionListNewCreditCotizacionToAttach = em.getReference(creditCotizacionListNewCreditCotizacionToAttach.getClass(), creditCotizacionListNewCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionListNew.add(creditCotizacionListNewCreditCotizacionToAttach);
            }
            creditCotizacionListNew = attachedCreditCotizacionListNew;
            creditPersona.setCreditCotizacionList(creditCotizacionListNew);
            List<CreditConyugue> attachedCreditConyugueListNew = new ArrayList<CreditConyugue>();
            for (CreditConyugue creditConyugueListNewCreditConyugueToAttach : creditConyugueListNew) {
                creditConyugueListNewCreditConyugueToAttach = em.getReference(creditConyugueListNewCreditConyugueToAttach.getClass(), creditConyugueListNewCreditConyugueToAttach.getIdconyugue());
                attachedCreditConyugueListNew.add(creditConyugueListNewCreditConyugueToAttach);
            }
            creditConyugueListNew = attachedCreditConyugueListNew;
            creditPersona.setCreditConyugueList(creditConyugueListNew);
            creditPersona = em.merge(creditPersona);
            for (CreditAsociado creditAsociadoListNewCreditAsociado : creditAsociadoListNew) {
                if (!creditAsociadoListOld.contains(creditAsociadoListNewCreditAsociado)) {
                    CreditPersona oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado = creditAsociadoListNewCreditAsociado.getCreditPersonaDpi();
                    creditAsociadoListNewCreditAsociado.setCreditPersonaDpi(creditPersona);
                    creditAsociadoListNewCreditAsociado = em.merge(creditAsociadoListNewCreditAsociado);
                    if (oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado != null && !oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado.equals(creditPersona)) {
                        oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado.getCreditAsociadoList().remove(creditAsociadoListNewCreditAsociado);
                        oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado = em.merge(oldCreditPersonaDpiOfCreditAsociadoListNewCreditAsociado);
                    }
                }
            }
            for (CreditCotizacion creditCotizacionListNewCreditCotizacion : creditCotizacionListNew) {
                if (!creditCotizacionListOld.contains(creditCotizacionListNewCreditCotizacion)) {
                    CreditPersona oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion = creditCotizacionListNewCreditCotizacion.getCreditPersonaDpi();
                    creditCotizacionListNewCreditCotizacion.setCreditPersonaDpi(creditPersona);
                    creditCotizacionListNewCreditCotizacion = em.merge(creditCotizacionListNewCreditCotizacion);
                    if (oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion != null && !oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion.equals(creditPersona)) {
                        oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListNewCreditCotizacion);
                        oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion = em.merge(oldCreditPersonaDpiOfCreditCotizacionListNewCreditCotizacion);
                    }
                }
            }
            for (CreditConyugue creditConyugueListNewCreditConyugue : creditConyugueListNew) {
                if (!creditConyugueListOld.contains(creditConyugueListNewCreditConyugue)) {
                    CreditPersona oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue = creditConyugueListNewCreditConyugue.getCreditPersonaDpi();
                    creditConyugueListNewCreditConyugue.setCreditPersonaDpi(creditPersona);
                    creditConyugueListNewCreditConyugue = em.merge(creditConyugueListNewCreditConyugue);
                    if (oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue != null && !oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue.equals(creditPersona)) {
                        oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue.getCreditConyugueList().remove(creditConyugueListNewCreditConyugue);
                        oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue = em.merge(oldCreditPersonaDpiOfCreditConyugueListNewCreditConyugue);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = creditPersona.getDpi();
                if (findCreditPersona(id) == null) {
                    throw new NonexistentEntityException("The creditPersona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditPersona creditPersona;
            try {
                creditPersona = em.getReference(CreditPersona.class, id);
                creditPersona.getDpi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditPersona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditAsociado> creditAsociadoListOrphanCheck = creditPersona.getCreditAsociadoList();
            for (CreditAsociado creditAsociadoListOrphanCheckCreditAsociado : creditAsociadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditPersona (" + creditPersona + ") cannot be destroyed since the CreditAsociado " + creditAsociadoListOrphanCheckCreditAsociado + " in its creditAsociadoList field has a non-nullable creditPersonaDpi field.");
            }
            List<CreditCotizacion> creditCotizacionListOrphanCheck = creditPersona.getCreditCotizacionList();
            for (CreditCotizacion creditCotizacionListOrphanCheckCreditCotizacion : creditCotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditPersona (" + creditPersona + ") cannot be destroyed since the CreditCotizacion " + creditCotizacionListOrphanCheckCreditCotizacion + " in its creditCotizacionList field has a non-nullable creditPersonaDpi field.");
            }
            List<CreditConyugue> creditConyugueListOrphanCheck = creditPersona.getCreditConyugueList();
            for (CreditConyugue creditConyugueListOrphanCheckCreditConyugue : creditConyugueListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditPersona (" + creditPersona + ") cannot be destroyed since the CreditConyugue " + creditConyugueListOrphanCheckCreditConyugue + " in its creditConyugueList field has a non-nullable creditPersonaDpi field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(creditPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditPersona> findCreditPersonaEntities() {
        return findCreditPersonaEntities(true, -1, -1);
    }

    public List<CreditPersona> findCreditPersonaEntities(int maxResults, int firstResult) {
        return findCreditPersonaEntities(false, maxResults, firstResult);
    }

    private List<CreditPersona> findCreditPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditPersona.class));
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

    public CreditPersona findCreditPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditPersona> rt = cq.from(CreditPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
