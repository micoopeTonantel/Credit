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
@Table(name = "credit_tipocredito")
@NamedQueries({
    @NamedQuery(name = "CreditTipocredito.findAll", query = "SELECT c FROM CreditTipocredito c")})
public class CreditTipocredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipocredito")
    private Integer idtipocredito;
    @Basic(optional = false)
    @Column(name = "descrip_tipocredito")
    private String descripTipocredito;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditTipocreditoIdtipocredito")
    private List<CreditFinanciamiento> creditFinanciamientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditTipocreditoIdtipocredito")
    private List<CreditRequisito> creditRequisitoList;

    public CreditTipocredito() {
    }

    public CreditTipocredito(Integer idtipocredito) {
        this.idtipocredito = idtipocredito;
    }

    public CreditTipocredito(Integer idtipocredito, String descripTipocredito, Character estado) {
        this.idtipocredito = idtipocredito;
        this.descripTipocredito = descripTipocredito;
        this.estado = estado;
    }

    public Integer getIdtipocredito() {
        return idtipocredito;
    }

    public void setIdtipocredito(Integer idtipocredito) {
        this.idtipocredito = idtipocredito;
    }

    public String getDescripTipocredito() {
        return descripTipocredito;
    }

    public void setDescripTipocredito(String descripTipocredito) {
        this.descripTipocredito = descripTipocredito;
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

    public List<CreditRequisito> getCreditRequisitoList() {
        return creditRequisitoList;
    }

    public void setCreditRequisitoList(List<CreditRequisito> creditRequisitoList) {
        this.creditRequisitoList = creditRequisitoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocredito != null ? idtipocredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTipocredito)) {
            return false;
        }
        CreditTipocredito other = (CreditTipocredito) object;
        if ((this.idtipocredito == null && other.idtipocredito != null) || (this.idtipocredito != null && !this.idtipocredito.equals(other.idtipocredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditTipocredito[ idtipocredito=" + idtipocredito + " ]";
    }
    
}
