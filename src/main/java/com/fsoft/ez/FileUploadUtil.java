package com.fsoft.ez;

import com.fsoft.ez.constant.Constants;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;

public class FileUploadUtil {
	
	private FileUploadUtil() {
		
	}

	public static String uploadImage(Environment env, MultipartFile file, String destination) throws IOException {

		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());

		Path absoluteFolderMultimedia = Paths.get(env.getProperty("directory.static.basedir"),
				env.getProperty("directory.static.mulltimedia-folder"), destination);
		Path urlMediaFile = Paths.get(env.getProperty("directory.static.mulltimedia-folder"), destination, fileName);

		Files.createDirectories(absoluteFolderMultimedia);
		
		if (!isValidImageExtension(fileName)) {
			return null;
		}
		
		return upload(file, absoluteFolderMultimedia.toString(), fileName) ? urlMediaFile.toString().replace("\\", "/"): null;
	}

	public static boolean upload(MultipartFile file, String destination, String fileName) {
		try {
			saveFile(file, destination, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void saveFile(MultipartFile file, String destination, String fileName) throws IOException {
		saveFile(file, Paths.get(destination, fileName));
	}

	public static void saveFile(MultipartFile file, Path filePath) throws IOException {
		file.transferTo(filePath);
	}

	public static boolean isValidImageExtension(String imageName) {
		if (imageName != null) {
			Pattern pattern = Pattern.compile(Constants.REGEX_MEDIA, Pattern.CASE_INSENSITIVE);
			return pattern.matcher(imageName).find();
		}
		return true;
	}
}
