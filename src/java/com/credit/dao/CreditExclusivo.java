/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "credit_exclusivo")
@NamedQueries({
    @NamedQuery(name = "CreditExclusivo.findAll", query = "SELECT c FROM CreditExclusivo c")})
public class CreditExclusivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idexclusivo")
    private Integer idexclusivo;
    @Basic(optional = false)
    @Column(name = "numero_credito")
    private String numeroCredito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "saldo_actual")
    private BigDecimal saldoActual;
    @Column(name = "porcentaje_cancelado")
    private Integer porcentajeCancelado;
    @Basic(optional = false)
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @OneToMany(mappedBy = "creditExclusivoIdexclusivo")
    private List<CreditSolicitud> creditSolicitudList;

    public CreditExclusivo() {
    }

    public CreditExclusivo(Integer idexclusivo) {
        this.idexclusivo = idexclusivo;
    }

    public CreditExclusivo(Integer idexclusivo, String numeroCredito, BigDecimal saldoActual, Date fechaVencimiento) {
        this.idexclusivo = idexclusivo;
        this.numeroCredito = numeroCredito;
        this.saldoActual = saldoActual;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getIdexclusivo() {
        return idexclusivo;
    }

    public void setIdexclusivo(Integer idexclusivo) {
        this.idexclusivo = idexclusivo;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Integer getPorcentajeCancelado() {
        return porcentajeCancelado;
    }

    public void setPorcentajeCancelado(Integer porcentajeCancelado) {
        this.porcentajeCancelado = porcentajeCancelado;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<CreditSolicitud> getCreditSolicitudList() {
        return creditSolicitudList;
    }

    public void setCreditSolicitudList(List<CreditSolicitud> creditSolicitudList) {
        this.creditSolicitudList = creditSolicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexclusivo != null ? idexclusivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditExclusivo)) {
            return false;
        }
        CreditExclusivo other = (CreditExclusivo) object;
        if ((this.idexclusivo == null && other.idexclusivo != null) || (this.idexclusivo != null && !this.idexclusivo.equals(other.idexclusivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditExclusivo[ idexclusivo=" + idexclusivo + " ]";
    }
    
}
