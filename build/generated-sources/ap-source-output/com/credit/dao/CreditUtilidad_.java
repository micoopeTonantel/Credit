package com.credit.dao;

import com.credit.dao.CreditFinanciamiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditUtilidad.class)
public class CreditUtilidad_ { 

    public static volatile SingularAttribute<CreditUtilidad, Character> estado;
    public static volatile ListAttribute<CreditUtilidad, CreditFinanciamiento> creditFinanciamientoList;
    public static volatile SingularAttribute<CreditUtilidad, String> descripUtilidad;
    public static volatile SingularAttribute<CreditUtilidad, Integer> idutilidad;

}