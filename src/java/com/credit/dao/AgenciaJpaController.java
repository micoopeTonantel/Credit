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
public class AgenciaJpaController implements Serializable {

    public AgenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agencia agencia) {
        if (agencia.getColaboradorList() == null) {
            agencia.setColaboradorList(new ArrayList<Colaborador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa empresaIdempresa = agencia.getEmpresaIdempresa();
            if (empresaIdempresa != null) {
                empresaIdempresa = em.getReference(empresaIdempresa.getClass(), empresaIdempresa.getIdempresa());
                agencia.setEmpresaIdempresa(empresaIdempresa);
            }
            List<Colaborador> attachedColaboradorList = new ArrayList<Colaborador>();
            for (Colaborador colaboradorListColaboradorToAttach : agencia.getColaboradorList()) {
                colaboradorListColaboradorToAttach = em.getReference(colaboradorListColaboradorToAttach.getClass(), colaboradorListColaboradorToAttach.getOperador());
                attachedColaboradorList.add(colaboradorListColaboradorToAttach);
            }
            agencia.setColaboradorList(attachedColaboradorList);
            em.persist(agencia);
            if (empresaIdempresa != null) {
                empresaIdempresa.getAgenciaList().add(agencia);
                empresaIdempresa = em.merge(empresaIdempresa);
            }
            for (Colaborador colaboradorListColaborador : agencia.getColaboradorList()) {
                Agencia oldAgenciaIdagenciaOfColaboradorListColaborador = colaboradorListColaborador.getAgenciaIdagencia();
                colaboradorListColaborador.setAgenciaIdagencia(agencia);
                colaboradorListColaborador = em.merge(colaboradorListColaborador);
                if (oldAgenciaIdagenciaOfColaboradorListColaborador != null) {
                    oldAgenciaIdagenciaOfColaboradorListColaborador.getColaboradorList().remove(colaboradorListColaborador);
                    oldAgenciaIdagenciaOfColaboradorListColaborador = em.merge(oldAgenciaIdagenciaOfColaboradorListColaborador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agencia agencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agencia persistentAgencia = em.find(Agencia.class, agencia.getIdagencia());
            Empresa empresaIdempresaOld = persistentAgencia.getEmpresaIdempresa();
            Empresa empresaIdempresaNew = agencia.getEmpresaIdempresa();
            List<Colaborador> colaboradorListOld = persistentAgencia.getColaboradorList();
            List<Colaborador> colaboradorListNew = agencia.getColaboradorList();
            List<String> illegalOrphanMessages = null;
            for (Colaborador colaboradorListOldColaborador : colaboradorListOld) {
                if (!colaboradorListNew.contains(colaboradorListOldColaborador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Colaborador " + colaboradorListOldColaborador + " since its agenciaIdagencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empresaIdempresaNew != null) {
                empresaIdempresaNew = em.getReference(empresaIdempresaNew.getClass(), empresaIdempresaNew.getIdempresa());
                agencia.setEmpresaIdempresa(empresaIdempresaNew);
            }
            List<Colaborador> attachedColaboradorListNew = new ArrayList<Colaborador>();
            for (Colaborador colaboradorListNewColaboradorToAttach : colaboradorListNew) {
                colaboradorListNewColaboradorToAttach = em.getReference(colaboradorListNewColaboradorToAttach.getClass(), colaboradorListNewColaboradorToAttach.getOperador());
                attachedColaboradorListNew.add(colaboradorListNewColaboradorToAttach);
            }
            colaboradorListNew = attachedColaboradorListNew;
            agencia.setColaboradorList(colaboradorListNew);
            agencia = em.merge(agencia);
            if (empresaIdempresaOld != null && !empresaIdempresaOld.equals(empresaIdempresaNew)) {
                empresaIdempresaOld.getAgenciaList().remove(agencia);
                empresaIdempresaOld = em.merge(empresaIdempresaOld);
            }
            if (empresaIdempresaNew != null && !empresaIdempresaNew.equals(empresaIdempresaOld)) {
                empresaIdempresaNew.getAgenciaList().add(agencia);
                empresaIdempresaNew = em.merge(empresaIdempresaNew);
            }
            for (Colaborador colaboradorListNewColaborador : colaboradorListNew) {
                if (!colaboradorListOld.contains(colaboradorListNewColaborador)) {
                    Agencia oldAgenciaIdagenciaOfColaboradorListNewColaborador = colaboradorListNewColaborador.getAgenciaIdagencia();
                    colaboradorListNewColaborador.setAgenciaIdagencia(agencia);
                    colaboradorListNewColaborador = em.merge(colaboradorListNewColaborador);
                    if (oldAgenciaIdagenciaOfColaboradorListNewColaborador != null && !oldAgenciaIdagenciaOfColaboradorListNewColaborador.equals(agencia)) {
                        oldAgenciaIdagenciaOfColaboradorListNewColaborador.getColaboradorList().remove(colaboradorListNewColaborador);
                        oldAgenciaIdagenciaOfColaboradorListNewColaborador = em.merge(oldAgenciaIdagenciaOfColaboradorListNewColaborador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agencia.getIdagencia();
                if (findAgencia(id) == null) {
                    throw new NonexistentEntityException("The agencia with id " + id + " no longer exists.");
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
            Agencia agencia;
            try {
                agencia = em.getReference(Agencia.class, id);
                agencia.getIdagencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agencia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Colaborador> colaboradorListOrphanCheck = agencia.getColaboradorList();
            for (Colaborador colaboradorListOrphanCheckColaborador : colaboradorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Agencia (" + agencia + ") cannot be destroyed since the Colaborador " + colaboradorListOrphanCheckColaborador + " in its colaboradorList field has a non-nullable agenciaIdagencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresa empresaIdempresa = agencia.getEmpresaIdempresa();
            if (empresaIdempresa != null) {
                empresaIdempresa.getAgenciaList().remove(agencia);
                empresaIdempresa = em.merge(empresaIdempresa);
            }
            em.remove(agencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agencia> findAgenciaEntities() {
        return findAgenciaEntities(true, -1, -1);
    }

    public List<Agencia> findAgenciaEntities(int maxResults, int firstResult) {
        return findAgenciaEntities(false, maxResults, firstResult);
    }

    private List<Agencia> findAgenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agencia.class));
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

    public Agencia findAgencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agencia> rt = cq.from(Agencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
