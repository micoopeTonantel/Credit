package com.credit.dao;

import com.credit.dao.Colaborador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Puesto.class)
public class Puesto_ { 

    public static volatile SingularAttribute<Puesto, Integer> idpuesto;
    public static volatile SingularAttribute<Puesto, String> nombrePuesto;
    public static volatile SingularAttribute<Puesto, Character> estado;
    public static volatile SingularAttribute<Puesto, Date> fechaModificacion;
    public static volatile ListAttribute<Puesto, Colaborador> colaboradorList;

}