package com.fsoft.ez.service;

import java.io.IOException;
import java.util.List;

import com.fsoft.ez.entity.custom.EZN212001;
import com.fsoft.ez.entity.custom.EZN212002;
import com.fsoft.ez.model.request.EZN212N01Request;

public interface EZN212Service {

	List<EZN212001> findNewsNotify(Long limit);

	void setCoverImage(EZN212N01Request request) throws IOException;

	EZN212002 getCompanyDetail();
}
