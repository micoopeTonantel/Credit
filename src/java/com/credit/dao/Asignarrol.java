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
@Table(name = "asignarrol")
@NamedQueries({
    @NamedQuery(name = "Asignarrol.findAll", query = "SELECT a FROM Asignarrol a")})
public class Asignarrol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignarrolPK asignarrolPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "colaborador_operador", referencedColumnName = "operador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Colaborador colaborador;
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public Asignarrol() {
    }

    public Asignarrol(AsignarrolPK asignarrolPK) {
        this.asignarrolPK = asignarrolPK;
    }

    public Asignarrol(AsignarrolPK asignarrolPK, Date fecha) {
        this.asignarrolPK = asignarrolPK;
        this.fecha = fecha;
    }

    public Asignarrol(int rolIdrol, int colaboradorOperador) {
        this.asignarrolPK = new AsignarrolPK(rolIdrol, colaboradorOperador);
    }

    public AsignarrolPK getAsignarrolPK() {
        return asignarrolPK;
    }

    public void setAsignarrolPK(AsignarrolPK asignarrolPK) {
        this.asignarrolPK = asignarrolPK;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignarrolPK != null ? asignarrolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignarrol)) {
            return false;
        }
        Asignarrol other = (Asignarrol) object;
        if ((this.asignarrolPK == null && other.asignarrolPK != null) || (this.asignarrolPK != null && !this.asignarrolPK.equals(other.asignarrolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.Asignarrol[ asignarrolPK=" + asignarrolPK + " ]";
    }
    
}
