package com.credit.dao;

import com.credit.dao.Asignarprivilegio;
import com.credit.dao.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Privilegio.class)
public class Privilegio_ { 

    public static volatile SingularAttribute<Privilegio, String> nombrePrivilegio;
    public static volatile SingularAttribute<Privilegio, Character> estado;
    public static volatile SingularAttribute<Privilegio, Integer> idprivilegio;
    public static volatile SingularAttribute<Privilegio, String> formaPrivilegio;
    public static volatile ListAttribute<Privilegio, Asignarprivilegio> asignarprivilegioList;
    public static volatile SingularAttribute<Privilegio, Rol> rolIdrol;

}