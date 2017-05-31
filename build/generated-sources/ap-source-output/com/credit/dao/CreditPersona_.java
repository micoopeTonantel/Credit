package com.credit.dao;

import com.credit.dao.CreditAsociado;
import com.credit.dao.CreditConyugue;
import com.credit.dao.CreditCotizacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditPersona.class)
public class CreditPersona_ { 

    public static volatile SingularAttribute<CreditPersona, String> segundoNombre;
    public static volatile SingularAttribute<CreditPersona, String> apellidoCasada;
    public static volatile SingularAttribute<CreditPersona, String> tercerNombre;
    public static volatile SingularAttribute<CreditPersona, String> primerNombre;
    public static volatile SingularAttribute<CreditPersona, String> primerApellido;
    public static volatile SingularAttribute<CreditPersona, String> mail;
    public static volatile ListAttribute<CreditPersona, CreditCotizacion> creditCotizacionList;
    public static volatile SingularAttribute<CreditPersona, String> nit;
    public static volatile SingularAttribute<CreditPersona, String> segundoApellido;
    public static volatile ListAttribute<CreditPersona, CreditAsociado> creditAsociadoList;
    public static volatile ListAttribute<CreditPersona, CreditConyugue> creditConyugueList;
    public static volatile SingularAttribute<CreditPersona, String> dpi;

}