package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.dao.ChallangeDao;
import com.capgemini.dao.UserDao;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.impl.ChallangeRegistrationServiceImpl;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;


@RunWith(MockitoJUnitRunner.class)

public class ChallangeRegistrationServiceTest {

    @InjectMocks
    private ChallangeRegistrationServiceImpl registrationService;
	@Mock
	private ChallangeValidationService validationService; 
	@Mock
	private ChallangeCreationService creationService;
	@Mock
    private ChallangeDao challangeDao;
    @Mock
    private UserDao userDao;   
    @Mock
    private ChallangeTo challangeTo;
    @Before
    public void dsada(){
    	MockitoAnnotations.initMocks(this);
    }
	
    private ProfileTo createProfileTo(){
        ProfileTo to = new ProfileTo();
        to.setNumberOfPlays(140);
        return to;
    }
    
    private ProfileTo createProfileFrom(){
        ProfileTo from = new ProfileTo();
        from.setNumberOfPlays(6);
        return from;
    }   

    private UserTo createUserTo() {
        UserTo to = new UserTo();
        to.setId((long) 10);
        to.setProfile(createProfileTo());
        return to;
    }

    private UserTo createUserFrom() {
        UserTo from = new UserTo();
        from.setId((long) 20);
        from.setProfile(createProfileFrom());
        return from;
    }
    
    private ChallangeTo createChallange(){
    	ChallangeTo chall = new ChallangeTo();
    	chall.setUserFrom(createUserFrom());
    	chall.setUserTo(createUserTo());
    	return chall;
    }
    
    private ChallangeTo create2Challange(){
    	ChallangeTo chall = new ChallangeTo();
    	UserTo userFrom = new UserTo();
    	userFrom.setEmail("abdfrsg");
    	UserTo userTo = new UserTo();
    	userTo.setEmail("nananan");
    	chall.setUserFrom(userFrom);
    	chall.setUserTo(userTo);
    	return chall;
    }
 
	
	@Test 
	public void shouldRegisterNewChallangeWhenCreatorAndOponnentareKnown() throws ChallangeValidationException, UserValidationException{
	 // given
        UserTo userTo  = createUserTo();
        UserTo userFrom = createUserFrom();
        ChallangeTo challange = new ChallangeTo();
        challange.setUserTo(userTo);
        challange.setUserFrom(userFrom);
        
 		//when
        Mockito.when(creationService.create(userFrom, userTo)).thenReturn(challange);
        Mockito.when(userDao.getUser(userTo.getId())).thenReturn(userTo);
        ChallangeTo registerChallange = registrationService.registerChallange(userFrom, userTo);         
        
		//then		
		assertEquals(challange,registerChallange);
	}
	
	@Test
	public void shouldRegisterNewChallangeWhenItIsCreated() throws ChallangeValidationException, UserValidationException{
		//given
		ChallangeTo challange = createChallange();
		//when	
		Mockito.when(challangeTo.getUserTo()).thenReturn(challange.getUserTo());
		Mockito.when(userDao.getUser(challange.getUserTo().getId())).thenReturn(challange.getUserTo());
		ChallangeTo registerChallange = registrationService.registerChallange(challange);
		//then
		assertEquals(challange, registerChallange);
	}
	
	@Test
	public void shouldReceivePossibleChallanges() throws ChallangeValidationException, UserValidationException{
		//given
		UserTo userFrom = createUserFrom();
		UserTo userTo = createUserTo();
		ChallangeTo challange = new ChallangeTo();
        challange.setUserTo(userTo);
        challange.setUserFrom(userFrom);
        List<UserTo> opponents = new ArrayList<UserTo> ();
        opponents.add(userTo);
        opponents.add(userFrom);	
        
        List<UserTo> possibleOpponents = new ArrayList<UserTo> ();
		possibleOpponents.add(userTo);
		//when
		Mockito.when(validationService.validateChallange(userFrom, userTo)).thenReturn(true);
		Mockito.when(creationService.create(userFrom, userTo)).thenReturn(challange);
		Mockito.when(userDao.getUsers()).thenReturn(opponents);
		List<ChallangeTo> possibleChallanges = registrationService.getPossibleChallanges(userFrom);
		//then
		assertNotNull(possibleChallanges);
	}
	
	@Test
	public void shouldAcceptChallange() throws ChallangeValidationException{
		//given
		ChallangeTo challange = createChallange();
		//when
		Mockito.when(challangeDao.acceptChallange(challange)).thenReturn(challange);
		ChallangeTo acceptChallenge = registrationService.acceptChallenge(challange);
		//then
		assertEquals(challange, acceptChallenge);
	}
	
	@Test
	public void shouldCancellChallange() throws ChallangeValidationException{
		//given
		ChallangeTo challange = createChallange();
		//when
		Mockito.when(challangeDao.cancellChallange(challange)).thenReturn(challange);
		ChallangeTo cancellChallange = registrationService.cancellChallenge(challange);
		//then
		assertEquals(challange, cancellChallange);
	}
	
	@Test 
	public void shouldSelectChosenChallange() throws ChallangeValidationException, UserValidationException{
		//given
		ChallangeTo challange = createChallange();
		List<ChallangeTo> challangesList = new ArrayList<ChallangeTo>();
		challangesList.add(challange);		
		//when
		ChallangeTo selectChallange = registrationService.selectChallenge(challange, challangesList);
		//then
		assertEquals(challange, selectChallange);
	}
	
	@Test(expected=ChallangeValidationException.class)
	public void sholudNotSelectChallangeThatDoNotExist() throws ChallangeValidationException, UserValidationException{
		//given
		ChallangeTo challange = createChallange();
		ChallangeTo challange2 = create2Challange();
		List<ChallangeTo> challangesList = new ArrayList<ChallangeTo>();
		challangesList.add(challange2);
		//when
		registrationService.selectChallenge(challange, challangesList);
	}
}
