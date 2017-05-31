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
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) {
        if (empresa.getAgenciaList() == null) {
            empresa.setAgenciaList(new ArrayList<Agencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Agencia> attachedAgenciaList = new ArrayList<Agencia>();
            for (Agencia agenciaListAgenciaToAttach : empresa.getAgenciaList()) {
                agenciaListAgenciaToAttach = em.getReference(agenciaListAgenciaToAttach.getClass(), agenciaListAgenciaToAttach.getIdagencia());
                attachedAgenciaList.add(agenciaListAgenciaToAttach);
            }
            empresa.setAgenciaList(attachedAgenciaList);
            em.persist(empresa);
            for (Agencia agenciaListAgencia : empresa.getAgenciaList()) {
                Empresa oldEmpresaIdempresaOfAgenciaListAgencia = agenciaListAgencia.getEmpresaIdempresa();
                agenciaListAgencia.setEmpresaIdempresa(empresa);
                agenciaListAgencia = em.merge(agenciaListAgencia);
                if (oldEmpresaIdempresaOfAgenciaListAgencia != null) {
                    oldEmpresaIdempresaOfAgenciaListAgencia.getAgenciaList().remove(agenciaListAgencia);
                    oldEmpresaIdempresaOfAgenciaListAgencia = em.merge(oldEmpresaIdempresaOfAgenciaListAgencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getIdempresa());
            List<Agencia> agenciaListOld = persistentEmpresa.getAgenciaList();
            List<Agencia> agenciaListNew = empresa.getAgenciaList();
            List<String> illegalOrphanMessages = null;
            for (Agencia agenciaListOldAgencia : agenciaListOld) {
                if (!agenciaListNew.contains(agenciaListOldAgencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agencia " + agenciaListOldAgencia + " since its empresaIdempresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Agencia> attachedAgenciaListNew = new ArrayList<Agencia>();
            for (Agencia agenciaListNewAgenciaToAttach : agenciaListNew) {
                agenciaListNewAgenciaToAttach = em.getReference(agenciaListNewAgenciaToAttach.getClass(), agenciaListNewAgenciaToAttach.getIdagencia());
                attachedAgenciaListNew.add(agenciaListNewAgenciaToAttach);
            }
            agenciaListNew = attachedAgenciaListNew;
            empresa.setAgenciaList(agenciaListNew);
            empresa = em.merge(empresa);
            for (Agencia agenciaListNewAgencia : agenciaListNew) {
                if (!agenciaListOld.contains(agenciaListNewAgencia)) {
                    Empresa oldEmpresaIdempresaOfAgenciaListNewAgencia = agenciaListNewAgencia.getEmpresaIdempresa();
                    agenciaListNewAgencia.setEmpresaIdempresa(empresa);
                    agenciaListNewAgencia = em.merge(agenciaListNewAgencia);
                    if (oldEmpresaIdempresaOfAgenciaListNewAgencia != null && !oldEmpresaIdempresaOfAgenciaListNewAgencia.equals(empresa)) {
                        oldEmpresaIdempresaOfAgenciaListNewAgencia.getAgenciaList().remove(agenciaListNewAgencia);
                        oldEmpresaIdempresaOfAgenciaListNewAgencia = em.merge(oldEmpresaIdempresaOfAgenciaListNewAgencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getIdempresa();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getIdempresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Agencia> agenciaListOrphanCheck = empresa.getAgenciaList();
            for (Agencia agenciaListOrphanCheckAgencia : agenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Agencia " + agenciaListOrphanCheckAgencia + " in its agenciaList field has a non-nullable empresaIdempresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
