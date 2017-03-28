package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.UserDao;
import com.capgemini.entity.UserEntity;
import com.capgemini.mapper.UserMapper;
import com.capgemini.service.UserCreationService;
import com.capgemini.to.UserTo;

@Service
public class UserCreationServiceImpl implements UserCreationService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserEntity saveUser(UserTo user) {
		return userDao.save(UserMapper.map(user));
	
	}

}
