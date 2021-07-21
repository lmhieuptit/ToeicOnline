package com.fsoft.ez.service;

import java.io.IOException;
import java.util.List;

import com.fsoft.ez.model.request.EZN211N00Request;
import com.fsoft.ez.model.request.EZN211N01Request;
import com.fsoft.ez.model.request.EZN211N02Request;
import com.fsoft.ez.model.request.EZN211N03Request;
import com.fsoft.ez.model.request.EZN211N05Resquest;
import com.fsoft.ez.model.request.EZN211N07Request;
import com.fsoft.ez.model.response.EZN211N00Response;
import com.fsoft.ez.model.response.EZN211N02Response;
import com.fsoft.ez.model.response.EZN211N03Response;

public interface EZN211Service {

    /**
     * create news in a group
     *
     * @param requestDTO
     * @throws IOException
     */
    void createNewsGroup(EZN211N00Request requestDTO) throws IOException;

    /**
     * get detail for edit news action in group
     *
     * @param id
     *            of news which we want to edit
     * @return detail of news which we want to edit
     */
    EZN211N00Response getDataForEditNews(Long id);

    /**
     * update news in the group
     *
     * @param requestDTO
     * @throws IOException
     */
    void updateNewsGroup(EZN211N01Request requestDTO) throws IOException;

    /**
     * get all members of group
     *
     * @param groupId
     *
     * @return List member
     */
    public List<EZN211N02Response> getAllMembers(EZN211N07Request request);

    /**
     * get list news unapproved
     *
     * @param EZN211N03Request
     *            request
     *
     * @return list News have status = 0
     */
    public List<EZN211N03Response> getNewsUnapproved(EZN211N03Request request);

    /**
     * aprove news in group
     *
     * @param EZN211N02Request
     *            request
     */
    public String approveNewsInGroup(EZN211N02Request request) throws Exception;

    /**
     * reject news in group
     *
     * @param EZN211N05Resquest
     *            request
     */
    public String rejectNewsInGroup(EZN211N05Resquest request) throws Exception;

    /**
     * get introduce group
     *
     * @param groupId
     *
     * @return String introduce group
     */
    public String getIntroduceGroup(Long groupId);

}
