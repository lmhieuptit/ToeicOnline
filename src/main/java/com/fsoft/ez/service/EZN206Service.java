package com.fsoft.ez.service;

import java.io.IOException;
import java.util.List;

import com.fsoft.ez.entity.custom.EZN206001;
import com.fsoft.ez.model.request.EZN206001Request;
import com.fsoft.ez.model.request.EZN206002Request;

public interface EZN206Service {

    /**
     * create group
     *
     * @param request
     *        group infor
     * @throws IOException
     */
    void createGroup(EZN206001Request request) throws IOException;

    /**
     * update group
     *
     * @param request
     *        group infor
     * @throws IOException
     */
    void updateGroup(EZN206002Request request) throws IOException;

    /**
     * get list employee by name
     * 
     * @param keySearch
     * @return list employee
     */
    List<EZN206001> getAllUser();
}
