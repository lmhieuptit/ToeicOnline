package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10101;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN101001Request;

public interface EZN101Service {

	List<TblDepartment> getListDepartment();

	List<TblEmployee> findEmployeeByDeptId(String deptId);

	List<EZN10102> findJobIndicatorByDeptId(String deptId);

	void editProcess(EZN101001Request ezn101001Request) throws Exception;

	String deleteProcess(Long processId) throws Exception;

	List<EZN10101> getProcessByName(String keySearchProcess);

}
