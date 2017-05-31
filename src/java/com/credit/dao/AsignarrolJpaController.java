/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.NonexistentEntityException;
import com.credit.dao.exceptions.PreexistingEntityException;
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
public class AsignarrolJpaController implements Serializable {

    public AsignarrolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignarrol asignarrol) throws PreexistingEntityException, Exception {
        if (asignarrol.getAsignarrolPK() == null) {
            asignarrol.setAsignarrolPK(new AsignarrolPK());
        }
        asignarrol.getAsignarrolPK().setRolIdrol(asignarrol.getRol().getIdrol());
        asignarrol.getAsignarrolPK().setColaboradorOperador(asignarrol.getColaborador().getOperador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaborador = asignarrol.getColaborador();
            if (colaborador != null) {
                colaborador = em.getReference(colaborador.getClass(), colaborador.getOperador());
                asignarrol.setColaborador(colaborador);
            }
            Rol rol = asignarrol.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdrol());
                asignarrol.setRol(rol);
            }
            em.persist(asignarrol);
            if (colaborador != null) {
                colaborador.getAsignarrolList().add(asignarrol);
                colaborador = em.merge(colaborador);
            }
            if (rol != null) {
                rol.getAsignarrolList().add(asignarrol);
                rol = em.merge(rol);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignarrol(asignarrol.getAsignarrolPK()) != null) {
                throw new PreexistingEntityException("Asignarrol " + asignarrol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignarrol asignarrol) throws NonexistentEntityException, Exception {
        asignarrol.getAsignarrolPK().setRolIdrol(asignarrol.getRol().getIdrol());
        asignarrol.getAsignarrolPK().setColaboradorOperador(asignarrol.getColaborador().getOperador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignarrol persistentAsignarrol = em.find(Asignarrol.class, asignarrol.getAsignarrolPK());
            Colaborador colaboradorOld = persistentAsignarrol.getColaborador();
            Colaborador colaboradorNew = asignarrol.getColaborador();
            Rol rolOld = persistentAsignarrol.getRol();
            Rol rolNew = asignarrol.getRol();
            if (colaboradorNew != null) {
                colaboradorNew = em.getReference(colaboradorNew.getClass(), colaboradorNew.getOperador());
                asignarrol.setColaborador(colaboradorNew);
            }
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdrol());
                asignarrol.setRol(rolNew);
            }
            asignarrol = em.merge(asignarrol);
            if (colaboradorOld != null && !colaboradorOld.equals(colaboradorNew)) {
                colaboradorOld.getAsignarrolList().remove(asignarrol);
                colaboradorOld = em.merge(colaboradorOld);
            }
            if (colaboradorNew != null && !colaboradorNew.equals(colaboradorOld)) {
                colaboradorNew.getAsignarrolList().add(asignarrol);
                colaboradorNew = em.merge(colaboradorNew);
            }
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getAsignarrolList().remove(asignarrol);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getAsignarrolList().add(asignarrol);
                rolNew = em.merge(rolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignarrolPK id = asignarrol.getAsignarrolPK();
                if (findAsignarrol(id) == null) {
                    throw new NonexistentEntityException("The asignarrol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignarrolPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignarrol asignarrol;
            try {
                asignarrol = em.getReference(Asignarrol.class, id);
                asignarrol.getAsignarrolPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignarrol with id " + id + " no longer exists.", enfe);
            }
            Colaborador colaborador = asignarrol.getColaborador();
            if (colaborador != null) {
                colaborador.getAsignarrolList().remove(asignarrol);
                colaborador = em.merge(colaborador);
            }
            Rol rol = asignarrol.getRol();
            if (rol != null) {
                rol.getAsignarrolList().remove(asignarrol);
                rol = em.merge(rol);
            }
            em.remove(asignarrol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignarrol> findAsignarrolEntities() {
        return findAsignarrolEntities(true, -1, -1);
    }

    public List<Asignarrol> findAsignarrolEntities(int maxResults, int firstResult) {
        return findAsignarrolEntities(false, maxResults, firstResult);
    }

    private List<Asignarrol> findAsignarrolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignarrol.class));
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

    public Asignarrol findAsignarrol(AsignarrolPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignarrol.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignarrolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignarrol> rt = cq.from(Asignarrol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
