package com.fsoft.ez.listener;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fsoft.ez.entity.TblEmployee;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		String id = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmplid();
		return Optional.ofNullable(id);
	}

}
