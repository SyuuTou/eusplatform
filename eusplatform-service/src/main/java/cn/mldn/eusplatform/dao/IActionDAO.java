package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.eusplatform.vo.Action;
import cn.mldn.util.dao.IBaseDAO;

public interface IActionDAO extends IBaseDAO<Long, Action> {
	/**
	 * 根据部门编号查询所有对应的权限
	 * @param did 部门编号
	 * @return 权限编号集合
	 * @throws SQLException JDBC
	 */
	public Set<String> findAllByDept(Long did) throws SQLException ;
}
