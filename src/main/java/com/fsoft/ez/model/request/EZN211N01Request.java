package com.fsoft.ez.model.request;

import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.ez.constant.Constants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EZN211N01Request {

    @NotNull(message = "{MSG_001}")
    private Long newsId;

    @NotNull(message = "{MSG_001}")
    private Long groupId;

    @NotNull(message = "{MSG_001}")
	private Long categoryId;

	@NotEmpty(message = "{MSG_001}")
	private String title;

	@NotEmpty(message = "{MSG_001}")
	private String content;

	private MultipartFile thumbnail;

	@ApiModelProperty("Nếu không update thumbnail, thì set trường này là 0, nếu có thì là 1")
	private Integer isUpdatedThumbnail;

	@ApiModelProperty("file đính kèm")
	private MultipartFile[] files;

	private String hashtagJsonArray;

	private Integer notificationFlag;

	private String removedAttachment;

	private String voteTitle;

	private String answerJsonArray;

	private Long numberOfChoice;

	private Integer draftFlag;

	private Integer personalFlag;

	private Long limitFlag;

	private String endDate;

	@AssertTrue(message = "{MSG_CUSTOM_001}")
	public boolean isHaveContentAnswers() {
		return !(!StringUtils.isEmpty(voteTitle) && StringUtils.isEmpty(answerJsonArray));
	}

	@AssertTrue(message = "{MSG_CUSTOM_002}")
	public boolean isThumbnailExtensionValid() {
		if (isUpdatedThumbnail != null && isUpdatedThumbnail == 1 && thumbnail != null) {
			Pattern pattern = Pattern.compile(Constants.REGEX_MEDIA, Pattern.CASE_INSENSITIVE);
			return pattern.matcher("1."+FilenameUtils.getExtension(thumbnail.getOriginalFilename())).find();
		}
		return true;
	}

	@AssertTrue(message = "{MSG_CUSTOM_003}")
	public boolean isAttachmentExtensionValid() {
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				Pattern pattern = Pattern.compile(Constants.REGEX_DOCUMENT, Pattern.CASE_INSENSITIVE);
				if (!pattern.matcher("1."+FilenameUtils.getExtension(file.getOriginalFilename())).find()) {
					return false;
				}
			}
		}
		return true;
	}

}
