package com.fsoft.ez.service;

import com.fsoft.ez.model.response.EZN107N00Response;

public interface EZN107Service {

    EZN107N00Response getNewsById(Long newsId);
}
