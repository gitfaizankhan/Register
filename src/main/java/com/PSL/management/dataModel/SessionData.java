package com.PSL.management.dataModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "sessiondata")
public class SessionData {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String sessionid;
	private String sessionAccessTime;
	private String sessionEndTime;
	private String maxInInterval;
	private String username ;
	private String sessiontoken;



	public String getMaxInInterval() {
		return maxInInterval;
	}
	public void setMaxInInterval(String maxInInterval) {
		this.maxInInterval = maxInInterval;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getSessionAccessTime() {
		return sessionAccessTime;
	}
	public void setSessionAccessTime(String sessionAccessTime) {
		 this.sessionAccessTime = sessionAccessTime;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSessiontoken() {
		return sessiontoken;
	}
	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}
	@Override
	public String toString() {
		return "SessionData [id=" + id + ", sessionid=" + sessionid + ", sessionAccessTime=" + sessionAccessTime
				+ ", sessionEndTime=" + sessionEndTime + ", maxInInterval=" + maxInInterval + ", username=" + username
				+ ", sessiontoken=" + sessiontoken + "]";
	}

}
