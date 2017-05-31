/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "credit_financiamiento")
@NamedQueries({
    @NamedQuery(name = "CreditFinanciamiento.findAll", query = "SELECT c FROM CreditFinanciamiento c")})
public class CreditFinanciamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfinanciamiento")
    private Integer idfinanciamiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "monto")
    private BigDecimal monto;
    @Basic(optional = false)
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @Column(name = "cuota_estimada")
    private BigDecimal cuotaEstimada;
    @Basic(optional = false)
    @Column(name = "cuota_pagar")
    private BigDecimal cuotaPagar;
    @Basic(optional = false)
    @Column(name = "tasa")
    private int tasa;
    @Column(name = "destino_especifico")
    private String destinoEspecifico;
    @JoinColumn(name = "credit_utilidad_idutilidad", referencedColumnName = "idutilidad")
    @ManyToOne(optional = false)
    private CreditUtilidad creditUtilidadIdutilidad;
    @JoinColumn(name = "credit_tipocuota_idtipocuota", referencedColumnName = "idtipocuota")
    @ManyToOne(optional = false)
    private CreditTipocuota creditTipocuotaIdtipocuota;
    @JoinColumn(name = "credit_destinofondo_iddestinofondo", referencedColumnName = "iddestinofondo")
    @ManyToOne(optional = false)
    private CreditDestinofondo creditDestinofondoIddestinofondo;
    @JoinColumn(name = "credit_tiposolicitante_idtiposolicitante", referencedColumnName = "idtiposolicitante")
    @ManyToOne(optional = false)
    private CreditTiposolicitante creditTiposolicitanteIdtiposolicitante;
    @JoinColumn(name = "credit_pagocapital_idpagocapital", referencedColumnName = "idpagocapital")
    @ManyToOne(optional = false)
    private CreditPagocapital creditPagocapitalIdpagocapital;
    @JoinColumn(name = "credit_pagointeres_idpagointeres", referencedColumnName = "idpagointeres")
    @ManyToOne(optional = false)
    private CreditPagointeres creditPagointeresIdpagointeres;
    @JoinColumn(name = "credit_tipocredito_idtipocredito", referencedColumnName = "idtipocredito")
    @ManyToOne(optional = false)
    private CreditTipocredito creditTipocreditoIdtipocredito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditFinanciamientoIdfinanciamiento")
    private List<CreditCotizacion> creditCotizacionList;

    public CreditFinanciamiento() {
    }

    public CreditFinanciamiento(Integer idfinanciamiento) {
        this.idfinanciamiento = idfinanciamiento;
    }

    public CreditFinanciamiento(Integer idfinanciamiento, BigDecimal monto, int plazo, BigDecimal cuotaEstimada, BigDecimal cuotaPagar, int tasa) {
        this.idfinanciamiento = idfinanciamiento;
        this.monto = monto;
        this.plazo = plazo;
        this.cuotaEstimada = cuotaEstimada;
        this.cuotaPagar = cuotaPagar;
        this.tasa = tasa;
    }

    public Integer getIdfinanciamiento() {
        return idfinanciamiento;
    }

    public void setIdfinanciamiento(Integer idfinanciamiento) {
        this.idfinanciamiento = idfinanciamiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getCuotaEstimada() {
        return cuotaEstimada;
    }

    public void setCuotaEstimada(BigDecimal cuotaEstimada) {
        this.cuotaEstimada = cuotaEstimada;
    }

    public BigDecimal getCuotaPagar() {
        return cuotaPagar;
    }

    public void setCuotaPagar(BigDecimal cuotaPagar) {
        this.cuotaPagar = cuotaPagar;
    }

    public int getTasa() {
        return tasa;
    }

    public void setTasa(int tasa) {
        this.tasa = tasa;
    }

    public String getDestinoEspecifico() {
        return destinoEspecifico;
    }

    public void setDestinoEspecifico(String destinoEspecifico) {
        this.destinoEspecifico = destinoEspecifico;
    }

    public CreditUtilidad getCreditUtilidadIdutilidad() {
        return creditUtilidadIdutilidad;
    }

    public void setCreditUtilidadIdutilidad(CreditUtilidad creditUtilidadIdutilidad) {
        this.creditUtilidadIdutilidad = creditUtilidadIdutilidad;
    }

    public CreditTipocuota getCreditTipocuotaIdtipocuota() {
        return creditTipocuotaIdtipocuota;
    }

    public void setCreditTipocuotaIdtipocuota(CreditTipocuota creditTipocuotaIdtipocuota) {
        this.creditTipocuotaIdtipocuota = creditTipocuotaIdtipocuota;
    }

    public CreditDestinofondo getCreditDestinofondoIddestinofondo() {
        return creditDestinofondoIddestinofondo;
    }

    public void setCreditDestinofondoIddestinofondo(CreditDestinofondo creditDestinofondoIddestinofondo) {
        this.creditDestinofondoIddestinofondo = creditDestinofondoIddestinofondo;
    }

    public CreditTiposolicitante getCreditTiposolicitanteIdtiposolicitante() {
        return creditTiposolicitanteIdtiposolicitante;
    }

    public void setCreditTiposolicitanteIdtiposolicitante(CreditTiposolicitante creditTiposolicitanteIdtiposolicitante) {
        this.creditTiposolicitanteIdtiposolicitante = creditTiposolicitanteIdtiposolicitante;
    }

    public CreditPagocapital getCreditPagocapitalIdpagocapital() {
        return creditPagocapitalIdpagocapital;
    }

    public void setCreditPagocapitalIdpagocapital(CreditPagocapital creditPagocapitalIdpagocapital) {
        this.creditPagocapitalIdpagocapital = creditPagocapitalIdpagocapital;
    }

    public CreditPagointeres getCreditPagointeresIdpagointeres() {
        return creditPagointeresIdpagointeres;
    }

    public void setCreditPagointeresIdpagointeres(CreditPagointeres creditPagointeresIdpagointeres) {
        this.creditPagointeresIdpagointeres = creditPagointeresIdpagointeres;
    }

    public CreditTipocredito getCreditTipocreditoIdtipocredito() {
        return creditTipocreditoIdtipocredito;
    }

    public void setCreditTipocreditoIdtipocredito(CreditTipocredito creditTipocreditoIdtipocredito) {
        this.creditTipocreditoIdtipocredito = creditTipocreditoIdtipocredito;
    }

    public List<CreditCotizacion> getCreditCotizacionList() {
        return creditCotizacionList;
    }

    public void setCreditCotizacionList(List<CreditCotizacion> creditCotizacionList) {
        this.creditCotizacionList = creditCotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfinanciamiento != null ? idfinanciamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditFinanciamiento)) {
            return false;
        }
        CreditFinanciamiento other = (CreditFinanciamiento) object;
        if ((this.idfinanciamiento == null && other.idfinanciamiento != null) || (this.idfinanciamiento != null && !this.idfinanciamiento.equals(other.idfinanciamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditFinanciamiento[ idfinanciamiento=" + idfinanciamiento + " ]";
    }
    
}
