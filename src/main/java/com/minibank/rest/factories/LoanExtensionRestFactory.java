package com.minibank.rest.factories;

import com.minibank.rest.model.LoanExtension;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanExtensionRestFactory {

    public List<LoanExtension> getLoanExtensions(List<com.minibank.communications.model.LoanExtension> fromLoanExtensions) {
        return fromLoanExtensions.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private LoanExtension convert(com.minibank.communications.model.LoanExtension fromLoanExtension) {
        LoanExtension toLoanExtension = new LoanExtension();
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return toLoanExtension;
    }
}
