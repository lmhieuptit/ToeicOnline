package com.fsoft.ez.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * create group
 *
 * @author DanNT3
 *
 */
@Data
public class EZN206001Request {

    @NotEmpty(message = "{MSG_0001}")
    private String groupName;

    private String description;

    /**
     * 0: group mở, 1: group kín
     */
    @NotNull(message = "{MSG_0001}")
    @Min(value = 0, message = "{MSG_MIN} 0")
    @Max(value = 1, message = "{MSG_MAX} 1")
    private Integer privacy;

    private MultipartFile avatar;

    private List<Long> adminIdList = new ArrayList<Long>();

    private List<Long> memberIdList = new ArrayList<Long>();
}
