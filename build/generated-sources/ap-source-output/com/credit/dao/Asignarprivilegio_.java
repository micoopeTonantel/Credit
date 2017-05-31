package com.credit.dao;

import com.credit.dao.AsignarprivilegioPK;
import com.credit.dao.Colaborador;
import com.credit.dao.Privilegio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Asignarprivilegio.class)
public class Asignarprivilegio_ { 

    public static volatile SingularAttribute<Asignarprivilegio, Colaborador> colaborador;
    public static volatile SingularAttribute<Asignarprivilegio, Date> fecha;
    public static volatile SingularAttribute<Asignarprivilegio, Privilegio> privilegio;
    public static volatile SingularAttribute<Asignarprivilegio, AsignarprivilegioPK> asignarprivilegioPK;

}