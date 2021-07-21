package com.fsoft.ez.model.response;

import lombok.Data;
import org.springframework.util.CollectionUtils;

@Data
public class FileUploadResponse {
    private String name;
    private String url;
    private Long size;
    private String status;
    private StatusResponse response = new StatusResponse();

    public void success() {
        this.status = "success";
        response.setStatus("success");
    }

    public void fail() {
        this.status = "fail";
        response.setStatus("fail");
    }

    public boolean hasErrors() {
        return !CollectionUtils.isEmpty(response.getErrors());
    }

    public void addError(String code, String message) {
        response.addError(new ErrorResponse(code, message));
    }
}
