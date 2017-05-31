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
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) {
        if (departamento.getColaboradorList() == null) {
            departamento.setColaboradorList(new ArrayList<Colaborador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Colaborador> attachedColaboradorList = new ArrayList<Colaborador>();
            for (Colaborador colaboradorListColaboradorToAttach : departamento.getColaboradorList()) {
                colaboradorListColaboradorToAttach = em.getReference(colaboradorListColaboradorToAttach.getClass(), colaboradorListColaboradorToAttach.getOperador());
                attachedColaboradorList.add(colaboradorListColaboradorToAttach);
            }
            departamento.setColaboradorList(attachedColaboradorList);
            em.persist(departamento);
            for (Colaborador colaboradorListColaborador : departamento.getColaboradorList()) {
                Departamento oldDepartamentoIddepartamentoOfColaboradorListColaborador = colaboradorListColaborador.getDepartamentoIddepartamento();
                colaboradorListColaborador.setDepartamentoIddepartamento(departamento);
                colaboradorListColaborador = em.merge(colaboradorListColaborador);
                if (oldDepartamentoIddepartamentoOfColaboradorListColaborador != null) {
                    oldDepartamentoIddepartamentoOfColaboradorListColaborador.getColaboradorList().remove(colaboradorListColaborador);
                    oldDepartamentoIddepartamentoOfColaboradorListColaborador = em.merge(oldDepartamentoIddepartamentoOfColaboradorListColaborador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getIddepartamento());
            List<Colaborador> colaboradorListOld = persistentDepartamento.getColaboradorList();
            List<Colaborador> colaboradorListNew = departamento.getColaboradorList();
            List<String> illegalOrphanMessages = null;
            for (Colaborador colaboradorListOldColaborador : colaboradorListOld) {
                if (!colaboradorListNew.contains(colaboradorListOldColaborador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Colaborador " + colaboradorListOldColaborador + " since its departamentoIddepartamento field is not nullable.");
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
            departamento.setColaboradorList(colaboradorListNew);
            departamento = em.merge(departamento);
            for (Colaborador colaboradorListNewColaborador : colaboradorListNew) {
                if (!colaboradorListOld.contains(colaboradorListNewColaborador)) {
                    Departamento oldDepartamentoIddepartamentoOfColaboradorListNewColaborador = colaboradorListNewColaborador.getDepartamentoIddepartamento();
                    colaboradorListNewColaborador.setDepartamentoIddepartamento(departamento);
                    colaboradorListNewColaborador = em.merge(colaboradorListNewColaborador);
                    if (oldDepartamentoIddepartamentoOfColaboradorListNewColaborador != null && !oldDepartamentoIddepartamentoOfColaboradorListNewColaborador.equals(departamento)) {
                        oldDepartamentoIddepartamentoOfColaboradorListNewColaborador.getColaboradorList().remove(colaboradorListNewColaborador);
                        oldDepartamentoIddepartamentoOfColaboradorListNewColaborador = em.merge(oldDepartamentoIddepartamentoOfColaboradorListNewColaborador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamento.getIddepartamento();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getIddepartamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Colaborador> colaboradorListOrphanCheck = departamento.getColaboradorList();
            for (Colaborador colaboradorListOrphanCheckColaborador : colaboradorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamento (" + departamento + ") cannot be destroyed since the Colaborador " + colaboradorListOrphanCheckColaborador + " in its colaboradorList field has a non-nullable departamentoIddepartamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
