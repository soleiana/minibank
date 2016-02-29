package com.minibank.rest.model;

import javax.validation.constraints.NotNull;

public class Customer {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
