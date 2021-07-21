package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.entity.TblDepartment;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN10102;
import com.fsoft.ez.model.request.EZN102001Resquest;

public interface EZN102Service {

	List<TblDepartment> getListDepartment();

	List<TblEmployee> findEmployeeByDeptId(String deptId);

	List<EZN10102> findJobIndicatorByDeptId(String deptId);

	void createProcess(EZN102001Resquest ezn102001Resquest) throws Exception;

}
