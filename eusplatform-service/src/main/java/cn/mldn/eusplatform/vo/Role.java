package cn.mldn.eusplatform.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Role implements Serializable {
	private Long rid ;
	private String title ;
	
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", title=" + title + "]";
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
