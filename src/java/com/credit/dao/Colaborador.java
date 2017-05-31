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
@Table(name = "colaborador")
@NamedQueries({
    @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c")})
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "operador")
    private Integer operador;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "intentos")
    private int intentos;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "token")
    private String token;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaboradorOperador")
    private List<CreditSolicitud> creditSolicitudList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaborador")
    private List<Asignarprivilegio> asignarprivilegioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaboradorOperador")
    private List<CreditCotizacion> creditCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaborador")
    private List<Asignarrol> asignarrolList;
    @JoinColumn(name = "agencia_idagencia", referencedColumnName = "idagencia")
    @ManyToOne(optional = false)
    private Agencia agenciaIdagencia;
    @JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamento departamentoIddepartamento;
    @JoinColumn(name = "puesto_idpuesto", referencedColumnName = "idpuesto")
    @ManyToOne(optional = false)
    private Puesto puestoIdpuesto;

    public Colaborador() {
    }

    public Colaborador(Integer operador) {
        this.operador = operador;
    }

    public Colaborador(Integer operador, String usuario, String clave, String nombre, Character estado, int intentos, Date fechaModificacion) {
        this.operador = operador;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.estado = estado;
        this.intentos = intentos;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getOperador() {
        return operador;
    }

    public void setOperador(Integer operador) {
        this.operador = operador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<CreditSolicitud> getCreditSolicitudList() {
        return creditSolicitudList;
    }

    public void setCreditSolicitudList(List<CreditSolicitud> creditSolicitudList) {
        this.creditSolicitudList = creditSolicitudList;
    }

    public List<Asignarprivilegio> getAsignarprivilegioList() {
        return asignarprivilegioList;
    }

    public void setAsignarprivilegioList(List<Asignarprivilegio> asignarprivilegioList) {
        this.asignarprivilegioList = asignarprivilegioList;
    }

    public List<CreditCotizacion> getCreditCotizacionList() {
        return creditCotizacionList;
    }

    public void setCreditCotizacionList(List<CreditCotizacion> creditCotizacionList) {
        this.creditCotizacionList = creditCotizacionList;
    }

    public List<Asignarrol> getAsignarrolList() {
        return asignarrolList;
    }

    public void setAsignarrolList(List<Asignarrol> asignarrolList) {
        this.asignarrolList = asignarrolList;
    }

    public Agencia getAgenciaIdagencia() {
        return agenciaIdagencia;
    }

    public void setAgenciaIdagencia(Agencia agenciaIdagencia) {
        this.agenciaIdagencia = agenciaIdagencia;
    }

    public Departamento getDepartamentoIddepartamento() {
        return departamentoIddepartamento;
    }

    public void setDepartamentoIddepartamento(Departamento departamentoIddepartamento) {
        this.departamentoIddepartamento = departamentoIddepartamento;
    }

    public Puesto getPuestoIdpuesto() {
        return puestoIdpuesto;
    }

    public void setPuestoIdpuesto(Puesto puestoIdpuesto) {
        this.puestoIdpuesto = puestoIdpuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operador != null ? operador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colaborador)) {
            return false;
        }
        Colaborador other = (Colaborador) object;
        if ((this.operador == null && other.operador != null) || (this.operador != null && !this.operador.equals(other.operador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.Colaborador[ operador=" + operador + " ]";
    }
    
}
