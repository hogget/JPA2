package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;

public interface ChallangeRegistrationService  {
    public ChallangeTo  registerChallange(UserTo userFrom, UserTo userTo) throws ChallangeValidationException, UserValidationException;

    public ChallangeTo  registerChallange(ChallangeTo newChallange) throws ChallangeValidationException, UserValidationException;
    
    public UserTo actualizeUserTo(UserTo userTo, ChallangeTo newChallange);    
    
    public List<ChallangeTo> getPossibleChallanges(UserTo userFrom) throws ChallangeValidationException, UserValidationException;
    
    public ChallangeTo acceptChallenge(ChallangeTo challenge) throws ChallangeValidationException;
        
    public ChallangeTo cancellChallenge(ChallangeTo challenge) throws ChallangeValidationException;
    
    public ChallangeTo selectChallenge(ChallangeTo challenge, List<ChallangeTo> challangesList) throws ChallangeValidationException, UserValidationException;
        

}
