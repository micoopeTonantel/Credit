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
public class CreditCotizacionJpaController implements Serializable {

    public CreditCotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditCotizacion creditCotizacion) {
        if (creditCotizacion.getCreditSolicitudList() == null) {
            creditCotizacion.setCreditSolicitudList(new ArrayList<CreditSolicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaboradorOperador = creditCotizacion.getColaboradorOperador();
            if (colaboradorOperador != null) {
                colaboradorOperador = em.getReference(colaboradorOperador.getClass(), colaboradorOperador.getOperador());
                creditCotizacion.setColaboradorOperador(colaboradorOperador);
            }
            CreditFinanciamiento creditFinanciamientoIdfinanciamiento = creditCotizacion.getCreditFinanciamientoIdfinanciamiento();
            if (creditFinanciamientoIdfinanciamiento != null) {
                creditFinanciamientoIdfinanciamiento = em.getReference(creditFinanciamientoIdfinanciamiento.getClass(), creditFinanciamientoIdfinanciamiento.getIdfinanciamiento());
                creditCotizacion.setCreditFinanciamientoIdfinanciamiento(creditFinanciamientoIdfinanciamiento);
            }
            CreditIngreso creditIngresoIdingreso = creditCotizacion.getCreditIngresoIdingreso();
            if (creditIngresoIdingreso != null) {
                creditIngresoIdingreso = em.getReference(creditIngresoIdingreso.getClass(), creditIngresoIdingreso.getIdingreso());
                creditCotizacion.setCreditIngresoIdingreso(creditIngresoIdingreso);
            }
            CreditPersona creditPersonaDpi = creditCotizacion.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi = em.getReference(creditPersonaDpi.getClass(), creditPersonaDpi.getDpi());
                creditCotizacion.setCreditPersonaDpi(creditPersonaDpi);
            }
            List<CreditSolicitud> attachedCreditSolicitudList = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListCreditSolicitudToAttach : creditCotizacion.getCreditSolicitudList()) {
                creditSolicitudListCreditSolicitudToAttach = em.getReference(creditSolicitudListCreditSolicitudToAttach.getClass(), creditSolicitudListCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudList.add(creditSolicitudListCreditSolicitudToAttach);
            }
            creditCotizacion.setCreditSolicitudList(attachedCreditSolicitudList);
            em.persist(creditCotizacion);
            if (colaboradorOperador != null) {
                colaboradorOperador.getCreditCotizacionList().add(creditCotizacion);
                colaboradorOperador = em.merge(colaboradorOperador);
            }
            if (creditFinanciamientoIdfinanciamiento != null) {
                creditFinanciamientoIdfinanciamiento.getCreditCotizacionList().add(creditCotizacion);
                creditFinanciamientoIdfinanciamiento = em.merge(creditFinanciamientoIdfinanciamiento);
            }
            if (creditIngresoIdingreso != null) {
                creditIngresoIdingreso.getCreditCotizacionList().add(creditCotizacion);
                creditIngresoIdingreso = em.merge(creditIngresoIdingreso);
            }
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditCotizacionList().add(creditCotizacion);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            for (CreditSolicitud creditSolicitudListCreditSolicitud : creditCotizacion.getCreditSolicitudList()) {
                CreditCotizacion oldCreditCotizacionNumeroOfCreditSolicitudListCreditSolicitud = creditSolicitudListCreditSolicitud.getCreditCotizacionNumero();
                creditSolicitudListCreditSolicitud.setCreditCotizacionNumero(creditCotizacion);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
                if (oldCreditCotizacionNumeroOfCreditSolicitudListCreditSolicitud != null) {
                    oldCreditCotizacionNumeroOfCreditSolicitudListCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListCreditSolicitud);
                    oldCreditCotizacionNumeroOfCreditSolicitudListCreditSolicitud = em.merge(oldCreditCotizacionNumeroOfCreditSolicitudListCreditSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditCotizacion creditCotizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditCotizacion persistentCreditCotizacion = em.find(CreditCotizacion.class, creditCotizacion.getNumero());
            Colaborador colaboradorOperadorOld = persistentCreditCotizacion.getColaboradorOperador();
            Colaborador colaboradorOperadorNew = creditCotizacion.getColaboradorOperador();
            CreditFinanciamiento creditFinanciamientoIdfinanciamientoOld = persistentCreditCotizacion.getCreditFinanciamientoIdfinanciamiento();
            CreditFinanciamiento creditFinanciamientoIdfinanciamientoNew = creditCotizacion.getCreditFinanciamientoIdfinanciamiento();
            CreditIngreso creditIngresoIdingresoOld = persistentCreditCotizacion.getCreditIngresoIdingreso();
            CreditIngreso creditIngresoIdingresoNew = creditCotizacion.getCreditIngresoIdingreso();
            CreditPersona creditPersonaDpiOld = persistentCreditCotizacion.getCreditPersonaDpi();
            CreditPersona creditPersonaDpiNew = creditCotizacion.getCreditPersonaDpi();
            List<CreditSolicitud> creditSolicitudListOld = persistentCreditCotizacion.getCreditSolicitudList();
            List<CreditSolicitud> creditSolicitudListNew = creditCotizacion.getCreditSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (CreditSolicitud creditSolicitudListOldCreditSolicitud : creditSolicitudListOld) {
                if (!creditSolicitudListNew.contains(creditSolicitudListOldCreditSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditSolicitud " + creditSolicitudListOldCreditSolicitud + " since its creditCotizacionNumero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (colaboradorOperadorNew != null) {
                colaboradorOperadorNew = em.getReference(colaboradorOperadorNew.getClass(), colaboradorOperadorNew.getOperador());
                creditCotizacion.setColaboradorOperador(colaboradorOperadorNew);
            }
            if (creditFinanciamientoIdfinanciamientoNew != null) {
                creditFinanciamientoIdfinanciamientoNew = em.getReference(creditFinanciamientoIdfinanciamientoNew.getClass(), creditFinanciamientoIdfinanciamientoNew.getIdfinanciamiento());
                creditCotizacion.setCreditFinanciamientoIdfinanciamiento(creditFinanciamientoIdfinanciamientoNew);
            }
            if (creditIngresoIdingresoNew != null) {
                creditIngresoIdingresoNew = em.getReference(creditIngresoIdingresoNew.getClass(), creditIngresoIdingresoNew.getIdingreso());
                creditCotizacion.setCreditIngresoIdingreso(creditIngresoIdingresoNew);
            }
            if (creditPersonaDpiNew != null) {
                creditPersonaDpiNew = em.getReference(creditPersonaDpiNew.getClass(), creditPersonaDpiNew.getDpi());
                creditCotizacion.setCreditPersonaDpi(creditPersonaDpiNew);
            }
            List<CreditSolicitud> attachedCreditSolicitudListNew = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListNewCreditSolicitudToAttach : creditSolicitudListNew) {
                creditSolicitudListNewCreditSolicitudToAttach = em.getReference(creditSolicitudListNewCreditSolicitudToAttach.getClass(), creditSolicitudListNewCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudListNew.add(creditSolicitudListNewCreditSolicitudToAttach);
            }
            creditSolicitudListNew = attachedCreditSolicitudListNew;
            creditCotizacion.setCreditSolicitudList(creditSolicitudListNew);
            creditCotizacion = em.merge(creditCotizacion);
            if (colaboradorOperadorOld != null && !colaboradorOperadorOld.equals(colaboradorOperadorNew)) {
                colaboradorOperadorOld.getCreditCotizacionList().remove(creditCotizacion);
                colaboradorOperadorOld = em.merge(colaboradorOperadorOld);
            }
            if (colaboradorOperadorNew != null && !colaboradorOperadorNew.equals(colaboradorOperadorOld)) {
                colaboradorOperadorNew.getCreditCotizacionList().add(creditCotizacion);
                colaboradorOperadorNew = em.merge(colaboradorOperadorNew);
            }
            if (creditFinanciamientoIdfinanciamientoOld != null && !creditFinanciamientoIdfinanciamientoOld.equals(creditFinanciamientoIdfinanciamientoNew)) {
                creditFinanciamientoIdfinanciamientoOld.getCreditCotizacionList().remove(creditCotizacion);
                creditFinanciamientoIdfinanciamientoOld = em.merge(creditFinanciamientoIdfinanciamientoOld);
            }
            if (creditFinanciamientoIdfinanciamientoNew != null && !creditFinanciamientoIdfinanciamientoNew.equals(creditFinanciamientoIdfinanciamientoOld)) {
                creditFinanciamientoIdfinanciamientoNew.getCreditCotizacionList().add(creditCotizacion);
                creditFinanciamientoIdfinanciamientoNew = em.merge(creditFinanciamientoIdfinanciamientoNew);
            }
            if (creditIngresoIdingresoOld != null && !creditIngresoIdingresoOld.equals(creditIngresoIdingresoNew)) {
                creditIngresoIdingresoOld.getCreditCotizacionList().remove(creditCotizacion);
                creditIngresoIdingresoOld = em.merge(creditIngresoIdingresoOld);
            }
            if (creditIngresoIdingresoNew != null && !creditIngresoIdingresoNew.equals(creditIngresoIdingresoOld)) {
                creditIngresoIdingresoNew.getCreditCotizacionList().add(creditCotizacion);
                creditIngresoIdingresoNew = em.merge(creditIngresoIdingresoNew);
            }
            if (creditPersonaDpiOld != null && !creditPersonaDpiOld.equals(creditPersonaDpiNew)) {
                creditPersonaDpiOld.getCreditCotizacionList().remove(creditCotizacion);
                creditPersonaDpiOld = em.merge(creditPersonaDpiOld);
            }
            if (creditPersonaDpiNew != null && !creditPersonaDpiNew.equals(creditPersonaDpiOld)) {
                creditPersonaDpiNew.getCreditCotizacionList().add(creditCotizacion);
                creditPersonaDpiNew = em.merge(creditPersonaDpiNew);
            }
            for (CreditSolicitud creditSolicitudListNewCreditSolicitud : creditSolicitudListNew) {
                if (!creditSolicitudListOld.contains(creditSolicitudListNewCreditSolicitud)) {
                    CreditCotizacion oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud = creditSolicitudListNewCreditSolicitud.getCreditCotizacionNumero();
                    creditSolicitudListNewCreditSolicitud.setCreditCotizacionNumero(creditCotizacion);
                    creditSolicitudListNewCreditSolicitud = em.merge(creditSolicitudListNewCreditSolicitud);
                    if (oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud != null && !oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud.equals(creditCotizacion)) {
                        oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListNewCreditSolicitud);
                        oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud = em.merge(oldCreditCotizacionNumeroOfCreditSolicitudListNewCreditSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditCotizacion.getNumero();
                if (findCreditCotizacion(id) == null) {
                    throw new NonexistentEntityException("The creditCotizacion with id " + id + " no longer exists.");
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
            CreditCotizacion creditCotizacion;
            try {
                creditCotizacion = em.getReference(CreditCotizacion.class, id);
                creditCotizacion.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditCotizacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditSolicitud> creditSolicitudListOrphanCheck = creditCotizacion.getCreditSolicitudList();
            for (CreditSolicitud creditSolicitudListOrphanCheckCreditSolicitud : creditSolicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditCotizacion (" + creditCotizacion + ") cannot be destroyed since the CreditSolicitud " + creditSolicitudListOrphanCheckCreditSolicitud + " in its creditSolicitudList field has a non-nullable creditCotizacionNumero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Colaborador colaboradorOperador = creditCotizacion.getColaboradorOperador();
            if (colaboradorOperador != null) {
                colaboradorOperador.getCreditCotizacionList().remove(creditCotizacion);
                colaboradorOperador = em.merge(colaboradorOperador);
            }
            CreditFinanciamiento creditFinanciamientoIdfinanciamiento = creditCotizacion.getCreditFinanciamientoIdfinanciamiento();
            if (creditFinanciamientoIdfinanciamiento != null) {
                creditFinanciamientoIdfinanciamiento.getCreditCotizacionList().remove(creditCotizacion);
                creditFinanciamientoIdfinanciamiento = em.merge(creditFinanciamientoIdfinanciamiento);
            }
            CreditIngreso creditIngresoIdingreso = creditCotizacion.getCreditIngresoIdingreso();
            if (creditIngresoIdingreso != null) {
                creditIngresoIdingreso.getCreditCotizacionList().remove(creditCotizacion);
                creditIngresoIdingreso = em.merge(creditIngresoIdingreso);
            }
            CreditPersona creditPersonaDpi = creditCotizacion.getCreditPersonaDpi();
            if (creditPersonaDpi != null) {
                creditPersonaDpi.getCreditCotizacionList().remove(creditCotizacion);
                creditPersonaDpi = em.merge(creditPersonaDpi);
            }
            em.remove(creditCotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditCotizacion> findCreditCotizacionEntities() {
        return findCreditCotizacionEntities(true, -1, -1);
    }

    public List<CreditCotizacion> findCreditCotizacionEntities(int maxResults, int firstResult) {
        return findCreditCotizacionEntities(false, maxResults, firstResult);
    }

    private List<CreditCotizacion> findCreditCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditCotizacion.class));
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

    public CreditCotizacion findCreditCotizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditCotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditCotizacion> rt = cq.from(CreditCotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
