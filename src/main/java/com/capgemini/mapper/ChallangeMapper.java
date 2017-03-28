package com.capgemini.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.to.ChallangeTo;
import com.capgemini.to.ProfileTo;
import com.capgemini.entity.ChallangeEntity;
import com.capgemini.entity.ChallangeStatus;
import com.capgemini.entity.ProfileEntity;

public class ChallangeMapper {
	public static ChallangeTo map(ChallangeEntity entity) {
		if (entity != null) {
			ChallangeTo to = new ChallangeTo();
			to.setUserTo(UserMapper.map(entity.getUserTo()));
			to.setUserFrom(UserMapper.map(entity.getUserFrom()));
			to.setState(entity.getState());	
			to.setId(entity.getId());
			return to;
		}
		return null;
	}

	public static ChallangeEntity map(ChallangeTo to) {
		if (to != null) {
			ChallangeEntity entity = new ChallangeEntity();
			entity.setUserFrom(UserMapper.map(to.getUserFrom()));
			entity.setUserTo(UserMapper.map(to.getUserTo()));
			entity.setState(to.getState());
			entity.setId(to.getId());
			return entity;
		}
		return null;
	}

	public static List<ChallangeTo> map2TOs(List<ChallangeEntity> entities) {
		return entities.stream().map(ChallangeMapper::map).collect(Collectors.toList());
	}

	public static List<ChallangeEntity> map2Entities(List<ChallangeTo> tos) {
		return tos.stream().map(ChallangeMapper::map).collect(Collectors.toList());
	}

}
