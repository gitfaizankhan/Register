package com.PSL.management.employeeModel;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Employeedata")
public class EmployeeRegistrationEntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "employeeid")
	private String employeeid;
	
	@Column(name = "employeefirstname")
	private String employeefirstname;
	
	
	@Column(name = "employeelastname")
	private String employeelastname;
	
	@Column(name = "employeeusername")
	private String employeeusername;
	
	@Column(name = "employeephonenumber")
	private String employeephonenumber;
	
	@Column(name = "employeemail")
	private String employeemial;
	
	@Column(name = "employeeaddress")
	private String employeeaddress;
	
	
	@Temporal(TemporalType.DATE)
	private Date employeeregistrationdate;
	
	
	@Temporal(TemporalType.DATE)
	private Date employeejoindate;
	
	
	@Temporal(TemporalType.DATE)
	private Date employeeleavedate;
	
	
	@Lob
	@Column(name = "employeeimage")
	private String employeeimage;
	
	
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeeimage() {
		return employeeimage;
	}
	public void setEmployeeimage(String employeeimage) {
		this.employeeimage = employeeimage;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmployeefirstname() {
		return employeefirstname;
	}
	public void setEmployeefirstname(String employeefirstname) {
		this.employeefirstname = employeefirstname;
	}
	public String getEmployeelastname() {
		return employeelastname;
	}
	public void setEmployeelastname(String employeelastname) {
		this.employeelastname = employeelastname;
	}
	public String getEmployeeusername() {
		return employeeusername;
	}
	public void setEmployeeusername(String employeeusername) {
		this.employeeusername = employeeusername;
	}
	public String getEmployeephonenumber() {
		return employeephonenumber;
	}
	public void setEmployeephonenumber(String employeephonenumber) {
		this.employeephonenumber = employeephonenumber;
	}
	public String getEmployeemial() {
		return employeemial;
	}
	public void setEmployeemial(String employeemial) {
		this.employeemial = employeemial;
	}
	public String getEmployeeaddress() {
		return employeeaddress;
	}
	public void setEmployeeaddress(String employeeaddress) {
		this.employeeaddress = employeeaddress;
	}
	public Date getEmployeeregistrationdate() {
		return employeeregistrationdate;
	}
	public void setEmployeeregistrationdate(Date employeeregistrationdate) {
		this.employeeregistrationdate = employeeregistrationdate;
	}
	public Date getEmployeejoindate() {
		return employeejoindate;
	}
	public void setEmployeejoindate(Date date) {
		this.employeejoindate = date;
	}
	public Date getEmployeeleavedate() {
		return employeeleavedate;
	}
	public void setEmployeeleavedate(Date date) {
		this.employeeleavedate = date;
	}
	
	@Override
	public String toString() {
		return "EmployeeRegistrationEntityModel [id=" + id + ", employeeid=" + employeeid + ", employeefirstname="
				+ employeefirstname + ", employeelastname=" + employeelastname + ", employeeusername="
				+ employeeusername + ", employeephonenumber=" + employeephonenumber + ", employeemial=" + employeemial
				+ ", employeeaddress=" + employeeaddress + ", employeeregistrationdate=" + employeeregistrationdate
				+ ", employeejoindate=" + employeejoindate + ", employeeleavedate=" + employeeleavedate
				+ ", employeeimage=" + employeeimage + "]";
	}
	

}




