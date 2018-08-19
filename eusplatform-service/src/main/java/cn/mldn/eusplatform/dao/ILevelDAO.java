package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.IBaseDAO;

public interface ILevelDAO extends IBaseDAO<Long,Level> {

	public Map<Long,String> findByLid(Set<Long> lids)throws SQLException;
	
	Map<Long,String> findByEmps(Collection<Emp> emps) throws SQLException;
	
	public String getTitleByLid(String eid)throws SQLException;
	
	/**
	 * 查询出Level 中的数据
	 * @return key 是lid  值是title
	 * @throws SQLException 
	 */
	public Map<Long,String> findByLid() throws SQLException;
}
