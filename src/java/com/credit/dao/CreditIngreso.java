/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "credit_ingreso")
@NamedQueries({
    @NamedQuery(name = "CreditIngreso.findAll", query = "SELECT c FROM CreditIngreso c")})
public class CreditIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idingreso")
    private Integer idingreso;
    @Basic(optional = false)
    @Column(name = "proveniencia")
    private String proveniencia;
    @Basic(optional = false)
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @Column(name = "frecuencia")
    private String frecuencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditIngresoIdingreso")
    private List<CreditCotizacion> creditCotizacionList;

    public CreditIngreso() {
    }

    public CreditIngreso(Integer idingreso) {
        this.idingreso = idingreso;
    }

    public CreditIngreso(Integer idingreso, String proveniencia, String cargo, String frecuencia) {
        this.idingreso = idingreso;
        this.proveniencia = proveniencia;
        this.cargo = cargo;
        this.frecuencia = frecuencia;
    }

    public Integer getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(Integer idingreso) {
        this.idingreso = idingreso;
    }

    public String getProveniencia() {
        return proveniencia;
    }

    public void setProveniencia(String proveniencia) {
        this.proveniencia = proveniencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
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
        hash += (idingreso != null ? idingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditIngreso)) {
            return false;
        }
        CreditIngreso other = (CreditIngreso) object;
        if ((this.idingreso == null && other.idingreso != null) || (this.idingreso != null && !this.idingreso.equals(other.idingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditIngreso[ idingreso=" + idingreso + " ]";
    }
    
}
