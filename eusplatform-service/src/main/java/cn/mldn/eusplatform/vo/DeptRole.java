package cn.mldn.eusplatform.vo;

import java.io.Serializable;

public class DeptRole implements Serializable {
	private Long did;
	private String rid;
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
}
