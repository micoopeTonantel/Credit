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
public class PrivilegioJpaController implements Serializable {

    public PrivilegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Privilegio privilegio) {
        if (privilegio.getAsignarprivilegioList() == null) {
            privilegio.setAsignarprivilegioList(new ArrayList<Asignarprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolIdrol = privilegio.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol = em.getReference(rolIdrol.getClass(), rolIdrol.getIdrol());
                privilegio.setRolIdrol(rolIdrol);
            }
            List<Asignarprivilegio> attachedAsignarprivilegioList = new ArrayList<Asignarprivilegio>();
            for (Asignarprivilegio asignarprivilegioListAsignarprivilegioToAttach : privilegio.getAsignarprivilegioList()) {
                asignarprivilegioListAsignarprivilegioToAttach = em.getReference(asignarprivilegioListAsignarprivilegioToAttach.getClass(), asignarprivilegioListAsignarprivilegioToAttach.getAsignarprivilegioPK());
                attachedAsignarprivilegioList.add(asignarprivilegioListAsignarprivilegioToAttach);
            }
            privilegio.setAsignarprivilegioList(attachedAsignarprivilegioList);
            em.persist(privilegio);
            if (rolIdrol != null) {
                rolIdrol.getPrivilegioList().add(privilegio);
                rolIdrol = em.merge(rolIdrol);
            }
            for (Asignarprivilegio asignarprivilegioListAsignarprivilegio : privilegio.getAsignarprivilegioList()) {
                Privilegio oldPrivilegioOfAsignarprivilegioListAsignarprivilegio = asignarprivilegioListAsignarprivilegio.getPrivilegio();
                asignarprivilegioListAsignarprivilegio.setPrivilegio(privilegio);
                asignarprivilegioListAsignarprivilegio = em.merge(asignarprivilegioListAsignarprivilegio);
                if (oldPrivilegioOfAsignarprivilegioListAsignarprivilegio != null) {
                    oldPrivilegioOfAsignarprivilegioListAsignarprivilegio.getAsignarprivilegioList().remove(asignarprivilegioListAsignarprivilegio);
                    oldPrivilegioOfAsignarprivilegioListAsignarprivilegio = em.merge(oldPrivilegioOfAsignarprivilegioListAsignarprivilegio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Privilegio privilegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getIdprivilegio());
            Rol rolIdrolOld = persistentPrivilegio.getRolIdrol();
            Rol rolIdrolNew = privilegio.getRolIdrol();
            List<Asignarprivilegio> asignarprivilegioListOld = persistentPrivilegio.getAsignarprivilegioList();
            List<Asignarprivilegio> asignarprivilegioListNew = privilegio.getAsignarprivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Asignarprivilegio asignarprivilegioListOldAsignarprivilegio : asignarprivilegioListOld) {
                if (!asignarprivilegioListNew.contains(asignarprivilegioListOldAsignarprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignarprivilegio " + asignarprivilegioListOldAsignarprivilegio + " since its privilegio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolIdrolNew != null) {
                rolIdrolNew = em.getReference(rolIdrolNew.getClass(), rolIdrolNew.getIdrol());
                privilegio.setRolIdrol(rolIdrolNew);
            }
            List<Asignarprivilegio> attachedAsignarprivilegioListNew = new ArrayList<Asignarprivilegio>();
            for (Asignarprivilegio asignarprivilegioListNewAsignarprivilegioToAttach : asignarprivilegioListNew) {
                asignarprivilegioListNewAsignarprivilegioToAttach = em.getReference(asignarprivilegioListNewAsignarprivilegioToAttach.getClass(), asignarprivilegioListNewAsignarprivilegioToAttach.getAsignarprivilegioPK());
                attachedAsignarprivilegioListNew.add(asignarprivilegioListNewAsignarprivilegioToAttach);
            }
            asignarprivilegioListNew = attachedAsignarprivilegioListNew;
            privilegio.setAsignarprivilegioList(asignarprivilegioListNew);
            privilegio = em.merge(privilegio);
            if (rolIdrolOld != null && !rolIdrolOld.equals(rolIdrolNew)) {
                rolIdrolOld.getPrivilegioList().remove(privilegio);
                rolIdrolOld = em.merge(rolIdrolOld);
            }
            if (rolIdrolNew != null && !rolIdrolNew.equals(rolIdrolOld)) {
                rolIdrolNew.getPrivilegioList().add(privilegio);
                rolIdrolNew = em.merge(rolIdrolNew);
            }
            for (Asignarprivilegio asignarprivilegioListNewAsignarprivilegio : asignarprivilegioListNew) {
                if (!asignarprivilegioListOld.contains(asignarprivilegioListNewAsignarprivilegio)) {
                    Privilegio oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio = asignarprivilegioListNewAsignarprivilegio.getPrivilegio();
                    asignarprivilegioListNewAsignarprivilegio.setPrivilegio(privilegio);
                    asignarprivilegioListNewAsignarprivilegio = em.merge(asignarprivilegioListNewAsignarprivilegio);
                    if (oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio != null && !oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio.equals(privilegio)) {
                        oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio.getAsignarprivilegioList().remove(asignarprivilegioListNewAsignarprivilegio);
                        oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio = em.merge(oldPrivilegioOfAsignarprivilegioListNewAsignarprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = privilegio.getIdprivilegio();
                if (findPrivilegio(id) == null) {
                    throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.");
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
            Privilegio privilegio;
            try {
                privilegio = em.getReference(Privilegio.class, id);
                privilegio.getIdprivilegio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignarprivilegio> asignarprivilegioListOrphanCheck = privilegio.getAsignarprivilegioList();
            for (Asignarprivilegio asignarprivilegioListOrphanCheckAsignarprivilegio : asignarprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Privilegio (" + privilegio + ") cannot be destroyed since the Asignarprivilegio " + asignarprivilegioListOrphanCheckAsignarprivilegio + " in its asignarprivilegioList field has a non-nullable privilegio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol rolIdrol = privilegio.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol.getPrivilegioList().remove(privilegio);
                rolIdrol = em.merge(rolIdrol);
            }
            em.remove(privilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Privilegio> findPrivilegioEntities() {
        return findPrivilegioEntities(true, -1, -1);
    }

    public List<Privilegio> findPrivilegioEntities(int maxResults, int firstResult) {
        return findPrivilegioEntities(false, maxResults, firstResult);
    }

    private List<Privilegio> findPrivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Privilegio.class));
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

    public Privilegio findPrivilegio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Privilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Privilegio> rt = cq.from(Privilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
