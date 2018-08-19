package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ScheduleEmpDAOImpl extends AbstractDAO implements IScheduleEmpDAO {

	@Override
	public boolean doCreate(ScheduleEmp vo) throws SQLException {
		String sql="insert into schedule_emp(sid,eid) values(?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getEid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doEdit(ScheduleEmp vo) throws SQLException {
		
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		String sql="delete from schedule_emp where sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScheduleEmp findById(Long id) throws SQLException {
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll() throws SQLException {
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
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
	public Set<Long> findById(String eid) throws SQLException {
		Set<Long> all=new HashSet<>();
		String sql="select sid from schedule_emp where eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			all.add(rs.getLong(1));
		}
		return all;
	}

	@Override
	public Long find_last_insert() throws SQLException {
		String sql="select last_insert_id()";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public List<ScheduleEmp> findBySId(Long sid) throws SQLException {
		List<ScheduleEmp> all=new ArrayList<>();
		String sql="select sid,eid from schedule_emp where sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			ScheduleEmp vo=new ScheduleEmp();
			vo.setSid(rs.getLong(1));
			vo.setEid(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public boolean doRemove(Long sid, String eid) throws SQLException {
		String sql="delete from schedule_emp where sid=? and eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, eid);
		return super.pstmt.executeUpdate()>0;
	}


}
