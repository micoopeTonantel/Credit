package com.credit.dao;

import com.credit.dao.Colaborador;
import com.credit.dao.Empresa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(Agencia.class)
public class Agencia_ { 

    public static volatile SingularAttribute<Agencia, Character> estado;
    public static volatile SingularAttribute<Agencia, Date> fechaModificacion;
    public static volatile ListAttribute<Agencia, Colaborador> colaboradorList;
    public static volatile SingularAttribute<Agencia, Integer> idagencia;
    public static volatile SingularAttribute<Agencia, Empresa> empresaIdempresa;
    public static volatile SingularAttribute<Agencia, String> nombreAgencia;

}