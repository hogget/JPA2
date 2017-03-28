package com.capgemini.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.ChallangeStatus;
import com.capgemini.service.ChallangeValidationService;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;

@Service

public class ChallangeValidationServiceImpl implements ChallangeValidationService {
	private static final int MAX_NUMBER_OF_CHALLANGERS = 5;
	private static final int DIVERSITY_LEVELS = 2;
	private static final double DIVERSITY_PLAYED_GAMES = 0.1;

	@Override
	public boolean validateChallange(UserTo user1, UserTo user2) {
		return validateNumberOfChallanges(user1) && validateNumberOfChallanges(user2)
				&& validateSimilarNumberOfPlayedGames(user1, user2) && isSimilarLevel(user1, user2)
				&& validateExistingChallange(user1, user2);
	}

	private boolean isSimilarLevel(UserTo user1, UserTo user2) {
		int diversity = Math.abs(user1.getProfile().getLevel() - user2.getProfile().getLevel());
		return diversity <= DIVERSITY_LEVELS;
	}

	private boolean validateSimilarNumberOfPlayedGames(UserTo user1, UserTo user2) {
		long gamesPlayedUser1 = user1.getChallanges().stream().filter(ch -> ch.getState() == ChallangeStatus.ACCEPTED)
				.count();
		long gamesPlayedUser2 = user2.getChallanges().stream().filter(ch -> ch.getState() == ChallangeStatus.ACCEPTED)
				.count();
		long diversity = Math.abs(gamesPlayedUser1 - gamesPlayedUser2);
		if (diversity < gamesPlayedUser1 * DIVERSITY_PLAYED_GAMES) {
			return true;
		}
		return false;
	}

	private boolean validateNumberOfChallanges(UserTo user1) {
		long numberOfChallanges = user1.getChallanges().stream().filter(ch -> ch.getState() == ChallangeStatus.WAITING)
				.count();
		return numberOfChallanges <= MAX_NUMBER_OF_CHALLANGERS;
	}

	private boolean validateExistingChallange(UserTo user1, UserTo user2) {
		for (ChallangeTo challenge : user1.getChallanges()) {
			if (challenge.getUserTo().equals(user2) && challenge.getState() == ChallangeStatus.WAITING)
				return false;
		}
		return true;
	}

}
