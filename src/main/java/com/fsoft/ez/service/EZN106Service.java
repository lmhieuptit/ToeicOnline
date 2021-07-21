package com.fsoft.ez.service;

import com.fsoft.ez.model.response.EZN106N00Response;

public interface EZN106Service {

    EZN106N00Response getNewsById(Long newsId);
}
