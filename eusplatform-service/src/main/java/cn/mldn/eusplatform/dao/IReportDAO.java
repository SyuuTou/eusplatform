package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.IBaseDAO;

public interface IReportDAO extends IBaseDAO<Long, Report> {
	/**
	 * 根据用户id查找调度任务报告记录
	 * @param eid 用户Id
	 * @return 调度任务申请记录
	 * @throws SQLException
	 */
	public Report findById(String eid)throws SQLException;
	/**
	 * 查找调度任务报告记录
	 * @param eid 任务发起者编号
	 * @param sid 任务编号
	 * @return 任务报告
	 * @throws SQLException
	 */
	public Report findById(String eid,Long sid)throws SQLException;
	
	/**
	 * 根据用户id 查询report
	 * @param eid 用户id
	 * @return 返回查询数据
	 * @throws SQLException 
	 */
	public List<Report> findSplitReport(String eid) throws SQLException;
	
	/**
	 * 查询出所有的report 数据
	 * @return key eid  
	 */
	public Map<String,Report> list()throws SQLException;
	
	/**
	 * 查询任务的完成总数
	 * 
	 * @return key sid  value 数量
	 * @throws SQLException
	 */
	public Map<Long,Long> findBySid() throws SQLException;
	
	
}
