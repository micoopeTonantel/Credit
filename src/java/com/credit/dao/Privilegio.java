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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "privilegio")
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p")})
public class Privilegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprivilegio")
    private Integer idprivilegio;
    @Basic(optional = false)
    @Column(name = "nombre_privilegio")
    private String nombrePrivilegio;
    @Basic(optional = false)
    @Column(name = "forma_privilegio")
    private String formaPrivilegio;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegio")
    private List<Asignarprivilegio> asignarprivilegioList;
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false)
    private Rol rolIdrol;

    public Privilegio() {
    }

    public Privilegio(Integer idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public Privilegio(Integer idprivilegio, String nombrePrivilegio, String formaPrivilegio, Character estado) {
        this.idprivilegio = idprivilegio;
        this.nombrePrivilegio = nombrePrivilegio;
        this.formaPrivilegio = formaPrivilegio;
        this.estado = estado;
    }

    public Integer getIdprivilegio() {
        return idprivilegio;
    }

    public void setIdprivilegio(Integer idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public String getNombrePrivilegio() {
        return nombrePrivilegio;
    }

    public void setNombrePrivilegio(String nombrePrivilegio) {
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public String getFormaPrivilegio() {
        return formaPrivilegio;
    }

    public void setFormaPrivilegio(String formaPrivilegio) {
        this.formaPrivilegio = formaPrivilegio;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public List<Asignarprivilegio> getAsignarprivilegioList() {
        return asignarprivilegioList;
    }

    public void setAsignarprivilegioList(List<Asignarprivilegio> asignarprivilegioList) {
        this.asignarprivilegioList = asignarprivilegioList;
    }

    public Rol getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(Rol rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilegio != null ? idprivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.idprivilegio == null && other.idprivilegio != null) || (this.idprivilegio != null && !this.idprivilegio.equals(other.idprivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.Privilegio[ idprivilegio=" + idprivilegio + " ]";
    }
    
}
