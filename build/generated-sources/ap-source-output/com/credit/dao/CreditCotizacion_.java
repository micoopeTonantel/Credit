package com.credit.dao;

import com.credit.dao.Colaborador;
import com.credit.dao.CreditFinanciamiento;
import com.credit.dao.CreditIngreso;
import com.credit.dao.CreditPersona;
import com.credit.dao.CreditSolicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditCotizacion.class)
public class CreditCotizacion_ { 

    public static volatile SingularAttribute<CreditCotizacion, Date> fecha;
    public static volatile SingularAttribute<CreditCotizacion, Character> estado;
    public static volatile SingularAttribute<CreditCotizacion, Integer> numero;
    public static volatile SingularAttribute<CreditCotizacion, CreditIngreso> creditIngresoIdingreso;
    public static volatile ListAttribute<CreditCotizacion, CreditSolicitud> creditSolicitudList;
    public static volatile SingularAttribute<CreditCotizacion, CreditFinanciamiento> creditFinanciamientoIdfinanciamiento;
    public static volatile SingularAttribute<CreditCotizacion, Colaborador> colaboradorOperador;
    public static volatile SingularAttribute<CreditCotizacion, CreditPersona> creditPersonaDpi;

}