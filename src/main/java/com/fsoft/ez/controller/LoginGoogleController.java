package com.fsoft.ez.controller;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.ez.service.LoginGoogleService;

@Controller
public class LoginGoogleController {

	@Autowired
	private Environment env;
	
	@Autowired
	private LoginGoogleService loginGoogleService;

	@GetMapping("/demo-login-google")
	public String loginGooglePage() {
		return "demo-login-google";
	}

	@GetMapping("/redirect-login-google")
	public ResponseEntity<Void> redirectLoginGoogle() {
		String redirectUri = env.getProperty("google.redirect.uri");
		String clientId = env.getProperty("google.app.id");
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(
				"https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri="+redirectUri+"&response_type=code"
						+ "&client_id="+clientId+"&approval_prompt=force"))
				.build();
	}

	@ResponseBody
	@GetMapping("/login-google")
	public Map<String, String> loginGoogle(@RequestParam("code") String code) throws IOException {
		if (code == null || code.isEmpty()) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "Đăng nhập bằng Google thất bại");
			return errorResponse;
		}

		Map<String, String> result = new HashMap<>();
		result.put("jwttoken", this.loginGoogleService.authenticationGoogleAccount(code));
		return result;
	}
}
