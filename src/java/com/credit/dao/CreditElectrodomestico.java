/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credit.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "credit_electrodomestico")
@NamedQueries({
    @NamedQuery(name = "CreditElectrodomestico.findAll", query = "SELECT c FROM CreditElectrodomestico c")})
public class CreditElectrodomestico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idelectrodomestico")
    private Integer idelectrodomestico;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Column(name = "estilo")
    private String estilo;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "numero_serie")
    private String numeroSerie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @Column(name = "enganche")
    private BigDecimal enganche;
    @Basic(optional = false)
    @Column(name = "lugar_permanencia")
    private String lugarPermanencia;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Column(name = "vendido_por")
    private String vendidoPor;
    @OneToMany(mappedBy = "creditElectrodomesticoIdelectrodomestico")
    private List<CreditSolicitud> creditSolicitudList;

    public CreditElectrodomestico() {
    }

    public CreditElectrodomestico(Integer idelectrodomestico) {
        this.idelectrodomestico = idelectrodomestico;
    }

    public CreditElectrodomestico(Integer idelectrodomestico, String tipo, String marca, String color, String numeroSerie, BigDecimal precio, BigDecimal enganche, String lugarPermanencia, String numeroFactura, String vendidoPor) {
        this.idelectrodomestico = idelectrodomestico;
        this.tipo = tipo;
        this.marca = marca;
        this.color = color;
        this.numeroSerie = numeroSerie;
        this.precio = precio;
        this.enganche = enganche;
        this.lugarPermanencia = lugarPermanencia;
        this.numeroFactura = numeroFactura;
        this.vendidoPor = vendidoPor;
    }

    public Integer getIdelectrodomestico() {
        return idelectrodomestico;
    }

    public void setIdelectrodomestico(Integer idelectrodomestico) {
        this.idelectrodomestico = idelectrodomestico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getEnganche() {
        return enganche;
    }

    public void setEnganche(BigDecimal enganche) {
        this.enganche = enganche;
    }

    public String getLugarPermanencia() {
        return lugarPermanencia;
    }

    public void setLugarPermanencia(String lugarPermanencia) {
        this.lugarPermanencia = lugarPermanencia;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getVendidoPor() {
        return vendidoPor;
    }

    public void setVendidoPor(String vendidoPor) {
        this.vendidoPor = vendidoPor;
    }

    public List<CreditSolicitud> getCreditSolicitudList() {
        return creditSolicitudList;
    }

    public void setCreditSolicitudList(List<CreditSolicitud> creditSolicitudList) {
        this.creditSolicitudList = creditSolicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idelectrodomestico != null ? idelectrodomestico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditElectrodomestico)) {
            return false;
        }
        CreditElectrodomestico other = (CreditElectrodomestico) object;
        if ((this.idelectrodomestico == null && other.idelectrodomestico != null) || (this.idelectrodomestico != null && !this.idelectrodomestico.equals(other.idelectrodomestico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditElectrodomestico[ idelectrodomestico=" + idelectrodomestico + " ]";
    }
    
}
