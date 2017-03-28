package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.ChallangeDao;
import com.capgemini.entity.ChallangeEntity;
import com.capgemini.entity.ChallangeStatus;
import com.capgemini.entity.ProfileEntity;
import com.capgemini.entity.UserEntity;
import com.capgemini.exception.ChallangeValidationException;
import com.capgemini.mapper.ChallangeMapper;
import com.capgemini.mapper.ProfileMapper;
import com.capgemini.mapper.UserMapper;
import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.to.UserTo;

@Repository
public class ChallangeDaoImpl extends AbstractDao<ChallangeEntity, Long> implements ChallangeDao {

	@Override
	public ChallangeTo acceptChallange(ChallangeTo challange) throws ChallangeValidationException {
		List<ChallangeTo> listOfChallanges = ChallangeMapper.map2TOs(findAll());

		for (int i = 0; i < listOfChallanges.size(); i++) {
			ChallangeTo chall = listOfChallanges.get(i);
			if (chall.getUserFrom().equals(challange.getUserFrom())
					&& chall.getUserTo().equals(challange.getUserTo())) {
				listOfChallanges.get(i).setState(ChallangeStatus.ACCEPTED);
				return listOfChallanges.get(i);
			}
		}
		throw new ChallangeValidationException("Challange does not exists");
	}

	@Override
	public ChallangeTo cancellChallange(ChallangeTo challange) throws ChallangeValidationException {
		List<ChallangeTo> listOfChallanges = ChallangeMapper.map2TOs(findAll());
		for (int i = 0; i < listOfChallanges.size(); i++) {
			ChallangeTo chall = listOfChallanges.get(i);
			if (chall.getUserFrom().equals(challange.getUserFrom())
					&& chall.getUserTo().equals(challange.getUserTo())) {
				listOfChallanges.get(i).setState(ChallangeStatus.CANCELLED);
				return listOfChallanges.get(i);
			}
		}
		throw new ChallangeValidationException("Challange does not exists");
	}

	@Override
	public void save(ChallangeTo challange) {
		save(ChallangeMapper.map(challange));
	}

	@Override
	public List<ChallangeTo> getListOfChallanges() throws InvalidDataAccessApiUsageException {
		List<ChallangeTo> map2tOs = ChallangeMapper.map2TOs(findAll());
		if (map2tOs != null) {
			return ChallangeMapper.map2TOs(findAll());
		}
		throw new InvalidDataAccessApiUsageException("ThereAreNoChallanges");
	}

	@Override
	public List<ProfileTo> challangesWithRequiredLevelAndNumberOfPlays(UserTo userFrom) {
//		TypedQuery<ProfileEntity> query = entityManager.createQuery(
//				"SELECT p_to.id, p_to.level, p_to.number_of_plays, p_to.name, p_to.surname FROM User u_from "
//						+ " JOIN Challange c on c.user_from_id = u_from.id JOIN profile p_from on u_from.profile_id = p_from.id "
//						+ " JOIN User u_to on c.user_to_id = u_to.id JOIN profile p_to on u_to.profile_id = p_to.id "
//						+ " WHERE u_from.id = :userFromId and ABS(p_from.level - p_to.level) <= 5 "
//						+ " AND ABS(p_from.number_of_plays -p_to.number_of_plays) < 0.1*p_from.number_of_plays order by p_to.id",
//				ProfileEntity.class);
		TypedQuery<ProfileEntity> query = entityManager.createQuery(
				"SELECT u.profile FROM UserEntity u "
						+ " JOIN ChallangeEntity c on c.user_from_id = u_from.id "
						+ " WHERE u.id = :userFromId and ABS(c.userFrom.profile.level - c.userTo.profile.level) <= 5 "
						+ " AND ABS(c.userFrom.profile.number_of_plays -c.userTo.profile.number_of_plays) < 0.1*c.userFrom.profile.number_of_plays order by c.userTo.profile.id",
				ProfileEntity.class);
		UserEntity userEntity = UserMapper.map(userFrom);
		query.setParameter("userFromId", userEntity.getId());
		List<ProfileTo> resultList = ProfileMapper.map2TOs(query.getResultList());
		return resultList;
	}

	@Override
	public List<UserTo> usersWithSimilarNumberOfGames(UserTo userTo, UserTo userFrom) {
		TypedQuery<UserEntity> query = entityManager.createQuery("Select u FROM UserEntity u where 8 ", UserEntity.class);
		query.setParameter("userFromGames", userFrom.getProfile().getNumberOfPlays());
		query.setParameter("userToGames", userTo.getProfile().getNumberOfPlays());
		List<UserTo> resultList = UserMapper.map2TOs(query.getResultList());
		return resultList;
	}

	@Override
	public List<ChallangeTo> findChallangesOfOneUser(UserTo userFrom) {
		TypedQuery<ChallangeEntity> query = entityManager.createQuery(
				"Select c from ChallangeEntity c where c.userFrom.id = :userFromId",
				ChallangeEntity.class);
		query.setParameter("userFromId", userFrom.getId());
		List<ChallangeTo> resultList = ChallangeMapper.map2TOs(query.getResultList());
		return resultList;
	}

}
