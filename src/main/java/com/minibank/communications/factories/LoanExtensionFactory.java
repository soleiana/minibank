package com.minibank.communications.factories;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LoanExtensionFactory {

    public List<com.minibank.communications.model.LoanExtension> getLoanExtensions(List<com.minibank.core.model.LoanExtension> fromLoanExtensions) {
        return fromLoanExtensions.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private com.minibank.communications.model.LoanExtension convert(com.minibank.core.model.LoanExtension fromLoanExtension) {
        com.minibank.communications.model.LoanExtension toLoanExtension = new com.minibank.communications.model.LoanExtension();
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return toLoanExtension;
    }
}
