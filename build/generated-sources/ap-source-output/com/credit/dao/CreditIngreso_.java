package com.credit.dao;

import com.credit.dao.CreditCotizacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditIngreso.class)
public class CreditIngreso_ { 

    public static volatile SingularAttribute<CreditIngreso, String> frecuencia;
    public static volatile ListAttribute<CreditIngreso, CreditCotizacion> creditCotizacionList;
    public static volatile SingularAttribute<CreditIngreso, String> cargo;
    public static volatile SingularAttribute<CreditIngreso, Integer> idingreso;
    public static volatile SingularAttribute<CreditIngreso, String> proveniencia;

}