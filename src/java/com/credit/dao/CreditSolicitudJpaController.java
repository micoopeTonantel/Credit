/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.NonexistentEntityException;
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
public class CreditSolicitudJpaController implements Serializable {

    public CreditSolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditSolicitud creditSolicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaboradorOperador = creditSolicitud.getColaboradorOperador();
            if (colaboradorOperador != null) {
                colaboradorOperador = em.getReference(colaboradorOperador.getClass(), colaboradorOperador.getOperador());
                creditSolicitud.setColaboradorOperador(colaboradorOperador);
            }
            CreditCotizacion creditCotizacionNumero = creditSolicitud.getCreditCotizacionNumero();
            if (creditCotizacionNumero != null) {
                creditCotizacionNumero = em.getReference(creditCotizacionNumero.getClass(), creditCotizacionNumero.getNumero());
                creditSolicitud.setCreditCotizacionNumero(creditCotizacionNumero);
            }
            CreditAsociado creditAsociadoCif = creditSolicitud.getCreditAsociadoCif();
            if (creditAsociadoCif != null) {
                creditAsociadoCif = em.getReference(creditAsociadoCif.getClass(), creditAsociadoCif.getCif());
                creditSolicitud.setCreditAsociadoCif(creditAsociadoCif);
            }
            CreditElectrodomestico creditElectrodomesticoIdelectrodomestico = creditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
            if (creditElectrodomesticoIdelectrodomestico != null) {
                creditElectrodomesticoIdelectrodomestico = em.getReference(creditElectrodomesticoIdelectrodomestico.getClass(), creditElectrodomesticoIdelectrodomestico.getIdelectrodomestico());
                creditSolicitud.setCreditElectrodomesticoIdelectrodomestico(creditElectrodomesticoIdelectrodomestico);
            }
            CreditExclusivo creditExclusivoIdexclusivo = creditSolicitud.getCreditExclusivoIdexclusivo();
            if (creditExclusivoIdexclusivo != null) {
                creditExclusivoIdexclusivo = em.getReference(creditExclusivoIdexclusivo.getClass(), creditExclusivoIdexclusivo.getIdexclusivo());
                creditSolicitud.setCreditExclusivoIdexclusivo(creditExclusivoIdexclusivo);
            }
            em.persist(creditSolicitud);
            if (colaboradorOperador != null) {
                colaboradorOperador.getCreditSolicitudList().add(creditSolicitud);
                colaboradorOperador = em.merge(colaboradorOperador);
            }
            if (creditCotizacionNumero != null) {
                creditCotizacionNumero.getCreditSolicitudList().add(creditSolicitud);
                creditCotizacionNumero = em.merge(creditCotizacionNumero);
            }
            if (creditAsociadoCif != null) {
                creditAsociadoCif.getCreditSolicitudList().add(creditSolicitud);
                creditAsociadoCif = em.merge(creditAsociadoCif);
            }
            if (creditElectrodomesticoIdelectrodomestico != null) {
                creditElectrodomesticoIdelectrodomestico.getCreditSolicitudList().add(creditSolicitud);
                creditElectrodomesticoIdelectrodomestico = em.merge(creditElectrodomesticoIdelectrodomestico);
            }
            if (creditExclusivoIdexclusivo != null) {
                creditExclusivoIdexclusivo.getCreditSolicitudList().add(creditSolicitud);
                creditExclusivoIdexclusivo = em.merge(creditExclusivoIdexclusivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditSolicitud creditSolicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditSolicitud persistentCreditSolicitud = em.find(CreditSolicitud.class, creditSolicitud.getIdsolicitud());
            Colaborador colaboradorOperadorOld = persistentCreditSolicitud.getColaboradorOperador();
            Colaborador colaboradorOperadorNew = creditSolicitud.getColaboradorOperador();
            CreditCotizacion creditCotizacionNumeroOld = persistentCreditSolicitud.getCreditCotizacionNumero();
            CreditCotizacion creditCotizacionNumeroNew = creditSolicitud.getCreditCotizacionNumero();
            CreditAsociado creditAsociadoCifOld = persistentCreditSolicitud.getCreditAsociadoCif();
            CreditAsociado creditAsociadoCifNew = creditSolicitud.getCreditAsociadoCif();
            CreditElectrodomestico creditElectrodomesticoIdelectrodomesticoOld = persistentCreditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
            CreditElectrodomestico creditElectrodomesticoIdelectrodomesticoNew = creditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
            CreditExclusivo creditExclusivoIdexclusivoOld = persistentCreditSolicitud.getCreditExclusivoIdexclusivo();
            CreditExclusivo creditExclusivoIdexclusivoNew = creditSolicitud.getCreditExclusivoIdexclusivo();
            if (colaboradorOperadorNew != null) {
                colaboradorOperadorNew = em.getReference(colaboradorOperadorNew.getClass(), colaboradorOperadorNew.getOperador());
                creditSolicitud.setColaboradorOperador(colaboradorOperadorNew);
            }
            if (creditCotizacionNumeroNew != null) {
                creditCotizacionNumeroNew = em.getReference(creditCotizacionNumeroNew.getClass(), creditCotizacionNumeroNew.getNumero());
                creditSolicitud.setCreditCotizacionNumero(creditCotizacionNumeroNew);
            }
            if (creditAsociadoCifNew != null) {
                creditAsociadoCifNew = em.getReference(creditAsociadoCifNew.getClass(), creditAsociadoCifNew.getCif());
                creditSolicitud.setCreditAsociadoCif(creditAsociadoCifNew);
            }
            if (creditElectrodomesticoIdelectrodomesticoNew != null) {
                creditElectrodomesticoIdelectrodomesticoNew = em.getReference(creditElectrodomesticoIdelectrodomesticoNew.getClass(), creditElectrodomesticoIdelectrodomesticoNew.getIdelectrodomestico());
                creditSolicitud.setCreditElectrodomesticoIdelectrodomestico(creditElectrodomesticoIdelectrodomesticoNew);
            }
            if (creditExclusivoIdexclusivoNew != null) {
                creditExclusivoIdexclusivoNew = em.getReference(creditExclusivoIdexclusivoNew.getClass(), creditExclusivoIdexclusivoNew.getIdexclusivo());
                creditSolicitud.setCreditExclusivoIdexclusivo(creditExclusivoIdexclusivoNew);
            }
            creditSolicitud = em.merge(creditSolicitud);
            if (colaboradorOperadorOld != null && !colaboradorOperadorOld.equals(colaboradorOperadorNew)) {
                colaboradorOperadorOld.getCreditSolicitudList().remove(creditSolicitud);
                colaboradorOperadorOld = em.merge(colaboradorOperadorOld);
            }
            if (colaboradorOperadorNew != null && !colaboradorOperadorNew.equals(colaboradorOperadorOld)) {
                colaboradorOperadorNew.getCreditSolicitudList().add(creditSolicitud);
                colaboradorOperadorNew = em.merge(colaboradorOperadorNew);
            }
            if (creditCotizacionNumeroOld != null && !creditCotizacionNumeroOld.equals(creditCotizacionNumeroNew)) {
                creditCotizacionNumeroOld.getCreditSolicitudList().remove(creditSolicitud);
                creditCotizacionNumeroOld = em.merge(creditCotizacionNumeroOld);
            }
            if (creditCotizacionNumeroNew != null && !creditCotizacionNumeroNew.equals(creditCotizacionNumeroOld)) {
                creditCotizacionNumeroNew.getCreditSolicitudList().add(creditSolicitud);
                creditCotizacionNumeroNew = em.merge(creditCotizacionNumeroNew);
            }
            if (creditAsociadoCifOld != null && !creditAsociadoCifOld.equals(creditAsociadoCifNew)) {
                creditAsociadoCifOld.getCreditSolicitudList().remove(creditSolicitud);
                creditAsociadoCifOld = em.merge(creditAsociadoCifOld);
            }
            if (creditAsociadoCifNew != null && !creditAsociadoCifNew.equals(creditAsociadoCifOld)) {
                creditAsociadoCifNew.getCreditSolicitudList().add(creditSolicitud);
                creditAsociadoCifNew = em.merge(creditAsociadoCifNew);
            }
            if (creditElectrodomesticoIdelectrodomesticoOld != null && !creditElectrodomesticoIdelectrodomesticoOld.equals(creditElectrodomesticoIdelectrodomesticoNew)) {
                creditElectrodomesticoIdelectrodomesticoOld.getCreditSolicitudList().remove(creditSolicitud);
                creditElectrodomesticoIdelectrodomesticoOld = em.merge(creditElectrodomesticoIdelectrodomesticoOld);
            }
            if (creditElectrodomesticoIdelectrodomesticoNew != null && !creditElectrodomesticoIdelectrodomesticoNew.equals(creditElectrodomesticoIdelectrodomesticoOld)) {
                creditElectrodomesticoIdelectrodomesticoNew.getCreditSolicitudList().add(creditSolicitud);
                creditElectrodomesticoIdelectrodomesticoNew = em.merge(creditElectrodomesticoIdelectrodomesticoNew);
            }
            if (creditExclusivoIdexclusivoOld != null && !creditExclusivoIdexclusivoOld.equals(creditExclusivoIdexclusivoNew)) {
                creditExclusivoIdexclusivoOld.getCreditSolicitudList().remove(creditSolicitud);
                creditExclusivoIdexclusivoOld = em.merge(creditExclusivoIdexclusivoOld);
            }
            if (creditExclusivoIdexclusivoNew != null && !creditExclusivoIdexclusivoNew.equals(creditExclusivoIdexclusivoOld)) {
                creditExclusivoIdexclusivoNew.getCreditSolicitudList().add(creditSolicitud);
                creditExclusivoIdexclusivoNew = em.merge(creditExclusivoIdexclusivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditSolicitud.getIdsolicitud();
                if (findCreditSolicitud(id) == null) {
                    throw new NonexistentEntityException("The creditSolicitud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditSolicitud creditSolicitud;
            try {
                creditSolicitud = em.getReference(CreditSolicitud.class, id);
                creditSolicitud.getIdsolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditSolicitud with id " + id + " no longer exists.", enfe);
            }
            Colaborador colaboradorOperador = creditSolicitud.getColaboradorOperador();
            if (colaboradorOperador != null) {
                colaboradorOperador.getCreditSolicitudList().remove(creditSolicitud);
                colaboradorOperador = em.merge(colaboradorOperador);
            }
            CreditCotizacion creditCotizacionNumero = creditSolicitud.getCreditCotizacionNumero();
            if (creditCotizacionNumero != null) {
                creditCotizacionNumero.getCreditSolicitudList().remove(creditSolicitud);
                creditCotizacionNumero = em.merge(creditCotizacionNumero);
            }
            CreditAsociado creditAsociadoCif = creditSolicitud.getCreditAsociadoCif();
            if (creditAsociadoCif != null) {
                creditAsociadoCif.getCreditSolicitudList().remove(creditSolicitud);
                creditAsociadoCif = em.merge(creditAsociadoCif);
            }
            CreditElectrodomestico creditElectrodomesticoIdelectrodomestico = creditSolicitud.getCreditElectrodomesticoIdelectrodomestico();
            if (creditElectrodomesticoIdelectrodomestico != null) {
                creditElectrodomesticoIdelectrodomestico.getCreditSolicitudList().remove(creditSolicitud);
                creditElectrodomesticoIdelectrodomestico = em.merge(creditElectrodomesticoIdelectrodomestico);
            }
            CreditExclusivo creditExclusivoIdexclusivo = creditSolicitud.getCreditExclusivoIdexclusivo();
            if (creditExclusivoIdexclusivo != null) {
                creditExclusivoIdexclusivo.getCreditSolicitudList().remove(creditSolicitud);
                creditExclusivoIdexclusivo = em.merge(creditExclusivoIdexclusivo);
            }
            em.remove(creditSolicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditSolicitud> findCreditSolicitudEntities() {
        return findCreditSolicitudEntities(true, -1, -1);
    }

    public List<CreditSolicitud> findCreditSolicitudEntities(int maxResults, int firstResult) {
        return findCreditSolicitudEntities(false, maxResults, firstResult);
    }

    private List<CreditSolicitud> findCreditSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditSolicitud.class));
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

    public CreditSolicitud findCreditSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditSolicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditSolicitud> rt = cq.from(CreditSolicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
