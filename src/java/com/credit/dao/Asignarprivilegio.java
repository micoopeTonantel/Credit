/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "asignarprivilegio")
@NamedQueries({
    @NamedQuery(name = "Asignarprivilegio.findAll", query = "SELECT a FROM Asignarprivilegio a")})
public class Asignarprivilegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignarprivilegioPK asignarprivilegioPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "colaborador_operador", referencedColumnName = "operador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Colaborador colaborador;
    @JoinColumn(name = "privilegio_idprivilegio", referencedColumnName = "idprivilegio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Privilegio privilegio;

    public Asignarprivilegio() {
    }

    public Asignarprivilegio(AsignarprivilegioPK asignarprivilegioPK) {
        this.asignarprivilegioPK = asignarprivilegioPK;
    }

    public Asignarprivilegio(AsignarprivilegioPK asignarprivilegioPK, Date fecha) {
        this.asignarprivilegioPK = asignarprivilegioPK;
        this.fecha = fecha;
    }

    public Asignarprivilegio(int privilegioIdprivilegio, int colaboradorOperador) {
        this.asignarprivilegioPK = new AsignarprivilegioPK(privilegioIdprivilegio, colaboradorOperador);
    }

    public AsignarprivilegioPK getAsignarprivilegioPK() {
        return asignarprivilegioPK;
    }

    public void setAsignarprivilegioPK(AsignarprivilegioPK asignarprivilegioPK) {
        this.asignarprivilegioPK = asignarprivilegioPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignarprivilegioPK != null ? asignarprivilegioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignarprivilegio)) {
            return false;
        }
        Asignarprivilegio other = (Asignarprivilegio) object;
        if ((this.asignarprivilegioPK == null && other.asignarprivilegioPK != null) || (this.asignarprivilegioPK != null && !this.asignarprivilegioPK.equals(other.asignarprivilegioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.Asignarprivilegio[ asignarprivilegioPK=" + asignarprivilegioPK + " ]";
    }
    
}
