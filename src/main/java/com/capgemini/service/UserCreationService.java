package com.capgemini.service;

import com.capgemini.entity.UserEntity;
import com.capgemini.to.UserTo;

public interface UserCreationService {
	UserEntity saveUser (UserTo user);
}
