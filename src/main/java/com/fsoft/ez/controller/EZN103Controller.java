package com.fsoft.ez.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.common.model.request.EZN103N02RequestDTO;
import com.fsoft.ez.entity.custom.EZN103N01;
import com.fsoft.ez.model.response.EZN103N00Response;
import com.fsoft.ez.service.EZN103Service;

@RestController
@RequestMapping("/api")
public class EZN103Controller {
    @Autowired
    EZN103Service ezn103Service;

    /**
     * get all news
     *
     * @return List News
     */
    @GetMapping("/get-all-news-admin")
    public List<EZN103N01> getAllNews() {

        return this.ezn103Service.getAllNews();

    }

    /**
     * get content of new
     *
     * @param newsId
     *
     * @return content
     */
    @GetMapping("/get-news-detail-admin")
    public EZN103N00Response getNewsDetail(long newsId) {
        return this.ezn103Service.getNewsDetail(newsId);

    }

    @PutMapping("/process-confirm-news-admin")
    public void confirmNews(EZN103N02RequestDTO ezn103N02RequestDTO)
            throws IOException {
        this.ezn103Service.confirmNews(ezn103N02RequestDTO);
    }
}
