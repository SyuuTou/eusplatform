package cn.mldn.eusplatform.vo;

import java.io.Serializable;

public class ScheduleEmp implements Serializable {
	private Long seid;
	private Long sid;
	private String eid;
	public Long getSeid() {
		return seid;
	}
	public void setSeid(Long seid) {
		this.seid = seid;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
}
