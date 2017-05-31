package com.credit.dao;

import com.credit.dao.CreditFinanciamiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditPagocapital.class)
public class CreditPagocapital_ { 

    public static volatile SingularAttribute<CreditPagocapital, Character> estado;
    public static volatile ListAttribute<CreditPagocapital, CreditFinanciamiento> creditFinanciamientoList;
    public static volatile SingularAttribute<CreditPagocapital, String> descripPagocapital;
    public static volatile SingularAttribute<CreditPagocapital, Integer> idpagocapital;

}