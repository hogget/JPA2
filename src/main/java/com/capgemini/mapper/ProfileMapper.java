package com.capgemini.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.to.ProfileTo;
import com.capgemini.entity.ProfileEntity;

public class ProfileMapper {

	public static ProfileTo map(ProfileEntity entity) {
		if (entity != null) {
			ProfileTo to = new ProfileTo();
			to.setId(entity.getId());
			to.setName(entity.getName());
			to.setSurname(entity.getSurname());
			to.setAboutMe(entity.getAboutMe());
			to.setLifeMotto(entity.getLifeMotto());
			to.setNumberOfPlays(entity.getNumberOfPlays());
			to.setLevel(entity.getLevel());
			return to;
		}
		return null;
	}

	public static ProfileEntity map(ProfileTo to) {
		if (to != null) {
			ProfileEntity entity = new ProfileEntity();
			entity.setId(to.getId());
			entity.setName(to.getName());
			entity.setSurname(to.getSurname());
			entity.setAboutMe(to.getAboutMe());
			entity.setLifeMotto(to.getLifeMotto());
			entity.setNumberOfPlays(to.getNumberOfPlays());
			entity.setLevel(to.getLevel());
			return entity;
		}
		return null;
	}

	public static List<ProfileTo> map2TOs(List<ProfileEntity> entities) {
		return entities.stream().map(ProfileMapper::map).collect(Collectors.toList());
	}

	public static List<ProfileEntity> map2Entities(List<ProfileTo> tos) {
		return tos.stream().map(ProfileMapper::map).collect(Collectors.toList());
	}
}
