package com.minibank.core.communications;


public abstract class CreateEntityResponse {

    protected boolean created = true;
    protected String message;

    public boolean isCreated() {
        return created;
    }

    public String getMessage() {
        return message;
    }
}
