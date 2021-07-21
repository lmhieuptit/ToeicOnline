package com.fsoft.ez.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.ez.common.exception.NotFoundException;
import com.fsoft.ez.common.utils.EZCommonUtils;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.Group;
import com.fsoft.ez.entity.GroupMember;
import com.fsoft.ez.entity.custom.EZN206001;
import com.fsoft.ez.model.request.EZN206001Request;
import com.fsoft.ez.model.request.EZN206002Request;
import com.fsoft.ez.repository.EZN206001Repository;
import com.fsoft.ez.repository.GroupMemberRepository;
import com.fsoft.ez.repository.GroupRepository;
import com.fsoft.ez.service.EZN206Service;
import com.fsoft.ez.service.UserService;

@Service
@Transactional
public class EZN206ServiceImpl implements EZN206Service {

	private GroupRepository groupRepository;

	private GroupMemberRepository groupMemberRepository;

	private ModelMapper mapper;
	
	private EZN206001Repository ezn206001Repository;

	private UserService userService ;
	
	private Environment env;
	
	@Autowired
	public EZN206ServiceImpl(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository,
			ModelMapper mapper, EZN206001Repository ezn206001Repository, UserService userService, Environment env) {
		this.groupRepository = groupRepository;
		this.mapper = mapper;
		this.groupMemberRepository = groupMemberRepository;
		this.ezn206001Repository = ezn206001Repository;
		this.userService = userService;
		this.env = env;
	}

	@Override
	public void createGroup(EZN206001Request request) throws IOException {
		
		Group group = this.mapper.map(request, Group.class);

		// get user login 
		String emplId = userService.getUserInformation().getEmplId();
		Long userId = Long.valueOf(emplId);
		
		group.setCoverImageUrl(Constants.DEFAULT_BANNER_GROUP);
		if (request.getAvatar() != null) {
			group.setCoverImageUrl(EZCommonUtils.saveImagesNews(request.getAvatar(), env));
		}

		group.setDeleteFlag(false);
		Group newGroup = this.groupRepository.save(group);

		List<GroupMember> memberList = new ArrayList<>();

		// add admin
		GroupMember myAdmin = new GroupMember();
		myAdmin.setGroupId(newGroup.getGroupId());
		myAdmin.setUserId(userId);
		myAdmin.setRoleGroup(Constants.GROUP_ADMIN);
		myAdmin.setDeleteFlag(false);
		memberList.add(myAdmin);

		for (Long adminId : request.getAdminIdList()) {
			GroupMember admin = new GroupMember();
			admin.setGroupId(newGroup.getGroupId());
			admin.setUserId(adminId);
			admin.setRoleGroup(Constants.GROUP_ADMIN);
			admin.setDeleteFlag(false);
			memberList.add(admin);
		}

		// add member
		for (Long memberId : request.getMemberIdList()) {
			GroupMember member = new GroupMember();
			member.setGroupId(newGroup.getGroupId());
			member.setUserId(memberId);
			member.setRoleGroup(Constants.GROUP_MEMBER);
			member.setDeleteFlag(false);
			memberList.add(member);
		}

		this.groupMemberRepository.saveAll(memberList);
	}

	@Override
	@Transactional
	public void updateGroup(EZN206002Request request) throws IOException {
		
		// get user login 
		String emplId = userService.getUserInformation().getEmplId();
		Long userId = Long.valueOf(emplId);
				
		Group group = this.groupRepository.findByGroupId(request.getGroupId());
		if (group == null) {
			throw new NotFoundException("not found group " + request.getGroupId());
		}
		
		group = this.mapper.map(request, Group.class);

		if (request.getAvatar() != null) {
			group.setCoverImageUrl(EZCommonUtils.saveImagesNews(request.getAvatar(), env));
		} else {
			group.setCoverImageUrl(group.getCoverImageUrl());
		}
		
		group.setDeleteFlag(false);
		Group newGroup = this.groupRepository.save(group);

		// delete all old member, admin
		this.groupMemberRepository.deleteByGroupId(request.getGroupId());

		// re-add all new member, admin
		List<GroupMember> memberList = new ArrayList<>();

		// add admin
		GroupMember myAdmin = new GroupMember();
		myAdmin.setGroupId(newGroup.getGroupId());
		myAdmin.setUserId(userId);
		myAdmin.setRoleGroup(Constants.GROUP_ADMIN);
		myAdmin.setDeleteFlag(false);
		memberList.add(myAdmin);

		for (Long adminId : request.getAdminIdList()) {
			
			GroupMember admin = new GroupMember();
			admin.setGroupId(newGroup.getGroupId());
			admin.setUserId(adminId);
			admin.setRoleGroup(Constants.GROUP_ADMIN);
			admin.setDeleteFlag(false);
			memberList.add(admin);
		}

		// add member
		for (Long memberId : request.getMemberIdList()) {
			GroupMember member = new GroupMember();
			member.setGroupId(newGroup.getGroupId());
			member.setUserId(memberId);
			member.setRoleGroup(Constants.GROUP_MEMBER);
			member.setDeleteFlag(false);
			memberList.add(member);
		}
		
		for (GroupMember member : memberList) {
			List<Long> groupMemberId = this.groupMemberRepository.selectByGroupIdAndUserId(member.getGroupId(),
					member.getUserId());

			// if member is not exits, then add new
			if (groupMemberId == null || groupMemberId.isEmpty()) {
				this.groupMemberRepository.save(member);
			}

			// if member is exits, then re-active member by set deleteFlag to 0
			else {
				this.groupMemberRepository.activeMemberByGroupIdAndUserId(member.getGroupId(), member.getUserId());
			}
		}
	}

	@Override
	public List<EZN206001> getAllUser() {
		
		// get user login 
		String emplId = userService.getUserInformation().getEmplId();
		Long userId = Long.valueOf(emplId);
		
		return this.ezn206001Repository.getAllUser(userId);
	}

	

}
