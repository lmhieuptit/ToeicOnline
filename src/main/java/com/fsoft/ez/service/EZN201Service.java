package com.fsoft.ez.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.validation.BindException;

import com.fsoft.ez.entity.Hashtag;
import com.fsoft.ez.model.request.EZN201N00Request;
import com.fsoft.ez.model.request.EZN201N01Request;
import com.fsoft.ez.model.response.EZN201N00Response;
import com.fsoft.ez.model.response.EZN201N01Response;

public interface EZN201Service {

    /**
     * get all categories
     * 
     * @return all categories in database
     */
    List<EZN201N00Response> getAllCategoryNewsList();

    /**
     * find hashtag by name in database
     * 
     * @param keyword
     * @return list hashtag which have element's name like keyword
     */
    List<Hashtag> findHashtagByNameLike(String keyword);

    /**
     * create news
     * 
     * @param requestDTO
     * @param principal
     * @throws IOException
     * @throws BindException 
     */
    void createNews(EZN201N00Request requestDTO, Principal principal) throws IOException, BindException;

    /**
     * find hashtag by name in database
     * 
     * @param keyword
     * @return list hashtag which have element's name is keyword arg
     */
    List<Hashtag> findHashtagByName(String keyword);

    /**
     * get detail for edit news action
     * 
     * @param id of news which we want to edit
     * @return detail of news which we want to edit
     */
    EZN201N01Response getDataForEditNews(Long id);

    /**
     * update news
     * 
     * @param requestDTO
     * @param principal
     * @throws IOException
     */
    void updateNews(EZN201N01Request requestDTO, Principal principal) throws IOException;
    
    /**
     * delete logic news (set deleteFlag = 1)
     * 
     * @param id
     */
    void deleteNews(Long id);
}
