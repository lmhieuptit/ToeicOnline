package com.fsoft.ez.common.model;

import java.util.Date;

public class RestResponseInfor {
    private final Date timestamp;
    private final String message;

    public RestResponseInfor(String message) {
        this.timestamp = new Date();
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

}
