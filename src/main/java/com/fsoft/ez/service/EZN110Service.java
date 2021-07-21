package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.common.model.response.EZN110N01ResponseDTO;
import com.fsoft.ez.model.request.ezn110.EZN110AddCategoryRequest;
import com.fsoft.ez.model.request.ezn110.EZN110EditCategoryRequest;
import com.fsoft.ez.model.response.EZN110N00Response;

public interface EZN110Service {
    // get all category
    List<EZN110N01ResponseDTO> getAllCategoryNews();

    // delete category
    String deleteCategory(Long categoryNewsId);

    // edit Category
    String editCategory(EZN110EditCategoryRequest request) throws Exception;

    // add new category
    String addCategory(EZN110AddCategoryRequest request) throws Exception;

    // search by name
    List<EZN110N00Response> searchByName(String categoryNewsName);

}
