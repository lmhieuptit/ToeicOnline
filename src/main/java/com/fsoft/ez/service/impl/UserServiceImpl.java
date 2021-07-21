package com.fsoft.ez.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.ez.common.model.UserDTO;
import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.UserCustom00;
import com.fsoft.ez.repository.ProcessRepository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.repository.TblUserRolesRepository;
import com.fsoft.ez.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;

    private TblEmployeeRepository tblEmployeeRepository;

    private TblUserRolesRepository tblUserRolesRepository;

    private TblDepartmentRepository tblDepartmentRepository;

    private ProcessRepository processRepository;

    @Autowired
    public UserServiceImpl(EntityManager entityManager,
            TblEmployeeRepository tblEmployeeRepository,
            TblUserRolesRepository tblUserRolesRepository,
            TblDepartmentRepository tblDepartmentRepository,
            ProcessRepository processRepository) {
        this.entityManager = entityManager;
        this.tblEmployeeRepository = tblEmployeeRepository;
        this.tblUserRolesRepository = tblUserRolesRepository;
        this.tblDepartmentRepository = tblDepartmentRepository;
        this.processRepository = processRepository;
    }

    @Override
    public UserDTO getUserInfo(String email, String tenantId) {
        Session session = this.entityManager.unwrap(Session.class);
        String queryName = "UserCustomList";
        String queryString = session.getNamedQuery(queryName).getQueryString();
        @SuppressWarnings("unchecked")
        Query<UserCustom00> nativeQuery = session.createNativeQuery(queryString,
                "UserCustomListResult");
        nativeQuery.setParameter("mail", email);
        UserCustom00 user = nativeQuery.getSingleResult();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public TblEmployee getUserInfo(String email) {
        List<TblEmployee> emplList = this.tblEmployeeRepository
                .findByLowerEmailAddr(email.toLowerCase());
        if (emplList.size() > 0) {
            return emplList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<GrantedAuthority> getUserRole(String email) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<String> roleList = this.tblUserRolesRepository.findByEmail(email);
        for (String role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role));
        }
        return authorityList;
    }

    @Override
    public UserInfoDTO getUserInformation() {
        TblEmployee employee = ((TblEmployee) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        String empid = employee.getEmplid();
        // get dept name
        String deptName = this.tblDepartmentRepository
                .getDeptNameByDeptId(employee.getDeptid());
        // get deptshort name
        String deptShort = this.tblDepartmentRepository
                .getDescShortById(employee.getDeptid());
        // get role
        String roleCode = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));
        Long processIdByjob = null;
        if (employee.getJobIndicator() != null) {

            processIdByjob = this.processRepository.getProcessIdByJobAndDeptId(
                    employee.getDeptid(), employee.getJobIndicator());
        }

        Long ProcessIdByEmplId = this.processRepository
                .getProcessIdByEmplId(employee.getEmplid());
        // String procesId = .
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        // role Admin
        if (Constants.ROLE_ADMIN.equals(roleCode)) {

            userInfoDTO.setEmplId(empid);
            userInfoDTO.setDeptId(employee.getDeptid());
            userInfoDTO.setDeptName(deptName);
            userInfoDTO.setRoleCode(Constants.ROLE_ADMIN);
            userInfoDTO.setEmail(employee.getEmailAddr());
            userInfoDTO.setAddress(employee.getAddress());
            userInfoDTO.setPhoneNumber(employee.getPhoneNumber());
            userInfoDTO.setJobIndicator(employee.getJobIndicator());
            userInfoDTO.setDateOfBirth(employee.getDateOfBirth());
            userInfoDTO.setNameDisplay(employee.getNameDisplay());
            userInfoDTO.setAccount(employee.getAccount());
            userInfoDTO.setDescrshort(deptShort);
            userInfoDTO.setCompany(employee.getCompany());
            userInfoDTO.setAvatarUrl(employee.getAvatarUrl());
            userInfoDTO.setCoverImage(employee.getCoverImage());
            return userInfoDTO;

            // Role user
        } else if ((processIdByjob != null)) {

            Boolean status = this.processRepository
                    .getStatusById(processIdByjob);
            userInfoDTO.setEmplId(empid);
            userInfoDTO.setDeptId(employee.getDeptid());
            userInfoDTO.setDeptName(deptName);
            userInfoDTO.setRoleCode(Constants.ROLE_USER);
            userInfoDTO.setEmail(employee.getEmailAddr());
            userInfoDTO.setAddress(employee.getAddress());
            userInfoDTO.setPhoneNumber(employee.getPhoneNumber());
            userInfoDTO.setJobIndicator(employee.getJobIndicator());
            userInfoDTO.setDateOfBirth(employee.getDateOfBirth());
            userInfoDTO.setNameDisplay(employee.getNameDisplay());
            userInfoDTO.setAccount(employee.getAccount());
            userInfoDTO.setDescrshort(deptShort);
            userInfoDTO.setProcessId(processIdByjob);
            userInfoDTO.setCompany(employee.getCompany());
            userInfoDTO.setStatus(status);
            userInfoDTO.setAvatarUrl(employee.getAvatarUrl());
            userInfoDTO.setCoverImage(employee.getCoverImage());
            return userInfoDTO;

        } else if (ProcessIdByEmplId != null) {

            Boolean status = this.processRepository
                    .getStatusById(ProcessIdByEmplId);
            userInfoDTO.setEmplId(empid);
            userInfoDTO.setDeptId(employee.getDeptid());
            userInfoDTO.setDeptName(deptName);
            userInfoDTO.setRoleCode(Constants.ROLE_USER);
            userInfoDTO.setEmail(employee.getEmailAddr());
            userInfoDTO.setAddress(employee.getAddress());
            userInfoDTO.setPhoneNumber(employee.getPhoneNumber());
            userInfoDTO.setJobIndicator(employee.getJobIndicator());
            userInfoDTO.setDateOfBirth(employee.getDateOfBirth());
            userInfoDTO.setNameDisplay(employee.getNameDisplay());
            userInfoDTO.setAccount(employee.getAccount());
            userInfoDTO.setDescrshort(deptShort);
            userInfoDTO.setProcessId(ProcessIdByEmplId);
            userInfoDTO.setCompany(employee.getCompany());
            userInfoDTO.setStatus(status);
            userInfoDTO.setAvatarUrl(employee.getAvatarUrl());
            userInfoDTO.setCoverImage(employee.getCoverImage());

            return userInfoDTO;
        } else {
            userInfoDTO.setEmplId(empid);
            userInfoDTO.setDeptId(employee.getDeptid());
            userInfoDTO.setDeptName(deptName);
            userInfoDTO.setRoleCode(Constants.ROLE_USER);
            userInfoDTO.setEmail(employee.getEmailAddr());
            userInfoDTO.setAddress(employee.getAddress());
            userInfoDTO.setPhoneNumber(employee.getPhoneNumber());
            userInfoDTO.setJobIndicator(employee.getJobIndicator());
            userInfoDTO.setDateOfBirth(employee.getDateOfBirth());
            userInfoDTO.setNameDisplay(employee.getNameDisplay());
            userInfoDTO.setAccount(employee.getAccount());
            userInfoDTO.setDescrshort(deptShort);
            userInfoDTO.setCompany(employee.getCompany());
            userInfoDTO.setAvatarUrl(employee.getAvatarUrl());
            userInfoDTO.setCoverImage(employee.getCoverImage());

            return userInfoDTO;
        }
    }
}
