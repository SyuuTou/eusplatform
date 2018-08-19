package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.IBaseDAO;

public interface IEmpDAO extends IBaseDAO<String, Emp> {
	
	
    /**
     * 查询部门中的领导者
     * @param lid 职位id
     * @return 返回部门领导者
     * @throws SQLException
     */
	public Map<Long,Object> findName(int lid) throws SQLException;
	
	public Map<String,Long> findByEid(Set<String> eids)throws SQLException;
	
	
	
	public Emp findByEid(String eid)throws SQLException;
	
	/**
	 * 根据雇员表eid找到对应的部门名称
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public String findDname(String eid)throws SQLException;
	
	/**
	 * 根据雇员表eid找到对应的部门id
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Long findDnameGetDid(String eid)throws SQLException;
	
	/**
	 * 调整员工的职位等级
	 * @param lid 要调至的职位id
	 * @param eid 该员工的id
	 * @return 返回true为调整成功，false为调整失败
	 * @throws SQLException sql异常
	 */
	boolean doEdit(Long lid ,String eid) throws SQLException;
	
	/**
	 * 增加员工操作
	 * @param vo 待添加的员工
	 * @param hrEid 操作hr的id
	 * @return 返回true表示操作成功，返回false表示失败
	 * @throws SQLException sql异常
	 */
	boolean doCreate(Emp vo , String hrEid) throws SQLException;
	
	/**
	 * 修改员工信息
	 * @param vo
	 * @param hrEid
	 * @return
	 * @throws SQLException
	 */
	public boolean doEditEmp(Emp vo,String hrEid) throws SQLException;
	
	/**
	 * 根据一组emp查询出该员工的部门名称
	 * @return 返回该员工的did-dname键值对
	 * @throws SQLException sql异常
	 */
	Map<Long,String> findDameByEmps(Collection<Emp> emps) throws SQLException;

	/**
	 * 分页查找所有雇员
	 * @param did 部门编号
	 * @param currengPage 当前页
	 * @param linesize 行数
	 * @return 所有的雇员
	 * @throws SQLException
	 */
	public List<Emp> findByDid(Long did,Long currengPage,Integer linesize) throws SQLException;
	/**
	 * 部门雇员人数统计
	 * @return 人员总数
	 * @throws SQLException
	 */
	public Long getCount(Long did)throws SQLException;
	
	/**
	 * 通过任务标题查询雇员信息
	 * @param title 任务标题 
	 * @return 返回查询数据
	 * @throws SQLException 
	 */
	public List<Emp> findTitle(String title) throws SQLException;
	
	/**
	 * 根据任务查询对应的emp中的所有数据 
	 * @param sid 任务的id
	 * @return 返回查询数据
	 * @throws SQLException
	 */
	public List<Emp> findById(Long sid) throws SQLException;
	/**
	 * 根据参与者编号查询所有雇员信息
	 * @param eids
	 * @return 所有雇员信息
	 * @throws SQLException
	 */
	public List<Emp> findByEids(Set<String> eids)throws SQLException;
}
