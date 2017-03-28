package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.entity.ChallangeStatus;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.exception.UserValidationException;
import com.capgemini.service.impl.ChallangeValidationServiceImpl;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@RunWith(MockitoJUnitRunner.class)
public class ChallangeValidationServiceImplTest {
	
	@InjectMocks
	private ChallangeValidationServiceImpl service;	
	@Mock
	private ChallangeRegistrationService registrationService;
	
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
		to.setId(10L);
		to.setProfile(createProfileTo());
		return to;
	}

	private UserTo createUserFrom() {
		UserTo from = new UserTo();
		from.setId(20L);
		from.setProfile(createProfileFrom());
		return from;
	}
	private ProfileTo makeProfile(String name, String surname, String motto, int level, String aboutme, int numbOfPlays ){
		ProfileTo profile = new ProfileTo();
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
	
	@Test
	public void shouldNotValidateMakingNewChallangeNumberOfPlaysToDifferent() throws UserValidationException, ChallangeValidationException {
		// given
		UserTo userTo  = createUserTo();
		UserTo userFrom = createUserFrom();

		// when
		boolean isValidate = service.validateChallange(userFrom,  userTo);		

		// then
		assertEquals(false, isValidate);	
		}	
	
	@Test
	public void shouldValidateChallange() throws UserValidationException{
		//given
		UserTo user1 = new UserTo();
		ProfileTo profile1 = new ProfileTo();
		profile1.setLevel(3);
		user1.setProfile(profile1);
		
		UserTo user2 = new UserTo();
		ProfileTo profile2 = new ProfileTo();
		profile2.setLevel(3);
		user2.setProfile(profile1);
		
		ChallangeTo challange = new ChallangeTo();
		challange.setUserFrom(user1);
		challange.setUserTo(user2);
		challange.setState(ChallangeStatus.WAITING);
		
		//when
		boolean isValidate = service.validateChallange(user1, user2);
		
		//then
		assertEquals(false, isValidate);
	}
	
	@Test
	public void shouldNotValidateChallangeToBigLevelDiversity(){
		//given
		ProfileTo profile1 = makeProfile("Adam", "Szemionka", "Motto", 5, "Happy", 6);
		ProfileTo profile2 = makeProfile("Kinga", "Kamionka", "Motto", 15, "Sad", 24);
		UserTo user1 = makeUser(1L, "adam@one.pl", profile1);
		UserTo user2 = makeUser(2L, "kinga@edu.pl", profile2);
		
		//when
		boolean validateChallange = service.validateChallange(user1, user2);
		
		//then
		assertEquals(false, validateChallange);
				
	}

	@Test
	public void shouldNotValidateWhenChallangeIsNotInTheUsersList(){
		//given
		ProfileTo profile1 = makeProfile("Aga", "Cabak", "jdjshd", 1, "jschois", 1);
		UserTo user1 = makeUser(1L, "aksk@kdkd", profile1);
		UserTo user2 = makeUser(2l,"ajdj@kskd", profile1);
		ChallangeTo challange = new ChallangeTo();
		challange.setUserFrom(user1);
		challange.setUserTo(user2);
		challange.setState(ChallangeStatus.WAITING);
		
		//user1.getChallanges().add(challange);
		
		//when
		boolean validateChallange = service.validateChallange(user1, user2);
		//then
		assertEquals(false,validateChallange);
		
	}
}
