/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rgalicia
 */
@Entity
@Table(name = "agencia")
@NamedQueries({
    @NamedQuery(name = "Agencia.findAll", query = "SELECT a FROM Agencia a")})
public class Agencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagencia")
    private Integer idagencia;
    @Basic(optional = false)
    @Column(name = "nombre_agencia")
    private String nombreAgencia;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "empresa_idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa empresaIdempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenciaIdagencia")
    private List<Colaborador> colaboradorList;

    public Agencia() {
    }

    public Agencia(Integer idagencia) {
        this.idagencia = idagencia;
    }

    public Agencia(Integer idagencia, String nombreAgencia, Character estado, Date fechaModificacion) {
        this.idagencia = idagencia;
        this.nombreAgencia = nombreAgencia;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Integer idagencia) {
        this.idagencia = idagencia;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Empresa getEmpresaIdempresa() {
        return empresaIdempresa;
    }

    public void setEmpresaIdempresa(Empresa empresaIdempresa) {
        this.empresaIdempresa = empresaIdempresa;
    }

    public List<Colaborador> getColaboradorList() {
        return colaboradorList;
    }

    public void setColaboradorList(List<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagencia != null ? idagencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencia)) {
            return false;
        }
        Agencia other = (Agencia) object;
        if ((this.idagencia == null && other.idagencia != null) || (this.idagencia != null && !this.idagencia.equals(other.idagencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.Agencia[ idagencia=" + idagencia + " ]";
    }
    
}
