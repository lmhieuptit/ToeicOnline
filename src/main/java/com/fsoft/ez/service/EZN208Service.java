package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.model.response.EZN208001Response;

public interface EZN208Service {

    /**
     * get all group
     */
    List<EZN208001Response> getGroup(String keySearch);

    /**
     * get my group
     */
    List<EZN208001Response> getMyGroup();

    /**
     * join group
     */
    void joinGroup(Long groupId);

    /**
     * leave group
     */
    void leaveGroup(Long groupId);

    /**
     * delete group
     * 
     * @param groupId
     */
    void deleteGroup(Long groupId);
}
