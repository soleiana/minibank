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
@Table(name="REQUEST_IP")
public class RequestIP
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Column(name="IP", nullable = false)
    private String ip;

    @Column(name="LOAN_ATTEMPTS", nullable = false)
    private Byte loanAttempts;

    @Column(name="LAST_LOAN_ATTEMPT", nullable = false)
    private Date lastLoanAttempt;

    public RequestIP()
    {}

    public RequestIP(String ip, Date lastLoanAttempt, Byte loanAttempts)
    {
        this.ip = ip;
        this.lastLoanAttempt = lastLoanAttempt;
        this.loanAttempts = loanAttempts;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Byte getLoanAttempts()
    {
        return loanAttempts;
    }

    public void setLoanAttempts(Byte loanAttempts)
    {
        this.loanAttempts = loanAttempts;
    }

    public Date getLastLoanAttempt()
    {
        return lastLoanAttempt;
    }

    public void setLastLoanAttempt(Date lastLoanAttempt)
    {
        this.lastLoanAttempt = lastLoanAttempt;
    }
}
