package com.credit.dao;

import com.credit.dao.CreditSolicitud;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-31T15:07:18")
@StaticMetamodel(CreditExclusivo.class)
public class CreditExclusivo_ { 

    public static volatile SingularAttribute<CreditExclusivo, String> numeroCredito;
    public static volatile SingularAttribute<CreditExclusivo, Date> fechaVencimiento;
    public static volatile SingularAttribute<CreditExclusivo, Integer> idexclusivo;
    public static volatile SingularAttribute<CreditExclusivo, Integer> porcentajeCancelado;
    public static volatile SingularAttribute<CreditExclusivo, BigDecimal> saldoActual;
    public static volatile ListAttribute<CreditExclusivo, CreditSolicitud> creditSolicitudList;

}