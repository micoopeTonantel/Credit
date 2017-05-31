package com.credit.dao;

import com.credit.dao.Agencia;
import com.credit.dao.Asignarprivilegio;
import com.credit.dao.Asignarrol;
import com.credit.dao.CreditCotizacion;
import com.credit.dao.CreditSolicitud;
import com.credit.dao.Departamento;
import com.credit.dao.Puesto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Colaborador.class)
public class Colaborador_ { 

    public static volatile SingularAttribute<Colaborador, String> clave;
    public static volatile SingularAttribute<Colaborador, Character> estado;
    public static volatile SingularAttribute<Colaborador, Date> fechaModificacion;
    public static volatile SingularAttribute<Colaborador, Puesto> puestoIdpuesto;
    public static volatile ListAttribute<Colaborador, Asignarprivilegio> asignarprivilegioList;
    public static volatile ListAttribute<Colaborador, CreditSolicitud> creditSolicitudList;
    public static volatile SingularAttribute<Colaborador, Agencia> agenciaIdagencia;
    public static volatile SingularAttribute<Colaborador, String> nombre;
    public static volatile SingularAttribute<Colaborador, String> token;
    public static volatile ListAttribute<Colaborador, Asignarrol> asignarrolList;
    public static volatile SingularAttribute<Colaborador, String> correo;
    public static volatile ListAttribute<Colaborador, CreditCotizacion> creditCotizacionList;
    public static volatile SingularAttribute<Colaborador, String> usuario;
    public static volatile SingularAttribute<Colaborador, Departamento> departamentoIddepartamento;
    public static volatile SingularAttribute<Colaborador, Integer> operador;
    public static volatile SingularAttribute<Colaborador, Integer> intentos;

}