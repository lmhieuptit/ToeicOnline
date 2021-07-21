package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.model.request.EZN210N002Request;
import com.fsoft.ez.model.request.EZN210N003Request;
import com.fsoft.ez.model.request.EZN210N004Request;
import com.fsoft.ez.model.request.EZN211N06Request;
import com.fsoft.ez.model.response.EZN210N001Response;
import com.fsoft.ez.model.response.EZN210N002Response;
import com.fsoft.ez.model.response.EZN210N003Response;
import com.fsoft.ez.model.response.EZN210N004Response;

public interface EZN210Service {

    /**
     * get infor of group
     *
     * @param Long
     *            groupId
     *
     * @return group detail
     */
    public EZN210N001Response getGroupDetail(Long groupId);

    /**
     * get list 10 newest mwmber
     *
     * @param EZN210N002Request
     *            ezn210n002Request
     * @return List News Member
     */
    public List<EZN210N002Response> getNewMembers(Long groupId);

    /**
     * get notification
     *
     * @param EZN210N003Request
     *            request
     *
     * @return List notification
     */
    public List<EZN210N003Response> getNotifications(EZN210N003Request request);

    /**
     * get List members
     *
     * @return List user
     */
    public List<EZN210N004Response> getSuggestMembers(String keySearch, Long groupId);

    /**
     * set cover image
     *
     * @param request
     */
    public void setCoverImage(EZN211N06Request request) throws Exception;
    
    /**
     * add member
     * 
     * @param request
     * @return 
     */
    public void addMemberGroup(EZN210N004Request request) throws Exception;

}
