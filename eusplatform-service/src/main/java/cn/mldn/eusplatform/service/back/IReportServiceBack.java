package cn.mldn.eusplatform.service.back;

import java.util.Map;

import cn.mldn.eusplatform.vo.Report;

public interface IReportServiceBack {
	/**
	 * 增加调度任务申请
	 * @param vo 调度任务
	 * @return 如果同一任务发起者已经存在，返回flase,否则执行调度申请增加操作，成功返回true
	 * @throws Exception
	 */
	public boolean add(Report vo)throws Exception;
	public Map<String,Object> list(String eid,Long sid) throws Exception;		
	
	/**
	  * 根据用户id 分页模糊查询出调度报告
	 * @param eid 用户的id
	 * @param column 查询的字段
	 * @param keyWord 关键字
	 * @param currentPage 页数
	 * @param lineSize 每页显示的个数
	 * @return key allReport 表示的是全部的调度报告  key  allSchedult表示调度报告中对应的调度任务信息
	 */
	public Map<String,Object> list(String eid,String column,String keyWord,long currentPage,int lineSize);
	
}
