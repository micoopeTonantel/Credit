package com.credit.dao;

import com.credit.dao.CreditCotizacion;
import com.credit.dao.CreditDestinofondo;
import com.credit.dao.CreditPagocapital;
import com.credit.dao.CreditPagointeres;
import com.credit.dao.CreditTipocredito;
import com.credit.dao.CreditTipocuota;
import com.credit.dao.CreditTiposolicitante;
import com.credit.dao.CreditUtilidad;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditFinanciamiento.class)
public class CreditFinanciamiento_ { 

    public static volatile SingularAttribute<CreditFinanciamiento, BigDecimal> cuotaPagar;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditTipocredito> creditTipocreditoIdtipocredito;
    public static volatile SingularAttribute<CreditFinanciamiento, Integer> plazo;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditDestinofondo> creditDestinofondoIddestinofondo;
    public static volatile SingularAttribute<CreditFinanciamiento, Integer> idfinanciamiento;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditPagocapital> creditPagocapitalIdpagocapital;
    public static volatile SingularAttribute<CreditFinanciamiento, Integer> tasa;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditUtilidad> creditUtilidadIdutilidad;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditPagointeres> creditPagointeresIdpagointeres;
    public static volatile SingularAttribute<CreditFinanciamiento, BigDecimal> monto;
    public static volatile ListAttribute<CreditFinanciamiento, CreditCotizacion> creditCotizacionList;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditTipocuota> creditTipocuotaIdtipocuota;
    public static volatile SingularAttribute<CreditFinanciamiento, CreditTiposolicitante> creditTiposolicitanteIdtiposolicitante;
    public static volatile SingularAttribute<CreditFinanciamiento, BigDecimal> cuotaEstimada;
    public static volatile SingularAttribute<CreditFinanciamiento, String> destinoEspecifico;

}