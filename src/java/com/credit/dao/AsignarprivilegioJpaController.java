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
public class AsignarprivilegioJpaController implements Serializable {

    public AsignarprivilegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignarprivilegio asignarprivilegio) throws PreexistingEntityException, Exception {
        if (asignarprivilegio.getAsignarprivilegioPK() == null) {
            asignarprivilegio.setAsignarprivilegioPK(new AsignarprivilegioPK());
        }
        asignarprivilegio.getAsignarprivilegioPK().setPrivilegioIdprivilegio(asignarprivilegio.getPrivilegio().getIdprivilegio());
        asignarprivilegio.getAsignarprivilegioPK().setColaboradorOperador(asignarprivilegio.getColaborador().getOperador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaborador = asignarprivilegio.getColaborador();
            if (colaborador != null) {
                colaborador = em.getReference(colaborador.getClass(), colaborador.getOperador());
                asignarprivilegio.setColaborador(colaborador);
            }
            Privilegio privilegio = asignarprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio = em.getReference(privilegio.getClass(), privilegio.getIdprivilegio());
                asignarprivilegio.setPrivilegio(privilegio);
            }
            em.persist(asignarprivilegio);
            if (colaborador != null) {
                colaborador.getAsignarprivilegioList().add(asignarprivilegio);
                colaborador = em.merge(colaborador);
            }
            if (privilegio != null) {
                privilegio.getAsignarprivilegioList().add(asignarprivilegio);
                privilegio = em.merge(privilegio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignarprivilegio(asignarprivilegio.getAsignarprivilegioPK()) != null) {
                throw new PreexistingEntityException("Asignarprivilegio " + asignarprivilegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignarprivilegio asignarprivilegio) throws NonexistentEntityException, Exception {
        asignarprivilegio.getAsignarprivilegioPK().setPrivilegioIdprivilegio(asignarprivilegio.getPrivilegio().getIdprivilegio());
        asignarprivilegio.getAsignarprivilegioPK().setColaboradorOperador(asignarprivilegio.getColaborador().getOperador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignarprivilegio persistentAsignarprivilegio = em.find(Asignarprivilegio.class, asignarprivilegio.getAsignarprivilegioPK());
            Colaborador colaboradorOld = persistentAsignarprivilegio.getColaborador();
            Colaborador colaboradorNew = asignarprivilegio.getColaborador();
            Privilegio privilegioOld = persistentAsignarprivilegio.getPrivilegio();
            Privilegio privilegioNew = asignarprivilegio.getPrivilegio();
            if (colaboradorNew != null) {
                colaboradorNew = em.getReference(colaboradorNew.getClass(), colaboradorNew.getOperador());
                asignarprivilegio.setColaborador(colaboradorNew);
            }
            if (privilegioNew != null) {
                privilegioNew = em.getReference(privilegioNew.getClass(), privilegioNew.getIdprivilegio());
                asignarprivilegio.setPrivilegio(privilegioNew);
            }
            asignarprivilegio = em.merge(asignarprivilegio);
            if (colaboradorOld != null && !colaboradorOld.equals(colaboradorNew)) {
                colaboradorOld.getAsignarprivilegioList().remove(asignarprivilegio);
                colaboradorOld = em.merge(colaboradorOld);
            }
            if (colaboradorNew != null && !colaboradorNew.equals(colaboradorOld)) {
                colaboradorNew.getAsignarprivilegioList().add(asignarprivilegio);
                colaboradorNew = em.merge(colaboradorNew);
            }
            if (privilegioOld != null && !privilegioOld.equals(privilegioNew)) {
                privilegioOld.getAsignarprivilegioList().remove(asignarprivilegio);
                privilegioOld = em.merge(privilegioOld);
            }
            if (privilegioNew != null && !privilegioNew.equals(privilegioOld)) {
                privilegioNew.getAsignarprivilegioList().add(asignarprivilegio);
                privilegioNew = em.merge(privilegioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignarprivilegioPK id = asignarprivilegio.getAsignarprivilegioPK();
                if (findAsignarprivilegio(id) == null) {
                    throw new NonexistentEntityException("The asignarprivilegio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignarprivilegioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignarprivilegio asignarprivilegio;
            try {
                asignarprivilegio = em.getReference(Asignarprivilegio.class, id);
                asignarprivilegio.getAsignarprivilegioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignarprivilegio with id " + id + " no longer exists.", enfe);
            }
            Colaborador colaborador = asignarprivilegio.getColaborador();
            if (colaborador != null) {
                colaborador.getAsignarprivilegioList().remove(asignarprivilegio);
                colaborador = em.merge(colaborador);
            }
            Privilegio privilegio = asignarprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio.getAsignarprivilegioList().remove(asignarprivilegio);
                privilegio = em.merge(privilegio);
            }
            em.remove(asignarprivilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignarprivilegio> findAsignarprivilegioEntities() {
        return findAsignarprivilegioEntities(true, -1, -1);
    }

    public List<Asignarprivilegio> findAsignarprivilegioEntities(int maxResults, int firstResult) {
        return findAsignarprivilegioEntities(false, maxResults, firstResult);
    }

    private List<Asignarprivilegio> findAsignarprivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignarprivilegio.class));
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

    public Asignarprivilegio findAsignarprivilegio(AsignarprivilegioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignarprivilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignarprivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignarprivilegio> rt = cq.from(Asignarprivilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
