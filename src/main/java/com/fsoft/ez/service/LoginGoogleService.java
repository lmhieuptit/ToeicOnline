package com.fsoft.ez.service;

import java.io.IOException;

public interface LoginGoogleService {

	String authenticationGoogleAccount(String code) throws IOException;
}
