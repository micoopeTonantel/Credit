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
public class CreditFinanciamientoJpaController implements Serializable {

    public CreditFinanciamientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CreditFinanciamiento creditFinanciamiento) {
        if (creditFinanciamiento.getCreditCotizacionList() == null) {
            creditFinanciamiento.setCreditCotizacionList(new ArrayList<CreditCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditUtilidad creditUtilidadIdutilidad = creditFinanciamiento.getCreditUtilidadIdutilidad();
            if (creditUtilidadIdutilidad != null) {
                creditUtilidadIdutilidad = em.getReference(creditUtilidadIdutilidad.getClass(), creditUtilidadIdutilidad.getIdutilidad());
                creditFinanciamiento.setCreditUtilidadIdutilidad(creditUtilidadIdutilidad);
            }
            CreditTipocuota creditTipocuotaIdtipocuota = creditFinanciamiento.getCreditTipocuotaIdtipocuota();
            if (creditTipocuotaIdtipocuota != null) {
                creditTipocuotaIdtipocuota = em.getReference(creditTipocuotaIdtipocuota.getClass(), creditTipocuotaIdtipocuota.getIdtipocuota());
                creditFinanciamiento.setCreditTipocuotaIdtipocuota(creditTipocuotaIdtipocuota);
            }
            CreditDestinofondo creditDestinofondoIddestinofondo = creditFinanciamiento.getCreditDestinofondoIddestinofondo();
            if (creditDestinofondoIddestinofondo != null) {
                creditDestinofondoIddestinofondo = em.getReference(creditDestinofondoIddestinofondo.getClass(), creditDestinofondoIddestinofondo.getIddestinofondo());
                creditFinanciamiento.setCreditDestinofondoIddestinofondo(creditDestinofondoIddestinofondo);
            }
            CreditTiposolicitante creditTiposolicitanteIdtiposolicitante = creditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
            if (creditTiposolicitanteIdtiposolicitante != null) {
                creditTiposolicitanteIdtiposolicitante = em.getReference(creditTiposolicitanteIdtiposolicitante.getClass(), creditTiposolicitanteIdtiposolicitante.getIdtiposolicitante());
                creditFinanciamiento.setCreditTiposolicitanteIdtiposolicitante(creditTiposolicitanteIdtiposolicitante);
            }
            CreditPagocapital creditPagocapitalIdpagocapital = creditFinanciamiento.getCreditPagocapitalIdpagocapital();
            if (creditPagocapitalIdpagocapital != null) {
                creditPagocapitalIdpagocapital = em.getReference(creditPagocapitalIdpagocapital.getClass(), creditPagocapitalIdpagocapital.getIdpagocapital());
                creditFinanciamiento.setCreditPagocapitalIdpagocapital(creditPagocapitalIdpagocapital);
            }
            CreditPagointeres creditPagointeresIdpagointeres = creditFinanciamiento.getCreditPagointeresIdpagointeres();
            if (creditPagointeresIdpagointeres != null) {
                creditPagointeresIdpagointeres = em.getReference(creditPagointeresIdpagointeres.getClass(), creditPagointeresIdpagointeres.getIdpagointeres());
                creditFinanciamiento.setCreditPagointeresIdpagointeres(creditPagointeresIdpagointeres);
            }
            CreditTipocredito creditTipocreditoIdtipocredito = creditFinanciamiento.getCreditTipocreditoIdtipocredito();
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito = em.getReference(creditTipocreditoIdtipocredito.getClass(), creditTipocreditoIdtipocredito.getIdtipocredito());
                creditFinanciamiento.setCreditTipocreditoIdtipocredito(creditTipocreditoIdtipocredito);
            }
            List<CreditCotizacion> attachedCreditCotizacionList = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListCreditCotizacionToAttach : creditFinanciamiento.getCreditCotizacionList()) {
                creditCotizacionListCreditCotizacionToAttach = em.getReference(creditCotizacionListCreditCotizacionToAttach.getClass(), creditCotizacionListCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionList.add(creditCotizacionListCreditCotizacionToAttach);
            }
            creditFinanciamiento.setCreditCotizacionList(attachedCreditCotizacionList);
            em.persist(creditFinanciamiento);
            if (creditUtilidadIdutilidad != null) {
                creditUtilidadIdutilidad.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditUtilidadIdutilidad = em.merge(creditUtilidadIdutilidad);
            }
            if (creditTipocuotaIdtipocuota != null) {
                creditTipocuotaIdtipocuota.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTipocuotaIdtipocuota = em.merge(creditTipocuotaIdtipocuota);
            }
            if (creditDestinofondoIddestinofondo != null) {
                creditDestinofondoIddestinofondo.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditDestinofondoIddestinofondo = em.merge(creditDestinofondoIddestinofondo);
            }
            if (creditTiposolicitanteIdtiposolicitante != null) {
                creditTiposolicitanteIdtiposolicitante.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTiposolicitanteIdtiposolicitante = em.merge(creditTiposolicitanteIdtiposolicitante);
            }
            if (creditPagocapitalIdpagocapital != null) {
                creditPagocapitalIdpagocapital.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditPagocapitalIdpagocapital = em.merge(creditPagocapitalIdpagocapital);
            }
            if (creditPagointeresIdpagointeres != null) {
                creditPagointeresIdpagointeres.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditPagointeresIdpagointeres = em.merge(creditPagointeresIdpagointeres);
            }
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTipocreditoIdtipocredito = em.merge(creditTipocreditoIdtipocredito);
            }
            for (CreditCotizacion creditCotizacionListCreditCotizacion : creditFinanciamiento.getCreditCotizacionList()) {
                CreditFinanciamiento oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListCreditCotizacion = creditCotizacionListCreditCotizacion.getCreditFinanciamientoIdfinanciamiento();
                creditCotizacionListCreditCotizacion.setCreditFinanciamientoIdfinanciamiento(creditFinanciamiento);
                creditCotizacionListCreditCotizacion = em.merge(creditCotizacionListCreditCotizacion);
                if (oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListCreditCotizacion != null) {
                    oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListCreditCotizacion);
                    oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListCreditCotizacion = em.merge(oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListCreditCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CreditFinanciamiento creditFinanciamiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CreditFinanciamiento persistentCreditFinanciamiento = em.find(CreditFinanciamiento.class, creditFinanciamiento.getIdfinanciamiento());
            CreditUtilidad creditUtilidadIdutilidadOld = persistentCreditFinanciamiento.getCreditUtilidadIdutilidad();
            CreditUtilidad creditUtilidadIdutilidadNew = creditFinanciamiento.getCreditUtilidadIdutilidad();
            CreditTipocuota creditTipocuotaIdtipocuotaOld = persistentCreditFinanciamiento.getCreditTipocuotaIdtipocuota();
            CreditTipocuota creditTipocuotaIdtipocuotaNew = creditFinanciamiento.getCreditTipocuotaIdtipocuota();
            CreditDestinofondo creditDestinofondoIddestinofondoOld = persistentCreditFinanciamiento.getCreditDestinofondoIddestinofondo();
            CreditDestinofondo creditDestinofondoIddestinofondoNew = creditFinanciamiento.getCreditDestinofondoIddestinofondo();
            CreditTiposolicitante creditTiposolicitanteIdtiposolicitanteOld = persistentCreditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
            CreditTiposolicitante creditTiposolicitanteIdtiposolicitanteNew = creditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
            CreditPagocapital creditPagocapitalIdpagocapitalOld = persistentCreditFinanciamiento.getCreditPagocapitalIdpagocapital();
            CreditPagocapital creditPagocapitalIdpagocapitalNew = creditFinanciamiento.getCreditPagocapitalIdpagocapital();
            CreditPagointeres creditPagointeresIdpagointeresOld = persistentCreditFinanciamiento.getCreditPagointeresIdpagointeres();
            CreditPagointeres creditPagointeresIdpagointeresNew = creditFinanciamiento.getCreditPagointeresIdpagointeres();
            CreditTipocredito creditTipocreditoIdtipocreditoOld = persistentCreditFinanciamiento.getCreditTipocreditoIdtipocredito();
            CreditTipocredito creditTipocreditoIdtipocreditoNew = creditFinanciamiento.getCreditTipocreditoIdtipocredito();
            List<CreditCotizacion> creditCotizacionListOld = persistentCreditFinanciamiento.getCreditCotizacionList();
            List<CreditCotizacion> creditCotizacionListNew = creditFinanciamiento.getCreditCotizacionList();
            List<String> illegalOrphanMessages = null;
            for (CreditCotizacion creditCotizacionListOldCreditCotizacion : creditCotizacionListOld) {
                if (!creditCotizacionListNew.contains(creditCotizacionListOldCreditCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditCotizacion " + creditCotizacionListOldCreditCotizacion + " since its creditFinanciamientoIdfinanciamiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditUtilidadIdutilidadNew != null) {
                creditUtilidadIdutilidadNew = em.getReference(creditUtilidadIdutilidadNew.getClass(), creditUtilidadIdutilidadNew.getIdutilidad());
                creditFinanciamiento.setCreditUtilidadIdutilidad(creditUtilidadIdutilidadNew);
            }
            if (creditTipocuotaIdtipocuotaNew != null) {
                creditTipocuotaIdtipocuotaNew = em.getReference(creditTipocuotaIdtipocuotaNew.getClass(), creditTipocuotaIdtipocuotaNew.getIdtipocuota());
                creditFinanciamiento.setCreditTipocuotaIdtipocuota(creditTipocuotaIdtipocuotaNew);
            }
            if (creditDestinofondoIddestinofondoNew != null) {
                creditDestinofondoIddestinofondoNew = em.getReference(creditDestinofondoIddestinofondoNew.getClass(), creditDestinofondoIddestinofondoNew.getIddestinofondo());
                creditFinanciamiento.setCreditDestinofondoIddestinofondo(creditDestinofondoIddestinofondoNew);
            }
            if (creditTiposolicitanteIdtiposolicitanteNew != null) {
                creditTiposolicitanteIdtiposolicitanteNew = em.getReference(creditTiposolicitanteIdtiposolicitanteNew.getClass(), creditTiposolicitanteIdtiposolicitanteNew.getIdtiposolicitante());
                creditFinanciamiento.setCreditTiposolicitanteIdtiposolicitante(creditTiposolicitanteIdtiposolicitanteNew);
            }
            if (creditPagocapitalIdpagocapitalNew != null) {
                creditPagocapitalIdpagocapitalNew = em.getReference(creditPagocapitalIdpagocapitalNew.getClass(), creditPagocapitalIdpagocapitalNew.getIdpagocapital());
                creditFinanciamiento.setCreditPagocapitalIdpagocapital(creditPagocapitalIdpagocapitalNew);
            }
            if (creditPagointeresIdpagointeresNew != null) {
                creditPagointeresIdpagointeresNew = em.getReference(creditPagointeresIdpagointeresNew.getClass(), creditPagointeresIdpagointeresNew.getIdpagointeres());
                creditFinanciamiento.setCreditPagointeresIdpagointeres(creditPagointeresIdpagointeresNew);
            }
            if (creditTipocreditoIdtipocreditoNew != null) {
                creditTipocreditoIdtipocreditoNew = em.getReference(creditTipocreditoIdtipocreditoNew.getClass(), creditTipocreditoIdtipocreditoNew.getIdtipocredito());
                creditFinanciamiento.setCreditTipocreditoIdtipocredito(creditTipocreditoIdtipocreditoNew);
            }
            List<CreditCotizacion> attachedCreditCotizacionListNew = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListNewCreditCotizacionToAttach : creditCotizacionListNew) {
                creditCotizacionListNewCreditCotizacionToAttach = em.getReference(creditCotizacionListNewCreditCotizacionToAttach.getClass(), creditCotizacionListNewCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionListNew.add(creditCotizacionListNewCreditCotizacionToAttach);
            }
            creditCotizacionListNew = attachedCreditCotizacionListNew;
            creditFinanciamiento.setCreditCotizacionList(creditCotizacionListNew);
            creditFinanciamiento = em.merge(creditFinanciamiento);
            if (creditUtilidadIdutilidadOld != null && !creditUtilidadIdutilidadOld.equals(creditUtilidadIdutilidadNew)) {
                creditUtilidadIdutilidadOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditUtilidadIdutilidadOld = em.merge(creditUtilidadIdutilidadOld);
            }
            if (creditUtilidadIdutilidadNew != null && !creditUtilidadIdutilidadNew.equals(creditUtilidadIdutilidadOld)) {
                creditUtilidadIdutilidadNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditUtilidadIdutilidadNew = em.merge(creditUtilidadIdutilidadNew);
            }
            if (creditTipocuotaIdtipocuotaOld != null && !creditTipocuotaIdtipocuotaOld.equals(creditTipocuotaIdtipocuotaNew)) {
                creditTipocuotaIdtipocuotaOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTipocuotaIdtipocuotaOld = em.merge(creditTipocuotaIdtipocuotaOld);
            }
            if (creditTipocuotaIdtipocuotaNew != null && !creditTipocuotaIdtipocuotaNew.equals(creditTipocuotaIdtipocuotaOld)) {
                creditTipocuotaIdtipocuotaNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTipocuotaIdtipocuotaNew = em.merge(creditTipocuotaIdtipocuotaNew);
            }
            if (creditDestinofondoIddestinofondoOld != null && !creditDestinofondoIddestinofondoOld.equals(creditDestinofondoIddestinofondoNew)) {
                creditDestinofondoIddestinofondoOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditDestinofondoIddestinofondoOld = em.merge(creditDestinofondoIddestinofondoOld);
            }
            if (creditDestinofondoIddestinofondoNew != null && !creditDestinofondoIddestinofondoNew.equals(creditDestinofondoIddestinofondoOld)) {
                creditDestinofondoIddestinofondoNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditDestinofondoIddestinofondoNew = em.merge(creditDestinofondoIddestinofondoNew);
            }
            if (creditTiposolicitanteIdtiposolicitanteOld != null && !creditTiposolicitanteIdtiposolicitanteOld.equals(creditTiposolicitanteIdtiposolicitanteNew)) {
                creditTiposolicitanteIdtiposolicitanteOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTiposolicitanteIdtiposolicitanteOld = em.merge(creditTiposolicitanteIdtiposolicitanteOld);
            }
            if (creditTiposolicitanteIdtiposolicitanteNew != null && !creditTiposolicitanteIdtiposolicitanteNew.equals(creditTiposolicitanteIdtiposolicitanteOld)) {
                creditTiposolicitanteIdtiposolicitanteNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTiposolicitanteIdtiposolicitanteNew = em.merge(creditTiposolicitanteIdtiposolicitanteNew);
            }
            if (creditPagocapitalIdpagocapitalOld != null && !creditPagocapitalIdpagocapitalOld.equals(creditPagocapitalIdpagocapitalNew)) {
                creditPagocapitalIdpagocapitalOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditPagocapitalIdpagocapitalOld = em.merge(creditPagocapitalIdpagocapitalOld);
            }
            if (creditPagocapitalIdpagocapitalNew != null && !creditPagocapitalIdpagocapitalNew.equals(creditPagocapitalIdpagocapitalOld)) {
                creditPagocapitalIdpagocapitalNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditPagocapitalIdpagocapitalNew = em.merge(creditPagocapitalIdpagocapitalNew);
            }
            if (creditPagointeresIdpagointeresOld != null && !creditPagointeresIdpagointeresOld.equals(creditPagointeresIdpagointeresNew)) {
                creditPagointeresIdpagointeresOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditPagointeresIdpagointeresOld = em.merge(creditPagointeresIdpagointeresOld);
            }
            if (creditPagointeresIdpagointeresNew != null && !creditPagointeresIdpagointeresNew.equals(creditPagointeresIdpagointeresOld)) {
                creditPagointeresIdpagointeresNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditPagointeresIdpagointeresNew = em.merge(creditPagointeresIdpagointeresNew);
            }
            if (creditTipocreditoIdtipocreditoOld != null && !creditTipocreditoIdtipocreditoOld.equals(creditTipocreditoIdtipocreditoNew)) {
                creditTipocreditoIdtipocreditoOld.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTipocreditoIdtipocreditoOld = em.merge(creditTipocreditoIdtipocreditoOld);
            }
            if (creditTipocreditoIdtipocreditoNew != null && !creditTipocreditoIdtipocreditoNew.equals(creditTipocreditoIdtipocreditoOld)) {
                creditTipocreditoIdtipocreditoNew.getCreditFinanciamientoList().add(creditFinanciamiento);
                creditTipocreditoIdtipocreditoNew = em.merge(creditTipocreditoIdtipocreditoNew);
            }
            for (CreditCotizacion creditCotizacionListNewCreditCotizacion : creditCotizacionListNew) {
                if (!creditCotizacionListOld.contains(creditCotizacionListNewCreditCotizacion)) {
                    CreditFinanciamiento oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion = creditCotizacionListNewCreditCotizacion.getCreditFinanciamientoIdfinanciamiento();
                    creditCotizacionListNewCreditCotizacion.setCreditFinanciamientoIdfinanciamiento(creditFinanciamiento);
                    creditCotizacionListNewCreditCotizacion = em.merge(creditCotizacionListNewCreditCotizacion);
                    if (oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion != null && !oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion.equals(creditFinanciamiento)) {
                        oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListNewCreditCotizacion);
                        oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion = em.merge(oldCreditFinanciamientoIdfinanciamientoOfCreditCotizacionListNewCreditCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditFinanciamiento.getIdfinanciamiento();
                if (findCreditFinanciamiento(id) == null) {
                    throw new NonexistentEntityException("The creditFinanciamiento with id " + id + " no longer exists.");
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
            CreditFinanciamiento creditFinanciamiento;
            try {
                creditFinanciamiento = em.getReference(CreditFinanciamiento.class, id);
                creditFinanciamiento.getIdfinanciamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditFinanciamiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditCotizacion> creditCotizacionListOrphanCheck = creditFinanciamiento.getCreditCotizacionList();
            for (CreditCotizacion creditCotizacionListOrphanCheckCreditCotizacion : creditCotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CreditFinanciamiento (" + creditFinanciamiento + ") cannot be destroyed since the CreditCotizacion " + creditCotizacionListOrphanCheckCreditCotizacion + " in its creditCotizacionList field has a non-nullable creditFinanciamientoIdfinanciamiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CreditUtilidad creditUtilidadIdutilidad = creditFinanciamiento.getCreditUtilidadIdutilidad();
            if (creditUtilidadIdutilidad != null) {
                creditUtilidadIdutilidad.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditUtilidadIdutilidad = em.merge(creditUtilidadIdutilidad);
            }
            CreditTipocuota creditTipocuotaIdtipocuota = creditFinanciamiento.getCreditTipocuotaIdtipocuota();
            if (creditTipocuotaIdtipocuota != null) {
                creditTipocuotaIdtipocuota.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTipocuotaIdtipocuota = em.merge(creditTipocuotaIdtipocuota);
            }
            CreditDestinofondo creditDestinofondoIddestinofondo = creditFinanciamiento.getCreditDestinofondoIddestinofondo();
            if (creditDestinofondoIddestinofondo != null) {
                creditDestinofondoIddestinofondo.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditDestinofondoIddestinofondo = em.merge(creditDestinofondoIddestinofondo);
            }
            CreditTiposolicitante creditTiposolicitanteIdtiposolicitante = creditFinanciamiento.getCreditTiposolicitanteIdtiposolicitante();
            if (creditTiposolicitanteIdtiposolicitante != null) {
                creditTiposolicitanteIdtiposolicitante.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTiposolicitanteIdtiposolicitante = em.merge(creditTiposolicitanteIdtiposolicitante);
            }
            CreditPagocapital creditPagocapitalIdpagocapital = creditFinanciamiento.getCreditPagocapitalIdpagocapital();
            if (creditPagocapitalIdpagocapital != null) {
                creditPagocapitalIdpagocapital.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditPagocapitalIdpagocapital = em.merge(creditPagocapitalIdpagocapital);
            }
            CreditPagointeres creditPagointeresIdpagointeres = creditFinanciamiento.getCreditPagointeresIdpagointeres();
            if (creditPagointeresIdpagointeres != null) {
                creditPagointeresIdpagointeres.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditPagointeresIdpagointeres = em.merge(creditPagointeresIdpagointeres);
            }
            CreditTipocredito creditTipocreditoIdtipocredito = creditFinanciamiento.getCreditTipocreditoIdtipocredito();
            if (creditTipocreditoIdtipocredito != null) {
                creditTipocreditoIdtipocredito.getCreditFinanciamientoList().remove(creditFinanciamiento);
                creditTipocreditoIdtipocredito = em.merge(creditTipocreditoIdtipocredito);
            }
            em.remove(creditFinanciamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CreditFinanciamiento> findCreditFinanciamientoEntities() {
        return findCreditFinanciamientoEntities(true, -1, -1);
    }

    public List<CreditFinanciamiento> findCreditFinanciamientoEntities(int maxResults, int firstResult) {
        return findCreditFinanciamientoEntities(false, maxResults, firstResult);
    }

    private List<CreditFinanciamiento> findCreditFinanciamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CreditFinanciamiento.class));
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

    public CreditFinanciamiento findCreditFinanciamiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CreditFinanciamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditFinanciamientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CreditFinanciamiento> rt = cq.from(CreditFinanciamiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
