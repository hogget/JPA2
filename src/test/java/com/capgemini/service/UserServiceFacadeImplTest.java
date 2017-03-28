package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.dao.ProfileDao;
import com.capgemini.entity.ChallangeStatus;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.impl.UserServiceFacadeImpl;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceFacadeImplTest {

	@InjectMocks
	UserServiceFacadeImpl facade;
	@Mock
	ProfileDao profileDao;
	@Mock
	ChallangeRegistrationService registrationService;
	@Mock
	ChallangeCreationService creationService;
	
	private ProfileTo makeProfile(Long id, String name, String surname, String motto, int level, String aboutme, int numbOfPlays ){
		ProfileTo profile = new ProfileTo();
		profile.setId(id);
		profile.setName(name);
		profile.setSurname(surname);
		profile.setLifeMotto(motto);
		profile.setLevel(level);
		profile.setNumberOfPlays(numbOfPlays);
		profile.setAboutMe(aboutme);
		return profile;
	}
	
	private UserTo makeUser(Long id, String mail, ProfileTo profile){
		UserTo user = new UserTo();
		user.setId(id);
		user.setEmail(mail);
		user.setProfile(profile);
		return user;
	}
	
	private ChallangeTo makeChallange(Long id, UserTo userFrom, UserTo userTo, ChallangeStatus status){
		ChallangeTo challange = new ChallangeTo();
		challange.setState(status);
		challange.setUserFrom(userFrom);
		challange.setUserTo(userTo);
		challange.setId(id);
		return challange;
	}	

	@Test
	public void shouldGetProfileWithGivenId() throws UserValidationException{
		//given
		ProfileTo profile = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		//when
		Mockito.when(profileDao.getProfile(profile.getId())).thenReturn(profile);
		ProfileTo profile2 = facade.getProfile(profile.getId());
		//then
		assertEquals(profile,profile2);					
	}
	
		
	@Test 
	public void shouldSelectChosenChallange() throws ChallangeValidationException, UserValidationException{
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		UserTo user3 = makeUser(3L, "kdh@kdkd", profile2);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		ChallangeTo challange2 = makeChallange(2L, user2, user3, ChallangeStatus.WAITING);
		List<ChallangeTo> challanges = new ArrayList<ChallangeTo>();
		challanges.add(challange);
		challanges.add(challange2);
		
		//when
		Mockito.when(registrationService.selectChallenge(challange, challanges)).thenReturn(challange);
		ChallangeTo selectChallange = facade.selectChallange(challange, challanges);
		//then
		
		assertEquals(challange, selectChallange);
	}
		
	/*
	 * @Override
	public List<ChallangeTo> getActiveUsersChallanges(UserTo user) {
		return user.getChallanges();
	}
	 */
	@Test
	public void shouldGetListOfAllChallanges(){
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		UserTo user3 = makeUser(3L, "kdh@kdkd", profile2);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		ChallangeTo challange2 = makeChallange(2L, user2, user3, ChallangeStatus.WAITING);
		List<ChallangeTo> user2challanges = new ArrayList<ChallangeTo>();
		List<ChallangeTo> user3challanges = new ArrayList<ChallangeTo>();
		user2challanges.add(challange);
		user3challanges.add(challange2);
		user2.setChallanges(user2challanges);
		user3.setChallanges(user3challanges);
		//when
		List<ChallangeTo> possibleChallanges = facade.getActiveUsersChallanges(user2);
		List<ChallangeTo> possibleChallanges1 = facade.getActiveUsersChallanges(user3);
		//then
		assertEquals(user2challanges, possibleChallanges);
		assertEquals(user3challanges, possibleChallanges1);
	}

	@Test
	public void shouldCreateNewChallange() throws ChallangeValidationException, UserValidationException{
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		//when
		Mockito.when(creationService.create(user1, user2)).thenReturn(challange);
		ChallangeTo createNewChallange = facade.createNewChallange(user1, user2);
		//then
		assertEquals(challange, createNewChallange);
	}
	
	/*
	
	@Override
	public List<ChallangeTo> getPossibleChallenges(UserTo userFrom) throws ChallangeValidationException, UserValidationException {
		return registrationService.getPossibleChallanges(userFrom);
	}
	 */

	@Test 
	public void shoulGetPossibleChallangesSendedByParticularUser() throws ChallangeValidationException, UserValidationException{
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		ProfileTo profile3 = makeProfile(3L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user3 = makeUser(3L, "kdiff@kd", profile3);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		ChallangeTo challange2 = makeChallange(1L, user1, user3, ChallangeStatus.WAITING);
		List<ChallangeTo> challanges = new ArrayList<ChallangeTo>();
		challanges.add(challange);
		challanges.add(challange2);
		//when
		Mockito.when(registrationService.getPossibleChallanges(user1)).thenReturn(challanges);
		List<ChallangeTo> activeUsersChallanges = facade.getPossibleChallenges(user1);
		//then
		assertEquals(challanges, activeUsersChallanges);
	}
	
	/*
	 * 	@Override
	public ChallangeTo acceptChallange(ChallangeTo challange) throws ChallangeValidationException {
		return registrationService.acceptChallenge(challange);
	}

	 */
	
	@Test
	public void shouldAcceptChallange() throws ChallangeValidationException{
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		ChallangeTo challangeAfter = makeChallange(1L, user1, user2, ChallangeStatus.ACCEPTED);
		
		//when
		Mockito.when(registrationService.acceptChallenge(challange)).thenReturn(challangeAfter);
		ChallangeTo acceptChallange = facade.acceptChallange(challange);
		//then
		assertEquals(challangeAfter, acceptChallange);
	}
	
	@Test
	public void shouldCancellChallange() throws ChallangeValidationException{
		//given
		ProfileTo profile1 = makeProfile(1L, "Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		UserTo user1 = makeUser(1L, "abdf@ksj", profile1);
		ProfileTo profile2 = makeProfile(2L, "Jakub","Macierewicz","live your life",5,"Tall and slim",7);
		UserTo user2 = makeUser(2L, "ksjd@kd",profile2);
		ChallangeTo challange = makeChallange(1L, user1, user2, ChallangeStatus.WAITING);
		ChallangeTo challangeAfter = makeChallange(1L, user1, user2, ChallangeStatus.CANCELLED);
		
		//when
		Mockito.when(registrationService.cancellChallenge(challange)).thenReturn(challangeAfter);
		ChallangeTo cancellChallange = facade.cancellChallange(challange);
		//then
		assertEquals(challangeAfter, cancellChallange);
	}
		
	
}

