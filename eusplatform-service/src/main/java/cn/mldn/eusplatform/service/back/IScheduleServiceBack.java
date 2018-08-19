package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;

public interface IScheduleServiceBack {
	/**
	 * 增加调度任务
	 * @param eid 任务发起者Id
	 * @param vo 调度任务申请内容
	 * @return 在当前时间范围内只能发起一次,成功返回true
	 * @throws Exception
	 */
	
	public boolean add(String eid,Schedule vo)throws Exception;
	/**
	 * 用户编号为
	 * @param eid 调度发起者编号，对应字段属性为emp表的eid属性
	 * @return key为allSchedules
	 * @throws Exception
	 */
	
	public Map<String,Object> list(String eid,String keyword,String column,Long currentPage,Integer linesize)throws Exception;

	/**
	 * 取得单个任务信息
	 * @param eid 用户编号
	 * @param sid 任务编号
	 * @return 任务信息
	 * @throws Exception
	 */
	public Map<String,Object> get(String eid,Long sid)throws Exception;

	/**
	 * 分页模糊查询出用户的任务
	 * @param eid 表示的是当前的用户
	 * @return 所有查询的数据
	 * @throws Exception
	 */
	public Map<String,Object> listEmp(String eid,String keyword,String column,Long currentPage,Integer linesize)throws Exception;
	
	/**
	 * 更新任务信息
	 * @param vo 调度任务
	 * @param eid 调度任务发起者编号
	 * @return 成功返回true
	 * @throws Exception
	 */
	public boolean edit(Schedule vo,String eid)throws Exception;
	
	/**
	 * 任务编辑内容回显
	 *@param eid 用户编号
	 * @param sid 任务编号
	 * @return 当前调度任务详情
	 * @throws Exception
	 */
	public Map<String,Object> listEdit(String eid,Long sid)throws Exception;
	
	/**
	 * 删除调度任务,并删除相关内容
	 * @param sid 要删除的调度任务编号
	 * @return 成功返回true
	 * @throws Exception
	 */
	public boolean delete(Long sid)throws Exception;
	
	/**
	 * 根据sid找到任务调度的调度具体信息
	 * @return 该任务调度的具体信息
	 * @throws SQLException sql异常
	 */
	Schedule getScheduleById(Long sid) throws SQLException;
	
	/**
	 * 根据sid查询出参与这次任务调度的所有emp
	 * @param sid 待查询的sid
	 * @return 返回
	 * @throws SQLException
	 */
	List<Emp> getEmpsBySid(Long sid) throws SQLException;
	
   /**
	 * 根据一组emp查询出它们对应的部门名称
	 * @param emps 待查询的一组emp
	 * @return 返回该组emp对应的部门名称
	 * @throws SQLException sql异常
	 */
	Map<Long,String> getDnamesByEmps(Collection<Emp> emps) throws SQLException;
	
/*	*//**
	 * 根据一组eid查询出它们对应的部门名称
	 * @param eids 待查询的一组eid
	 * @return 返回该组eid对应的部门名称
	 * @throws SQLException sql异常
	 *//*
	Map<String,String> getDnamesByEids(Set<String> eids) throws SQLException;*/
	
	/**
	 * 根据一组emps查询出它们对应的职位等级
	 * @param emps 待查询的一组emps
	 * @return 返回该组emps对应的职位名称
	 * @throws SQLException sql异常
	 */
	Map<Long,String> getLevelNamesByEmps(Set<Emp> emps) throws SQLException;
	
	/**
	 * 根据一组emps查询出它们对应的职位等级
	 * @param emps 待查询的一组emps
	 * @return 返回该组emps对应的职位名称
	 * @throws SQLException sql异常
	 */
	Map<Long,String> getLevelNamesByEmps(List<Emp> emps) throws SQLException;
	
	/**
	 * 根据任务的标题查询出 对应的schedule中的数据
	 * @param title  任务的标题
	 * @return 成功返回数据
	 * @throws SQLException 
	 */
	public Map<String,Object> listSchedule(String title) throws SQLException;
	
	/**
	 * 根据用户的eid 分页模糊查询 schedule表中的数据
	 * @param eid 用户的id
	 * @param column 查询的字段
	 * @param keyWord 关键字
	 * @param currentPage 页数
	 * @param lineSize 每页显示的数据行
	 * @return 返回查询数据
	 * @throws SQLException 
	 */
	public Map<String,Object> listScheduleReport(String eid,String column,String keyWord,long currentPage,int lineSize) throws SQLException;
	/**
	 * 查询正在参与的任务状态
	 * @param eid 参与者编号
	 * @return 返回状态结果,false表示任务还在参与中
	 * @throws SQLException
	 */
	public boolean addSchduleEmp(String eid,Long sid)throws SQLException;
	/**
	 * 根据eid查询雇员信息
	 * @param eid 雇员编号
	 * @return 员工相关信息，包括员工部门,员工等级信息
	 * @throws SQLException
	 */
	public Map<String,Object> ListEmp(String eid)throws SQLException;
	/**
	 * 根据sid查询所有相关雇员信息
	 * @param sid 任务编号
	 * @return 所有相关雇员信息
	 * @throws SQLException
	 */
	public Map<String,Object> ListAllEmp(Long sid)throws SQLException;
	/**
	 * 删除指定参与人员
	 * @param sid 任务编号
	 * @param eid 参与人员
	 * @return 成功返回true
	 * @throws SQLException
	 */
	public boolean delete(Long sid,String eid)throws SQLException;
}
