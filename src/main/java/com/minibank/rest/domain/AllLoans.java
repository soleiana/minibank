package com.minibank.rest.domain;

import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.rest.controller.LoanQueriesController;
import com.minibank.rest.factories.AllLoansFactory;
import com.minibank.rest.factories.AllLoansFactoryImpl;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Ann on 14/09/14.
 */

@XmlRootElement
public class AllLoans extends ResourceSupport
        implements Serializable
{
    private Integer customerId;
    private String name;
    private String surname;
    private List<Loan> loans = new ArrayList<>();

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    /*public static AllLoans fromAllLoansDetails(AllLoansDetails allLoansDetails)
    {
        AllLoansFactory allLoansFactory = new AllLoansFactoryImpl();
        AllLoans allLoans = allLoansFactory.getNewAllLoans(allLoansDetails);


        allLoans.add(linkTo(LoanQueriesController.class).slash(allLoans.customerId).withSelfRel());
        return allLoans;
    }*/
}
