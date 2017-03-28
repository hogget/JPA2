package com.capgemini.to;

import java.util.ArrayList;
import java.util.List;

public class UserTo {

	private Long id;
	private String email;
	private String password;
	private ProfileTo profile;
	private List<ChallangeTo>challanges = new ArrayList<>();

	public List<ChallangeTo> getChallanges() {
		return challanges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setChallanges(List<ChallangeTo> challanges) {
		this.challanges = challanges;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProfileTo getProfile() {
		return profile;
	}

	public void setProfile(ProfileTo profile) {
		this.profile = profile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challanges == null) ? 0 : challanges.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTo other = (UserTo) obj;
		if (challanges == null) {
			if (other.challanges != null)
				return false;
		} else if (!challanges.equals(other.challanges))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
	
	
}
