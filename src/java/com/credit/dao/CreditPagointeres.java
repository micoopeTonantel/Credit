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
@Table(name = "credit_pagointeres")
@NamedQueries({
    @NamedQuery(name = "CreditPagointeres.findAll", query = "SELECT c FROM CreditPagointeres c")})
public class CreditPagointeres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagointeres")
    private Integer idpagointeres;
    @Basic(optional = false)
    @Column(name = "descrip_pagointeres")
    private String descripPagointeres;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditPagointeresIdpagointeres")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditPagointeres() {
    }

    public CreditPagointeres(Integer idpagointeres) {
        this.idpagointeres = idpagointeres;
    }

    public CreditPagointeres(Integer idpagointeres, String descripPagointeres, Character estado) {
        this.idpagointeres = idpagointeres;
        this.descripPagointeres = descripPagointeres;
        this.estado = estado;
    }

    public Integer getIdpagointeres() {
        return idpagointeres;
    }

    public void setIdpagointeres(Integer idpagointeres) {
        this.idpagointeres = idpagointeres;
    }

    public String getDescripPagointeres() {
        return descripPagointeres;
    }

    public void setDescripPagointeres(String descripPagointeres) {
        this.descripPagointeres = descripPagointeres;
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
        hash += (idpagointeres != null ? idpagointeres.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditPagointeres)) {
            return false;
        }
        CreditPagointeres other = (CreditPagointeres) object;
        if ((this.idpagointeres == null && other.idpagointeres != null) || (this.idpagointeres != null && !this.idpagointeres.equals(other.idpagointeres))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditPagointeres[ idpagointeres=" + idpagointeres + " ]";
    }
    
}
