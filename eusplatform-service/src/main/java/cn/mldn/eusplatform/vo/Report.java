package cn.mldn.eusplatform.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Report implements Serializable {
	private Long srid;
	private Long sid;
	private String eid;
	private Date subdate;
	private String note;
	public Long getSrid() {
		return srid;
	}
	public void setSrid(Long srid) {
		this.srid = srid;
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
	public Date getSubdate() {
		return subdate;
	}
	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Report [srid=" + srid + ", sid=" + sid + ", eid=" + eid + ", subdate=" + subdate + ", note=" + note
				+ "]";
	}
	
}
