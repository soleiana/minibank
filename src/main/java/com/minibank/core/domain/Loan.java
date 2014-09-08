package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="LOAN")
public class Loan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Transient
    private Integer loanRequestId;

    @Transient
    private Integer customerId;

    @Column(name="CURRENT_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterestRate;

    @Column(name="CURRENT_INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterest;

    @Column(name="START_DATE", nullable = false)
    private Date startDate;

    @Column(name="END_DATE", nullable = false)
    private Date endDate;
}
