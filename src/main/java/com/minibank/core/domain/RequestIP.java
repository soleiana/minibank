package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="REQUEST_IP")
public class RequestIP
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name= "REQUEST_IP_ID")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.REMOVE})
    private List<LoanRequest> loanRequests = new ArrayList<LoanRequest>();

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

    public List<LoanRequest> getLoanRequests()
    {
        return loanRequests;
    }

    public void setLoanRequests(List<LoanRequest> loanRequests)
    {
        this.loanRequests = loanRequests;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestIP requestIP = (RequestIP) o;

        if (id != null ? !id.equals(requestIP.id) : requestIP.id != null) return false;
        if (ip != null ? !ip.equals(requestIP.ip) : requestIP.ip != null) return false;
        if (lastLoanAttempt != null ? !lastLoanAttempt.equals(requestIP.lastLoanAttempt) : requestIP.lastLoanAttempt != null)
            return false;
        if (loanAttempts != null ? !loanAttempts.equals(requestIP.loanAttempts) : requestIP.loanAttempts != null)
            return false;
        if (loanRequests != null ? !loanRequests.equals(requestIP.loanRequests) : requestIP.loanRequests != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loanRequests != null ? loanRequests.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (loanAttempts != null ? loanAttempts.hashCode() : 0);
        result = 31 * result + (lastLoanAttempt != null ? lastLoanAttempt.hashCode() : 0);
        return result;
    }
}
