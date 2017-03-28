package com.capgemini.service;

import com.capgemini.to.UserTo;
import com.capgemini.exception.ChallangeValidationException;

public interface ChallangeValidationService {
	public boolean validateChallange (UserTo user1, UserTo user2) throws ChallangeValidationException;	
}
