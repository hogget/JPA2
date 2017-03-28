package com.capgemini.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.UserDao;
import com.capgemini.entity.UserEntity;
import com.capgemini.mapper.UserMapper;
import com.capgemini.to.UserTo;

@Repository
public class UserDaoImpl extends AbstractDao <UserEntity, Long> implements UserDao {
	
	public UserTo getUser(long id) {
		return UserMapper.map( findAll().get((int)id));
	}

	public UserEntity addUser(UserTo user) {
		return UserMapper.map(user);
	}
	
	public List<UserTo> getUsers() {
		List<UserTo> listOfUsersTo = UserMapper.map2TOs(findAll());
		return listOfUsersTo;
	}
}
