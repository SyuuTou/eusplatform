package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IRoleDAO;
import cn.mldn.eusplatform.vo.Role;
import cn.mldn.util.dao.abs.AbstractDAO;

public class RoleDAOImpl extends AbstractDAO implements IRoleDAO {

	@Override
	public boolean doCreate(Role vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Role vo) throws SQLException {
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
	public Role findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
		String sql = "SELECT rid FROM dept_role WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, did);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			all.add(rs.getString(1)) ;
		}
		return all; 
	}

	@Override
	public List<String> findRidByEid(String eid) throws SQLException {
		String sql = " select rid from dept_role where did = "
				+" (select did from emp where "
						+" eid = ? ) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eid);
		List<String> rids = new ArrayList<String>();
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
		rids.add(set.getString(1));
		}
		return rids;
	}


}
