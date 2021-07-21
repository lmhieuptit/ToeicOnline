package com.fsoft.ez.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.ez.model.response.EZN301N00Response;
import com.fsoft.ez.repository.EZN301N00Repository;
import com.fsoft.ez.service.EZN301Service;

@Service
public class EZN301ServiceImpl implements EZN301Service {
    private EZN301N00Repository ezn301N00Repository;

    public EZN301ServiceImpl(EZN301N00Repository ezn301N00Repository) {
        super();
        this.ezn301N00Repository = ezn301N00Repository;
    }

    @Override
    public List<EZN301N00Response> getNotifications() {

        return this.ezn301N00Repository.getAllNotification();
    }

}
