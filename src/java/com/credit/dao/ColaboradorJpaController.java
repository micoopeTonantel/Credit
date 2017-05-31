/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import com.credit.dao.exceptions.IllegalOrphanException;
import com.credit.dao.exceptions.NonexistentEntityException;
import com.credit.dao.exceptions.PreexistingEntityException;
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
public class ColaboradorJpaController implements Serializable {

    public ColaboradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Colaborador colaborador) throws PreexistingEntityException, Exception {
        if (colaborador.getCreditSolicitudList() == null) {
            colaborador.setCreditSolicitudList(new ArrayList<CreditSolicitud>());
        }
        if (colaborador.getAsignarprivilegioList() == null) {
            colaborador.setAsignarprivilegioList(new ArrayList<Asignarprivilegio>());
        }
        if (colaborador.getCreditCotizacionList() == null) {
            colaborador.setCreditCotizacionList(new ArrayList<CreditCotizacion>());
        }
        if (colaborador.getAsignarrolList() == null) {
            colaborador.setAsignarrolList(new ArrayList<Asignarrol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agencia agenciaIdagencia = colaborador.getAgenciaIdagencia();
            if (agenciaIdagencia != null) {
                agenciaIdagencia = em.getReference(agenciaIdagencia.getClass(), agenciaIdagencia.getIdagencia());
                colaborador.setAgenciaIdagencia(agenciaIdagencia);
            }
            Departamento departamentoIddepartamento = colaborador.getDepartamentoIddepartamento();
            if (departamentoIddepartamento != null) {
                departamentoIddepartamento = em.getReference(departamentoIddepartamento.getClass(), departamentoIddepartamento.getIddepartamento());
                colaborador.setDepartamentoIddepartamento(departamentoIddepartamento);
            }
            Puesto puestoIdpuesto = colaborador.getPuestoIdpuesto();
            if (puestoIdpuesto != null) {
                puestoIdpuesto = em.getReference(puestoIdpuesto.getClass(), puestoIdpuesto.getIdpuesto());
                colaborador.setPuestoIdpuesto(puestoIdpuesto);
            }
            List<CreditSolicitud> attachedCreditSolicitudList = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListCreditSolicitudToAttach : colaborador.getCreditSolicitudList()) {
                creditSolicitudListCreditSolicitudToAttach = em.getReference(creditSolicitudListCreditSolicitudToAttach.getClass(), creditSolicitudListCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudList.add(creditSolicitudListCreditSolicitudToAttach);
            }
            colaborador.setCreditSolicitudList(attachedCreditSolicitudList);
            List<Asignarprivilegio> attachedAsignarprivilegioList = new ArrayList<Asignarprivilegio>();
            for (Asignarprivilegio asignarprivilegioListAsignarprivilegioToAttach : colaborador.getAsignarprivilegioList()) {
                asignarprivilegioListAsignarprivilegioToAttach = em.getReference(asignarprivilegioListAsignarprivilegioToAttach.getClass(), asignarprivilegioListAsignarprivilegioToAttach.getAsignarprivilegioPK());
                attachedAsignarprivilegioList.add(asignarprivilegioListAsignarprivilegioToAttach);
            }
            colaborador.setAsignarprivilegioList(attachedAsignarprivilegioList);
            List<CreditCotizacion> attachedCreditCotizacionList = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListCreditCotizacionToAttach : colaborador.getCreditCotizacionList()) {
                creditCotizacionListCreditCotizacionToAttach = em.getReference(creditCotizacionListCreditCotizacionToAttach.getClass(), creditCotizacionListCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionList.add(creditCotizacionListCreditCotizacionToAttach);
            }
            colaborador.setCreditCotizacionList(attachedCreditCotizacionList);
            List<Asignarrol> attachedAsignarrolList = new ArrayList<Asignarrol>();
            for (Asignarrol asignarrolListAsignarrolToAttach : colaborador.getAsignarrolList()) {
                asignarrolListAsignarrolToAttach = em.getReference(asignarrolListAsignarrolToAttach.getClass(), asignarrolListAsignarrolToAttach.getAsignarrolPK());
                attachedAsignarrolList.add(asignarrolListAsignarrolToAttach);
            }
            colaborador.setAsignarrolList(attachedAsignarrolList);
            em.persist(colaborador);
            if (agenciaIdagencia != null) {
                agenciaIdagencia.getColaboradorList().add(colaborador);
                agenciaIdagencia = em.merge(agenciaIdagencia);
            }
            if (departamentoIddepartamento != null) {
                departamentoIddepartamento.getColaboradorList().add(colaborador);
                departamentoIddepartamento = em.merge(departamentoIddepartamento);
            }
            if (puestoIdpuesto != null) {
                puestoIdpuesto.getColaboradorList().add(colaborador);
                puestoIdpuesto = em.merge(puestoIdpuesto);
            }
            for (CreditSolicitud creditSolicitudListCreditSolicitud : colaborador.getCreditSolicitudList()) {
                Colaborador oldColaboradorOperadorOfCreditSolicitudListCreditSolicitud = creditSolicitudListCreditSolicitud.getColaboradorOperador();
                creditSolicitudListCreditSolicitud.setColaboradorOperador(colaborador);
                creditSolicitudListCreditSolicitud = em.merge(creditSolicitudListCreditSolicitud);
                if (oldColaboradorOperadorOfCreditSolicitudListCreditSolicitud != null) {
                    oldColaboradorOperadorOfCreditSolicitudListCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListCreditSolicitud);
                    oldColaboradorOperadorOfCreditSolicitudListCreditSolicitud = em.merge(oldColaboradorOperadorOfCreditSolicitudListCreditSolicitud);
                }
            }
            for (Asignarprivilegio asignarprivilegioListAsignarprivilegio : colaborador.getAsignarprivilegioList()) {
                Colaborador oldColaboradorOfAsignarprivilegioListAsignarprivilegio = asignarprivilegioListAsignarprivilegio.getColaborador();
                asignarprivilegioListAsignarprivilegio.setColaborador(colaborador);
                asignarprivilegioListAsignarprivilegio = em.merge(asignarprivilegioListAsignarprivilegio);
                if (oldColaboradorOfAsignarprivilegioListAsignarprivilegio != null) {
                    oldColaboradorOfAsignarprivilegioListAsignarprivilegio.getAsignarprivilegioList().remove(asignarprivilegioListAsignarprivilegio);
                    oldColaboradorOfAsignarprivilegioListAsignarprivilegio = em.merge(oldColaboradorOfAsignarprivilegioListAsignarprivilegio);
                }
            }
            for (CreditCotizacion creditCotizacionListCreditCotizacion : colaborador.getCreditCotizacionList()) {
                Colaborador oldColaboradorOperadorOfCreditCotizacionListCreditCotizacion = creditCotizacionListCreditCotizacion.getColaboradorOperador();
                creditCotizacionListCreditCotizacion.setColaboradorOperador(colaborador);
                creditCotizacionListCreditCotizacion = em.merge(creditCotizacionListCreditCotizacion);
                if (oldColaboradorOperadorOfCreditCotizacionListCreditCotizacion != null) {
                    oldColaboradorOperadorOfCreditCotizacionListCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListCreditCotizacion);
                    oldColaboradorOperadorOfCreditCotizacionListCreditCotizacion = em.merge(oldColaboradorOperadorOfCreditCotizacionListCreditCotizacion);
                }
            }
            for (Asignarrol asignarrolListAsignarrol : colaborador.getAsignarrolList()) {
                Colaborador oldColaboradorOfAsignarrolListAsignarrol = asignarrolListAsignarrol.getColaborador();
                asignarrolListAsignarrol.setColaborador(colaborador);
                asignarrolListAsignarrol = em.merge(asignarrolListAsignarrol);
                if (oldColaboradorOfAsignarrolListAsignarrol != null) {
                    oldColaboradorOfAsignarrolListAsignarrol.getAsignarrolList().remove(asignarrolListAsignarrol);
                    oldColaboradorOfAsignarrolListAsignarrol = em.merge(oldColaboradorOfAsignarrolListAsignarrol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findColaborador(colaborador.getOperador()) != null) {
                throw new PreexistingEntityException("Colaborador " + colaborador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Colaborador colaborador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador persistentColaborador = em.find(Colaborador.class, colaborador.getOperador());
            Agencia agenciaIdagenciaOld = persistentColaborador.getAgenciaIdagencia();
            Agencia agenciaIdagenciaNew = colaborador.getAgenciaIdagencia();
            Departamento departamentoIddepartamentoOld = persistentColaborador.getDepartamentoIddepartamento();
            Departamento departamentoIddepartamentoNew = colaborador.getDepartamentoIddepartamento();
            Puesto puestoIdpuestoOld = persistentColaborador.getPuestoIdpuesto();
            Puesto puestoIdpuestoNew = colaborador.getPuestoIdpuesto();
            List<CreditSolicitud> creditSolicitudListOld = persistentColaborador.getCreditSolicitudList();
            List<CreditSolicitud> creditSolicitudListNew = colaborador.getCreditSolicitudList();
            List<Asignarprivilegio> asignarprivilegioListOld = persistentColaborador.getAsignarprivilegioList();
            List<Asignarprivilegio> asignarprivilegioListNew = colaborador.getAsignarprivilegioList();
            List<CreditCotizacion> creditCotizacionListOld = persistentColaborador.getCreditCotizacionList();
            List<CreditCotizacion> creditCotizacionListNew = colaborador.getCreditCotizacionList();
            List<Asignarrol> asignarrolListOld = persistentColaborador.getAsignarrolList();
            List<Asignarrol> asignarrolListNew = colaborador.getAsignarrolList();
            List<String> illegalOrphanMessages = null;
            for (CreditSolicitud creditSolicitudListOldCreditSolicitud : creditSolicitudListOld) {
                if (!creditSolicitudListNew.contains(creditSolicitudListOldCreditSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditSolicitud " + creditSolicitudListOldCreditSolicitud + " since its colaboradorOperador field is not nullable.");
                }
            }
            for (Asignarprivilegio asignarprivilegioListOldAsignarprivilegio : asignarprivilegioListOld) {
                if (!asignarprivilegioListNew.contains(asignarprivilegioListOldAsignarprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignarprivilegio " + asignarprivilegioListOldAsignarprivilegio + " since its colaborador field is not nullable.");
                }
            }
            for (CreditCotizacion creditCotizacionListOldCreditCotizacion : creditCotizacionListOld) {
                if (!creditCotizacionListNew.contains(creditCotizacionListOldCreditCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditCotizacion " + creditCotizacionListOldCreditCotizacion + " since its colaboradorOperador field is not nullable.");
                }
            }
            for (Asignarrol asignarrolListOldAsignarrol : asignarrolListOld) {
                if (!asignarrolListNew.contains(asignarrolListOldAsignarrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignarrol " + asignarrolListOldAsignarrol + " since its colaborador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (agenciaIdagenciaNew != null) {
                agenciaIdagenciaNew = em.getReference(agenciaIdagenciaNew.getClass(), agenciaIdagenciaNew.getIdagencia());
                colaborador.setAgenciaIdagencia(agenciaIdagenciaNew);
            }
            if (departamentoIddepartamentoNew != null) {
                departamentoIddepartamentoNew = em.getReference(departamentoIddepartamentoNew.getClass(), departamentoIddepartamentoNew.getIddepartamento());
                colaborador.setDepartamentoIddepartamento(departamentoIddepartamentoNew);
            }
            if (puestoIdpuestoNew != null) {
                puestoIdpuestoNew = em.getReference(puestoIdpuestoNew.getClass(), puestoIdpuestoNew.getIdpuesto());
                colaborador.setPuestoIdpuesto(puestoIdpuestoNew);
            }
            List<CreditSolicitud> attachedCreditSolicitudListNew = new ArrayList<CreditSolicitud>();
            for (CreditSolicitud creditSolicitudListNewCreditSolicitudToAttach : creditSolicitudListNew) {
                creditSolicitudListNewCreditSolicitudToAttach = em.getReference(creditSolicitudListNewCreditSolicitudToAttach.getClass(), creditSolicitudListNewCreditSolicitudToAttach.getIdsolicitud());
                attachedCreditSolicitudListNew.add(creditSolicitudListNewCreditSolicitudToAttach);
            }
            creditSolicitudListNew = attachedCreditSolicitudListNew;
            colaborador.setCreditSolicitudList(creditSolicitudListNew);
            List<Asignarprivilegio> attachedAsignarprivilegioListNew = new ArrayList<Asignarprivilegio>();
            for (Asignarprivilegio asignarprivilegioListNewAsignarprivilegioToAttach : asignarprivilegioListNew) {
                asignarprivilegioListNewAsignarprivilegioToAttach = em.getReference(asignarprivilegioListNewAsignarprivilegioToAttach.getClass(), asignarprivilegioListNewAsignarprivilegioToAttach.getAsignarprivilegioPK());
                attachedAsignarprivilegioListNew.add(asignarprivilegioListNewAsignarprivilegioToAttach);
            }
            asignarprivilegioListNew = attachedAsignarprivilegioListNew;
            colaborador.setAsignarprivilegioList(asignarprivilegioListNew);
            List<CreditCotizacion> attachedCreditCotizacionListNew = new ArrayList<CreditCotizacion>();
            for (CreditCotizacion creditCotizacionListNewCreditCotizacionToAttach : creditCotizacionListNew) {
                creditCotizacionListNewCreditCotizacionToAttach = em.getReference(creditCotizacionListNewCreditCotizacionToAttach.getClass(), creditCotizacionListNewCreditCotizacionToAttach.getNumero());
                attachedCreditCotizacionListNew.add(creditCotizacionListNewCreditCotizacionToAttach);
            }
            creditCotizacionListNew = attachedCreditCotizacionListNew;
            colaborador.setCreditCotizacionList(creditCotizacionListNew);
            List<Asignarrol> attachedAsignarrolListNew = new ArrayList<Asignarrol>();
            for (Asignarrol asignarrolListNewAsignarrolToAttach : asignarrolListNew) {
                asignarrolListNewAsignarrolToAttach = em.getReference(asignarrolListNewAsignarrolToAttach.getClass(), asignarrolListNewAsignarrolToAttach.getAsignarrolPK());
                attachedAsignarrolListNew.add(asignarrolListNewAsignarrolToAttach);
            }
            asignarrolListNew = attachedAsignarrolListNew;
            colaborador.setAsignarrolList(asignarrolListNew);
            colaborador = em.merge(colaborador);
            if (agenciaIdagenciaOld != null && !agenciaIdagenciaOld.equals(agenciaIdagenciaNew)) {
                agenciaIdagenciaOld.getColaboradorList().remove(colaborador);
                agenciaIdagenciaOld = em.merge(agenciaIdagenciaOld);
            }
            if (agenciaIdagenciaNew != null && !agenciaIdagenciaNew.equals(agenciaIdagenciaOld)) {
                agenciaIdagenciaNew.getColaboradorList().add(colaborador);
                agenciaIdagenciaNew = em.merge(agenciaIdagenciaNew);
            }
            if (departamentoIddepartamentoOld != null && !departamentoIddepartamentoOld.equals(departamentoIddepartamentoNew)) {
                departamentoIddepartamentoOld.getColaboradorList().remove(colaborador);
                departamentoIddepartamentoOld = em.merge(departamentoIddepartamentoOld);
            }
            if (departamentoIddepartamentoNew != null && !departamentoIddepartamentoNew.equals(departamentoIddepartamentoOld)) {
                departamentoIddepartamentoNew.getColaboradorList().add(colaborador);
                departamentoIddepartamentoNew = em.merge(departamentoIddepartamentoNew);
            }
            if (puestoIdpuestoOld != null && !puestoIdpuestoOld.equals(puestoIdpuestoNew)) {
                puestoIdpuestoOld.getColaboradorList().remove(colaborador);
                puestoIdpuestoOld = em.merge(puestoIdpuestoOld);
            }
            if (puestoIdpuestoNew != null && !puestoIdpuestoNew.equals(puestoIdpuestoOld)) {
                puestoIdpuestoNew.getColaboradorList().add(colaborador);
                puestoIdpuestoNew = em.merge(puestoIdpuestoNew);
            }
            for (CreditSolicitud creditSolicitudListNewCreditSolicitud : creditSolicitudListNew) {
                if (!creditSolicitudListOld.contains(creditSolicitudListNewCreditSolicitud)) {
                    Colaborador oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud = creditSolicitudListNewCreditSolicitud.getColaboradorOperador();
                    creditSolicitudListNewCreditSolicitud.setColaboradorOperador(colaborador);
                    creditSolicitudListNewCreditSolicitud = em.merge(creditSolicitudListNewCreditSolicitud);
                    if (oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud != null && !oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud.equals(colaborador)) {
                        oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud.getCreditSolicitudList().remove(creditSolicitudListNewCreditSolicitud);
                        oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud = em.merge(oldColaboradorOperadorOfCreditSolicitudListNewCreditSolicitud);
                    }
                }
            }
            for (Asignarprivilegio asignarprivilegioListNewAsignarprivilegio : asignarprivilegioListNew) {
                if (!asignarprivilegioListOld.contains(asignarprivilegioListNewAsignarprivilegio)) {
                    Colaborador oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio = asignarprivilegioListNewAsignarprivilegio.getColaborador();
                    asignarprivilegioListNewAsignarprivilegio.setColaborador(colaborador);
                    asignarprivilegioListNewAsignarprivilegio = em.merge(asignarprivilegioListNewAsignarprivilegio);
                    if (oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio != null && !oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio.equals(colaborador)) {
                        oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio.getAsignarprivilegioList().remove(asignarprivilegioListNewAsignarprivilegio);
                        oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio = em.merge(oldColaboradorOfAsignarprivilegioListNewAsignarprivilegio);
                    }
                }
            }
            for (CreditCotizacion creditCotizacionListNewCreditCotizacion : creditCotizacionListNew) {
                if (!creditCotizacionListOld.contains(creditCotizacionListNewCreditCotizacion)) {
                    Colaborador oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion = creditCotizacionListNewCreditCotizacion.getColaboradorOperador();
                    creditCotizacionListNewCreditCotizacion.setColaboradorOperador(colaborador);
                    creditCotizacionListNewCreditCotizacion = em.merge(creditCotizacionListNewCreditCotizacion);
                    if (oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion != null && !oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion.equals(colaborador)) {
                        oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion.getCreditCotizacionList().remove(creditCotizacionListNewCreditCotizacion);
                        oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion = em.merge(oldColaboradorOperadorOfCreditCotizacionListNewCreditCotizacion);
                    }
                }
            }
            for (Asignarrol asignarrolListNewAsignarrol : asignarrolListNew) {
                if (!asignarrolListOld.contains(asignarrolListNewAsignarrol)) {
                    Colaborador oldColaboradorOfAsignarrolListNewAsignarrol = asignarrolListNewAsignarrol.getColaborador();
                    asignarrolListNewAsignarrol.setColaborador(colaborador);
                    asignarrolListNewAsignarrol = em.merge(asignarrolListNewAsignarrol);
                    if (oldColaboradorOfAsignarrolListNewAsignarrol != null && !oldColaboradorOfAsignarrolListNewAsignarrol.equals(colaborador)) {
                        oldColaboradorOfAsignarrolListNewAsignarrol.getAsignarrolList().remove(asignarrolListNewAsignarrol);
                        oldColaboradorOfAsignarrolListNewAsignarrol = em.merge(oldColaboradorOfAsignarrolListNewAsignarrol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colaborador.getOperador();
                if (findColaborador(id) == null) {
                    throw new NonexistentEntityException("The colaborador with id " + id + " no longer exists.");
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
            Colaborador colaborador;
            try {
                colaborador = em.getReference(Colaborador.class, id);
                colaborador.getOperador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colaborador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CreditSolicitud> creditSolicitudListOrphanCheck = colaborador.getCreditSolicitudList();
            for (CreditSolicitud creditSolicitudListOrphanCheckCreditSolicitud : creditSolicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colaborador (" + colaborador + ") cannot be destroyed since the CreditSolicitud " + creditSolicitudListOrphanCheckCreditSolicitud + " in its creditSolicitudList field has a non-nullable colaboradorOperador field.");
            }
            List<Asignarprivilegio> asignarprivilegioListOrphanCheck = colaborador.getAsignarprivilegioList();
            for (Asignarprivilegio asignarprivilegioListOrphanCheckAsignarprivilegio : asignarprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colaborador (" + colaborador + ") cannot be destroyed since the Asignarprivilegio " + asignarprivilegioListOrphanCheckAsignarprivilegio + " in its asignarprivilegioList field has a non-nullable colaborador field.");
            }
            List<CreditCotizacion> creditCotizacionListOrphanCheck = colaborador.getCreditCotizacionList();
            for (CreditCotizacion creditCotizacionListOrphanCheckCreditCotizacion : creditCotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colaborador (" + colaborador + ") cannot be destroyed since the CreditCotizacion " + creditCotizacionListOrphanCheckCreditCotizacion + " in its creditCotizacionList field has a non-nullable colaboradorOperador field.");
            }
            List<Asignarrol> asignarrolListOrphanCheck = colaborador.getAsignarrolList();
            for (Asignarrol asignarrolListOrphanCheckAsignarrol : asignarrolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colaborador (" + colaborador + ") cannot be destroyed since the Asignarrol " + asignarrolListOrphanCheckAsignarrol + " in its asignarrolList field has a non-nullable colaborador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Agencia agenciaIdagencia = colaborador.getAgenciaIdagencia();
            if (agenciaIdagencia != null) {
                agenciaIdagencia.getColaboradorList().remove(colaborador);
                agenciaIdagencia = em.merge(agenciaIdagencia);
            }
            Departamento departamentoIddepartamento = colaborador.getDepartamentoIddepartamento();
            if (departamentoIddepartamento != null) {
                departamentoIddepartamento.getColaboradorList().remove(colaborador);
                departamentoIddepartamento = em.merge(departamentoIddepartamento);
            }
            Puesto puestoIdpuesto = colaborador.getPuestoIdpuesto();
            if (puestoIdpuesto != null) {
                puestoIdpuesto.getColaboradorList().remove(colaborador);
                puestoIdpuesto = em.merge(puestoIdpuesto);
            }
            em.remove(colaborador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Colaborador> findColaboradorEntities() {
        return findColaboradorEntities(true, -1, -1);
    }

    public List<Colaborador> findColaboradorEntities(int maxResults, int firstResult) {
        return findColaboradorEntities(false, maxResults, firstResult);
    }

    private List<Colaborador> findColaboradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colaborador.class));
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

    public Colaborador findColaborador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Colaborador.class, id);
        } finally {
            em.close();
        }
    }

    public int getColaboradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Colaborador> rt = cq.from(Colaborador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
