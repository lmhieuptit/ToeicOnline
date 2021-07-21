package com.fsoft.ez.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.dao.EZN208Dao;
import com.fsoft.ez.entity.Group;
import com.fsoft.ez.entity.GroupMember;
import com.fsoft.ez.entity.custom.EZN208001;
import com.fsoft.ez.model.response.EZN208001Response;
import com.fsoft.ez.repository.GroupMemberRepository;
import com.fsoft.ez.repository.GroupRepository;
import com.fsoft.ez.service.EZN208Service;
import com.fsoft.ez.service.UserService;

@Service
@Transactional
public class EZN208ServiceImpl implements EZN208Service {

    private EZN208Dao ezn208Dao;

    private GroupMemberRepository groupMemberRepository;

    private ModelMapper mapper;

    private UserService userService;

    private GroupRepository groupRepository;

    @Autowired
    public EZN208ServiceImpl(EZN208Dao ezn208Dao,
            GroupMemberRepository groupMemberRepository, ModelMapper mapper,
            UserService userService, GroupRepository groupRepository) {
        this.ezn208Dao = ezn208Dao;
        this.groupMemberRepository = groupMemberRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<EZN208001Response> getGroup(String keySearch) {

        String key = keySearch + "%";

        // get user login
        String emplId = this.userService.getUserInformation().getEmplId();
        Long userId = Long.valueOf(emplId);

        List<EZN208001> groupList = this.ezn208Dao.getGroup(userId, key);
        List<EZN208001Response> groupResList = this.mapper.map(groupList,
                new TypeToken<List<EZN208001Response>>() {
                }.getType());

        return groupResList;
    }

    @Override
    public List<EZN208001Response> getMyGroup() {

        // get user login
        String emplId = this.userService.getUserInformation().getEmplId();
        Long userId = Long.valueOf(emplId);

        List<EZN208001> groupList = this.ezn208Dao.getMyGroup(userId);
        List<EZN208001Response> groupResList = this.mapper.map(groupList,
                new TypeToken<List<EZN208001Response>>() {
                }.getType());

        return groupResList;
    }

    @Override
    public void joinGroup(Long groupId) {

        // get user login
        String emplId = this.userService.getUserInformation().getEmplId();
        Long userId = Long.valueOf(emplId);

        List<GroupMember> groupMemberList = this.groupMemberRepository
                .findByGroupIdAndUserId(groupId, userId);
        if ((groupMemberList == null) || (groupMemberList.size() == 0)) {
            GroupMember groupMember = new GroupMember();
            groupMember.setGroupId(groupId);
            groupMember.setUserId(userId);
            groupMember.setDeleteFlag(false);
            this.groupMemberRepository.save(groupMember);
        } else {
            GroupMember groupMember = groupMemberList.get(0);
            groupMember.setDeleteFlag(false);
            this.groupMemberRepository.save(groupMember);
        }
    }

    @Override
    public void leaveGroup(Long groupId) {

        // get user login
        String emplId = this.userService.getUserInformation().getEmplId();
        Long userId = Long.valueOf(emplId);

        List<GroupMember> groupMemberList = this.groupMemberRepository
                .findByGroupIdAndUserId(groupId, userId);
        if ((groupMemberList != null) && (groupMemberList.size() > 0)) {
            GroupMember groupMember = groupMemberList.get(0);
            groupMember.setDeleteFlag(true);
            this.groupMemberRepository.save(groupMember);
        }
    }

    @Override
    public void deleteGroup(Long groupId) {

        Group group = this.groupRepository.getGroupById(groupId);

        // get user login
        UserInfoDTO user = this.userService.getUserInformation();
        String emplId = user.getEmplId();
        String roleCode = user.getRoleCode();

        Integer roleGroup = this.groupMemberRepository.getRoleGroup(emplId,
                groupId);

        if ((roleGroup != null) && (roleCode != null)) {

            if (Constants.ROLE_ADMIN.equals(roleCode) || (roleGroup == 1)) {

                group.setDeleteFlag(true);

                List<GroupMember> memberList = this.groupMemberRepository
                        .getGroupMemberByGroupId(groupId);

                System.out.println(memberList.toString() + "++++++++++++++++");
                List<GroupMember> gmList = new ArrayList<GroupMember>();
                memberList.forEach(member -> {
                    member.setDeleteFlag(true);

                    gmList.add(member);
                });
                this.groupRepository.save(group);
                this.groupMemberRepository.saveAll(gmList);

            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        Constants.STATUS_FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    " data loi ");
        }
    }

}
