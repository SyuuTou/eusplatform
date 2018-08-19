package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.IBaseDAO;

public interface IDeptDAO extends IBaseDAO<Long, Dept> {
	
	public Map<Long,String> findByDid(Set<Long> dids)throws SQLException;
	
	
	/**
	 * 根据部门名称获取部门信息
	 * @param dname 部门名称
	 * @return 返回部门的vo对象,不存在返回null
	 * @throws SQLException
	 */
	public Dept findDeptByName(String dname) throws SQLException;
	/**
	 * 返回部门的所有部门名
	 * @return 部门列表的的名字所构成的List集合
	 * @throws SQLException
	 */
	public List<String> getAllDnames() throws SQLException;	
	/**
	 * 查询出该部门的剩余坑位
	 * @param did 该部门的id
	 * @return 返回该部门剩余坑位
	 * @throws SQLException SQL异常
	 */
	Integer getRestCount(Long did) throws SQLException;
	
	/**
	 * 根据部门id和职位id查询某部门的某岗位的职员eid
	 * @param lid 职位id
	 * @param did 部门id
	 * @return 返回 某部门的某岗位的职员eid
	 * @throws SQLException SQL异常
	 */
	String findEidByLevelIdAndDid(Long lid , Long did) throws SQLException;
	
	/**
	 * 增加雇员成功后，所在部门人数更新
	 * @param did
	 * @param newcount  更新后的人数
	 * @return
	 * @throws SQLException
	 */
	public boolean currentCountAotu(Long did,Integer newcount)throws SQLException;
	
	/**
	 * 取得当前的部门人数
	 * @param did
	 * @return
	 * @throws SQLException
	 */
	public Integer getCurrnum(Long did)throws SQLException;
	
	/**
	 * 找到某员工所在部门所有非普通员工的所有人eid和lid
	 * @param eid 该员工id
	 * @return 返回所有某员工所在部门所有非普通员工的所有人eid和lid
	 * @throws SQLException sql异常
	 */
	List<Emp> findNotNomalEmpInDept(String eid) throws SQLException;
	
	/**
	 * 对集合类所有员工的职位等级进行降级
	 * @param emps 待降级的员工
	 * @return 返回true表示降级操作成功，false为降级操作失败
	 * @throws SQLException SQL异常
	 */
	boolean deposeRecursion(List<Emp> emps) throws SQLException;
	
	public String getDnameByEid(String eid)throws SQLException;
	
	/**
	 * 修改部门领导
	 * @param did
	 * @return
	 */
	public boolean editByDid(Long did,String eid)throws SQLException;
	/**
	 * 查询出所有的数据以map集合返回
	 * @return
	 * @throws SQLException 
	 */
	public Map<Long,String> findById() throws SQLException;
}
