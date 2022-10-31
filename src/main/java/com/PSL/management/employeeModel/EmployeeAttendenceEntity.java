package com.PSL.management.employeeModel;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Employeeattendencedata")
public class EmployeeAttendenceEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String employeename;
	private String employeeid;
	
	@Temporal(TemporalType.DATE)
	private Date attendencedate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date entrytime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date exittime;

	public EmployeeAttendenceEntity() {
		super();
	}

	public EmployeeAttendenceEntity(Long id, String employeename, String employeeid, Date attendencedate,
			Date entrytime, Date exittime) {
		super();
		this.id = id;
		this.employeename = employeename;
		this.employeeid = employeeid;
		this.attendencedate = attendencedate;
		this.entrytime = entrytime;
		this.exittime = exittime;
	}

	@Override
	public String toString() {
		return "EmployeeAttendenceEntity [id=" + id + ", employeename=" + employeename + ", employeeid=" + employeeid
				+ ", attendencedate=" + attendencedate + ", entrytime=" + entrytime + ", exittime=" + exittime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public Date getAttendencedate() {
		return attendencedate;
	}

	public void setAttendencedate(Date attendencedate) {
		this.attendencedate = attendencedate;
	}

	public Date getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	public Date getExittime() {
		return exittime;
	}

	public void setExittime(Date exittime) {
		this.exittime = exittime;
	}




	
}
