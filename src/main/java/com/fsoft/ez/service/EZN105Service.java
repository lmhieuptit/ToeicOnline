package com.fsoft.ez.service;

import com.fsoft.ez.entity.custom.EZN105001;
import com.fsoft.ez.model.response.EZN105N00Response;

public interface EZN105Service {

    EZN105N00Response getNewsById(Long newsId);

}
