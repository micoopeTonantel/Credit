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
@Table(name = "credit_conyugue")
@NamedQueries({
    @NamedQuery(name = "CreditConyugue.findAll", query = "SELECT c FROM CreditConyugue c")})
public class CreditConyugue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconyugue")
    private Integer idconyugue;
    @Column(name = "cif")
    private String cif;
    @Basic(optional = false)
    @Column(name = "ocupacion")
    private String ocupacion;
    @Column(name = "empresa_labora")
    private String empresaLabora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private BigDecimal salario;
    @OneToMany(mappedBy = "creditConyugueIdconyugue")
    private List<CreditAsociado> creditAsociadoList;
    @JoinColumn(name = "credit_persona_dpi", referencedColumnName = "dpi")
    @ManyToOne(optional = false)
    private CreditPersona creditPersonaDpi;

    public CreditConyugue() {
    }

    public CreditConyugue(Integer idconyugue) {
        this.idconyugue = idconyugue;
    }

    public CreditConyugue(Integer idconyugue, String ocupacion) {
        this.idconyugue = idconyugue;
        this.ocupacion = ocupacion;
    }

    public Integer getIdconyugue() {
        return idconyugue;
    }

    public void setIdconyugue(Integer idconyugue) {
        this.idconyugue = idconyugue;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEmpresaLabora() {
        return empresaLabora;
    }

    public void setEmpresaLabora(String empresaLabora) {
        this.empresaLabora = empresaLabora;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public List<CreditAsociado> getCreditAsociadoList() {
        return creditAsociadoList;
    }

    public void setCreditAsociadoList(List<CreditAsociado> creditAsociadoList) {
        this.creditAsociadoList = creditAsociadoList;
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
        hash += (idconyugue != null ? idconyugue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditConyugue)) {
            return false;
        }
        CreditConyugue other = (CreditConyugue) object;
        if ((this.idconyugue == null && other.idconyugue != null) || (this.idconyugue != null && !this.idconyugue.equals(other.idconyugue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credit.dao.CreditConyugue[ idconyugue=" + idconyugue + " ]";
    }
    
}
