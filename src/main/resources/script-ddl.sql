CREATE TABLE IF NOT EXISTS BANK_PARAMS (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	MAX_LOAN_AMOUNT NUMERIC(10,2) DEFAULT 4000.00 NOT NULL,
	BASE_INTEREST_RATE NUMERIC(10,2) DEFAULT 100.00 NOT NULL,
	INTEREST_RATE_FACTOR NUMERIC(10,1) DEFAULT 1.5 NOT NULL,
	MAX_LOAN_ATTEMPTS TINYINT DEFAULT 3 NOT NULL,
	RISK_TIME_START TIME NOT NULL,
	RISK_TIME_END TIME NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS CUSTOMER (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	NAME VARCHAR(25) NOT NULL,
	SURNAME VARCHAR(25) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS REQUEST_IP (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	IP CHARACTER(15) NOT NULL,
	LOAN_ATTEMPTS TINYINT DEFAULT 0 NOT NULL,
	LAST_LOAN_ATTEMPT DATE NOT NULL,
	PRIMARY KEY (ID),
	UNIQUE (IP)
);


CREATE TABLE IF NOT EXISTS LOAN (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	LOAN_REQUEST_ID INTEGER NOT NULL,
	CURRENT_INTEREST_RATE NUMERIC(10,2) NOT NULL,
	CURRENT_INTEREST NUMERIC(10,2) NOT NULL,
	START_DATE DATE NOT NULL,
	END_DATE DATE NOT NULL,
	CUSTOMER_ID INTEGER NOT NULL,
	PRIMARY KEY (ID),
	UNIQUE (LOAN_REQUEST_ID),
	CONSTRAINT CUSTOMER_LOAN_FK
    FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (ID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS LOAN_EXTENSION (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	LOAN_ID INTEGER NOT NULL,
	START_DATE DATE NOT NULL,
	END_DATE DATE NOT NULL,
	INTEREST_RATE NUMERIC(10,2) NOT NULL,
	INTEREST NUMERIC(10,2) NOT NULL,
	SUBMISSION_DATE DATE NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT LOAN_LOAN_EXTENSION_FK
    FOREIGN KEY (LOAN_ID)
    REFERENCES LOAN (ID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS LOAN_REQUEST (
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000, INCREMENT BY 1) NOT NULL,
	CUSTOMER_ID INTEGER NOT NULL,
	SUBMISSION_DATE DATE NOT NULL,
	SUBMISSION_TIME TIME NOT NULL,
	TERM INTEGER NOT NULL,
	AMOUNT NUMERIC(10,2) NOT NULL,
	STATUS CHARACTER(8) DEFAULT 'NEW     ' NOT NULL,
	REQUEST_IP_ID INTEGER NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT CUSTOMER_LOAN_REQUEST_FK
    FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (ID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT REQUEST_IP_LOAN_REQUEST_FK
    FOREIGN KEY (REQUEST_IP_ID)
    REFERENCES REQUEST_IP (ID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);



