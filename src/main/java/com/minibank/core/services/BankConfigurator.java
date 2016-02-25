package com.minibank.core.services;

import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
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
    private BankParametersRepository bankParametersRepository;


    @Transactional
    public void setParameters() {
        BankParameters bankParameters = getBankParameters();
        bankParametersRepository.create(bankParameters);
    }

    private BankParameters getBankParameters() {
        BankParameters bankParameters = new BankParameters();
        bankParameters.setMaxLoanAmount(env.getProperty("maxLoanAmount", BigDecimal.class));
        bankParameters.setMaxLoanAttempts(env.getProperty("maxLoanAttempts", Byte.class));
        bankParameters.setBaseInterestRate(env.getProperty("baseInterestRate", BigDecimal.class));
        bankParameters.setInterestRateFactor(env.getProperty("interestRateFactor", BigDecimal.class));
        bankParameters.setRiskTimeStart(env.getProperty("riskTimeStart", Time.class));
        bankParameters.setRiskTimeEnd(env.getProperty("riskTimeEnd", Time.class));
        bankParameters.setLoanExtensionTerm(env.getProperty("loanExtensionTerm", Short.class));
        return bankParameters;
    }
}
