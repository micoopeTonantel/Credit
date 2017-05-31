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
@Table(name = "credit_persona")
@NamedQueries({
    @NamedQuery(name = "CreditPersona.findAll", query = "SELECT c FROM CreditPersona c")})
public class CreditPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dpi")
    private String dpi;
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "tercer_nombre")
    private String tercerNombre;
    @Basic(optional = false)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "apellido_casada")
    private String apellidoCasada;
    @Column(name = "mail")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditPersonaDpi")
    private List<CreditAsociado> creditAsociadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditPersonaDpi")
    private List<CreditCotizacion> creditCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditPersonaDpi")
    private List<CreditConyugue> creditConyugueList;

    public CreditPersona() {
    }

    public CreditPersona(String dpi) {
        this.dpi = dpi;
    }

    public CreditPersona(String dpi, String primerNombre, String primerApellido) {
        this.dpi = dpi;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTercerNombre() {
        return tercerNombre;
    }

    public void setTercerNombre(String tercerNombre) {
        this.tercerNombre = tercerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<CreditAsociado> getCreditAsociadoList() {
        return creditAsociadoList;
    }

    public void setCreditAsociadoList(List<CreditAsociado> creditAsociadoList) {
        this.creditAsociadoList = creditAsociadoList;
    }

    public List<CreditCotizacion> getCreditCotizacionList() {
        return creditCotizacionList;
    }

    public void setCreditCotizacionList(List<CreditCotizacion> creditCotizacionList) {
        this.creditCotizacionList = creditCotizacionList;
    }

    public List<CreditConyugue> getCreditConyugueList() {
        return creditConyugueList;
    }

    public void setCreditConyugueList(List<CreditConyugue> creditConyugueList) {
        this.creditConyugueList = creditConyugueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dpi != null ? dpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditPersona)) {
            return false;
        }
        CreditPersona other = (CreditPersona) object;
        if ((this.dpi == null && other.dpi != null) || (this.dpi != null && !this.dpi.equals(other.dpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditPersona[ dpi=" + dpi + " ]";
    }
    
}
