package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;

public class LevelDAOImpl extends AbstractDAO implements ILevelDAO{

	@Override
	public boolean doCreate(Level vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Level vo) throws SQLException {
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
	public Level findById(Long id) throws SQLException {
		String sql = " select  lid ,title, losal , hisal from level where lid = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		Level level = null;
		ResultSet set = pstmt.executeQuery();
		if(set.next()) {
			level = new Level();
			level.setHisal(set.getDouble("hisal"));
			level.setLid(set.getLong("lid"));
			level.setLosal(set.getDouble("losal"));
			level.setTitle(set.getString("title"));
		}
		return level;
	}

	@Override
	public List<Level> findAll() throws SQLException {
		String sql = " select lid,title,losal,hisal from level ";
		pstmt = conn.prepareStatement(sql);
		ResultSet set = pstmt.executeQuery();
		List<Level> levels = new ArrayList<Level>();
		while(set.next()) {
			Level level = new Level();
			level.setHisal(set.getDouble("hisal"));
			level.setLid(set.getLong("lid"));
			level.setLosal(set.getDouble("losal"));
			level.setTitle(set.getString("title"));
			levels.add(level);
		}
		return levels;
	}

	@Override
	public List<Level> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public Map<Long, String> findByLid(Set<Long> lids) throws SQLException {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT lid,title FROM level WHERE lid IN (");
		Iterator<Long> iter=lids.iterator();
		while(iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length()-1,sql.length()).append(")");
		System.out.println(sql.toString());
		super.pstmt=super.conn.prepareStatement(sql.toString());
		ResultSet rs=super.pstmt.executeQuery();
		Map<Long,String> map=new HashMap<Long,String>();
		while(rs.next()) {
			map.put(rs.getLong(1),rs.getString(2));
		}
		
		return map;
	}

	@Override
	public Map<Long, String> findByEmps(Collection<Emp> emps) throws SQLException {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT lid,title FROM level WHERE lid IN (");
		Iterator<Emp> iter=emps.iterator();
		while(iter.hasNext()) {
			sql.append(iter.next().getLid()).append(",");
		}
		sql.delete(sql.length()-1,sql.length()).append(")");
		super.pstmt=super.conn.prepareStatement(sql.toString());
		ResultSet rs=super.pstmt.executeQuery();
		Map<Long,String> map=new HashMap<Long,String>();
		while(rs.next()) {
			map.put(rs.getLong(1),rs.getString(2));
		}
		return map;
	}

	@Override
	public String getTitleByLid(String eid) throws SQLException {
		String sql="SELECT title FROM level WHERE lid=(SELECT lid FROM emp WHERE eid=?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,eid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	@Override
	public Map<Long, String> findByLid() throws SQLException {
		Map<Long,String> map = new HashMap<Long,String>();
		String sql = "select lid,title,losal,hisal from level";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			map.put(rs.getLong(1), rs.getString(2));
			
		}
		return map;
	}

}
