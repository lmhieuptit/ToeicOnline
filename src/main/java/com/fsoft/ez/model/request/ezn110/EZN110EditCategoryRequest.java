package com.fsoft.ez.model.request.ezn110;

import lombok.Data;

/**
 * Edit category request body
 * 
 * @author QuanTD4
 *
 */
@Data
public class EZN110EditCategoryRequest {

    private Long categoryNewsId;

    private String categoryNewsName;
}
