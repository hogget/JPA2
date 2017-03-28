package com.capgemini.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.dao.UserDao;
import com.capgemini.entity.UserEntity;
import com.capgemini.mapper.UserMapper;
import com.capgemini.service.impl.UserCreationServiceImpl;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@RunWith(MockitoJUnitRunner.class)
public class UserCreationServiceImplTest {

	@InjectMocks
	private UserCreationServiceImpl service;

	@Mock
	UserDao userDao;

	private ProfileTo makeProfile(Long id, String name, String surname, String motto, int level, String aboutme,
			int numbOfPlays) {
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

	private UserTo makeUser(Long id, String mail, ProfileTo profile) {
		UserTo user = new UserTo();
		user.setId(id);
		user.setEmail(mail);
		user.setProfile(profile);
		return user;
	}

	@Ignore
	@Test
	public void shouldCreateUserWithProfile() {
		// given
		ProfileTo profile = makeProfile(1L, "Anna", "mm", "kisifc", 3, "aboutMe", 4);
		UserTo user = makeUser(1L, "abjd@kdd", profile);
		// when
		Mockito.when(userDao.addUser(user)).thenReturn(UserMapper.map(user));
		UserEntity saveUser = service.saveUser(user);
		// then
		// assertEquals(user.getId(), saveUser);
		assertNotNull(saveUser);
	}

}
