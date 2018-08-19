package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.vo.Dept;

public interface IDeptServiceBack {
	
	
	/**
	 * 列出所有的部门
	 * @return 返回所有的部门
	 * @throws SQLException sql异常
 	 */
	List<Dept> list()throws SQLException;
	
	/**
	 * 根据部门id查询出该部门最大容纳人数
	 * @param did 部门id
	 * @return 返回部门最大容纳人数
	 * @throws SQLException
	 */
	int getMaxCountInDept(Long did) throws SQLException;
	
	/**
	 * 判断该部门是否满人
	 * @param did 该部门id
	 * @return 返回true为满人，false为未满
	 * @throws SQLException SQL异常
	 */
	boolean isFull(Long did) throws SQLException;
	
	/**
	 * 为某部门的某员工降职
	 * @param eid 该员工的id
	 * @param lid  要降到的职位id
	 * @return
	 * @throws SQLException sql异常
	 */
	boolean depose(String eid , Long lid) throws SQLException;
	
	/**
	 * 找到某部门的某员工id
	 * @param lid 职位id
	 * @param did 部门id 
	 * @return 该员工id
	 * @throws SQLException sql异常
	 */
	String findEidByLidAndDid(Long lid , Long did) throws SQLException;
	
	
	/**
	 * 部门表增加操作
	 * @param dept 数据内容
	 * @return 成功返回true
	 */
	public boolean add(Dept dept);
	/**
	 * 部门列表的显示页面
	 * @param lid 职位的id
	 * @return 返回部门列表的所有数据
	 */
	public Map<String,Object> list(int lid);
	
	/**
	 * 部门的修改
	 * @param dept 修改的数据
	 * @return 成功就返回true
	 */
	public boolean edit(Dept dept);
	
	public Map<String,Object> getEidAll(String eid)throws Exception;
	
	public boolean editByEid(Long did,String eid)throws Exception;
}
