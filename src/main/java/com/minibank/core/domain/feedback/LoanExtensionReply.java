package com.minibank.core.domain.feedback;

/**
 * Created by Ann on 08/09/14.
 */
public class LoanExtensionReply implements Reply
{
    private Integer customerId;
    private String message;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
