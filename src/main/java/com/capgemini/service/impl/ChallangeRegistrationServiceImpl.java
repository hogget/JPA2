package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;
import com.capgemini.dao.ChallangeDao;
import com.capgemini.dao.UserDao;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.ChallangeCreationService;
import com.capgemini.service.ChallangeRegistrationService;
import com.capgemini.service.ChallangeValidationService;

@Service

public class ChallangeRegistrationServiceImpl implements ChallangeRegistrationService {

	@Autowired
	private ChallangeDao challangeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ChallangeValidationService validationService;
	@Autowired
	private ChallangeCreationService creationService;

	@Override
	public ChallangeTo registerChallange(UserTo userFrom, UserTo userTo)
			throws ChallangeValidationException, UserValidationException {
		ChallangeTo newChallange = creationService.create(userFrom, userTo);
		challangeDao.save(newChallange);
		actualizeUserTo(userTo, newChallange);
		return newChallange;
	}

	public ChallangeTo registerChallange(ChallangeTo newChallange)
			throws ChallangeValidationException, UserValidationException {
		challangeDao.save(newChallange);
		actualizeUserTo(newChallange.getUserTo(), newChallange);
		return newChallange;
	}

	public UserTo actualizeUserTo(UserTo userTo, ChallangeTo newChallange) {
		UserTo user = userDao.getUser(userTo.getId());
		user.getChallanges().add(newChallange);
		return user;
	}

	public List<ChallangeTo> getPossibleChallanges(UserTo userFrom)
			throws ChallangeValidationException, UserValidationException {

		List<UserTo> opponentList = userDao.getUsers();
		long seed = System.nanoTime();
		Collections.shuffle(opponentList, new Random(seed));

		List<ChallangeTo> challengeList = new ArrayList<ChallangeTo>();
		for (UserTo opponent : opponentList) {
			if (validationService.validateChallange(userFrom, opponent))
				challengeList.add(creationService.create(userFrom, opponent));
		}
		return challengeList;
	}

	public ChallangeTo acceptChallenge(ChallangeTo challenge) throws ChallangeValidationException {
		challangeDao.acceptChallange(challenge);
		return challenge;
	}

	public ChallangeTo cancellChallenge(ChallangeTo challenge) throws ChallangeValidationException {
		challangeDao.cancellChallange(challenge);
		return challenge;
	}

	public ChallangeTo selectChallenge(ChallangeTo challenge, List<ChallangeTo> challangesList)
			throws ChallangeValidationException, UserValidationException {

		for (ChallangeTo chall : challangesList) {
			if (chall.equals(challenge)) {
				return chall;
			}
		}
		throw new ChallangeValidationException("Challange doesn't exist");
	}

}
