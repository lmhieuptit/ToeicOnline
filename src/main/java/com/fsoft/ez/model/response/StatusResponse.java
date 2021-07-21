package com.fsoft.ez.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StatusResponse {
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorResponse> errors;

    public StatusResponse(String status) {
        this.status = status;
    }

    public void addError(ErrorResponse error) {
        if(CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<>();
        }

        errors.add(error);
    }
}
