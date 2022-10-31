package com.PSL.management.employeeModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "employeemonthlypresentdata")
public class EmployeeMonthlyStatusEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String employeeusername;
	private String leavenumber;
	private String absentnumber;
	private String wfhnumber;
	private String presentnumber;
	private String totalworkingdays;
	public EmployeeMonthlyStatusEntity() {
		super();
	}
	public EmployeeMonthlyStatusEntity(Long id, String employeeusername, String leavenumber, String absentnumber,
			String wfhnumber, String presentnumber, String totalworkingdays) {
		super();
		this.id = id;
		this.employeeusername = employeeusername;
		this.leavenumber = leavenumber;
		this.absentnumber = absentnumber;
		this.wfhnumber = wfhnumber;
		this.presentnumber = presentnumber;
		this.totalworkingdays = totalworkingdays;
	}
	@Override
	public String toString() {
		return "EmployeeMonthlyStatusEntity [id=" + id + ", employeeusername=" + employeeusername + ", leavenumber="
				+ leavenumber + ", absentnumber=" + absentnumber + ", wfhnumber=" + wfhnumber + ", presentnumber="
				+ presentnumber + ", totalworkingdays=" + totalworkingdays + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeusername() {
		return employeeusername;
	}
	public void setEmployeeusername(String employeeusername) {
		this.employeeusername = employeeusername;
	}
	public String getLeavenumber() {
		return leavenumber;
	}
	public void setLeavenumber(String leavenumber) {
		this.leavenumber = leavenumber;
	}
	public String getAbsentnumber() {
		return absentnumber;
	}
	public void setAbsentnumber(String absentnumber) {
		this.absentnumber = absentnumber;
	}
	public String getWfhnumber() {
		return wfhnumber;
	}
	public void setWfhnumber(String wfhnumber) {
		this.wfhnumber = wfhnumber;
	}
	public String getPresentnumber() {
		return presentnumber;
	}
	public void setPresentnumber(String presentnumber) {
		this.presentnumber = presentnumber;
	}
	public String getTotalworkingdays() {
		return totalworkingdays;
	}
	public void setTotalworkingdays(String totalworkingdays) {
		this.totalworkingdays = totalworkingdays;
	}
}
