package com.fsoft.ez.controller;

import com.fsoft.ez.model.request.FileUploadRequest;
import com.fsoft.ez.model.response.FileUploadResponse;
import com.fsoft.ez.service.FileUploadService;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file/upload")
public class FileUploadController {

	private final FileUploadService fileUploadService;

	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@PostMapping("/image")
	public FileUploadResponse uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		FileUploadRequest requestDTO = new FileUploadRequest();
		requestDTO.setFile(file);
		return fileUploadService.upload(requestDTO);
	}

}
