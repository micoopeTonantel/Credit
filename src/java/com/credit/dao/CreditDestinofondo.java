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
@Table(name = "credit_destinofondo")
@NamedQueries({
    @NamedQuery(name = "CreditDestinofondo.findAll", query = "SELECT c FROM CreditDestinofondo c")})
public class CreditDestinofondo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddestinofondo")
    private Integer iddestinofondo;
    @Basic(optional = false)
    @Column(name = "descrip_destinofondo")
    private String descripDestinofondo;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditDestinofondoIddestinofondo")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditDestinofondo() {
    }

    public CreditDestinofondo(Integer iddestinofondo) {
        this.iddestinofondo = iddestinofondo;
    }

    public CreditDestinofondo(Integer iddestinofondo, String descripDestinofondo, Character estado) {
        this.iddestinofondo = iddestinofondo;
        this.descripDestinofondo = descripDestinofondo;
        this.estado = estado;
    }

    public Integer getIddestinofondo() {
        return iddestinofondo;
    }

    public void setIddestinofondo(Integer iddestinofondo) {
        this.iddestinofondo = iddestinofondo;
    }

    public String getDescripDestinofondo() {
        return descripDestinofondo;
    }

    public void setDescripDestinofondo(String descripDestinofondo) {
        this.descripDestinofondo = descripDestinofondo;
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
        hash += (iddestinofondo != null ? iddestinofondo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditDestinofondo)) {
            return false;
        }
        CreditDestinofondo other = (CreditDestinofondo) object;
        if ((this.iddestinofondo == null && other.iddestinofondo != null) || (this.iddestinofondo != null && !this.iddestinofondo.equals(other.iddestinofondo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditDestinofondo[ iddestinofondo=" + iddestinofondo + " ]";
    }
    
}
