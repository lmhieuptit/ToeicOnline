package com.fsoft.ez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.common.model.response.EZN110N01ResponseDTO;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Category;
import com.fsoft.ez.model.request.ezn110.EZN110AddCategoryRequest;
import com.fsoft.ez.model.request.ezn110.EZN110EditCategoryRequest;
import com.fsoft.ez.model.response.EZN110N00Response;
import com.fsoft.ez.service.EZN110Service;

@RestController
@RequestMapping("/api")
public class EZN110Controller {

    @Autowired
    EZN110Service ezn110Service;

    /**
     * getAll category
     *
     * @return List<CategoryNews>
     */
    @GetMapping("/get-category-list-admin")
    public List<EZN110N01ResponseDTO> getCategoryList() {

        return this.ezn110Service.getAllCategoryNews();

    }

    /**
     * delete category
     *
     * @param categoryNews
     *
     * @return List category after delete
     */
    @DeleteMapping("/delete-category-admin")
    public void deleteCategory(long categoryNewsId) {

        this.ezn110Service.deleteCategory(categoryNewsId);

    }

    /**
     * edit category
     *
     * @param request
     *            - {@link EZN110EditCategoryRequest}
     *
     * @return category after Edit
     */
    @Modifying
    @PutMapping("/edit-category-admin")
    public void editCategoryNews(@RequestBody EZN110EditCategoryRequest request)
            throws Exception {
        this.ezn110Service.editCategory(request);
    }

    /**
     * add new category
     *
     * @param categoryNews
     */
    @PostMapping("/add-category-admin")
    public String addCategoryNews(@RequestBody EZN110AddCategoryRequest request)
            throws Exception {
        this.ezn110Service.addCategory(request);

        return Constants.SUCCESS_MSG;

    }

    /**
     * Search by Name
     *
     * @param categoryNewsName
     *            the name of category
     * @return {@link Category}
     */
    @GetMapping("/search-by-name-admin")
    public List<EZN110N00Response> searchByName(
            @RequestParam(name = "categoryNewsName", required = false, defaultValue = "") String categoryNewsName) {
        return this.ezn110Service.searchByName(categoryNewsName);
    }
}
