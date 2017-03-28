package com.capgemini.to;

import com.capgemini.entity.ChallangeStatus;

public class ChallangeTo {

	private UserTo userFrom;
	private UserTo userTo;
	private ChallangeStatus state;
	private Long id;
	
	public ChallangeTo(){	    
	}
	
	public ChallangeTo(UserTo userFrom, UserTo userTo){
	    this.userFrom = userFrom;
	    this.userTo = userTo;
	    this.state = ChallangeStatus.WAITING;
	}
	
	public UserTo getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(UserTo userFrom) {
		this.userFrom = userFrom;
	}
	public UserTo challenge() {
		return userTo;
	}
	public void setUserTo(UserTo userTo) {
		this.userTo = userTo;
	}
	public UserTo getUserTo() {
        return userTo;
    }
	public ChallangeStatus getState() {
		return state;
	}
	public void setState(ChallangeStatus state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userFrom == null) ? 0 : userFrom.hashCode());
		result = prime * result + ((userTo == null) ? 0 : userTo.hashCode());
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
		ChallangeTo other = (ChallangeTo) obj;
		if (state != other.state)
			return false;
		if (userFrom == null) {
			if (other.userFrom != null)
				return false;
		} else if (!userFrom.equals(other.userFrom))
			return false;
		if (userTo == null) {
			if (other.userTo != null)
				return false;
		} else if (!userTo.equals(other.userTo))
			return false;
		return true;
	}	
}
