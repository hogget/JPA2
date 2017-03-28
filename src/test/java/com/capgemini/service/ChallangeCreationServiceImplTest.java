package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.entity.ChallangeStatus;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.impl.ChallangeCreationServiceImpl;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.UserTo;


@RunWith(MockitoJUnitRunner.class)
public class ChallangeCreationServiceImplTest {
	@InjectMocks
	private ChallangeCreationServiceImpl service;
	
	private UserTo createUserTo() {
		UserTo to = new UserTo();
		to.setId(10L);
		return to;
	}

	private UserTo createUserFrom() {
		UserTo from = new UserTo();
		from.setId(20L);
		return from;
	}

	private ChallangeTo createChallange() {		
		ChallangeTo to = new ChallangeTo();
		to.setUserFrom(createUserFrom());
		to.setUserTo(createUserTo());
		to.setState(ChallangeStatus.WAITING);		
		return to;
	}
	
	@Test
	public void shouldCreateUserWithProfile() throws UserValidationException, ChallangeValidationException {
		// given
		ChallangeTo challange = createChallange();
		UserTo userTo  = createUserTo();
		UserTo userFrom = createUserFrom();
		
		// when
		ChallangeTo challangeFromService = service.create(userFrom,  userTo);				

		// then
		assertEquals(challange.getUserFrom(), challangeFromService.getUserFrom());
		assertEquals(challange.getUserTo(), challangeFromService.getUserTo());
		assertEquals(challange.getState(), challangeFromService.getState());
	}
	
	

}
