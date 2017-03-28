package com.cagemini.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;
import com.capgemini.ChessApplication;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChessApplication.class)
@Transactional
public class UserDaoImplTest {
	
	
	@Autowired
	private UserDao dao;
	@Autowired
	private UserTo user;
	@Autowired
	private ProfileTo profile;
	

	private UserTo makeUser(){
		profile = new ProfileTo();
		profile.setLevel(5);
		profile.setNumberOfPlays(10);
		profile.setName("Jan");
		profile.setSurname("Kowalski");
		profile.setAboutMe("About");
		profile.setLifeMotto("motto");
				
		user = new UserTo();
		user.setId( 1L);
		user.setEmail("abc@gmail.com");
		user.setPassword("passwd");
		user.setProfile(profile);
		return user;
	}
	
	@Test
	public void shouldFindUserWithGivenId(){
		//given
		UserEntity addUser = dao.addUser(makeUser());
	}
}
