package cn.mldn.eusplatform.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Action implements Serializable {
	private Long actid ;
	private Long rid ;
	private String title ;
	@Override
	public String toString() {
		return "Action [actid=" + actid + ", rid=" + rid + ", title=" + title + "]";
	}
	public Long getActid() {
		return actid;
	}
	public void setActid(Long actid) {
		this.actid = actid;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
