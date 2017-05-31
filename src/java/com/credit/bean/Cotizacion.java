package com.credit.bean;

import com.credit.dao.Colaborador;
import com.credit.dao.CreditCliente;
import com.credit.dao.CreditCotizacion;
import com.credit.dao.CreditDestinofondo;
import com.credit.dao.CreditFinanciamiento;
import com.credit.dao.CreditIngreso;
import com.credit.dao.CreditPagocapital;
import com.credit.dao.CreditPagointeres;
import com.credit.dao.CreditPersona;
import com.credit.dao.CreditTipocredito;
import com.credit.dao.CreditTipocuota;
import com.credit.dao.CreditTiposolicitante;
import com.credit.dao.CreditUtilidad;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "cotizacion")
@RequestScoped
public class Cotizacion {
    private CreditPersona persona = new CreditPersona();
    private CreditIngreso ingreso = new CreditIngreso();
    private CreditFinanciamiento financiamiento = new CreditFinanciamiento();
    private CreditCotizacion cotizacion = new CreditCotizacion();
    private CreditUtilidad utilidad = new CreditUtilidad();
    private CreditTiposolicitante tipoSolicitante = new CreditTiposolicitante();
    private CreditDestinofondo destinoFondos = new CreditDestinofondo();
    private CreditPagocapital pagoCapital = new CreditPagocapital();
    private CreditPagointeres pagoInteres = new CreditPagointeres();
    private CreditTipocuota tipoCuota = new CreditTipocuota();
    private CreditTipocredito tipoCredito = new CreditTipocredito();
    private List<SelectItem> tipoSolicitanteList;
    private List<SelectItem> pagoCapitalList;
    private List<SelectItem> pagoInteresesList;
    private List<SelectItem> tipoCuotaList;
    private List<SelectItem> tipoCreditoList;
    private List<SelectItem> destinoFondosList;
    private List<SelectItem> utilidadList;
    
    public Cotizacion() {
    }
    
    // Declaracion de metodos establecer y obtener
    public CreditPersona getPersona() {
        return persona;
    }

    public void setPersona(CreditPersona persona) {
        this.persona = persona;
    }

    public CreditIngreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(CreditIngreso ingreso) {
        this.ingreso = ingreso;
    }

    public CreditFinanciamiento getFinanciamiento() {
        return financiamiento;
    }

    public void setFinanciamiento(CreditFinanciamiento financiamiento) {
        this.financiamiento = financiamiento;
    }

    public CreditCotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(CreditCotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public CreditUtilidad getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(CreditUtilidad utilidad) {
        this.utilidad = utilidad;
    }

    public CreditTiposolicitante getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(CreditTiposolicitante tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public CreditDestinofondo getDestinoFondos() {
        return destinoFondos;
    }

    public void setDestinoFondos(CreditDestinofondo destinoFondos) {
        this.destinoFondos = destinoFondos;
    }

    public CreditPagocapital getPagoCapital() {
        return pagoCapital;
    }

    public void setPagoCapital(CreditPagocapital pagoCapital) {
        this.pagoCapital = pagoCapital;
    }

    public CreditPagointeres getPagoInteres() {
        return pagoInteres;
    }

    public void setPagoInteres(CreditPagointeres pagoInteres) {
        this.pagoInteres = pagoInteres;
    }

    public CreditTipocuota getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(CreditTipocuota tipoCuota) {
        this.tipoCuota = tipoCuota;
    }

    public CreditTipocredito getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(CreditTipocredito tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public List<SelectItem> getTipoSolicitanteList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT t "
                + "FROM CreditTiposolicitante t "
                + "WHERE t.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditTiposolicitante> miLista = (List<CreditTiposolicitante>) consulta.getResultList();
        
        tipoSolicitanteList = new ArrayList<SelectItem>();
        tipoSolicitanteList.clear();
        for(CreditTiposolicitante solicitante : miLista){
            SelectItem item = new SelectItem(solicitante.getIdtiposolicitante(), solicitante.getDescripTiposolicitante());
            tipoSolicitanteList.add(item);
        }
        
        em.close();
        emf.close();
        
        return tipoSolicitanteList;
    }

    public void setTipoSolicitanteList(List<SelectItem> tipoSolicitanteList) {
        this.tipoSolicitanteList = tipoSolicitanteList;
    }

    public List<SelectItem> getPagoCapitalList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT p "
                + "FROM CreditPagocapital p "
                + "WHERE p.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditPagocapital> resultado = (List<CreditPagocapital>) consulta.getResultList();
        
        pagoCapitalList = new ArrayList<SelectItem>();
        pagoCapitalList.clear();
        for(CreditPagocapital capital : resultado){
            SelectItem item = new SelectItem(capital.getIdpagocapital(), capital.getDescripPagocapital());
            pagoCapitalList.add(item);
        }
        
        em.close();
        emf.close();
        
        return pagoCapitalList;
    }

    public void setPagoCapitalList(List<SelectItem> pagoCapitalList) {
        this.pagoCapitalList = pagoCapitalList;
    }

    public List<SelectItem> getPagoInteresesList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT p "
                + "FROM CreditPagointeres p "
                + "WHERE p.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditPagointeres> resultado = (List<CreditPagointeres>) consulta.getResultList();
        
        pagoInteresesList = new ArrayList<SelectItem>();
        pagoInteresesList.clear();
        for(CreditPagointeres pagoInteres : resultado){
            SelectItem item = new SelectItem(pagoInteres.getIdpagointeres(), pagoInteres.getDescripPagointeres());
            pagoInteresesList.add(item);
        }
        
        em.close();
        emf.close();
        
        return pagoInteresesList;
    }

    public void setPagoInteresesList(List<SelectItem> pagoInteresesList) {
        this.pagoInteresesList = pagoInteresesList;
    }

    public List<SelectItem> getTipoCuotaList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT t "
                + "FROM CreditTipocuota t "
                + "WHERE t.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        
        List<CreditTipocuota> resultado = (List<CreditTipocuota>) consulta.getResultList();
        
        tipoCuotaList = new ArrayList<SelectItem>();
        tipoCuotaList.clear();
        for(CreditTipocuota cuota : resultado){
            SelectItem item = new SelectItem(cuota.getIdtipocuota(), cuota.getDescripTipocuota());
            tipoCuotaList.add(item);
        }
        
        em.close();
        emf.close();
        
        return tipoCuotaList;
    }

    public void setTipoCuotaList(List<SelectItem> tipoCuotaList) {
        this.tipoCuotaList = tipoCuotaList;
    }

    public List<SelectItem> getTipoCreditoList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT t "
                + "FROM CreditTipocredito t "
                + "WHERE t.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditTipocredito> resultado = (List<CreditTipocredito>) consulta.getResultList();
        
        tipoCreditoList = new ArrayList<SelectItem>();
        tipoCreditoList.clear();
        for(CreditTipocredito tipoCredito : resultado){
            SelectItem item = new SelectItem(tipoCredito.getIdtipocredito(), tipoCredito.getDescripTipocredito());
            tipoCreditoList.add(item);
        }
        
        em.close();
        emf.close();
        
        return tipoCreditoList;
    }

    public void setTipoCreditoList(List<SelectItem> tipoCreditoList) {
        this.tipoCreditoList = tipoCreditoList;
    }

    public List<SelectItem> getDestinoFondosList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT d "
                + "FROM CreditDestinofondo d "
                + "WHERE d.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditDestinofondo> resultado = (List<CreditDestinofondo>) consulta.getResultList();
        
        destinoFondosList = new ArrayList<SelectItem>();
        destinoFondosList.clear();
        for(CreditDestinofondo destinoFondo : resultado){
            SelectItem item = new SelectItem(destinoFondo.getIddestinofondo(), destinoFondo.getDescripDestinofondo());
            destinoFondosList.add(item);
        }
        
        em.close();
        emf.close();
        
        return destinoFondosList;
    }

    public void setDestinoFondosList(List<SelectItem> destinoFondosList) {
        this.destinoFondosList = destinoFondosList;
    }

    public List<SelectItem> getUtilidadList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT u "
                + "FROM CreditUtilidad u "
                + "WHERE u.estado = :miEstado";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("miEstado", 'a');
        List<CreditUtilidad> resultado = (List<CreditUtilidad>) consulta.getResultList();
        
        utilidadList = new ArrayList<SelectItem>();
        utilidadList.clear();
        for(CreditUtilidad utilidad : resultado){
            SelectItem item = new SelectItem(utilidad.getIdutilidad(), utilidad.getDescripUtilidad());
            utilidadList.add(item);
        }
        
        em.close();
        emf.close();
        
        return utilidadList;
    }

    public void setUtilidadList(List<SelectItem> utilidadList) {
        this.utilidadList = utilidadList;
    }
    
    /*
        Metodo para verificar si existe una cotizacion activa para el asociado
    */
    private boolean isCotizacionActiva(){
        boolean result = false;
        
        if(!persona.getDpi().equals("")){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
            EntityManager em = emf.createEntityManager();
            
            String jpql = "SELECT f, i, p, c "
                    + "FROM CreditCotizacion c "
                    + "JOIN c.creditFinanciamientoIdfinanciamiento f "
                    + "JOIN c.creditIngresoIdingreso i "
                    + "JOIN c.creditPersonaDpi p "
                    + "WHERE p.dpi = :Dpi "
                    + "AND c.estado = :Estado";
            Query consulta = em.createQuery(jpql);
            consulta.setParameter("Dpi", persona.getDpi());
            consulta.setParameter("Estado", 'a');
            List<Object[]> resultado = (List<Object[]>) consulta.getResultList();
            
            for(Object[] objeto : resultado){
                financiamiento = (CreditFinanciamiento) objeto[0];
                tipoSolicitante = financiamiento.getCreditTiposolicitanteIdtiposolicitante();
                pagoCapital = financiamiento.getCreditPagocapitalIdpagocapital();
                pagoInteres = financiamiento.getCreditPagointeresIdpagointeres();
                tipoCuota = financiamiento.getCreditTipocuotaIdtipocuota();
                tipoCredito = financiamiento.getCreditTipocreditoIdtipocredito();
                destinoFondos = financiamiento.getCreditDestinofondoIddestinofondo();
                utilidad = financiamiento.getCreditUtilidadIdutilidad();
                ingreso = (CreditIngreso) objeto[1];
                persona = (CreditPersona) objeto[2];
                cotizacion = (CreditCotizacion) objeto[3];
                
                result = true;
                
            }

            em.close();
            emf.close();
        }
        
        return result;
    }
    
    /*
        Metodo utilizado para buscar los datos en la entidad persona
    */
    private boolean isDatosPersona(){
        boolean result = false;
        
        if(!persona.getDpi().equals("")){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
            EntityManager em = emf.createEntityManager();
            
            String jpql = "SELECT c "
                    + "FROM CreditPersona c "
                    + "WHERE c.dpi = :miDpi";
            Query consulta = em.createQuery(jpql);
            consulta.setParameter("miDpi", persona.getDpi());
            List<CreditPersona> resultado = (List<CreditPersona>) consulta.getResultList();
            
            for(CreditPersona p : resultado){
                if(persona.getDpi().equals(p.getDpi())){
                    persona = p;
                    result = true;
                }
            }
            
            em.close();
            emf.close();
        }
        
        return result;
    }
    
    /*
        Metodo utilizado para buscar informacion en la tabla de clientes
    */
    private boolean isDatosCliente(){
        boolean result = false;
        
        if(!persona.getDpi().equals("")){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
            EntityManager em = emf.createEntityManager();
            
            String jpql = "SELECT c "
                    + "FROM CreditCliente c "
                    + "WHERE c.dpi = :miDpi";
            Query consulta = em.createQuery(jpql);
            consulta.setParameter("miDpi", persona.getDpi());
            List<CreditCliente> resultado = (List<CreditCliente>) consulta.getResultList();
            
            for(CreditCliente c : resultado){
                if(persona.getDpi().equals(c.getDpi())){
                    persona.setDpi(c.getDpi());
                    persona.setNit(c.getNit());
                    persona.setPrimerNombre(c.getPrimerNombre());
                    persona.setSegundoNombre(c.getSegundoNombre());
                    persona.setTercerNombre(c.getTercerNombre());
                    persona.setPrimerApellido(c.getPrimerApellido());
                    persona.setSegundoApellido(c.getSegundoApellido());
                    persona.setApellidoCasada(c.getApellidoCasada());
                    persona.setMail(c.getEmail());
                    ingreso.setProveniencia(c.getEmpresa());
                    ingreso.setCargo(c.getPuesto());
                    
                    result = true;
                }
            }
            
            em.close();
            emf.close();
        }
        
        return result;
    }
    
    public void buscarDatos(){
        if(!isCotizacionActiva()){
            if(!isDatosPersona()){
                if(!isDatosCliente()){
                    System.out.print("Datos encontrados");
                }
            }
        }         
    }
   
    /*
        Metodo utilizado para crear una nueva cotizacion de creditos
    */
    public void crearCotizacion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CreditPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        // Relaciona la entidad de financiamiento
        financiamiento.setCreditUtilidadIdutilidad(utilidad);
        financiamiento.setCreditTiposolicitanteIdtiposolicitante(tipoSolicitante);
        financiamiento.setCreditDestinofondoIddestinofondo(destinoFondos);
        financiamiento.setCreditPagocapitalIdpagocapital(pagoCapital);
        financiamiento.setCreditPagointeresIdpagointeres(pagoInteres);
        financiamiento.setCreditTipocuotaIdtipocuota(tipoCuota);
        financiamiento.setCreditTipocreditoIdtipocredito(tipoCredito);
        
        // Objeto de tipo cotizacion para agregar una nueva cotizacion
        cotizacion.setFecha(new Date());
        cotizacion.setEstado('a');
        cotizacion.setColaboradorOperador(new Colaborador(394));
        cotizacion.setCreditFinanciamientoIdfinanciamiento(financiamiento);
        cotizacion.setCreditIngresoIdingreso(ingreso);
        cotizacion.setCreditPersonaDpi(persona);
        
        em.merge(financiamiento);
        em.merge(ingreso);
        em.merge(persona);
        em.merge(cotizacion);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
