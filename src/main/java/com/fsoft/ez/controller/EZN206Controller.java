package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.custom.EZN206001;
import com.fsoft.ez.model.request.EZN206001Request;
import com.fsoft.ez.model.request.EZN206002Request;
import com.fsoft.ez.service.EZN206Service;

@RestController
@RequestMapping("/api")
public class EZN206Controller {

    @Autowired
    private EZN206Service ezn206Service;

    @PostMapping(
                    path = "/create-group",
                    consumes = {
                        MediaType.MULTIPART_FORM_DATA_VALUE
                    })
    public void createGroup(@Valid EZN206001Request request) throws IOException {
        this.ezn206Service.createGroup(request);
    }

    @PutMapping(
                    path = "/update-group",
                    consumes = {
                        MediaType.MULTIPART_FORM_DATA_VALUE
                    })
    public void updateGroup(@Valid EZN206002Request request) throws IOException {
        this.ezn206Service.updateGroup(request);
    }
    
    @GetMapping("/all-user")
    public List<EZN206001> findEmployeeByName(){
    	return this.ezn206Service.getAllUser();
    }
}
