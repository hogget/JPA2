package com.cagemini.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ChessApplication;
import com.capgemini.dao.ProfileDao;
import com.capgemini.exception.UserValidationException;
import com.capgemini.mapper.ProfileMapper;
import com.capgemini.to.ProfileTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChessApplication.class)
@Transactional
public class ProfileDaoImplTets {

	@Autowired
	private ProfileDao dao;

	private ProfileTo makeProfile() {
		ProfileTo profile = new ProfileTo();
		profile.setId(4L);
		profile.setLevel(4);
		profile.setName("Adam");
		profile.setSurname("Babicz");
		return profile;
	}
	
	@Test
	public void shouldReceiveProfileWithGivenId() {
		// when
		ProfileTo actualprofile = ProfileMapper.map(dao.findOne(4L));
		// then	
		assertNotNull(actualprofile);
	}

	@Test
	public void shouldNotFindProfileAndThrowException() throws UserValidationException {		
		// when
		dao.getProfile(104L);
	}


}
