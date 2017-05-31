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
@Table(name = "credit_tiposolicitante")
@NamedQueries({
    @NamedQuery(name = "CreditTiposolicitante.findAll", query = "SELECT c FROM CreditTiposolicitante c")})
public class CreditTiposolicitante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiposolicitante")
    private Integer idtiposolicitante;
    @Basic(optional = false)
    @Column(name = "descrip_tiposolicitante")
    private String descripTiposolicitante;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditTiposolicitanteIdtiposolicitante")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditTiposolicitante() {
    }

    public CreditTiposolicitante(Integer idtiposolicitante) {
        this.idtiposolicitante = idtiposolicitante;
    }

    public CreditTiposolicitante(Integer idtiposolicitante, String descripTiposolicitante, Character estado) {
        this.idtiposolicitante = idtiposolicitante;
        this.descripTiposolicitante = descripTiposolicitante;
        this.estado = estado;
    }

    public Integer getIdtiposolicitante() {
        return idtiposolicitante;
    }

    public void setIdtiposolicitante(Integer idtiposolicitante) {
        this.idtiposolicitante = idtiposolicitante;
    }

    public String getDescripTiposolicitante() {
        return descripTiposolicitante;
    }

    public void setDescripTiposolicitante(String descripTiposolicitante) {
        this.descripTiposolicitante = descripTiposolicitante;
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
        hash += (idtiposolicitante != null ? idtiposolicitante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTiposolicitante)) {
            return false;
        }
        CreditTiposolicitante other = (CreditTiposolicitante) object;
        if ((this.idtiposolicitante == null && other.idtiposolicitante != null) || (this.idtiposolicitante != null && !this.idtiposolicitante.equals(other.idtiposolicitante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditTiposolicitante[ idtiposolicitante=" + idtiposolicitante + " ]";
    }
    
}
