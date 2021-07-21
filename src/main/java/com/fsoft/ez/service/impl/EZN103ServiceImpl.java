package com.fsoft.ez.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fsoft.ez.common.model.UserInfoDTO;
import com.fsoft.ez.common.model.request.EZN103N02RequestDTO;
import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.entity.News;
import com.fsoft.ez.entity.custom.EZN103N01;
import com.fsoft.ez.entity.custom.EZN105001;
import com.fsoft.ez.model.response.EZN103N00Response;
import com.fsoft.ez.repository.EZN103N01Repository;
import com.fsoft.ez.repository.EZN105001Repository;
import com.fsoft.ez.repository.HashtagRepository;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.TblDepartmentRepository;
import com.fsoft.ez.repository.TblEmployeeRepository;
import com.fsoft.ez.repository.VoteOptionRepository;
import com.fsoft.ez.service.EZN103Service;
import com.fsoft.ez.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EZN103ServiceImpl implements EZN103Service {

    private NewsRepository newsRepository;

    private TblDepartmentRepository tbldepartmentRepository;

    private TblEmployeeRepository TblemployeeRepository;

    private EZN103N01Repository ezn103N01Repository;

    @Autowired
    private EZN105001Repository ezn105001Repository;

    @Autowired
    private VoteOptionRepository voteOptionRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public EZN103ServiceImpl(NewsRepository newsRepository,
            TblDepartmentRepository tbldepartmentRepository,
            TblEmployeeRepository TblemployeeRepository,
            EZN103N01Repository ezn103N01Repository) {
        super();
        this.newsRepository = newsRepository;
        this.tbldepartmentRepository = tbldepartmentRepository;
        this.TblemployeeRepository = TblemployeeRepository;
        this.ezn103N01Repository = ezn103N01Repository;
    }

    /**
     * get all news
     *
     * @return ezn103N01ResponseDTOList
     *
     */
    @Override
    public List<EZN103N01> getAllNews() {

        UserInfoDTO empl = this.userService.getUserInformation();
        log.info("getUserInformation : {}", empl.toString());
        Long processId = empl.getProcessId();
        String role = empl.getRoleCode();
        List<EZN103N01> ezn103N01List = new ArrayList<EZN103N01>();

        // if current account is admin
        if (Constants.ROLE_ADMIN.equals(role)) {
            ezn103N01List = this.ezn103N01Repository.getAllNewsAdmin();
        }

        if (Constants.ROLE_USER.equals(role) && (processId != null)) {
            ezn103N01List = this.ezn103N01Repository
                    .getAllNewsApprover(processId);
        }
        if (Constants.ROLE_USER.equals(role) && (processId == null)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    Constants.STATUS_FORBIDDEN);
        }

        return ezn103N01List;
    }

    /**
     * get news detail
     *
     * @param Long
     *            newsId
     *
     * @return EZN103N00Response response
     *
     */
    @Override
    public EZN103N00Response getNewsDetail(Long newsId) {

        EZN105001 news = this.ezn105001Repository.findNewsById(newsId);

        EZN103N00Response response = new EZN103N00Response();

        List<String> optionList = this.voteOptionRepository
                .getListVoteOption(newsId);
        List<String> hagTagList = this.hashtagRepository.getListHashTag(newsId);
        // set value detail news
        response.setTitle(news.getTitle());
        response.setContent(news.getContent());
        response.setAuthor(news.getAuthor());
        response.setDescr(news.getDesrc());
        response.setType(news.getType());
        response.setFileContent(news.getAttachment());
        response.setCreateDate(news.getCreateDate());
        response.setAccount(news.getAccount());
        response.setJobIndicator(news.getJobIndicator());
        response.setStatus(news.getStatus());
        response.setApproveDate(news.getApproveDate());
        response.setQuestion(news.getQuestion());
        response.setReasonReject(news.getReasonReject());
        response.setListOption(optionList);
        response.setListHashTag(hagTagList);

        return response;
    }

    /**
     * confirm News
     *
     * @param ezn103n02RequestDTO
     *
     */
    @Override
    public void confirmNews(EZN103N02RequestDTO ezn103n02RequestDTO) {

        if (ezn103n02RequestDTO != null) {
            Long id = ezn103n02RequestDTO.getNewsId();

            News news = this.newsRepository.findNewsById(id);

            // reject news
            if (ezn103n02RequestDTO.getConfirmStatus() == 0) {

                news.setStatus(Constants.NEWS_STATUS_REJECT);
                news.setReasonReject(ezn103n02RequestDTO.getReasonReject());

                this.newsRepository.save(news);
            }

            // approve news
            if (ezn103n02RequestDTO.getConfirmStatus() == 1) {
                news.setStatus(Constants.NEWS_STATUS_APPROVED);
            }
        }
    }

}
