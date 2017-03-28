package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ChallangeDao;
import com.capgemini.dao.ProfileDao;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.ChallangeCreationService;
import com.capgemini.service.ChallangeRegistrationService;
import com.capgemini.service.ChallangeValidationService;
import com.capgemini.service.UserServiceFacade;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {

	@Autowired
	ChallangeCreationService creationService;
	@Autowired
	ChallangeRegistrationService registrationService;
	@Autowired
	ChallangeValidationService validationService;
	@Autowired
	ProfileDao profileDao;
	@Autowired
	ChallangeDao challangeDao;

	@Override
	public ChallangeTo createNewChallange(UserTo userFrom, UserTo userTo) throws ChallangeValidationException, UserValidationException {
		return creationService.create(userFrom, userTo);		
	}

	@Override
	public List<ChallangeTo> getPossibleChallenges(UserTo userFrom) throws ChallangeValidationException, UserValidationException {
		return registrationService.getPossibleChallanges(userFrom);
	}

	@Override
	public ProfileTo getProfile(long id) throws UserValidationException {
		return profileDao.getProfile(id);
	}

	@Override
	public ChallangeTo acceptChallange(ChallangeTo challange) throws ChallangeValidationException {
		return registrationService.acceptChallenge(challange);
	}

	@Override
	public ChallangeTo cancellChallange(ChallangeTo challange) throws ChallangeValidationException {
		return registrationService.cancellChallenge(challange);
	}

	@Override
	public List<ChallangeTo> getActiveUsersChallanges(UserTo user) {
		return user.getChallanges();
	}

	@Override
	public ChallangeTo selectChallange( ChallangeTo chosenChallange, List<ChallangeTo> challangesList)throws ChallangeValidationException, UserValidationException {
		return registrationService.selectChallenge(chosenChallange, challangesList);		
	}

}
