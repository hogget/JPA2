package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.UserEntity;
import com.capgemini.to.UserTo;

public interface UserDao extends Dao <UserEntity, Long>{
	
	public UserTo getUser(long id);
	public UserEntity addUser(UserTo user);
	public List<UserTo> getUsers();
	
	
}
