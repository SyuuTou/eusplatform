package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.vo.Emp;

public interface IEmpServiceBack {
	/**
	 * 实现用户的登录处理操作，该操作要执行如下的几步：
	 * 1、调用IMemberDAO.findById()方法根据用户名查找用户的信息，随后进行密码比对，如果密码正确则表示登录成功；
	 * 2、当登录成功之后应该根据用户编号获得用户对应的角色与权限数据信息；
	 * @param emp 包含有用户登录名（mid）与密码（password）两个重要的信息
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = emp、value = 登录成功后的用户信息，如果没有返回雇员对象则表示登录失败；
	 * 2、key = allRoles、value = 用户对应的所有角色，Set集合；
	 * 3、key = allActions、value = 用户对应的所有权限，Set集合；
	 * @throws Exception 抛出状态的处理异常
	 */
	public Map<String, Object> login(Emp emp) throws Exception;
	
	/**
	 * 实现emplist页面的实现
	 @return 返回的内容包含有如下数据：<br>
	 * 1、key = emps、value = 所有emp信息；
	 * 2、key = levels、value = 通过lid查询所有的level信息；
	 * 3、key = depts、value = 通过did查询所有的dept信息；
	 * 4、key = lids、value = 通过eid查询所有的lids信息；
	 * 5、key = dname、value = 通过eid查询出dname信息；
	 * @throws Exception 抛出状态的处理异常
	 */
	public Map<String,Object> list(String eid)throws Exception;
	
	/**
	 * 实现emplist页面的实现
	 @return 返回的内容包含有如下数据：<br>
	 * 1、key = emp、value = 所有emp信息；
	 * 2、key = level、value = 通过lid查询所有的level信息；
	 * 3、key = dept、value = 通过did查询所有的dept信息；
	 * 4、key = lid、value = 通过eid查询所有的lids信息；
	 * @throws Exception 抛出状态的处理异常
	 */
	public Map<String,Object> editPro(String eid)throws Exception;
	
	/**
	 * 查询该用户id是否已经注册过
	 * @param eid 待查询的eid
	 * @return 返回 true表示已经注册过了，false为未注册。
	 * @throws SQLException SQL异常
	 */
	boolean isExisted(String eid) throws SQLException;
	
	/**
	 * 增加新员工
	 * @param vo 新增员工实例
	 * @param hrEid 执行操作的hr的id
	 * @return 返回true为增加成功，false为增加失败
	 * @throws SQLException SQL异常
	 */
	boolean add(Emp vo ,String hrEid) throws SQLException;
	
	/**
	 * 部门人数加一
	 * @param did
	 * @return
	 * @throws Exception
	 */
	public boolean autoDeptCurrnum(Long did)throws Exception;
	
	/**
	 * 部门人数减一
	 * @param did
	 * @return
	 * @throws Exception
	 */
	public boolean subDeptCurrnum(Long did)throws Exception;
	
	/**
	 * 根据eid找到部门
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Long findByEid(String eid)throws Exception;
	
	/**
	 * 判断修改的eid除了自己是否已经存在
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public boolean isExistedNotMy(String eid)throws Exception;
	
	/**
	 * 修改雇员信息
	 * @param vo
	 * @param hrEid
	 * @return
	 * @throws SQLException
	 */
	public boolean edit(Emp vo, String hrEid) throws Exception;
	
	/**
	 * 删除雇员
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String eid)throws Exception;
	/**
	 *查找部门下的所有雇员信息
	 * @param did 部门编号
	 * @param currengPage 当前页
	 * @param linesize 行数
	 * @return 雇员信息
	 * @throws Exception
	 */
	public Map<String,Object> list(long did,long currengPage,int linesize)throws Exception;
	
	/**
	 * 通过雇员id 查询所有雇员的信息
	 * @param eid 雇员id
	 * @return 返回查询数据
	 * @throws SQLException 
	 */
	public Map<String,Object> findById(String eid) throws SQLException;
}
