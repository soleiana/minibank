package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name="BANK_PARAMS")
public class BankParams
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Column(name="MAX_LOAN_AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal maxLoanAmount;

    @Column(name="BASE_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal baseInterestRate;

    @Column(name="INTEREST_RATE_FACTOR", precision = 10, scale = 2, nullable = false)
    private BigDecimal interestRateFactor;

    @Column(name="MAX_LOAN_ATTEMPTS", nullable = false)
    private Byte maxLoanAttempts;

    @Column(name="RISK_TIME_START", nullable = false)
    private Time riskTimeStart;

    @Column(name="RISK_TIME_END", nullable = false)
    private Time riskTimeEnd;

}
