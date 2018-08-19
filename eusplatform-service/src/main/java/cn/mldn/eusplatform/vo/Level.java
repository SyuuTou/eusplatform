package cn.mldn.eusplatform.vo;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Level implements Serializable{
	private Long lid;
	private String title;
	private Double losal;
	private Double hisal;
	
	public Long getLid() {
		return lid;
	}
	public void setLid(Long lid) {
		this.lid = lid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getLosal() {
		return losal;
	}
	public void setLosal(Double losal) {
		this.losal = losal;
	}
	public Double getHisal() {
		return hisal;
	}
	public void setHisal(Double hisal) {
		this.hisal = hisal;
	}
	
	
}
