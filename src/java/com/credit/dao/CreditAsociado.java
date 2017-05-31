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
@Table(name = "credit_asociado")
@NamedQueries({
    @NamedQuery(name = "CreditAsociado.findAll", query = "SELECT c FROM CreditAsociado c")})
public class CreditAsociado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cif")
    private String cif;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "genero")
    private Character genero;
    @Basic(optional = false)
    @Column(name = "estado_civil")
    private Character estadoCivil;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Basic(optional = false)
    @Column(name = "profesion")
    private String profesion;
    @Basic(optional = false)
    @Column(name = "ocupacion")
    private String ocupacion;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Column(name = "tel_recidencia")
    private String telRecidencia;
    @Column(name = "tel_trabajo")
    private String telTrabajo;
    @Column(name = "actualizacion_ive")
    @Temporal(TemporalType.DATE)
    private Date actualizacionIve;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "tipo_residencia")
    private Character tipoResidencia;
    @Column(name = "tiempo_alquiler")
    private String tiempoAlquiler;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditAsociadoCif")
    private List<CreditSolicitud> creditSolicitudList;
    @JoinColumn(name = "credit_conyugue_idconyugue", referencedColumnName = "idconyugue")
    @ManyToOne
    private CreditConyugue creditConyugueIdconyugue;
    @JoinColumn(name = "credit_persona_dpi", referencedColumnName = "dpi")
    @ManyToOne(optional = false)
    private CreditPersona creditPersonaDpi;

    public CreditAsociado() {
    }

    public CreditAsociado(String cif) {
        this.cif = cif;
    }

    public CreditAsociado(String cif, Date fechaIngreso, Date fechaNacimiento, Character genero, Character estadoCivil, String nacionalidad, String profesion, String ocupacion, String celular, String direccion, Character tipoResidencia) {
        this.cif = cif;
        this.fechaIngreso = fechaIngreso;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.nacionalidad = nacionalidad;
        this.profesion = profesion;
        this.ocupacion = ocupacion;
        this.celular = celular;
        this.direccion = direccion;
        this.tipoResidencia = tipoResidencia;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelRecidencia() {
        return telRecidencia;
    }

    public void setTelRecidencia(String telRecidencia) {
        this.telRecidencia = telRecidencia;
    }

    public String getTelTrabajo() {
        return telTrabajo;
    }

    public void setTelTrabajo(String telTrabajo) {
        this.telTrabajo = telTrabajo;
    }

    public Date getActualizacionIve() {
        return actualizacionIve;
    }

    public void setActualizacionIve(Date actualizacionIve) {
        this.actualizacionIve = actualizacionIve;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Character getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(Character tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public String getTiempoAlquiler() {
        return tiempoAlquiler;
    }

    public void setTiempoAlquiler(String tiempoAlquiler) {
        this.tiempoAlquiler = tiempoAlquiler;
    }

    public List<CreditSolicitud> getCreditSolicitudList() {
        return creditSolicitudList;
    }

    public void setCreditSolicitudList(List<CreditSolicitud> creditSolicitudList) {
        this.creditSolicitudList = creditSolicitudList;
    }

    public CreditConyugue getCreditConyugueIdconyugue() {
        return creditConyugueIdconyugue;
    }

    public void setCreditConyugueIdconyugue(CreditConyugue creditConyugueIdconyugue) {
        this.creditConyugueIdconyugue = creditConyugueIdconyugue;
    }

    public CreditPersona getCreditPersonaDpi() {
        return creditPersonaDpi;
    }

    public void setCreditPersonaDpi(CreditPersona creditPersonaDpi) {
        this.creditPersonaDpi = creditPersonaDpi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cif != null ? cif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditAsociado)) {
            return false;
        }
        CreditAsociado other = (CreditAsociado) object;
        if ((this.cif == null && other.cif != null) || (this.cif != null && !this.cif.equals(other.cif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditAsociado[ cif=" + cif + " ]";
    }
    
}
