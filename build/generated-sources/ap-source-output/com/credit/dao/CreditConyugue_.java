package com.credit.dao;

import com.credit.dao.CreditAsociado;
import com.credit.dao.CreditPersona;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditConyugue.class)
public class CreditConyugue_ { 

    public static volatile SingularAttribute<CreditConyugue, String> cif;
    public static volatile SingularAttribute<CreditConyugue, Integer> idconyugue;
    public static volatile SingularAttribute<CreditConyugue, BigDecimal> salario;
    public static volatile SingularAttribute<CreditConyugue, String> ocupacion;
    public static volatile SingularAttribute<CreditConyugue, String> empresaLabora;
    public static volatile ListAttribute<CreditConyugue, CreditAsociado> creditAsociadoList;
    public static volatile SingularAttribute<CreditConyugue, CreditPersona> creditPersonaDpi;

}