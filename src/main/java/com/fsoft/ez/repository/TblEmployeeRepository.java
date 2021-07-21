package com.fsoft.ez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.dto.UserInfoDTO;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.model.response.EZN302N00Response;

public interface TblEmployeeRepository extends JpaRepository<TblEmployee, Long> {

	@Query(value = "select new com.fsoft.ez.model.response.EZN302N00Response(te.emplid, te.account, "
			+ "DATE_FORMAT(te.dateOfBirth,'%d/%m/%Y'), year(current_date()) - year(te.dateOfBirth), "
			+ "te.avatarUrl, td.descr, td.descrshort) "
			+ "from com.fsoft.ez.entity.TblEmployee te left join com.fsoft.ez.entity.TblDepartment td on te.deptid = td.deptid "
			+ "where month(te.dateOfBirth) = :month and day(te.dateOfBirth) = :day order by te.account")
	List<EZN302N00Response> findByBirthDay(@Param("month") int month, @Param("day") int day);

	@Query(value = "select new com.fsoft.ez.model.response.EZN302N00Response(te.emplid, te.account, "
			+ "DATE_FORMAT(te.dateOfBirth,'%d/%m/%Y'), year(current_date()) - year(te.dateOfBirth), "
			+ "te.avatarUrl, td.descr, td.descrshort) "
			+ "from com.fsoft.ez.entity.TblEmployee te left join com.fsoft.ez.entity.TblDepartment td on te.deptid = td.deptid "
			+ "where month(te.dateOfBirth) = :month order by te.dateOfBirth asc, te.account asc")
	List<EZN302N00Response> findEmpBirthDayMonth(@Param("month") int month);

	@Query(nativeQuery = true, value = "select * from tbl_employee where lower_email_addr = :lowerEmailAddr")
	List<TblEmployee> findByLowerEmailAddr(@Param("lowerEmailAddr") String lowerEmailAddr);

	@Query(value = " SELECT empl.account FROM TblEmployee empl WHERE empl.emplid =:emplId ")
	String getAcountById(@Param("emplId") String emplId);

	@Query(nativeQuery = true, value = "SELECT empl.deptid FROM tbl_employee empl WHERE empl.emplid =:emplId")
	String getDeptIdByEmplId(@Param("emplId") String emplId);

	@Query(value = "select * from tbl_employee where deptid =:deptId", nativeQuery = true)
	List<TblEmployee> findEmployeeByDeptId(@Param("deptId") String deptId);

	@Query("select td.descrshort from TblDepartment td where td.deptid = :id")
	String getDepartmentDescrShort(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT count(e.job_indicator) FROM eznews_db.tbl_employee e WHERE e.emplid =:empid")
	Long countJobIndecator(@Param("empid") String empid);

//	@Query(value = " SELECT empl FROM TblEmployee empl WHERE empl.emplid =: emplid ")
//	TblEmployee getEmplById(@Param("emplid") String emplid);

	TblEmployee findByEmplid(String emplid);

	@Query(value = "SELECT e.account FROM TblEmployee e")
	List<String> getEmplName();

	@Query("select new com.fsoft.ez.dto.UserInfoDTO(te.emplid, te.account , te.emailAddr , te.lowerEmailAddr"
			+ ", te.nameDisplay, te.avatarUrl, td.descr, td.descrshort) from TblEmployee te left join TblDepartment td on te.deptid = td.deptid"
			+ " where te.emplid =:emplid")
	Optional<UserInfoDTO> getUserInfoByEmplId(@Param("emplid") String emplid);

	@Query(value = "SELECT e.emplid FROM TblEmployee e WHERE e.deptid =:departmentId ")
	List<String> getUserIdByDeptId(@Param("departmentId") String departmentId);

	@Modifying
	@Query(nativeQuery = true, value = " UPDATE tbl_employee SET cover_image =:filePath WHERE emplid =:emplid")
	void updateCoverImageById(@Param("filePath") String filePath, @Param("emplid") String emplId);

	@Query(nativeQuery = true, value = "SELECT emplid FROM tbl_employee WHERE month(date_of_birth) =:month AND day(date_of_birth) =:day")
	List<String> getNotifyBirthday(@Param("month") int month, @Param("day") int day);

	@Query(nativeQuery = true, value = "SELECT e.emplid FROM tbl_employee e WHERE e.job_indicator =:job ")
	List<String> getEmplIdByjobInsecator(@Param("job") String job);
}
