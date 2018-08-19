package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;

public class ScheduleDAOImpl extends AbstractDAO implements IScheduleDAO {

	@Override
	public boolean doCreate(Schedule vo) throws SQLException {
		String sql="insert into schedule(iid,title,sdate,subdate,audit,note,ecount,seid) values(?,?,?,?,?,?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getIid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getSdate().getTime()));
		super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getSubdate().getTime()));
		super.pstmt.setInt(5, vo.getAudit());
		super.pstmt.setString(6, vo.getNote());
		super.pstmt.setInt(7, 0);
		super.pstmt.setString(8, vo.getSeid());
		return super.pstmt.executeUpdate()>0;
		
	}

	@Override
	public boolean doEdit(Schedule vo) throws SQLException {
		String sql="update schedule set iid=?,title=?,sdate=?,subdate=?,audit=?,note=?,anote=?,ecount=?,aeid=? where sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getIid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getSdate().getTime()));
		super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getSubdate().getTime()));
		super.pstmt.setInt(5, vo.getAudit());
		super.pstmt.setString(6, vo.getNote());
		super.pstmt.setString(7, vo.getAnote());
		super.pstmt.setInt(8, vo.getEcount());
		super.pstmt.setString(9, vo.getAeid());
		super.pstmt.setLong(10, vo.getSid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		String sql="delete from schedule where sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		return false;
	}

	@Override
	public Schedule findById(Long id) throws SQLException {
		String sql = " select sid, seid , aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from "
				+ " schedule where sid = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		ResultSet set = pstmt.executeQuery();
		Schedule schedule = null;
		if(set.next()) {
			schedule = new Schedule();
			schedule.setAeid(set.getString("aeid"));
			schedule.setAnote(set.getString("anote"));
			schedule.setAuddate(set.getTimestamp("auddate"));
			schedule.setAudit(set.getInt("audit"));
			schedule.setEcount(set.getInt("ecount"));
			schedule.setIid(set.getLong("iid"));
			schedule.setNote(set.getString("note"));
			schedule.setSdate(set.getTimestamp("sdate"));
			schedule.setSeid(set.getString("seid"));
			schedule.setSid(set.getLong("sid"));
			schedule.setSubdate(set.getTimestamp("subdate"));
			schedule.setTitle(set.getString("title"));
		}
		return schedule;
	}

	@Override
	public List<Schedule> findAll() throws SQLException {
		return null;
	}

	@Override
	public List<Schedule> findAll(Long currentPage, Integer lineSize) throws Exception {
		return null;
	}

	@Override
	public List<Schedule> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
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
	public List<Schedule> findAllBySeid(String seid,String keyword,String column,Long currentPage,Integer linesize) throws SQLException{
		List<Schedule> all=new ArrayList<>();
		String sql=" select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount "
				+ " from schedule where seid=? and "+column+" like ? limit ?,?";
		super.pstmt=super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, seid);
		super.pstmt.setString(2, "%"+keyword+"%");
		super.pstmt.setLong(3, (currentPage-1)*linesize);
		super.pstmt.setInt(4, linesize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Schedule vo=new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findAllBySeid(String seid, Long currentPage, Integer linesize) throws SQLException {
		List<Schedule> all=new ArrayList<>();
		String sql=" select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount "
				+ " from schedule where seid=? limit ?,?";
		super.pstmt=super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, seid);
		super.pstmt.setLong(2, (currentPage-1)*linesize);
		super.pstmt.setInt(3, linesize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Schedule vo=new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getCount(String seid) throws SQLException {
		String sql="select count(*) from schedule where seid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, seid);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getCount(String seid, String keyword, String column)
			throws SQLException {
		String sql="select count(*) from schedule where seid=? and "+column+" like ?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, seid);
		super.pstmt.setString(2, "%"+keyword+"%");
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getCountEmp(String seid, String keyword, String column) throws SQLException {
		Long count = 0L;
		String sql = "SELECT count(*) FROM  SCHEDULE WHERE  sid IN ( SELECT sid FROM schedule_emp WHERE eid =?) AND audit IN(2) AND "+ column+" like ?";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, seid);
		super.pstmt.setString(2, "%" +keyword+ "%");
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getLong(1);
		}
		return count;
	}

	@Override
	public List<Schedule> findAllEmp(String seid, String keyword, String column, Long currentPage, Integer linesize)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "SELECT  sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount FROM  SCHEDULE WHERE  sid IN ( SELECT sid FROM schedule_emp WHERE eid =?) AND audit IN(2) AND "+ column+" like ? limit ?,?";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, seid);
		super.pstmt.setString(2, "%" + keyword+ "%");
		super.pstmt.setLong(3, (currentPage -1) * linesize);
		super.pstmt.setInt(4, linesize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Schedule vo=new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
		
	}

	@Override
	public Long getCountEmp(String seid) throws SQLException {
		Long count = 0L;
		String sql = "SELECT count(*) FROM  SCHEDULE WHERE  sid IN ( SELECT sid FROM schedule_emp WHERE eid =?) AND audit IN(2)";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, seid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getLong(1);
		}
		return count;
	}

	@Override
	public List<Schedule> findAllEmp(String seid, Long currentPage, Integer linesize) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		String sql = "SELECT  sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount FROM  SCHEDULE WHERE  sid IN ( SELECT sid FROM schedule_emp WHERE eid= ?) AND audit IN(2)  limit ?,?";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, seid);
		super.pstmt.setLong(2, (currentPage -1) * linesize);
		super.pstmt.setInt(3, linesize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Schedule vo=new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Schedule findBySeid(Long sid) throws SQLException {
		Schedule vo=null;
		String sql="select title,iid,ecount,sdate,note,sid,subdate,auddate,audit,anote,aeid,seid from schedule where sid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			vo=new Schedule();
			vo.setTitle(rs.getString(1));
			vo.setIid(rs.getLong(2));
			vo.setEcount(rs.getInt(3));
			vo.setSdate(rs.getDate(4));
			vo.setNote(rs.getString(5));
			vo.setSid(rs.getLong(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAuddate(rs.getDate(8));
			vo.setAudit((Integer)rs.getInt(9));
			vo.setAnote(rs.getString(10));
			vo.setAeid(rs.getString(11));
			vo.setSeid(rs.getString(12));
		}
		return vo;
	}



	@Override
	public Map<Long, Object> findReport(String eid,String column,String keyWord,long currentPage,int lineSize) throws SQLException {
		Map<Long,Object>map = new HashMap<>();
		Schedule vo = null;
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where sid in (select sid from report where eid=? ) and "+ column +" like ? limit ?,?";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, eid);
		super.pstmt.setString(2,"%"+keyWord+"%");
		super.pstmt.setLong(3, (currentPage -1) * lineSize);
		super.pstmt.setInt(4, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			map.put(rs.getLong(1),vo);
		}
		return map;
	}

	@Override
	public Map<Long, Object> findReport(String eid, long currentPage, int lineSize) throws SQLException {
		Map<Long,Object>map = new HashMap<>();
		Schedule vo = null;
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where sid in ( select sid from report where eid=? )  limit ?,? ";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, eid);
		super.pstmt.setLong(2, (currentPage -1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			map.put(rs.getLong(1),vo);
		}
		return map;
	}

	@Override
	public Long findReport(String eid, String column, String keyWord) throws SQLException {
		Long count = 0L;
		String sql = "select count(*) from schedule where sid in ( select sid from report where eid=? ) and "+column+" like ?";
		super.pstmt =DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, eid);
		super.pstmt.setString(2,"%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getLong(1);
			
		}
		return count;
	}

	@Override
	public Long findReport(String eid) throws SQLException {
		Long count = 0L;
		String sql = "select count(*) from schedule where sid in ( select sid from report where eid=? ) ";
		super.pstmt =DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getLong(1);
		}
		return count;
	}



	@Override
	public List<Emp> getEmpsBySid(Long sid) throws SQLException {
		String sql = " select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked from emp where eid in( " + 
				"    select eid from schedule_emp where sid = ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, sid);
		ResultSet rs = pstmt.executeQuery();
		List<Emp> list=new ArrayList<Emp>();
		while(rs.next()) {
			Emp emp=new Emp();
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getLong(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(rs.getDate(10));
			emp.setIneid(rs.getString(11));
			emp.setLocked(rs.getInt(12));
			list.add(emp);
		}
		return list;
	}

	@Override
	public Schedule findByTitle(String title) throws SQLException {
		Schedule vo = null;
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule "
				+ "where title = ?";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, title);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
		vo=new Schedule();
		vo.setSid(rs.getLong(1));
		vo.setSeid(rs.getString(2));
		vo.setAeid(rs.getString(3));
		vo.setIid(rs.getLong(4));
		vo.setTitle(rs.getString(5));
		vo.setSdate(rs.getDate(6));
		vo.setSubdate(rs.getDate(7));
		vo.setAudit(rs.getInt(8));
		vo.setNote(rs.getString(9));
		vo.setAuddate(rs.getDate(10));
		vo.setAnote(rs.getString(11));
		vo.setEcount(rs.getInt(12));
		}
		return vo;
	}

	@Override
	public List<Schedule> findAllBySid(Set<Long> sids) throws SQLException {
		List<Schedule> all=new ArrayList<>();
		StringBuffer buf=new StringBuffer();
		buf.append("select title,iid,ecount,sdate,note,sid,subdate,auddate,audit,anote,aeid,seid from schedule where sid in(");
		Iterator<Long> iter=sids.iterator();
		while(iter.hasNext()) {
			buf.append("'").append(iter.next()).append("',");
		}
		buf.delete(buf.length()-1, buf.length()).append(")");
		System.out.println(buf.toString());
		super.pstmt=super.conn.prepareStatement(buf.toString());
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Schedule vo=new Schedule();
			vo.setTitle(rs.getString(1));
			vo.setIid(rs.getLong(2));
			vo.setEcount(rs.getInt(3));
			vo.setSdate(rs.getDate(4));
			vo.setNote(rs.getString(5));
			vo.setSid(rs.getLong(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAuddate(rs.getDate(8));
			vo.setAudit((Integer)rs.getInt(9));
			vo.setAnote(rs.getString(10));
			vo.setAeid(rs.getString(11));
			vo.setSeid(rs.getString(12));
			all.add(vo);
		}
		return all;
	}

//	@Override
//	public Schedule findBySeid(String seid, Long sid) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
