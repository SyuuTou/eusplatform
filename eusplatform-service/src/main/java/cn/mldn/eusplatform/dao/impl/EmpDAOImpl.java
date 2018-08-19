package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;
import cn.mldn.util.enctype.PasswordUtil;

public class EmpDAOImpl extends AbstractDAO implements IEmpDAO {

	@Override
	public boolean doCreate(Emp vo) throws SQLException {
		return false;
	}

	@Override
	public boolean doEdit(Emp vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(String id) throws SQLException {
		String sql="DELETE FROM emp WHERE eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,id);
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Emp findById(String id) throws SQLException {
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked "
				+ " FROM emp WHERE eid=? AND locked=0 " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Emp emp = new Emp() ;
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
			return emp ;
		}
		return null;
	}

	@Override
	public List<Emp> findAll() throws SQLException {
		String sql="SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked FROM emp WHERE locked=0";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
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
	public List<Emp> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public Map<String,Long> findByEid(Set<String> eids) throws SQLException {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT eid,lid FROM emp WHERE eid IN (");
		Iterator<String> iter=eids.iterator();
		while(iter.hasNext()) {
			sql.append("'").append(iter.next()).append("',");
		}
		sql.delete(sql.length()-1,sql.length()).append(")");
		super.pstmt=super.conn.prepareStatement(sql.toString());
		ResultSet rs=super.pstmt.executeQuery();
		Map<String,Long> map=new HashMap<String,Long>();
		while(rs.next()) {
			map.put(rs.getString(1),rs.getLong(2));
		}
		
		return map;
	}

	@Override
	public String findDname(String eid) throws SQLException {
		String dname=null;
		String sql="select dname from dept where did=(select did from emp where eid=?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,eid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			dname=rs.getString(1);
		}
		return dname;
	}
	
	@Override
	public Long findDnameGetDid(String eid) throws SQLException {
		Long did=null;
		String sql="select did from dept where did=(select did from emp where eid=?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,eid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			did=rs.getLong(1);
		}
		return did;
	}

	@Override
	public boolean doEdit(Long lid, String eid) throws SQLException {
		String sql = " update emp set lid = ?  where eid = ? ";
		super.pstmt = conn.prepareStatement(sql);
		super.pstmt.setLong(1, lid);
		super.pstmt.setString(2, eid);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEditEmp(Emp vo, String hrEid) throws SQLException {
		String sql = " UPDATE emp SET lid=?,did=?,ename=?,salary=?,phone=?,password=?,photo=?,note=?,hiredate=?,ineid=? WHERE eid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, vo.getLid());
		pstmt.setLong(2, vo.getDid());
		pstmt.setString(3, vo.getEname());
		pstmt.setDouble(4, vo.getSalary());
		pstmt.setString(5, vo.getPhone());
		pstmt.setString(6, PasswordUtil.encoder(vo.getPassword()));
		pstmt.setString(7, vo.getPhoto());
		pstmt.setString(8 , vo.getNote());
		pstmt.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
		pstmt.setString(10, hrEid);
		pstmt.setString(11, vo.getEid());
		return pstmt.executeUpdate() > 0;
	}
	
	@Override
	public boolean doCreate(Emp vo, String hrEid) throws SQLException {
		String sql = " insert into emp(eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,? ) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getEid());
		pstmt.setLong(2, vo.getLid());
		pstmt.setLong(3, vo.getDid());
		pstmt.setString(4, vo.getEname());
		pstmt.setDouble(5, vo.getSalary());
		pstmt.setString(6, vo.getPhone());
		pstmt.setString(7, PasswordUtil.encoder(vo.getPassword()));
		pstmt.setString(8, vo.getPhoto());
		pstmt.setString(9 , vo.getNote());
		pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
		pstmt.setString(11, hrEid);
		pstmt.setInt(12,0);
		return pstmt.executeUpdate() > 0;
	}
	
	@Override
	public Emp findByEid(String eid) throws SQLException {
		String sql="SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked FROM emp WHERE locked=0 AND eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,eid);
		ResultSet rs=super.pstmt.executeQuery();
		Emp emp=null; 
		while(rs.next()) {
			emp=new Emp();
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getLong(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(new java.util.Date(rs.getDate(10).getTime()));
			emp.setIneid(rs.getString(11));
			emp.setLocked(rs.getInt(12));
			
		}
		return emp;
	}


	@Override
	public Map<Long,Object> findName(int lid) throws SQLException {
		Emp emp = null;
		Map<Long,Object> map = new HashMap<Long,Object>();
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked FROM emp WHERE lid IN (1,?) AND locked=0 AND did in(select did from dept) " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, lid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			emp=new Emp();
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
			map.put(rs.getLong(3),emp);
			
		}
		return map;
	}

	@Override
	public Map<Long, String> findDameByEmps(Collection<Emp> emps) throws SQLException {
		String sql = null;
		StringBuilder sb = new StringBuilder(" select dname ,did from dept where did in(");
		Iterator<Emp> iterator = emps.iterator();
		while (iterator.hasNext()) {
			Long did = iterator.next().getDid();
			sb.append( did + ",");
		}
		sql = sb.deleteCharAt(sb.length() - 1).append(")").toString();
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		ResultSet set = pstmt.executeQuery();
		Map<Long,String> map = new HashMap<Long,String>();
		while(set.next()) {
			map.put(set.getLong("did"), set.getString("dname"));
		}
		return map;
	}

	@Override
	public List<Emp> findByDid(Long did,Long currengPage,Integer linesize) throws SQLException {
		List<Emp> all=new ArrayList<>();
		String sql="SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked FROM emp WHERE locked=0 AND did=? limit ?,?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1,did);
		super.pstmt.setLong(2, (currengPage-1)*linesize);
		super.pstmt.setInt(3, linesize);
		ResultSet rs=super.pstmt.executeQuery();
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
			emp.setHiredate(new java.util.Date(rs.getDate(10).getTime()));
			emp.setIneid(rs.getString(11));
			emp.setLocked(rs.getInt(12));
			all.add(emp);
		}
		return all;
	}

	@Override
	public Long getCount(Long did) throws SQLException {
		String sql="select count(*) from emp where did=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, did);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public List<Emp> findTitle(String title) throws SQLException {
		List<Emp> list = new ArrayList<>();
		Emp emp = null;
		String sql = " select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked from emp"
				+ " where eid in (select seid from schedule where title=?) and locked=0";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setString(1, title);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			emp = new Emp();
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
	public List<Emp> findById(Long sid) throws SQLException {
		List<Emp> list = new ArrayList<>();
		Emp emp = null;
		String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked from emp"
				+ " where eid in (select eid from report where sid = ?) and locked=0";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			emp = new Emp();
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
	public List<Emp> findByEids(Set<String> eids) throws SQLException {
		List<Emp> all = new ArrayList<>();
		StringBuffer buf=new StringBuffer();
		buf.append("select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,locked from emp where locked=0 and eid in(");
		Iterator<String> iter=eids.iterator();
		while(iter.hasNext()) {
			buf.append("'").append(iter.next()).append("',");
		}
		buf.delete(buf.length()-1, buf.length()).append(")");
		super.pstmt=super.conn.prepareStatement(buf.toString());
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Emp emp = new Emp();
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
			all.add(emp);
		}
		return all;
	}

}
