package com.credit.dao;

import com.credit.dao.Colaborador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, Character> estado;
    public static volatile SingularAttribute<Departamento, String> nombreDepartamento;
    public static volatile SingularAttribute<Departamento, Date> fechaModificacion;
    public static volatile ListAttribute<Departamento, Colaborador> colaboradorList;
    public static volatile SingularAttribute<Departamento, Integer> iddepartamento;

}