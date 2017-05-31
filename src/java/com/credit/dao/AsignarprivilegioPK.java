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
public class AsignarprivilegioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "privilegio_idprivilegio")
    private int privilegioIdprivilegio;
    @Basic(optional = false)
    @Column(name = "colaborador_operador")
    private int colaboradorOperador;

    public AsignarprivilegioPK() {
    }

    public AsignarprivilegioPK(int privilegioIdprivilegio, int colaboradorOperador) {
        this.privilegioIdprivilegio = privilegioIdprivilegio;
        this.colaboradorOperador = colaboradorOperador;
    }

    public int getPrivilegioIdprivilegio() {
        return privilegioIdprivilegio;
    }

    public void setPrivilegioIdprivilegio(int privilegioIdprivilegio) {
        this.privilegioIdprivilegio = privilegioIdprivilegio;
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
        hash += (int) privilegioIdprivilegio;
        hash += (int) colaboradorOperador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarprivilegioPK)) {
            return false;
        }
        AsignarprivilegioPK other = (AsignarprivilegioPK) object;
        if (this.privilegioIdprivilegio != other.privilegioIdprivilegio) {
            return false;
        }
        if (this.colaboradorOperador != other.colaboradorOperador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.AsignarprivilegioPK[ privilegioIdprivilegio=" + privilegioIdprivilegio + ", colaboradorOperador=" + colaboradorOperador + " ]";
    }
    
}
