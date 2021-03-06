package com.minibank.testutil.services;

import com.minibank.core.model.BankParameters;
import com.minibank.testutil.repositories.TestBankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource({ "classpath:bank.properties" })
public class BankConfigurator {

    @Autowired
    private Environment env;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;


    @Transactional
    public void persistParameters() {
        BankParameters bankParameters = getBankParameters();
        testBankParametersRepository.create(bankParameters);
    }

    private BankParameters getBankParameters() {
        BankParameters bankParameters = new BankParameters();
        bankParameters.setMaxLoanAmount(env.getProperty("maxLoanAmount", BigDecimal.class));
        bankParameters.setMaxLoanAttempts(env.getProperty("maxLoanAttempts", Byte.class));
        bankParameters.setBaseInterestRate(env.getProperty("baseInterestRate", BigDecimal.class));
        bankParameters.setInterestRateFactor(env.getProperty("interestRateFactor", BigDecimal.class));
        Time sqlRiskTimeStart = env.getProperty("riskTimeStart", Time.class);
        bankParameters.setRiskTimeStart(sqlRiskTimeStart.toLocalTime());
        Time sqlRiskTimeEnd = env.getProperty("riskTimeEnd", Time.class);
        bankParameters.setRiskTimeEnd(sqlRiskTimeEnd.toLocalTime());
        bankParameters.setLoanExtensionTerm(env.getProperty("loanExtensionTerm", Short.class));
        return bankParameters;
    }
}
