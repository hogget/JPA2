package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.UserEntity;
import com.capgemini.to.UserTo;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>{

}
