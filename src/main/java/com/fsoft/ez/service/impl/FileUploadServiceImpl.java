package com.fsoft.ez.service.impl;

import com.fsoft.ez.FileUploadUtil;
import com.fsoft.ez.model.request.FileUploadRequest;
import com.fsoft.ez.model.response.FileUploadResponse;
import com.fsoft.ez.service.FileUploadService;
import io.netty.util.internal.StringUtil;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private FileUploadResponse response;

    @Autowired
    private Environment env;
    
    @Override
    public FileUploadResponse upload(FileUploadRequest request) throws IOException{
        response = new FileUploadResponse();
        validate(request.getFile());

        if (response.hasErrors()) {
            response.fail();
            return response;
        }

        String imageUrl = FileUploadUtil.uploadImage(env, request.getFile(), StringUtil.EMPTY_STRING);
        if (imageUrl == null) {
            response.addError(StringUtils.EMPTY, "Cannot upload file");
            response.fail();
        } else {
            response.success();
            response.setName(request.getFile().getOriginalFilename());
            response.setUrl(imageUrl);
            response.setSize(request.getFile().getSize());
        }

        return response;
    }

    private void validate(MultipartFile file) {
        if (!FileUploadUtil.isValidImageExtension(file.getOriginalFilename())) {
            response.addError(StringUtils.EMPTY, "Invalid Extension");
        }
    }
    
}
