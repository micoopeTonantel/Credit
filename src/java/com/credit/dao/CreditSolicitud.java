/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "credit_solicitud")
@NamedQueries({
    @NamedQuery(name = "CreditSolicitud.findAll", query = "SELECT c FROM CreditSolicitud c")})
public class CreditSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsolicitud")
    private Integer idsolicitud;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "colaborador_operador", referencedColumnName = "operador")
    @ManyToOne(optional = false)
    private Colaborador colaboradorOperador;
    @JoinColumn(name = "credit_cotizacion_numero", referencedColumnName = "numero")
    @ManyToOne(optional = false)
    private CreditCotizacion creditCotizacionNumero;
    @JoinColumn(name = "credit_asociado_cif", referencedColumnName = "cif")
    @ManyToOne(optional = false)
    private CreditAsociado creditAsociadoCif;
    @JoinColumn(name = "credit_electrodomestico_idelectrodomestico", referencedColumnName = "idelectrodomestico")
    @ManyToOne
    private CreditElectrodomestico creditElectrodomesticoIdelectrodomestico;
    @JoinColumn(name = "credit_exclusivo_idexclusivo", referencedColumnName = "idexclusivo")
    @ManyToOne
    private CreditExclusivo creditExclusivoIdexclusivo;

    public CreditSolicitud() {
    }

    public CreditSolicitud(Integer idsolicitud) {
        this.idsolicitud = idsolicitud;
    }

    public CreditSolicitud(Integer idsolicitud, Date fecha, Character estado) {
        this.idsolicitud = idsolicitud;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getIdsolicitud() {
        return idsolicitud;
    }

    public void setIdsolicitud(Integer idsolicitud) {
        this.idsolicitud = idsolicitud;
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

    public Colaborador getColaboradorOperador() {
        return colaboradorOperador;
    }

    public void setColaboradorOperador(Colaborador colaboradorOperador) {
        this.colaboradorOperador = colaboradorOperador;
    }

    public CreditCotizacion getCreditCotizacionNumero() {
        return creditCotizacionNumero;
    }

    public void setCreditCotizacionNumero(CreditCotizacion creditCotizacionNumero) {
        this.creditCotizacionNumero = creditCotizacionNumero;
    }

    public CreditAsociado getCreditAsociadoCif() {
        return creditAsociadoCif;
    }

    public void setCreditAsociadoCif(CreditAsociado creditAsociadoCif) {
        this.creditAsociadoCif = creditAsociadoCif;
    }

    public CreditElectrodomestico getCreditElectrodomesticoIdelectrodomestico() {
        return creditElectrodomesticoIdelectrodomestico;
    }

    public void setCreditElectrodomesticoIdelectrodomestico(CreditElectrodomestico creditElectrodomesticoIdelectrodomestico) {
        this.creditElectrodomesticoIdelectrodomestico = creditElectrodomesticoIdelectrodomestico;
    }

    public CreditExclusivo getCreditExclusivoIdexclusivo() {
        return creditExclusivoIdexclusivo;
    }

    public void setCreditExclusivoIdexclusivo(CreditExclusivo creditExclusivoIdexclusivo) {
        this.creditExclusivoIdexclusivo = creditExclusivoIdexclusivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsolicitud != null ? idsolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditSolicitud)) {
            return false;
        }
        CreditSolicitud other = (CreditSolicitud) object;
        if ((this.idsolicitud == null && other.idsolicitud != null) || (this.idsolicitud != null && !this.idsolicitud.equals(other.idsolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditSolicitud[ idsolicitud=" + idsolicitud + " ]";
    }
    
}
