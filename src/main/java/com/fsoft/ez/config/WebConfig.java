package com.fsoft.ez.config;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final HttpsHandlerInterceptor handlerInterceptor;

	public WebConfig(HttpsHandlerInterceptor handlerInterceptor) {
		this.handlerInterceptor = handlerInterceptor;
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
//                        .addResourceLocations("classpath:/apps/")
//                        .addResourceLocations("classpath:/public/")
//                        .addResourceLocations("classpath:/resources/")
//                        .addResourceLocations("classpath:/manifest/");
//                        
//
//        registry.addResourceHandler("swagger-ui.html")
//                        .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                        .addResourceLocations("classpath:/META-INF/resources/webjars/")
//                        .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor);
	}
}
