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
@Table(name = "credit_tipocuota")
@NamedQueries({
    @NamedQuery(name = "CreditTipocuota.findAll", query = "SELECT c FROM CreditTipocuota c")})
public class CreditTipocuota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipocuota")
    private Integer idtipocuota;
    @Basic(optional = false)
    @Column(name = "descrip_tipocuota")
    private String descripTipocuota;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditTipocuotaIdtipocuota")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditTipocuota() {
    }

    public CreditTipocuota(Integer idtipocuota) {
        this.idtipocuota = idtipocuota;
    }

    public CreditTipocuota(Integer idtipocuota, String descripTipocuota, Character estado) {
        this.idtipocuota = idtipocuota;
        this.descripTipocuota = descripTipocuota;
        this.estado = estado;
    }

    public Integer getIdtipocuota() {
        return idtipocuota;
    }

    public void setIdtipocuota(Integer idtipocuota) {
        this.idtipocuota = idtipocuota;
    }

    public String getDescripTipocuota() {
        return descripTipocuota;
    }

    public void setDescripTipocuota(String descripTipocuota) {
        this.descripTipocuota = descripTipocuota;
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
        hash += (idtipocuota != null ? idtipocuota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTipocuota)) {
            return false;
        }
        CreditTipocuota other = (CreditTipocuota) object;
        if ((this.idtipocuota == null && other.idtipocuota != null) || (this.idtipocuota != null && !this.idtipocuota.equals(other.idtipocuota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditTipocuota[ idtipocuota=" + idtipocuota + " ]";
    }
    
}
