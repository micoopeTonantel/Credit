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
@Table(name = "credit_pagocapital")
@NamedQueries({
    @NamedQuery(name = "CreditPagocapital.findAll", query = "SELECT c FROM CreditPagocapital c")})
public class CreditPagocapital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagocapital")
    private Integer idpagocapital;
    @Basic(optional = false)
    @Column(name = "descrip_pagocapital")
    private String descripPagocapital;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditPagocapitalIdpagocapital")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditPagocapital() {
    }

    public CreditPagocapital(Integer idpagocapital) {
        this.idpagocapital = idpagocapital;
    }

    public CreditPagocapital(Integer idpagocapital, String descripPagocapital, Character estado) {
        this.idpagocapital = idpagocapital;
        this.descripPagocapital = descripPagocapital;
        this.estado = estado;
    }

    public Integer getIdpagocapital() {
        return idpagocapital;
    }

    public void setIdpagocapital(Integer idpagocapital) {
        this.idpagocapital = idpagocapital;
    }

    public String getDescripPagocapital() {
        return descripPagocapital;
    }

    public void setDescripPagocapital(String descripPagocapital) {
        this.descripPagocapital = descripPagocapital;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public List<CreditFinanciamiento> getCreditFinanciamientoList() {
        return creditFinanciamientoList;
    }

    public void setCreditFinanciamientoList(List<CreditFinanciamiento> creditFinanciamientoList) {
        this.creditFinanciamientoList = creditFinanciamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagocapital != null ? idpagocapital.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditPagocapital)) {
            return false;
        }
        CreditPagocapital other = (CreditPagocapital) object;
        if ((this.idpagocapital == null && other.idpagocapital != null) || (this.idpagocapital != null && !this.idpagocapital.equals(other.idpagocapital))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditPagocapital[ idpagocapital=" + idpagocapital + " ]";
    }
    
}
