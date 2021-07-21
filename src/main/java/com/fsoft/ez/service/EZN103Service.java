package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.common.model.request.EZN103N02RequestDTO;
import com.fsoft.ez.entity.custom.EZN103N01;
import com.fsoft.ez.model.response.EZN103N00Response;

public interface EZN103Service {

    /**
     * get all record table News
     *
     * @return List News
     */
    List<EZN103N01> getAllNews();

    /**
     * get content
     *
     * @param newsId
     *
     * @return content of new
     */
    EZN103N00Response getNewsDetail(Long newsId);

    /**
     * confirm
     *
     * @param ezn103N02RequestDTO
     */
    void confirmNews(EZN103N02RequestDTO ezn103N02RequestDTO);
}
