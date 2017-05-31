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
public class CreditAsociadoJpaController implements Serializable {

    public CreditAsociadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditAsociado creditAsociado) throws PreexistingEntityException, Exception {
        if (creditAsociado.getCreditSolicitudList() == null) {
            creditAsociado.setCreditSolicitudList(new ArrayList<CreditSolicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditConyugue creditConyugueIdconyugue = creditAsociado.getCreditConyugueIdconyugue();
            if (creditConyugueIdconyugue != null) {
                creditConyugueIdconyugue = em.getReference(creditConyugueIdconyugue.getClass(), creditConyugueIdconyugue.getIdconyugue());
                creditAsociado.setCreditConyugueIdconyugue(creditConyugueIdconyugue);
            }
            CreditPersona creditPersonaDpi = creditAsociado.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi = em.getReference(creditPersonaDpi.getClass(), creditPersonaDpi.getDpi());
                creditAsociado.setCreditPersonaDpi(creditPersonaDpi);
            }
            List<CreditSolicitud> attachedCreditSolicitudList = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListCreditSolicitudToAttach : creditAsociado.getCreditSolicitudList()) {
                creditSolicitudListCreditSolicitudToAttach = em.getReference(creditSolicitudListCreditSolicitudToAttach.getClass(), creditSolicitudListCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudList.add(creditSolicitudListCreditSolicitudToAttach);
            }
            creditAsociado.setCreditSolicitudList(attachedCreditSolicitudList);
            em.persist(creditAsociado);
            if (creditConyugueIdconyugue != null) {
                creditConyugueIdconyugue.getCreditAsociadoList().add(creditAsociado);
                creditConyugueIdconyugue = em.merge(creditConyugueIdconyugue);
            }
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditAsociadoList().add(creditAsociado);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditAsociado.getCreditSolicitudList()) {
                CreditAsociado oldCreditAsociadoCifOfCreditSolicitudListCreditSolicitud = creditSolicitudListCreditSolicitud.getCreditAsociadoCif();
                creditSolicitudListCreditSolicitud.setCreditAsociadoCif(creditAsociado);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
                if (oldCreditAsociadoCifOfCreditSolicitudListCreditSolicitud != null) {
                    oldCreditAsociadoCifOfCreditSolicitudListCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListCreditSolicitud);
                    oldCreditAsociadoCifOfCreditSolicitudListCreditSolicitud = em.merge(oldCreditAsociadoCifOfCreditSolicitudListCreditSolicitud);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditAsociado(creditAsociado.getCif()) != null) {
                throw new PreexistingEntityException("CreditAsociado " + creditAsociado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditAsociado creditAsociado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditAsociado persistentCreditAsociado = em.find(CreditAsociado.class, creditAsociado.getCif());
            CreditConyugue creditConyugueIdconyugueOld = persistentCreditAsociado.getCreditConyugueIdconyugue();
            CreditConyugue creditConyugueIdconyugueNew = creditAsociado.getCreditConyugueIdconyugue();
            CreditPersona creditPersonaDpiOld = persistentCreditAsociado.getCreditPersonaDpi();
            CreditPersona creditPersonaDpiNew = creditAsociado.getCreditPersonaDpi();
            List<CreditSolicitud> creditSolicitudListOld = persistentCreditAsociado.getCreditSolicitudList();
            List<CreditSolicitud> creditSolicitudListNew = creditAsociado.getCreditSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (CreditSolicitud creditSolicitudListOldCreditSolicitud : creditSolicitudListOld) {
                if (!creditSolicitudListNew.contains(creditSolicitudListOldCreditSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditSolicitud " + creditSolicitudListOldCreditSolicitud + " since its creditAsociadoCif field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditConyugueIdconyugueNew != null) {
                creditConyugueIdconyugueNew = em.getReference(creditConyugueIdconyugueNew.getClass(), creditConyugueIdconyugueNew.getIdconyugue());
                creditAsociado.setCreditConyugueIdconyugue(creditConyugueIdconyugueNew);
            }
            if (creditPersonaDpiNew != null) {
                creditPersonaDpiNew = em.getReference(creditPersonaDpiNew.getClass(), creditPersonaDpiNew.getDpi());
                creditAsociado.setCreditPersonaDpi(creditPersonaDpiNew);
            }
            List<CreditSolicitud> attachedCreditSolicitudListNew = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListNewCreditSolicitudToAttach : creditSolicitudListNew) {
                creditSolicitudListNewCreditSolicitudToAttach = em.getReference(creditSolicitudListNewCreditSolicitudToAttach.getClass(), creditSolicitudListNewCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudListNew.add(creditSolicitudListNewCreditSolicitudToAttach);
            }
            creditSolicitudListNew = attachedCreditSolicitudListNew;
            creditAsociado.setCreditSolicitudList(creditSolicitudListNew);
            creditAsociado = em.merge(creditAsociado);
            if (creditConyugueIdconyugueOld != null && !creditConyugueIdconyugueOld.equals(creditConyugueIdconyugueNew)) {
                creditConyugueIdconyugueOld.getCreditAsociadoList().remove(creditAsociado);
                creditConyugueIdconyugueOld = em.merge(creditConyugueIdconyugueOld);
            }
            if (creditConyugueIdconyugueNew != null && !creditConyugueIdconyugueNew.equals(creditConyugueIdconyugueOld)) {
                creditConyugueIdconyugueNew.getCreditAsociadoList().add(creditAsociado);
                creditConyugueIdconyugueNew = em.merge(creditConyugueIdconyugueNew);
            }
            if (creditPersonaDpiOld != null && !creditPersonaDpiOld.equals(creditPersonaDpiNew)) {
                creditPersonaDpiOld.getCreditAsociadoList().remove(creditAsociado);
                creditPersonaDpiOld = em.merge(creditPersonaDpiOld);
            }
            if (creditPersonaDpiNew != null && !creditPersonaDpiNew.equals(creditPersonaDpiOld)) {
                creditPersonaDpiNew.getCreditAsociadoList().add(creditAsociado);
                creditPersonaDpiNew = em.merge(creditPersonaDpiNew);
            }
            for (CreditSolicitud creditSolicitudListNewCreditSolicitud : creditSolicitudListNew) {
                if (!creditSolicitudListOld.contains(creditSolicitudListNewCreditSolicitud)) {
                    CreditAsociado oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud = creditSolicitudListNewCreditSolicitud.getCreditAsociadoCif();
                    creditSolicitudListNewCreditSolicitud.setCreditAsociadoCif(creditAsociado);
                    creditSolicitudListNewCreditSolicitud = em.merge(creditSolicitudListNewCreditSolicitud);
                    if (oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud != null && !oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud.equals(creditAsociado)) {
                        oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListNewCreditSolicitud);
                        oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud = em.merge(oldCreditAsociadoCifOfCreditSolicitudListNewCreditSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = creditAsociado.getCif();
                if (findCreditAsociado(id) == null) {
                    throw new NonexistentEntityException("The creditAsociado with id " + id + " no longer exists.");
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
            CreditAsociado creditAsociado;
            try {
                creditAsociado = em.getReference(CreditAsociado.class, id);
                creditAsociado.getCif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditAsociado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditSolicitud> creditSolicitudListOrphanCheck = creditAsociado.getCreditSolicitudList();
            for (CreditSolicitud creditSolicitudListOrphanCheckCreditSolicitud : creditSolicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditAsociado (" + creditAsociado + ") cannot be destroyed since the CreditSolicitud " + creditSolicitudListOrphanCheckCreditSolicitud + " in its creditSolicitudList field has a non-nullable creditAsociadoCif field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CreditConyugue creditConyugueIdconyugue = creditAsociado.getCreditConyugueIdconyugue();
            if (creditConyugueIdconyugue != null) {
                creditConyugueIdconyugue.getCreditAsociadoList().remove(creditAsociado);
                creditConyugueIdconyugue = em.merge(creditConyugueIdconyugue);
            }
            CreditPersona creditPersonaDpi = creditAsociado.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditAsociadoList().remove(creditAsociado);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            em.remove(creditAsociado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditAsociado> findCreditAsociadoEntities() {
        return findCreditAsociadoEntities(true, -1, -1);
    }

    public List<CreditAsociado> findCreditAsociadoEntities(int maxResults, int firstResult) {
        return findCreditAsociadoEntities(false, maxResults, firstResult);
    }

    private List<CreditAsociado> findCreditAsociadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditAsociado.class));
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

    public CreditAsociado findCreditAsociado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditAsociado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditAsociadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditAsociado> rt = cq.from(CreditAsociado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
