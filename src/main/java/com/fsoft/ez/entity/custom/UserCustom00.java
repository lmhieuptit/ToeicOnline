package com.fsoft.ez.entity.custom;

import java.time.LocalDate;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author TuanHA10
 *
 */
@Data
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@SqlResultSetMapping(
                name = "UserCustomListResult",
                classes = {
                    @ConstructorResult(
                                    targetClass = UserCustom00.class,
                                    columns = {
                                        @ColumnResult(name = "emplid", type = String.class),
                                        @ColumnResult(name = "account", type = String.class),
                                        @ColumnResult(name = "email_addr", type = String.class),
                                        @ColumnResult(name = "sex", type = String.class),
                                        @ColumnResult(name = "name_display", type = String.class),
                                        @ColumnResult(name = "job_indicator", type = String.class),
                                        @ColumnResult(name = "descr", type = String.class),
                                        @ColumnResult(name = "code_role", type = String.class)
                                    })
                })
public class UserCustom00 {
    private String userId;
    private String account;
    private String mailAddress;
    private String sex;
    private String nameDisplay;
    private String jobIndicator;
    private String departmentName;
    private String roleName;
    private String avatarUrl;
    private LocalDate birthday;

    public UserCustom00(String userId, String account, String mailAddress, String sex,
        String nameDisplay,
        String jobIndicator, String departmentName, String roleName) {
        super();
        this.userId = userId;
        this.account = account;
        this.mailAddress = mailAddress;
        this.sex = sex;
        this.nameDisplay = nameDisplay;
        this.jobIndicator = jobIndicator;
        this.departmentName = departmentName;
        this.roleName = roleName;
        this.avatarUrl = "";
        this.birthday = LocalDate.now();
    }

}
