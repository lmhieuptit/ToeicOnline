package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.Category;
import com.fsoft.ez.model.response.EZN201N00Response;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT category FROM Category category WHERE category.categoryId =:categoryNewsId AND category.deleteFlag = false")
    Category findCategoryById(@Param("categoryNewsId") Long categoryNewsId);

    @Query("SELECT category FROM Category category WHERE category.deleteFlag = false ORDER BY category.createDate DESC ")
    List<Category> findAllCategory();

    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE category_name LIKE :categoryNewsName ORDER BY category.create_date DESC")
    List<Category> findByCategoryName(
            @Param("categoryNewsName") String categoryNewsName);

    @Query(nativeQuery = true, value = "SELECT c.category_name FROM category c WHERE c.delete_flag = 0")
    List<String> getCategoryName();

    @Query("SELECT new com.fsoft.ez.model.response.EZN201N00Response(c.categoryId, c.categoryName) from Category c where c.deleteFlag = false")
    List<EZN201N00Response> getAllCategoriesNameActive();
}
