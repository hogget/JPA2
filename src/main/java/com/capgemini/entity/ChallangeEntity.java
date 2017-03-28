package com.capgemini.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CHALLANGE")
public class ChallangeEntity extends AbstractEntity{
		
	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinColumn(name="userFrom_id", nullable = true, referencedColumnName = "id")
	private UserEntity userFrom;
	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinColumn(name="userTo_id" , nullable = true, referencedColumnName = "id")
	private UserEntity userTo; 
	@Column(nullable = false, length = 45)
	@Enumerated(EnumType.STRING)
	ChallangeStatus state;
		
	public UserEntity getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(UserEntity userFrom) {
		this.userFrom = userFrom;
	}
	public UserEntity getUserTo() {
		return userTo;
	}
	public void setUserTo(UserEntity userTo) {
		this.userTo = userTo;
	}
	public ChallangeStatus getState() {
		return state;
	}
	public void setState(ChallangeStatus state) {
		this.state = state;
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
		ChallangeEntity other = (ChallangeEntity) obj;
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
