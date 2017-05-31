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
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getAsignarrolList() == null) {
            rol.setAsignarrolList(new ArrayList<Asignarrol>());
        }
        if (rol.getPrivilegioList() == null) {
            rol.setPrivilegioList(new ArrayList<Privilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignarrol> attachedAsignarrolList = new ArrayList<Asignarrol>();
            for (Asignarrol asignarrolListAsignarrolToAttach : rol.getAsignarrolList()) {
                asignarrolListAsignarrolToAttach = em.getReference(asignarrolListAsignarrolToAttach.getClass(), asignarrolListAsignarrolToAttach.getAsignarrolPK());
                attachedAsignarrolList.add(asignarrolListAsignarrolToAttach);
            }
            rol.setAsignarrolList(attachedAsignarrolList);
            List<Privilegio> attachedPrivilegioList = new ArrayList<Privilegio>();
            for (Privilegio privilegioListPrivilegioToAttach : rol.getPrivilegioList()) {
                privilegioListPrivilegioToAttach = em.getReference(privilegioListPrivilegioToAttach.getClass(), privilegioListPrivilegioToAttach.getIdprivilegio());
                attachedPrivilegioList.add(privilegioListPrivilegioToAttach);
            }
            rol.setPrivilegioList(attachedPrivilegioList);
            em.persist(rol);
            for (Asignarrol asignarrolListAsignarrol : rol.getAsignarrolList()) {
                Rol oldRolOfAsignarrolListAsignarrol = asignarrolListAsignarrol.getRol();
                asignarrolListAsignarrol.setRol(rol);
                asignarrolListAsignarrol = em.merge(asignarrolListAsignarrol);
                if (oldRolOfAsignarrolListAsignarrol != null) {
                    oldRolOfAsignarrolListAsignarrol.getAsignarrolList().remove(asignarrolListAsignarrol);
                    oldRolOfAsignarrolListAsignarrol = em.merge(oldRolOfAsignarrolListAsignarrol);
                }
            }
            for (Privilegio privilegioListPrivilegio : rol.getPrivilegioList()) {
                Rol oldRolIdrolOfPrivilegioListPrivilegio = privilegioListPrivilegio.getRolIdrol();
                privilegioListPrivilegio.setRolIdrol(rol);
                privilegioListPrivilegio = em.merge(privilegioListPrivilegio);
                if (oldRolIdrolOfPrivilegioListPrivilegio != null) {
                    oldRolIdrolOfPrivilegioListPrivilegio.getPrivilegioList().remove(privilegioListPrivilegio);
                    oldRolIdrolOfPrivilegioListPrivilegio = em.merge(oldRolIdrolOfPrivilegioListPrivilegio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getIdrol());
            List<Asignarrol> asignarrolListOld = persistentRol.getAsignarrolList();
            List<Asignarrol> asignarrolListNew = rol.getAsignarrolList();
            List<Privilegio> privilegioListOld = persistentRol.getPrivilegioList();
            List<Privilegio> privilegioListNew = rol.getPrivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Asignarrol asignarrolListOldAsignarrol : asignarrolListOld) {
                if (!asignarrolListNew.contains(asignarrolListOldAsignarrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignarrol " + asignarrolListOldAsignarrol + " since its rol field is not nullable.");
                }
            }
            for (Privilegio privilegioListOldPrivilegio : privilegioListOld) {
                if (!privilegioListNew.contains(privilegioListOldPrivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Privilegio " + privilegioListOldPrivilegio + " since its rolIdrol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asignarrol> attachedAsignarrolListNew = new ArrayList<Asignarrol>();
            for (Asignarrol asignarrolListNewAsignarrolToAttach : asignarrolListNew) {
                asignarrolListNewAsignarrolToAttach = em.getReference(asignarrolListNewAsignarrolToAttach.getClass(), asignarrolListNewAsignarrolToAttach.getAsignarrolPK());
                attachedAsignarrolListNew.add(asignarrolListNewAsignarrolToAttach);
            }
            asignarrolListNew = attachedAsignarrolListNew;
            rol.setAsignarrolList(asignarrolListNew);
            List<Privilegio> attachedPrivilegioListNew = new ArrayList<Privilegio>();
            for (Privilegio privilegioListNewPrivilegioToAttach : privilegioListNew) {
                privilegioListNewPrivilegioToAttach = em.getReference(privilegioListNewPrivilegioToAttach.getClass(), privilegioListNewPrivilegioToAttach.getIdprivilegio());
                attachedPrivilegioListNew.add(privilegioListNewPrivilegioToAttach);
            }
            privilegioListNew = attachedPrivilegioListNew;
            rol.setPrivilegioList(privilegioListNew);
            rol = em.merge(rol);
            for (Asignarrol asignarrolListNewAsignarrol : asignarrolListNew) {
                if (!asignarrolListOld.contains(asignarrolListNewAsignarrol)) {
                    Rol oldRolOfAsignarrolListNewAsignarrol = asignarrolListNewAsignarrol.getRol();
                    asignarrolListNewAsignarrol.setRol(rol);
                    asignarrolListNewAsignarrol = em.merge(asignarrolListNewAsignarrol);
                    if (oldRolOfAsignarrolListNewAsignarrol != null && !oldRolOfAsignarrolListNewAsignarrol.equals(rol)) {
                        oldRolOfAsignarrolListNewAsignarrol.getAsignarrolList().remove(asignarrolListNewAsignarrol);
                        oldRolOfAsignarrolListNewAsignarrol = em.merge(oldRolOfAsignarrolListNewAsignarrol);
                    }
                }
            }
            for (Privilegio privilegioListNewPrivilegio : privilegioListNew) {
                if (!privilegioListOld.contains(privilegioListNewPrivilegio)) {
                    Rol oldRolIdrolOfPrivilegioListNewPrivilegio = privilegioListNewPrivilegio.getRolIdrol();
                    privilegioListNewPrivilegio.setRolIdrol(rol);
                    privilegioListNewPrivilegio = em.merge(privilegioListNewPrivilegio);
                    if (oldRolIdrolOfPrivilegioListNewPrivilegio != null && !oldRolIdrolOfPrivilegioListNewPrivilegio.equals(rol)) {
                        oldRolIdrolOfPrivilegioListNewPrivilegio.getPrivilegioList().remove(privilegioListNewPrivilegio);
                        oldRolIdrolOfPrivilegioListNewPrivilegio = em.merge(oldRolIdrolOfPrivilegioListNewPrivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rol.getIdrol();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getIdrol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignarrol> asignarrolListOrphanCheck = rol.getAsignarrolList();
            for (Asignarrol asignarrolListOrphanCheckAsignarrol : asignarrolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Asignarrol " + asignarrolListOrphanCheckAsignarrol + " in its asignarrolList field has a non-nullable rol field.");
            }
            List<Privilegio> privilegioListOrphanCheck = rol.getPrivilegioList();
            for (Privilegio privilegioListOrphanCheckPrivilegio : privilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Privilegio " + privilegioListOrphanCheckPrivilegio + " in its privilegioList field has a non-nullable rolIdrol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
