package com.fsoft.ez.common.model;

import java.util.Date;

public class RestErrorInfo {

    private final Date timestamp;
    private final String message;
    private final String details;

    public RestErrorInfo(String message, String details) {
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
    }

    public RestErrorInfo(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }
}
