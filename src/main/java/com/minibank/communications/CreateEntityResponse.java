package com.minibank.communications;


public abstract class CreateEntityResponse {

    protected boolean created = true;
    protected final String message;

    protected CreateEntityResponse(final boolean created, final String message) {
        this.created = created;
        this.message = message;
    }

    public boolean isCreated() {
        return created;
    }

    public String getMessage() {
        return message;
    }
}
