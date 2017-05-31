/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "credit_requisito")
@NamedQueries({
    @NamedQuery(name = "CreditRequisito.findAll", query = "SELECT c FROM CreditRequisito c")})
public class CreditRequisito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrequisito")
    private Integer idrequisito;
    @Basic(optional = false)
    @Column(name = "descrip_requisito")
    private String descripRequisito;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "credit_tipocredito_idtipocredito", referencedColumnName = "idtipocredito")
    @ManyToOne(optional = false)
    private CreditTipocredito creditTipocreditoIdtipocredito;

    public CreditRequisito() {
    }

    public CreditRequisito(Integer idrequisito) {
        this.idrequisito = idrequisito;
    }

    public CreditRequisito(Integer idrequisito, String descripRequisito, Character estado) {
        this.idrequisito = idrequisito;
        this.descripRequisito = descripRequisito;
        this.estado = estado;
    }

    public Integer getIdrequisito() {
        return idrequisito;
    }

    public void setIdrequisito(Integer idrequisito) {
        this.idrequisito = idrequisito;
    }

    public String getDescripRequisito() {
        return descripRequisito;
    }

    public void setDescripRequisito(String descripRequisito) {
        this.descripRequisito = descripRequisito;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public CreditTipocredito getCreditTipocreditoIdtipocredito() {
        return creditTipocreditoIdtipocredito;
    }

    public void setCreditTipocreditoIdtipocredito(CreditTipocredito creditTipocreditoIdtipocredito) {
        this.creditTipocreditoIdtipocredito = creditTipocreditoIdtipocredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrequisito != null ? idrequisito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditRequisito)) {
            return false;
        }
        CreditRequisito other = (CreditRequisito) object;
        if ((this.idrequisito == null && other.idrequisito != null) || (this.idrequisito != null && !this.idrequisito.equals(other.idrequisito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditRequisito[ idrequisito=" + idrequisito + " ]";
    }
    
}
