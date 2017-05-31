package com.credit.dao;

import com.credit.dao.AsignarrolPK;
import com.credit.dao.Colaborador;
import com.credit.dao.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Asignarrol.class)
public class Asignarrol_ { 

    public static volatile SingularAttribute<Asignarrol, Colaborador> colaborador;
    public static volatile SingularAttribute<Asignarrol, Date> fecha;
    public static volatile SingularAttribute<Asignarrol, Rol> rol;
    public static volatile SingularAttribute<Asignarrol, AsignarrolPK> asignarrolPK;

}