package com.PSL.management.employeeModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "employeeabsentdata")
public class EmployeeAbsentDetails {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	private String employeename;
	private String employeeid;
	
	@Column(name = "offType")
	private String leave;

	@Temporal(TemporalType.DATE)
	private Date emloyeeabsentdate;

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

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public Date getEmmloyeeabsentdate() {
		return emloyeeabsentdate;
	}

	public void setEmmloyeeabsentdate(Date date) {
		this.emloyeeabsentdate = date;
	}

	@Override
	public String toString() {
		return "EmployeeAbsentDetails [id=" + id + ", employeename=" + employeename + ", employeeid=" + employeeid
				+ ", leave=" + leave + ", emloyeeabsentdate=" + emloyeeabsentdate + "]";
	}

	public EmployeeAbsentDetails(Long id, String employeename, String employeeid, String leave,
			Date emloyeeabsentdate) {
		super();
		this.id = id;
		this.employeename = employeename;
		this.employeeid = employeeid;
		this.leave = leave;
		this.emloyeeabsentdate = emloyeeabsentdate;
	}

	public EmployeeAbsentDetails() {
		super();
	}

	
}
