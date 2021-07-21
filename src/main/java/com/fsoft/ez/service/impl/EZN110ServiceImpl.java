package com.fsoft.ez.service.impl;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.common.model.response.EZN110N01ResponseDTO;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Category;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.model.request.ezn110.EZN110AddCategoryRequest;
import com.fsoft.ez.model.request.ezn110.EZN110EditCategoryRequest;
import com.fsoft.ez.model.response.EZN110N00Response;
import com.fsoft.ez.repository.CategoryRepository;
import com.fsoft.ez.repository.EZN110N01Repository;
import com.fsoft.ez.repository.EZN110N02Repository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.service.EZN110Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EZN110ServiceImpl implements EZN110Service {

    private CategoryRepository categoryRepository;

    private NewsRepository newsRepository;

    private EZN110N01Repository ezn110N01Repository;

    private EZN110N02Repository ezn110N02Repository;

    @Autowired
    public EZN110ServiceImpl(CategoryRepository categoryRepository,
            NewsRepository newsRepository, EntityManager entityManager,
            EZN110N01Repository ezn110N01Repository,
            EZN110N02Repository ezn110N02Repository) {
        super();
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;
        this.ezn110N01Repository = ezn110N01Repository;
        this.ezn110N02Repository = ezn110N02Repository;

    }

    /**
     * return List category
     */
    @Override
    public List<EZN110N01ResponseDTO> getAllCategoryNews() {

        return this.ezn110N01Repository.getAllCategory();
    }

    /**
     *
     * delete category
     *
     * @param CLong
     *            categoryNewsId
     *
     * @return SUCCESS_MSG
     */
    @Transactional(rollbackOn = Throwable.class)
    @Override
    public String deleteCategory(Long categoryNewsId) {

        Long count = this.newsRepository.countNewsInCategory(categoryNewsId);
        log.info(" newsRepository.countNewsInCategory : {}", count);
        if (count == 0) {
            this.categoryRepository.deleteById(categoryNewsId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    Constants.VALIDATE_CATEGORY);
        }
        return Constants.SUCCESS_MSG;
    }

    /**
     * add new category
     *
     * @return SUCCESS_MSG
     */
    @Override
    public String addCategory(EZN110AddCategoryRequest request)
            throws Exception {

        String categoryNewsName = request.getCategoryNewsName().trim();
        log.info("categoryName : {} ", categoryNewsName);

        String temp = Normalizer.normalize(categoryNewsName,
                Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String name = pattern.matcher(temp).replaceAll("");
        log.info("Normalizer.Form.NFD name : {}", name);

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();

        Category category = new Category();
        if (categoryNewsName != null) {

            List<String> countList = this.categoryRepository.getCategoryName();
            countList.forEach(count -> {

                if (!count.equals(categoryNewsName)) {

                    category.setCategoryName(categoryNewsName);
                    category.setCreateDate(LocalDateTime.now());
                    category.setAuthorId(((TblEmployee) SecurityContextHolder
                            .getContext().getAuthentication().getPrincipal())
                                    .getEmplid());
                    category.setDeleteFlag(false);

                    this.categoryRepository.save(category);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            Constants.VALIDATE_CATEGORY_EXXISTED);
                }
                if (b == true) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            Constants.VALIDATE_CATEGORY_REGEX);
                }
            });

        }
        return Constants.SUCCESS_MSG;

    }

    /**
     * Edit category
     *
     * @param EZN110EditCategoryRequest
     *            requess
     *
     * @return SUCCESS_MSG
     */
    @Transactional(rollbackOn = Throwable.class)
    @Override
    public String editCategory(EZN110EditCategoryRequest request)
            throws Exception {

        String categoryNewsName = request.getCategoryNewsName().trim();
        log.info("categoryName : {} ", categoryNewsName);

        String temp = Normalizer.normalize(categoryNewsName,
                Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String name = pattern.matcher(temp).replaceAll("");
        log.info("Normalizer.Form.NFD name : {}", name);

        // check validate regex category name
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();

        Long categoryNewsId = request.getCategoryNewsId();
        Category categoryEdit = this.categoryRepository
                .findCategoryById(categoryNewsId);
        List<String> countList = this.categoryRepository.getCategoryName();
        log.info(" categoryRepository.countCategoryNameByName : {}",
                countList.toString());
        countList.forEach(count -> {
            if (!count.equals(categoryNewsName)) {

                categoryEdit.setCategoryName(categoryNewsName);
                categoryEdit.setUpdateDate(LocalDateTime.now());
                categoryEdit.setModifiedBy(((TblEmployee) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal())
                                .getEmplid());
                this.categoryRepository.save(categoryEdit);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        Constants.VALIDATE_CATEGORY_EXXISTED);
            }

            if (b == true) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        Constants.VALIDATE_CATEGORY_REGEX);
            }
        });

        return Constants.SUCCESS_MSG;

    }

    /**
     * searrch by name
     *
     * @param categoryNewsName
     *
     * @return List category
     */
    @Override
    public List<EZN110N00Response> searchByName(String categoryNewsName) {

        String name = categoryNewsName + "%";
        return this.ezn110N02Repository.getNewsbyName(name);

    }

}
