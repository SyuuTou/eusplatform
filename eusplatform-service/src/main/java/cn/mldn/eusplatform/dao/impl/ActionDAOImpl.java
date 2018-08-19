package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IActionDAO;
import cn.mldn.eusplatform.vo.Action;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ActionDAOImpl extends AbstractDAO implements IActionDAO {

	@Override
	public boolean doCreate(Action vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Action vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Action findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findAllByDept(Long did) throws SQLException {
		Set<String> all = new HashSet<String>() ;
		String sql = "SELECT actid FROM action "
					+ " WHERE rid IN ("
					+ "		SELECT rid FROM dept_role WHERE did=?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, did);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			all.add(rs.getString(1)) ;
		}
		return all;
	}

}
