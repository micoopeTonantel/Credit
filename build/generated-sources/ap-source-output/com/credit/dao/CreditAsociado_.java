package com.credit.dao;

import com.credit.dao.CreditConyugue;
import com.credit.dao.CreditPersona;
import com.credit.dao.CreditSolicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditAsociado.class)
public class CreditAsociado_ { 

    public static volatile SingularAttribute<CreditAsociado, String> cif;
    public static volatile SingularAttribute<CreditAsociado, String> telRecidencia;
    public static volatile SingularAttribute<CreditAsociado, Date> fechaNacimiento;
    public static volatile SingularAttribute<CreditAsociado, String> direccion;
    public static volatile SingularAttribute<CreditAsociado, Character> estadoCivil;
    public static volatile SingularAttribute<CreditAsociado, String> ocupacion;
    public static volatile SingularAttribute<CreditAsociado, Character> tipoResidencia;
    public static volatile ListAttribute<CreditAsociado, CreditSolicitud> creditSolicitudList;
    public static volatile SingularAttribute<CreditAsociado, String> nacionalidad;
    public static volatile SingularAttribute<CreditAsociado, Date> actualizacionIve;
    public static volatile SingularAttribute<CreditAsociado, CreditConyugue> creditConyugueIdconyugue;
    public static volatile SingularAttribute<CreditAsociado, Date> fechaIngreso;
    public static volatile SingularAttribute<CreditAsociado, String> telTrabajo;
    public static volatile SingularAttribute<CreditAsociado, Character> genero;
    public static volatile SingularAttribute<CreditAsociado, String> celular;
    public static volatile SingularAttribute<CreditAsociado, String> profesion;
    public static volatile SingularAttribute<CreditAsociado, String> tiempoAlquiler;
    public static volatile SingularAttribute<CreditAsociado, CreditPersona> creditPersonaDpi;

}