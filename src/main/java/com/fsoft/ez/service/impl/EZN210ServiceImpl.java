package com.fsoft.ez.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.ez.common.utils.EZCommonUtils;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Group;
import com.fsoft.ez.entity.GroupMember;
import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.entity.TblEmployee;
import com.fsoft.ez.entity.custom.EZN210;
import com.fsoft.ez.entity.custom.EZN210N01;
import com.fsoft.ez.model.request.EZN210N002Request;
import com.fsoft.ez.model.request.EZN210N003Request;
import com.fsoft.ez.model.request.EZN210N004Request;
import com.fsoft.ez.model.request.EZN211N06Request;
import com.fsoft.ez.model.response.EZN210N001Response;
import com.fsoft.ez.model.response.EZN210N002Response;
import com.fsoft.ez.model.response.EZN210N003Response;
import com.fsoft.ez.model.response.EZN210N004Response;
import com.fsoft.ez.repository.EZN210N003Repository;
import com.fsoft.ez.repository.EZN210N004Repository;
import com.fsoft.ez.repository.EZN210N01Repository;
import com.fsoft.ez.repository.GroupMemberRepository;
import com.fsoft.ez.repository.GroupRepository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.service.EZN210Service;
import com.fsoft.ez.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN210ServiceImpl implements EZN210Service {

	private GroupRepository groupRepository;

	private GroupMemberRepository groupMemberRepository;

	private TblEmployeeRepository tblEmployeeRepository;

	private EZN210N003Repository ezn210N003Repository;

	private EZN210N004Repository ezn210N004Repository;

	private EZN210N01Repository ezn210N01Repository;

	private NotificationRepository notificationRipository;

	private TblDepartmentRepository tblDepartmentRepository;
	
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	public EZN210ServiceImpl(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository,
			TblEmployeeRepository tblEmployeeRepository, EZN210N003Repository ezn210N003Repository,
			EZN210N004Repository ezn210N004Repository, EZN210N01Repository ezn210N01Repository,
			NotificationRepository notificationRipository, EntityManager entityManager,
			TblDepartmentRepository tblDepartmentRepository, Environment env) {
		super();
		this.groupRepository = groupRepository;
		this.groupMemberRepository = groupMemberRepository;
		this.tblEmployeeRepository = tblEmployeeRepository;
		this.ezn210N003Repository = ezn210N003Repository;
		this.ezn210N004Repository = ezn210N004Repository;
		this.ezn210N01Repository = ezn210N01Repository;
		this.tblDepartmentRepository = tblDepartmentRepository;
		this.notificationRipository = notificationRipository;
		this.env = env;
	}

	/**
	 * get name and descr of group
	 *
	 * @param groupId
	 *
	 * @return EZN210N001Response response
	 *
	 */
	@Override
	public EZN210N001Response getGroupDetail(Long groupId) {

		log.info("parram groupid : {}", groupId);
		EZN210N001Response response = new EZN210N001Response();

		if (groupId != null) {

			Group group = this.groupRepository.getGroupById(groupId);

			Long count = this.groupMemberRepository.getAmountNumbers(groupId);
			log.info("groupMemberRepository.getAmountNumbers : {}", count);

			String emplId = this.userService.getUserInformation().getEmplId();
			log.info("userService.getUserInformation().getEmplId() : {}", emplId);

			Integer roleGroup = this.groupMemberRepository.getRoleGroup(emplId, groupId);

			// get list user in group
			List<EZN210N01> userGroupList = this.ezn210N01Repository.getuserList(groupId);

			// get list admin in group
			List<EZN210N01> adminGroupList = this.ezn210N01Repository.getadminList(groupId);
			if (group != null) {
				// set value response
				if (userGroupList.size() > 0) {
					response.setMemberGroupList(userGroupList);
				}

				if (adminGroupList.size() > 0) {
					response.setAdminGroupList(adminGroupList);

				}
				response.setGroupName(group.getGroupName());
				response.setDescription(group.getDescription());
				response.setCountMembers(count);
				response.setPrivacy(group.getPrivacy());
				response.setCoverImageUrl(group.getCoverImageUrl());
				response.setRoleGroup(roleGroup);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "group khong tin tai");
			}

		}

		return response;
	}

	/**
	 * get List new members
	 *
	 * @param EZN210N002Request ezn210n002Request
	 *
	 * @return EZN210N002Response response
	 */
	@Override
	public List<EZN210N002Response> getNewMembers(Long groupId) {

		List<EZN210N002Response> response = new ArrayList<>();
		if (groupId != null) {

			List<GroupMember> memberList = this.groupMemberRepository.findNewMembers(groupId);
			memberList.forEach(member -> {
				String emplId = member.getUserId().toString();
				TblEmployee employeee = this.tblEmployeeRepository.findByEmplid(emplId);
				String department = this.tblDepartmentRepository.getDeptNameByDeptId(employeee.getDeptid());
				EZN210N002Response ezn210n002Response = new EZN210N002Response();

				ezn210n002Response.setGroupId(member.getGroupId());
				ezn210n002Response.setUserId(member.getUserId());
				ezn210n002Response.setAvatarUrl(employeee.getAvatarUrl());
				ezn210n002Response.setNameDisplay(employeee.getAccount());
				ezn210n002Response.setDepartment(department);
				response.add(ezn210n002Response);
			});

		}
		return response;
	}

	/**
	 * get notifications
	 *
	 * @param EZN210N003Request request
	 *
	 * @return List notification
	 *
	 */
	@Override
	public List<EZN210N003Response> getNotifications(EZN210N003Request request) {
		List<EZN210N003Response> responseList = new ArrayList<EZN210N003Response>();
		if (request != null) {
			Long groupId = request.getGroupId();
			responseList = this.ezn210N003Repository.getNotifications(groupId);
		}
		return responseList;
	}

	/**
	 * get suggest members
	 *
	 * @return List members
	 *
	 */
	@Override
	public List<EZN210N004Response> getSuggestMembers(String keySearch, Long groupId) {

		List<EZN210> ezn210List = this.ezn210N004Repository.getSuggestMembers(keySearch);

		List<EZN210N004Response> reponse = new ArrayList<EZN210N004Response>();
		ezn210List.forEach(ezn210 -> {
			EZN210N004Response ezn210n004Response = new EZN210N004Response();

			List<Long> groupIdList = this.groupMemberRepository.getGroupId(ezn210.getEmplid());

			Boolean flag = false;
			for (Long i : groupIdList) {
				if (groupId == i) {
					flag = true;
					break;
				}
			}
			ezn210n004Response.setDescr(ezn210.getDescr());
			ezn210n004Response.setEmplid(ezn210.getEmplid());
			ezn210n004Response.setJobIndicator(ezn210.getJobIndicator());
			ezn210n004Response.setNameDisplay(ezn210.getNameDisplay());
			ezn210n004Response.setIsGroupMember(flag);
			ezn210n004Response.setAccount(ezn210.getAccount());

			reponse.add(ezn210n004Response);
		});
		return reponse;
	}

	/**
	 * set cover image
	 *
	 * @param EZN211N06Request request
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void setCoverImage(EZN211N06Request request) throws Exception {
		log.info("parram request : {}", request.toString());

		Long groupId = request.getGroupId();

		if (!request.getCoverImage().isEmpty()) {
			
			String filePath = EZCommonUtils.saveImagesNews(request.getCoverImage(), env);
			this.groupRepository.updateCoverImageById(filePath, groupId);

		}

	}

	/**
	 * add member
	 *
	 * @param request
	 */
	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void addMemberGroup(EZN210N004Request request) throws Exception {

		int countGroupMember = this.groupMemberRepository.countMemberGroup(request.getGroupId(), request.getUserId());

		if (countGroupMember == 0) {

			GroupMember groupMember = new GroupMember();
			groupMember.setGroupId(request.getGroupId());
			groupMember.setRoleGroup(0);
			groupMember.setUserId(request.getUserId());
			groupMember.setDeleteFlag(false);
			groupMember.setCreateDate(LocalDateTime.now());
			this.groupMemberRepository.save(groupMember);

			// insert notification
			if (this.groupMemberRepository.save(groupMember).getGroupMemberId() != null) {
				Notification notification = new Notification();

				notification.setTypeNotifi(Constants.NEW_MEMBER_JOIN_GROUP);
				notification.setMemberId(request.getUserId().toString());

				List<Long> memberList = this.groupMemberRepository.getUserIdByGroupId(request.getGroupId());
				String json = null;
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(memberList);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				notification.setToUser(json);

				this.notificationRipository.save(notification);
			}

		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.MSG_012);
		}

	}
}
