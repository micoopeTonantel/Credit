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
public class PuestoJpaController implements Serializable {

    public PuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puesto puesto) {
        if (puesto.getColaboradorList() == null) {
            puesto.setColaboradorList(new ArrayList<Colaborador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Colaborador> attachedColaboradorList = new ArrayList<Colaborador>();
            for (Colaborador colaboradorListColaboradorToAttach : puesto.getColaboradorList()) {
                colaboradorListColaboradorToAttach = em.getReference(colaboradorListColaboradorToAttach.getClass(), colaboradorListColaboradorToAttach.getOperador());
                attachedColaboradorList.add(colaboradorListColaboradorToAttach);
            }
            puesto.setColaboradorList(attachedColaboradorList);
            em.persist(puesto);
            for (Colaborador colaboradorListColaborador : puesto.getColaboradorList()) {
                Puesto oldPuestoIdpuestoOfColaboradorListColaborador = colaboradorListColaborador.getPuestoIdpuesto();
                colaboradorListColaborador.setPuestoIdpuesto(puesto);
                colaboradorListColaborador = em.merge(colaboradorListColaborador);
                if (oldPuestoIdpuestoOfColaboradorListColaborador != null) {
                    oldPuestoIdpuestoOfColaboradorListColaborador.getColaboradorList().remove(colaboradorListColaborador);
                    oldPuestoIdpuestoOfColaboradorListColaborador = em.merge(oldPuestoIdpuestoOfColaboradorListColaborador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puesto puesto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puesto persistentPuesto = em.find(Puesto.class, puesto.getIdpuesto());
            List<Colaborador> colaboradorListOld = persistentPuesto.getColaboradorList();
            List<Colaborador> colaboradorListNew = puesto.getColaboradorList();
            List<String> illegalOrphanMessages = null;
            for (Colaborador colaboradorListOldColaborador : colaboradorListOld) {
                if (!colaboradorListNew.contains(colaboradorListOldColaborador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Colaborador " + colaboradorListOldColaborador + " since its puestoIdpuesto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Colaborador> attachedColaboradorListNew = new ArrayList<Colaborador>();
            for (Colaborador colaboradorListNewColaboradorToAttach : colaboradorListNew) {
                colaboradorListNewColaboradorToAttach = em.getReference(colaboradorListNewColaboradorToAttach.getClass(), colaboradorListNewColaboradorToAttach.getOperador());
                attachedColaboradorListNew.add(colaboradorListNewColaboradorToAttach);
            }
            colaboradorListNew = attachedColaboradorListNew;
            puesto.setColaboradorList(colaboradorListNew);
            puesto = em.merge(puesto);
            for (Colaborador colaboradorListNewColaborador : colaboradorListNew) {
                if (!colaboradorListOld.contains(colaboradorListNewColaborador)) {
                    Puesto oldPuestoIdpuestoOfColaboradorListNewColaborador = colaboradorListNewColaborador.getPuestoIdpuesto();
                    colaboradorListNewColaborador.setPuestoIdpuesto(puesto);
                    colaboradorListNewColaborador = em.merge(colaboradorListNewColaborador);
                    if (oldPuestoIdpuestoOfColaboradorListNewColaborador != null && !oldPuestoIdpuestoOfColaboradorListNewColaborador.equals(puesto)) {
                        oldPuestoIdpuestoOfColaboradorListNewColaborador.getColaboradorList().remove(colaboradorListNewColaborador);
                        oldPuestoIdpuestoOfColaboradorListNewColaborador = em.merge(oldPuestoIdpuestoOfColaboradorListNewColaborador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puesto.getIdpuesto();
                if (findPuesto(id) == null) {
                    throw new NonexistentEntityException("The puesto with id " + id + " no longer exists.");
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
            Puesto puesto;
            try {
                puesto = em.getReference(Puesto.class, id);
                puesto.getIdpuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puesto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Colaborador> colaboradorListOrphanCheck = puesto.getColaboradorList();
            for (Colaborador colaboradorListOrphanCheckColaborador : colaboradorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Puesto (" + puesto + ") cannot be destroyed since the Colaborador " + colaboradorListOrphanCheckColaborador + " in its colaboradorList field has a non-nullable puestoIdpuesto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(puesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puesto> findPuestoEntities() {
        return findPuestoEntities(true, -1, -1);
    }

    public List<Puesto> findPuestoEntities(int maxResults, int firstResult) {
        return findPuestoEntities(false, maxResults, firstResult);
    }

    private List<Puesto> findPuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puesto.class));
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

    public Puesto findPuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puesto> rt = cq.from(Puesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
