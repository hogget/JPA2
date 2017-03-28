package com.capgemini.dao;

import java.util.List;

import com.capgemini.to.ProfileTo;
import com.capgemini.entity.ProfileEntity;
import com.capgemini.exception.UserValidationException;

public interface ProfileDao extends Dao <ProfileEntity, Long>{

	public ProfileTo getProfile(long userId) throws UserValidationException;
	public void save(ProfileTo profile);
	public List<ProfileTo> getProfiles();
}
