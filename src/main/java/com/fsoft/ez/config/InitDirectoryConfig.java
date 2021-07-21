package com.fsoft.ez.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitDirectoryConfig {

	@Autowired
	private Environment env;
	
	@PostConstruct
	public void initFolderStatic() {
		try {
			Files.createDirectories(Paths.get(env.getProperty("directory.static.basedir")));
		} catch (IOException e) {
			log.error("Cannot create default static folder, cause: {}", e.getMessage());
		}
	}
}
