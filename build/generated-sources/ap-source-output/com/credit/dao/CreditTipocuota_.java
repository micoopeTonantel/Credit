package com.credit.dao;

import com.credit.dao.CreditFinanciamiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditTipocuota.class)
public class CreditTipocuota_ { 

    public static volatile SingularAttribute<CreditTipocuota, Integer> idtipocuota;
    public static volatile SingularAttribute<CreditTipocuota, Character> estado;
    public static volatile ListAttribute<CreditTipocuota, CreditFinanciamiento> creditFinanciamientoList;
    public static volatile SingularAttribute<CreditTipocuota, String> descripTipocuota;

}