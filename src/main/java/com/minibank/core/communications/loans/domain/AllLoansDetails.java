package com.minibank.core.communications.loans.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ann on 14/09/14.
 */
public class AllLoansDetails
{
    private Integer customerId;
    private String name;
    private String surname;
    private List<Loan> loans = new ArrayList<>();

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
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

    public List<Loan> getLoans()
    {
        return loans;
    }

    public void setLoans(List<Loan> loans)
    {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllLoansDetails that = (AllLoansDetails) o;

        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (loans != null ? !loans.equals(that.loans) : that.loans != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (loans != null ? loans.hashCode() : 0);
        return result;
    }
}
