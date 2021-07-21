package com.fsoft.ez.common.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRequestDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2979375733111705411L;
    private String mail;
    private String tenantId;
}
