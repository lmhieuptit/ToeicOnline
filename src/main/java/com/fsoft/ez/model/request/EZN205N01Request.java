package com.fsoft.ez.model.request;

import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.ez.constant.Constants;

import lombok.Data;

@Data
public class EZN205N01Request {

    private MultipartFile coverImage;
    
	@AssertTrue(message = "{MSG_CUSTOM_004}")
	public boolean isCoverImageExtensionValid() {
		if (coverImage != null) {
			Pattern pattern = Pattern.compile(Constants.REGEX_IMAGES, Pattern.CASE_INSENSITIVE);
			return pattern.matcher("1."+FilenameUtils.getExtension(coverImage.getOriginalFilename())).find();
		}
		return true;
	}
}
