package cn.mldn.eusplatform.vo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.util.factory.Factory;

@SuppressWarnings("serial")
public class Emp implements Serializable {
	private String eid ;
	private Long lid ;
	private Long did ;
	private String ename ;
	private Double salary ;
	private String phone ;
	private String password ;
	private String photo ;
	private String note ;
	private Date hiredate ;
	private String ineid ;
	private Integer locked ;
	private String levelName;
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", lid=" + lid + ", did=" + did + ", ename=" + ename + ", salary=" + salary
				+ ", phone=" + phone + ", password=" + password + ", photo=" + photo + ", note=" + note + ", hiredate="
				+ hiredate + ", ineid=" + ineid + ", locked=" + locked + "]";
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public Long getLid() {
		return lid;
	}
	public void setLid(Long lid) {
		this.lid = lid;
	}
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getIneid() {
		return ineid;
	}
	public void setIneid(String ineid) {
		this.ineid = ineid;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	} 
	

	
}
