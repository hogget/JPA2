package com.cagemini.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ChessApplication;
import com.capgemini.dao.ChallangeDao;
import com.capgemini.dao.ProfileDao;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.ChallangeStatus;
import com.capgemini.entity.UserEntity;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.mapper.ChallangeMapper;
import com.capgemini.mapper.UserMapper;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChessApplication.class)
public class ChallangeDaoImplTest {

	@Autowired
	private ChallangeDao challangeDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;
	
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
	
	private UserTo makeUser(String mail, ProfileTo profile){
		UserTo user = new UserTo();
		user.setEmail(mail);
		user.setProfile(profile);
		return user;
	}
	
	private ChallangeTo makeChallange( UserTo userFrom, UserTo userTo, ChallangeStatus status){
		ChallangeTo challange = new ChallangeTo();
		challange.setState(status);
		challange.setUserFrom(userFrom);
		challange.setUserTo(userTo);

		return challange;
	}
	
	@Test
	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void shouldSaveChallange() throws ChallangeValidationException  {
		// given
		ProfileTo profile = makeProfile("Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		profileDao.save(profile);
		UserTo user1 = makeUser("user1@is.com",profile);
		userDao.addUser(user1);
		
		ProfileTo profile2 = makeProfile("Adam","Jedrzejczak","akjdis",2,"abracadabra",14);
		profileDao.save(profile);
		UserTo user2 = makeUser("user2@is.com", profile2);
		userDao.addUser(user2);
	
		ChallangeTo challange = makeChallange( user1,user2, ChallangeStatus.WAITING);
		challangeDao.save(challange);		
		// when	
		ChallangeTo actualChallange = ChallangeMapper.map(challangeDao.findOne(11L));
			
		// then
		assertNotNull(actualChallange);
		}	

	@Test
	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void shouldChangeStatusOfChallangeToAccepted() throws ChallangeValidationException {
		// given
		ProfileTo profile = makeProfile("Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		profileDao.save(profile);
		UserTo user1 = makeUser("user1@is.com",profile);
		userDao.addUser(user1);
		
		ProfileTo profile2 = makeProfile("Adam","Jedrzejczak","akjdis",2,"abracadabra",14);
		profileDao.save(profile);
		UserTo user2 = makeUser("user2@is.com", profile2);
		userDao.addUser(user2);
	
		assertNotNull(user2);
		assertNotNull(user1);
		ChallangeTo challange = makeChallange( user1,user2, ChallangeStatus.WAITING);
		challangeDao.save(challange);
	
		ChallangeTo actualChallange = ChallangeMapper.map(challangeDao.findOne(11L));
		
		// when
		ChallangeTo acceptedChallange = challangeDao.acceptChallange(actualChallange);

		// then
		assertEquals(ChallangeStatus.ACCEPTED, acceptedChallange.getState());
	}	
	

	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test(expected=ChallangeValidationException.class)
	public void shouldNotChangeTheStatusOfChallangeIfChallangeHasNotWaitingStatus() throws ChallangeValidationException{
		//given
		ProfileTo profile = makeProfile("Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		profileDao.save(profile);
		UserTo user1 = makeUser("user1@is.com",profile);
		userDao.addUser(user1);
		
		ProfileTo profile2 = makeProfile("Adam","Jedrzejczak","akjdis",2,"abracadabra",14);
		profileDao.save(profile);
		UserTo user2 = makeUser("user2@is.com", profile2);
		userDao.addUser(user2);
	
		ChallangeTo challange = makeChallange( user1,user2, ChallangeStatus.CANCELLED);
		//when
		challangeDao.acceptChallange(challange);		
	}

	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test
	public void shouldReturnProfilesOfAvailableOpponents(){
		//given
		UserEntity userFrom = userDao.findOne(1L);
		List<ProfileTo> challangesWithRequiredLevel = challangeDao.challangesWithRequiredLevelAndNumberOfPlays(UserMapper.map(userFrom));	
		//that
		assertEquals(3, challangesWithRequiredLevel.size());
	}
	
	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test
	public void shouldChangeStatusOfChallangeToCancelled() throws ChallangeValidationException {
		// given
		ProfileTo profile = makeProfile("Anna","Kostrzewa","live your life",2,"Tall and slim",12);
		profileDao.save(profile);
		UserTo user1 = makeUser("user1@is.com",profile);
		userDao.addUser(user1);
		
		ProfileTo profile2 = makeProfile("Adam","Jedrzejczak","akjdis",2,"abracadabra",14);
		profileDao.save(profile);
		UserTo user2 = makeUser("user2@is.com", profile2);
		userDao.addUser(user2);
	
		assertNotNull(user2);
		assertNotNull(user1);
		ChallangeTo challange = makeChallange( user1,user2, ChallangeStatus.WAITING);
		challangeDao.save(challange);
	
		ChallangeTo actualChallange = ChallangeMapper.map(challangeDao.findOne(11L));
		
		// when
		ChallangeTo cancelledChallange = challangeDao.cancellChallange(actualChallange);

		// then
		assertEquals(ChallangeStatus.CANCELLED, cancelledChallange.getState());
	}

	@Test
	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void shouldFindAllChallangesOfOneUser(){
		UserTo userFrom =  UserMapper.map(userDao.findOne(1L));
		List<ChallangeTo> challangesOfOneUser = challangeDao.findChallangesOfOneUser(userFrom);
		assertEquals(4, challangesOfOneUser.size());		
	}
	
	@Test
	@Transactional
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void shouldgetListOfAllChallanges(){
		List<ChallangeTo> challanges = ChallangeMapper.map2TOs(challangeDao.findAll());
		assertEquals(12, challanges.size());
	}	
	
	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void shouldThrowInvalidDataAccessApiUsageExceptionWhenNoChallangesFound() throws ChallangeValidationException{
		//given
		challangeDao.deleteAll();
		//when
		challangeDao.getListOfChallanges();		
	}	
}
