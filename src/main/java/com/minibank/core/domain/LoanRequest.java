package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */

import com.minibank.core.events.loans.LoanRequestDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="LOAN_REQUEST")
public class LoanRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="LOAN_ID", unique = true, nullable = true)
    private Loan loan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="REQUEST_IP_ID", nullable = false)
    private RequestIP requestIP;

    @Column(name="SUBMISSION_DATE", nullable = false)
    private Date submissionDate;

    @Column(name="SUBMISSION_TIME", nullable = false)
    private Time submissionTime;

    @Column(name="TERM", nullable = false)
    private Integer term;

    @Column(name="AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name="STATUS", nullable = false)
    @Enumerated (EnumType.STRING)
    private LoanRequestStatus status;

    public LoanRequest()
    {}

    public LoanRequest(Loan loan, Customer customer, RequestIP requestIP,
                       Date submissionDate, Time submissionTime, Integer term,
                       BigDecimal amount, LoanRequestStatus status)
    {
        this.loan = loan;
        this.customer = customer;
        this.requestIP = requestIP;
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
        this.term = term;
        this.amount = amount;
        this.status = status;
    }

    public LoanRequestDetails toLoanRequestDetails()
    {
        LoanRequestDetails loanRequestDetails = new LoanRequestDetails();

        loanRequestDetails.setId(this.id);
        loanRequestDetails.setLoanId(this.loan.getId());
        loanRequestDetails.setCustomerId(this.customer.getId());
        loanRequestDetails.setSubmissionDate(this.submissionDate);
        loanRequestDetails.setSubmissionTime(this.submissionTime);
        loanRequestDetails.setAmount(this.amount);
        loanRequestDetails.setRequestIP(this.requestIP.getIP().toString());
        loanRequestDetails.setTerm(this.term);
        loanRequestDetails.setStatus(this.status.toString());

        return  loanRequestDetails;
    }

    public static LoanRequest fromLoanRequestDetails(LoanRequestDetails requestDetails)
    {
        RequestIP requestIP = new RequestIP(requestDetails.getRequestIP());
        Customer customer = new Customer(requestDetails.getCustomerId());

        LoanRequest loanRequest = new LoanRequest();

        loanRequest.setRequestIP(requestIP);
        loanRequest.setCustomer(customer);
        loanRequest.setSubmissionDate(requestDetails.getSubmissionDate());
        loanRequest.setSubmissionTime(requestDetails.getSubmissionTime());
        loanRequest.setAmount(requestDetails.getAmount());
        loanRequest.setTerm(requestDetails.getTerm());
        loanRequest.setStatus(LoanRequestStatus.NEW);

        return loanRequest;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public RequestIP getRequestIP()
    {
        return requestIP;
    }

    public void setRequestIP(RequestIP requestIP)
    {
        this.requestIP = requestIP;
    }

    public Date getSubmissionDate()
    {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate)
    {
        this.submissionDate = submissionDate;
    }

    public Time getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Time submissionTime)
    {
        this.submissionTime = submissionTime;
    }

    public Integer getTerm()
    {
        return term;
    }

    public void setTerm(Integer term)
    {
        this.term = term;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public LoanRequestStatus getStatus()
    {
        return status;
    }

    public void setStatus(LoanRequestStatus status)
    {
        this.status = status;
    }

    public Loan getLoan()
    {
        return loan;
    }

    public void setLoan(Loan loan)
    {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanRequest that = (LoanRequest) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (loan != null ? !loan.equals(that.loan) : that.loan != null) return false;
        if (requestIP != null ? !requestIP.equals(that.requestIP) : that.requestIP != null) return false;
        if (status != that.status) return false;
        if (submissionDate != null ? !submissionDate.equals(that.submissionDate) : that.submissionDate != null)
            return false;
        if (submissionTime != null ? !submissionTime.equals(that.submissionTime) : that.submissionTime != null)
            return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loan != null ? loan.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (requestIP != null ? requestIP.hashCode() : 0);
        result = 31 * result + (submissionDate != null ? submissionDate.hashCode() : 0);
        result = 31 * result + (submissionTime != null ? submissionTime.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
