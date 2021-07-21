package com.fsoft.ez.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class FileUploadRequest {
    @NotNull
    private MultipartFile file;
}
