/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "credit_cotizacion")
@NamedQueries({
    @NamedQuery(name = "CreditCotizacion.findAll", query = "SELECT c FROM CreditCotizacion c")})
public class CreditCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditCotizacionNumero")
    private List<CreditSolicitud> creditSolicitudList;
    @JoinColumn(name = "colaborador_operador", referencedColumnName = "operador")
    @ManyToOne(optional = false)
    private Colaborador colaboradorOperador;
    @JoinColumn(name = "credit_financiamiento_idfinanciamiento", referencedColumnName = "idfinanciamiento")
    @ManyToOne(optional = false)
    private CreditFinanciamiento creditFinanciamientoIdfinanciamiento;
    @JoinColumn(name = "credit_ingreso_idingreso", referencedColumnName = "idingreso")
    @ManyToOne(optional = false)
    private CreditIngreso creditIngresoIdingreso;
    @JoinColumn(name = "credit_persona_dpi", referencedColumnName = "dpi")
    @ManyToOne(optional = false)
    private CreditPersona creditPersonaDpi;

    public CreditCotizacion() {
    }

    public CreditCotizacion(Integer numero) {
        this.numero = numero;
    }

    public CreditCotizacion(Integer numero, Date fecha, Character estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public List<CreditSolicitud> getCreditSolicitudList() {
        return creditSolicitudList;
    }

    public void setCreditSolicitudList(List<CreditSolicitud> creditSolicitudList) {
        this.creditSolicitudList = creditSolicitudList;
    }

    public Colaborador getColaboradorOperador() {
        return colaboradorOperador;
    }

    public void setColaboradorOperador(Colaborador colaboradorOperador) {
        this.colaboradorOperador = colaboradorOperador;
    }

    public CreditFinanciamiento getCreditFinanciamientoIdfinanciamiento() {
        return creditFinanciamientoIdfinanciamiento;
    }

    public void setCreditFinanciamientoIdfinanciamiento(CreditFinanciamiento creditFinanciamientoIdfinanciamiento) {
        this.creditFinanciamientoIdfinanciamiento = creditFinanciamientoIdfinanciamiento;
    }

    public CreditIngreso getCreditIngresoIdingreso() {
        return creditIngresoIdingreso;
    }

    public void setCreditIngresoIdingreso(CreditIngreso creditIngresoIdingreso) {
        this.creditIngresoIdingreso = creditIngresoIdingreso;
    }

    public CreditPersona getCreditPersonaDpi() {
        return creditPersonaDpi;
    }

    public void setCreditPersonaDpi(CreditPersona creditPersonaDpi) {
        this.creditPersonaDpi = creditPersonaDpi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCotizacion)) {
            return false;
        }
        CreditCotizacion other = (CreditCotizacion) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditCotizacion[ numero=" + numero + " ]";
    }
    
}
