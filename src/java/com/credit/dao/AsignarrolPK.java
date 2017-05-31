/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Rgalicia
 */
@Embeddable
public class AsignarrolPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "rol_idrol")
    private int rolIdrol;
    @Basic(optional = false)
    @Column(name = "colaborador_operador")
    private int colaboradorOperador;

    public AsignarrolPK() {
    }

    public AsignarrolPK(int rolIdrol, int colaboradorOperador) {
        this.rolIdrol = rolIdrol;
        this.colaboradorOperador = colaboradorOperador;
    }

    public int getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(int rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    public int getColaboradorOperador() {
        return colaboradorOperador;
    }

    public void setColaboradorOperador(int colaboradorOperador) {
        this.colaboradorOperador = colaboradorOperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rolIdrol;
        hash += (int) colaboradorOperador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarrolPK)) {
            return false;
        }
        AsignarrolPK other = (AsignarrolPK) object;
        if (this.rolIdrol != other.rolIdrol) {
            return false;
        }
        if (this.colaboradorOperador != other.colaboradorOperador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.AsignarrolPK[ rolIdrol=" + rolIdrol + ", colaboradorOperador=" + colaboradorOperador + " ]";
    }
    
}
