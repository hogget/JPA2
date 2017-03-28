package com.capgemini.service;

import java.util.List;

import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;

public interface UserServiceFacade {

	/**
	 * This methode creates new Challange using ChallangeCreationService. 
	 * @return 
	 * @throws UserValidationException 
	 * @params
	 * userFrom, UserTo
	 * userFrom and UserTo are arguments that keep essential information to create @Challange
	 * Those information are needed because ChallangeCreationService is invoking to ChallangeValidationService
	 * @returns
	 * ChallangeTo
	 * It returns new Challange  
	 */
	public ChallangeTo createNewChallange(UserTo userFrom, UserTo userTo) throws ChallangeValidationException, UserValidationException;
	
	/**
	 * The only responsibility of this methode is to save new Challange in database, which is represented by list in ChallangeDaoList
	 * @param userFrom, UserTo
	 * userFrom and UserTo are arguments that keep essential information to create Challange
	 * it does not return anything
	 * @return 
	 */
	List<ChallangeTo> getPossibleChallenges(UserTo userFrom)  throws ChallangeValidationException, UserValidationException;
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws UserValidationException
	 */
	public ProfileTo getProfile(long id) throws UserValidationException;
	
	/**
	 * 
	 * @param challange
	 * @return 
	 * @throws ChallangeValidationException 
	 */
	public ChallangeTo acceptChallange(ChallangeTo challange) throws ChallangeValidationException;
	
	/**
	 * 
	 * @param challange
	 * @throws ChallangeValidationException 
	 */
	public ChallangeTo cancellChallange(ChallangeTo challange) throws ChallangeValidationException;
	
	/**
	 * 
	 * @param challange
	 * @return
	 */
	public List<ChallangeTo> getActiveUsersChallanges (UserTo user);

    public ChallangeTo selectChallange(ChallangeTo chosenChallange, List<ChallangeTo> challangesList) throws ChallangeValidationException, UserValidationException;
}
