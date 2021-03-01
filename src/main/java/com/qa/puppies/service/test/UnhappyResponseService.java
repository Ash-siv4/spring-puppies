package com.qa.puppies.service.test;

import org.springframework.stereotype.Service;

@Service
public class UnhappyResponseService implements ResponseService {

	@Override
	public String generateResponse() {
		// TODO Auto-generated method stub
		return "Life is pain";
	}

}
