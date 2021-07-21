package com.fsoft.ez.model.request;

import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.ez.constant.Constants;

import lombok.Data;

@Data
public class EZN201N00Request {

	@NotNull(message = "{MSG_001}")
	private Long categoryId;

	@NotEmpty(message = "{MSG_001}")
	private String title;

	@NotEmpty(message = "{MSG_001}")
	private String content;

	private String summary;

	private MultipartFile thumbnail;

	private MultipartFile[] files;

	private String hashtagJsonArray;

	private Integer notificationFlag;

	private String voteTitle;

	private Long limitFlag;

	private String endDate;

	private Long numberOfChoice;

	private String answerJsonArray;

	private Integer draftFlag;

	private Integer personalFlag;

	@AssertTrue(message = "{MSG_CUSTOM_001}")
	public boolean isHaveContentAnswers() {
		return !(!StringUtils.isEmpty(voteTitle) && StringUtils.isEmpty(answerJsonArray));
	}

	@AssertTrue(message = "{MSG_CUSTOM_002}")
	public boolean isThumbnailExtensionValid() {
		if (thumbnail != null) {
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
