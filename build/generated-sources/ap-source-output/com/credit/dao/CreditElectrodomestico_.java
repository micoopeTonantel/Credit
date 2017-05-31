package com.credit.dao;

import com.credit.dao.CreditSolicitud;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditElectrodomestico.class)
public class CreditElectrodomestico_ { 

    public static volatile SingularAttribute<CreditElectrodomestico, String> numeroSerie;
    public static volatile SingularAttribute<CreditElectrodomestico, Integer> idelectrodomestico;
    public static volatile SingularAttribute<CreditElectrodomestico, String> marca;
    public static volatile SingularAttribute<CreditElectrodomestico, String> tipo;
    public static volatile SingularAttribute<CreditElectrodomestico, String> estilo;
    public static volatile SingularAttribute<CreditElectrodomestico, BigDecimal> precio;
    public static volatile SingularAttribute<CreditElectrodomestico, String> color;
    public static volatile SingularAttribute<CreditElectrodomestico, BigDecimal> enganche;
    public static volatile SingularAttribute<CreditElectrodomestico, String> vendidoPor;
    public static volatile SingularAttribute<CreditElectrodomestico, String> numeroFactura;
    public static volatile SingularAttribute<CreditElectrodomestico, String> lugarPermanencia;
    public static volatile ListAttribute<CreditElectrodomestico, CreditSolicitud> creditSolicitudList;

}