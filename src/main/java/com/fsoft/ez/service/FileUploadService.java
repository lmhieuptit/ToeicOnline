package com.fsoft.ez.service;


import java.io.IOException;

import com.fsoft.ez.model.request.FileUploadRequest;
import com.fsoft.ez.model.response.FileUploadResponse;

public interface FileUploadService {
    FileUploadResponse upload(FileUploadRequest request) throws IOException;
}
