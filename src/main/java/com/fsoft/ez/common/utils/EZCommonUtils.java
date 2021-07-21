package com.fsoft.ez.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

public final class EZCommonUtils {
	
	private EZCommonUtils() {
		
	}

	public static final void saveFile(MultipartFile file, String... args) throws IOException {
		Path path = Paths.get(StringUtils.EMPTY);
		for (String arg : args) {
			path = path.resolve(arg);
		}
		file.transferTo(path);
	}

	public static final void saveFile(MultipartFile file, Path filePath) throws IOException {
		file.transferTo(filePath);
	}

	public static final String saveImagesNews(MultipartFile file, Environment env) throws IOException {
		String generateFileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getExtension(file.getOriginalFilename());
		Path staticFolderImage = Paths.get(env.getProperty("directory.static.basedir"),
				env.getProperty("directory.static.mulltimedia-folder"));
		Files.createDirectories(staticFolderImage);
		String filePath = Paths.get(env.getProperty("directory.static.mulltimedia-folder"), generateFileName).toString()
				.replace("\\", "/");
		saveFile(file, staticFolderImage.toString(), generateFileName);
		return filePath;
	}

	public static final String saveAttachmentNews(MultipartFile file, Environment env, Long newsId) throws IOException {
		Path staticSrcFolder = Paths.get(env.getProperty("directory.static.basedir"),
				env.getProperty("directory.static.attachment-folder"), String.valueOf(newsId));
		Files.createDirectories(staticSrcFolder);
		String generateFileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getExtension(file.getOriginalFilename());
		String filePath = Paths
				.get(env.getProperty("directory.static.attachment-folder"), String.valueOf(newsId), generateFileName)
				.toString().replace("\\", "/");
		saveFile(file, staticSrcFolder.toString(), generateFileName);
		return filePath;
	}

}
