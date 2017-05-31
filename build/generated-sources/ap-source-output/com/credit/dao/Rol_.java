package com.credit.dao;

import com.credit.dao.Asignarrol;
import com.credit.dao.Privilegio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile ListAttribute<Rol, Asignarrol> asignarrolList;
    public static volatile SingularAttribute<Rol, Integer> idrol;
    public static volatile SingularAttribute<Rol, Character> estado;
    public static volatile SingularAttribute<Rol, String> formaRol;
    public static volatile ListAttribute<Rol, Privilegio> privilegioList;
    public static volatile SingularAttribute<Rol, String> nombreRol;

}