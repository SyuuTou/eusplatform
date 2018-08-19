package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.crypto.Data;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;

public class ReportDAOImpl extends AbstractDAO implements IReportDAO {

	@Override
	public boolean doCreate(Report vo) throws SQLException {
		String sql="insert into report(sid,eid,subdate,note) values(?,?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getSubdate().getTime()));
		super.pstmt.setString(4, vo.getNote());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doEdit(Report vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		String sql="delete from report where sid=?";
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
	public Report findById(Long id) throws SQLException {
		return null;
	}

	@Override
	public List<Report> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public Report findById(String eid) throws SQLException {
		Report vo=null;
		String sql="select srid,sid,eid,note,subdate from report where eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			vo=new Report();
			vo.setSrid(rs.getLong(1));
			vo.setSid(rs.getLong(2));
			vo.setEid(rs.getString(3));
			vo.setNote(rs.getString(4));
			vo.setSubdate(rs.getDate(5));
		}
		return vo;
	}

	@Override
	public Report findById(String eid, Long sid) throws SQLException {
		Report vo=null;
		String sql="select srid,sid,eid,note from report where eid=? and sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, eid);
		super.pstmt.setLong(2,sid);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			vo=new Report();
			vo.setSrid(rs.getLong(1));
			vo.setSid(rs.getLong(2));
			vo.setEid(rs.getString(3));
			vo.setNote(rs.getString(4));
		}
		return vo;
	}


	@Override
	public List<Report> findSplitReport(String eid) throws SQLException {
		List<Report> list = new ArrayList<Report>();
		Report report = null;
		String sql = "select srid,sid,eid,subdate,note from report  where eid = ? ";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			report = new Report();
			report.setSrid(rs.getLong(1));
			report.setSid(rs.getLong(2));
			report.setEid(rs.getString(3));
			report.setSubdate(rs.getDate(4));
			report.setNote(rs.getString(5));
			list.add(report);
		}
		return list;
	}

	@Override
	public Map<String, Report> list() throws SQLException {
		Map<String,Report> map = new HashMap<>();
		Report report = null;
		String sql = "select srid,sid,eid,subdate,note from report";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			report = new Report();
			report.setSrid(rs.getLong(1));
			report.setSid(rs.getLong(2));
			report.setEid(rs.getString(3));
			report.setSubdate(rs.getDate(4));
			report.setNote(rs.getString(5));
			map.put(rs.getString(3), report);
		}
		return map;
	}

	@Override
	public Map<Long,Long> findBySid() throws SQLException {
		Map<Long,Long> map = new HashMap<Long,Long>();
		String sql = "select sid,count(*) from report group by sid";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
		map.put(rs.getLong(1), rs.getLong(2));	
		}
		return map;
	}


}
