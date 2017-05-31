package com.credit.dao;

import com.credit.dao.Colaborador;
import com.credit.dao.CreditAsociado;
import com.credit.dao.CreditCotizacion;
import com.credit.dao.CreditElectrodomestico;
import com.credit.dao.CreditExclusivo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditSolicitud.class)
public class CreditSolicitud_ { 

    public static volatile SingularAttribute<CreditSolicitud, CreditAsociado> creditAsociadoCif;
    public static volatile SingularAttribute<CreditSolicitud, Date> fecha;
    public static volatile SingularAttribute<CreditSolicitud, CreditElectrodomestico> creditElectrodomesticoIdelectrodomestico;
    public static volatile SingularAttribute<CreditSolicitud, Character> estado;
    public static volatile SingularAttribute<CreditSolicitud, Integer> idsolicitud;
    public static volatile SingularAttribute<CreditSolicitud, CreditCotizacion> creditCotizacionNumero;
    public static volatile SingularAttribute<CreditSolicitud, Colaborador> colaboradorOperador;
    public static volatile SingularAttribute<CreditSolicitud, CreditExclusivo> creditExclusivoIdexclusivo;

}