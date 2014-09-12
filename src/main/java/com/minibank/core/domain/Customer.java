package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CUSTOMER")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name= "CUSTOMER_ID")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.REMOVE})
    private List<LoanRequest> loanRequests = new ArrayList<LoanRequest>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name= "CUSTOMER_ID")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.REMOVE})
    private List<Loan> loans = new ArrayList<Loan>();


    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="SURNAME", nullable = false)
    private String surname;

    public Customer()
    {}

    public Customer(Integer id)
    {
        this.id = id;
    }

    public Customer(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public List<LoanRequest> getLoanRequests() {
        return loanRequests;
    }

    public void setLoanRequests(List<LoanRequest> loanRequests) {
        this.loanRequests = loanRequests;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (loanRequests != null ? !loanRequests.equals(customer.loanRequests) : customer.loanRequests != null)
            return false;
        if (loans != null ? !loans.equals(customer.loans) : customer.loans != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (surname != null ? !surname.equals(customer.surname) : customer.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loanRequests != null ? loanRequests.hashCode() : 0);
        result = 31 * result + (loans != null ? loans.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
