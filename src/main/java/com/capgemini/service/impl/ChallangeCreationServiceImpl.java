package com.capgemini.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.ChallangeStatus;
import com.capgemini.service.ChallangeCreationService;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;

@Service

public class ChallangeCreationServiceImpl implements ChallangeCreationService {

	public ChallangeTo create(UserTo userFrom, UserTo userTo){
			return createChallange(userFrom, userTo);			
	}

	private ChallangeTo createChallange(UserTo userFrom, UserTo userTo) {
		ChallangeTo challange = new ChallangeTo();
		challange.setUserFrom(userFrom);
		challange.setUserTo(userTo);
		challange.setState(ChallangeStatus.WAITING); //wrzuci ale nie skomituje
		return challange;		
	}
	
	//flush sprawdz wersje 

}
