package com.capgemini.dao;

import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.capgemini.entity.ChallangeEntity;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

public interface ChallangeDao extends Dao<ChallangeEntity, Long> {
	void save(ChallangeTo challange);

	ChallangeTo acceptChallange(ChallangeTo challange) throws ChallangeValidationException;

	List<ChallangeTo> getListOfChallanges() throws InvalidDataAccessApiUsageException;

	ChallangeTo cancellChallange(ChallangeTo challange) throws ChallangeValidationException;

	List<ProfileTo> challangesWithRequiredLevelAndNumberOfPlays(UserTo userFrom);

	List<UserTo> usersWithSimilarNumberOfGames(UserTo userTo, UserTo userFrom);
	
	List<ChallangeTo>findChallangesOfOneUser(UserTo userFrom);
} 
