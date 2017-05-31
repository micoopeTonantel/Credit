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
@Table(name = "credit_utilidad")
@NamedQueries({
    @NamedQuery(name = "CreditUtilidad.findAll", query = "SELECT c FROM CreditUtilidad c")})
public class CreditUtilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idutilidad")
    private Integer idutilidad;
    @Basic(optional = false)
    @Column(name = "descrip_utilidad")
    private String descripUtilidad;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditUtilidadIdutilidad")
    private List<CreditFinanciamiento> creditFinanciamientoList;

    public CreditUtilidad() {
    }

    public CreditUtilidad(Integer idutilidad) {
        this.idutilidad = idutilidad;
    }

    public CreditUtilidad(Integer idutilidad, String descripUtilidad, Character estado) {
        this.idutilidad = idutilidad;
        this.descripUtilidad = descripUtilidad;
        this.estado = estado;
    }

    public Integer getIdutilidad() {
        return idutilidad;
    }

    public void setIdutilidad(Integer idutilidad) {
        this.idutilidad = idutilidad;
    }

    public String getDescripUtilidad() {
        return descripUtilidad;
    }

    public void setDescripUtilidad(String descripUtilidad) {
        this.descripUtilidad = descripUtilidad;
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
        hash += (idutilidad != null ? idutilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditUtilidad)) {
            return false;
        }
        CreditUtilidad other = (CreditUtilidad) object;
        if ((this.idutilidad == null && other.idutilidad != null) || (this.idutilidad != null && !this.idutilidad.equals(other.idutilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditUtilidad[ idutilidad=" + idutilidad + " ]";
    }
    
}
