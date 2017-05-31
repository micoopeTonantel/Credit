package com.credit.dao;

import com.credit.dao.CreditFinanciamiento;
import com.credit.dao.CreditRequisito;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditTipocredito.class)
public class CreditTipocredito_ { 

    public static volatile SingularAttribute<CreditTipocredito, Integer> idtipocredito;
    public static volatile SingularAttribute<CreditTipocredito, String> descripTipocredito;
    public static volatile SingularAttribute<CreditTipocredito, Character> estado;
    public static volatile ListAttribute<CreditTipocredito, CreditFinanciamiento> creditFinanciamientoList;
    public static volatile ListAttribute<CreditTipocredito, CreditRequisito> creditRequisitoList;

}