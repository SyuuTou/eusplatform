package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleDAO extends IBaseDAO<Long, Schedule> {
	/**
	 * 显示调度申请信息		
	 * @param seid 调度任务发起者
	 * @param keyword 模糊查询关键词
	 * @param column 模糊查询列
	 * @param currentPage 当前页
	 * @param linesize 行数
	 * @return 所有调度任务申请表
	 * @throws SQLException
	 */
	public List<Schedule> findAllBySeid(String seid,String keyword,String column,Long currentPage,Integer linesize) throws SQLException;
	/**
	 * 
	 * @param seid 调度任务发起者
	 * @param currentPage 当前页
	 * @param linesize 行数
	 * @return 所有调度任务申请表
	 * @throws SQLException
	 */
	public List<Schedule> findAllBySeid(String seid,Long currentPage,Integer linesize) throws SQLException;
	/**
	 * 统计调度任务申请
	 * @param seid 调度发起者编号
	 * @return 总数
	 * @throws SQLException
	 */
	public Long getCount(String seid)throws SQLException;
	/**
	 * 模糊统计调度任务申请
	 * @param seid 调度发起者编号
	 * @param keyword 关键词
	 * @param column 要查询的列
	 * @return 总数
	 * @throws SQLException
	 */
	public Long getCount(String seid,String keyword,String column)throws SQLException;
	
	/**
	 * 分页查询用户的任务
	 * @param ename 当前用户
	 * @param keyword 关键字
	 * @param column 查询的字段
	 * @return 返回总数
	 * @throws SQLException
	 */
	public Long getCountEmp(String ename,String keyword,String column)throws SQLException;
	
	/**
	 * 模糊分页统计调度信息
	 * @param ename 当前用户
	 * @param keyword 关键字
	 * @param column 查询字段
	 * @param currentPage 页数
	 * @param linesize 每页显示的个数
	 * @return 返回总数
	 * @throws SQLException
	 */
	public List<Schedule> findAllEmp(String ename,String keyword,String column,Long currentPage,Integer linesize) throws SQLException;
	
	/**
	 * 统计用户任务的总个数
	 * @param ename 当前用户  
	 * @return 返回 用户的 任务个数
	 * @throws SQLException
	 */
	public Long getCountEmp(String ename)throws SQLException;
	
	/**
	 * 根据sid查询该任务调度所有emp的信息
	 * @param sid 待查询的sid
	 * @return 返回任务调度所有emp的信息
	 * @throws SQLException sql异常
	 */
	List<Emp> getEmpsBySid(Long sid) throws SQLException;

/**
 * 
 * @param ename 当前用户
 * @param currentPage 当前页
 * @param linesize 行数
 * @return 任务的分页列表
 * @throws SQLException
 */
public List<Schedule> findAllEmp(String seid,Long currentPage,Integer linesize) throws SQLException;
/**
 * 调度信息显示
 * @param sid 任务编号
 * @return 调度任务
 * @throws SQLException
 */
public Schedule findBySeid(Long sid)throws SQLException;

/**
 * 根据用户id 查询schedule中对应的数据
 * @param eid 用户id
 * @return 返回查询数据
 * @throws SQLException
 */
public Map<Long,Object> findReport(String eid,String column,String keyWord,long currentPage,int lineSize) throws SQLException;
public Map<Long,Object> findReport(String eid,long currentPage,int lineSize ) throws SQLException;
public Long findReport(String eid,String column,String keyWord) throws SQLException;
public Long findReport(String eid) throws SQLException;

/**
 * 通过任务标题查询schedule表的内容
 * @param title 任务的标题
 * @return 成功返回数据
 * @throws SQLException 
 */
public Schedule findByTitle(String title) throws SQLException;
/**
 * 查询所有调度任务
 * @param sids
 * @return 所有调度任务
 * @throws SQLException
 */
public List<Schedule> findAllBySid(Set<Long> sids) throws SQLException;
}
