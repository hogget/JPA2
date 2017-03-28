package com.capgemini.service;

import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;

public interface ChallangeCreationService {
	public ChallangeTo create (UserTo userFrom, UserTo userTo) throws ChallangeValidationException, UserValidationException;
	
}
