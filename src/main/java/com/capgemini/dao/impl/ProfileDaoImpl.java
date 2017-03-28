package com.capgemini.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ProfileDao;
import com.capgemini.entity.ProfileEntity;
import com.capgemini.exception.UserValidationException;
import com.capgemini.mapper.ProfileMapper;
import com.capgemini.to.ProfileTo;

@Repository
public class ProfileDaoImpl extends AbstractDao <ProfileEntity, Long> implements ProfileDao{
	
	@Override
	public ProfileTo getProfile(long userId) throws UserValidationException {
		return ProfileMapper.map(findOne(userId));
	}

	@Override
	public void save(ProfileTo profile) {
		entityManager.persist(ProfileMapper.map(profile));
	}

	@Override
	public List<ProfileTo> getProfiles() {
		return ProfileMapper.map2TOs(findAll());
	}
	
	
	
}
