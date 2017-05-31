package com.credit.dao;

import com.credit.dao.Agencia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Integer> idempresa;
    public static volatile SingularAttribute<Empresa, Character> estado;
    public static volatile SingularAttribute<Empresa, Date> fechaModificacion;
    public static volatile ListAttribute<Empresa, Agencia> agenciaList;
    public static volatile SingularAttribute<Empresa, String> nombreEmpresa;

}