package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.dbc.DatabaseConnection;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {
	@Override
	public Dept findDeptByName(String dname) throws SQLException {
		String sql="select did,dname,eid,maxnum,currnum from dept where dname=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, dname);
		ResultSet rs = super.pstmt.executeQuery();
		Dept dept=null;
		while(rs.next()) {
			dept=new Dept();
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
		}
		return dept;
	}
	
	@Override
	public List<String> getAllDnames() throws SQLException {
		String sql="select dname from dept";
		List<String> allDnames=new ArrayList<String>();
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			allDnames.add(rs.getString(1));
		}
		return allDnames;
	}
	
	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "insert into dept(dname,eid,maxnum,currnum)values(?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setInt(3, vo.getMaxnum());
		super.pstmt.setInt(4, 0);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(Dept vo) throws SQLException {
		String sql = "update dept set dname=?,maxnum=? where did = ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getMaxnum());
		super.pstmt.setLong(3, vo.getDid());
		return super.pstmt.executeUpdate() > 0 ;
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
	public Dept findById(Long id) throws SQLException {
		 Dept dept = null;
		 String sql = "select did,dname,eid,maxnum,currnum from dept where did=?";
		 super.pstmt = super.conn.prepareStatement(sql);
		 super.pstmt.setLong(1, id);
		 ResultSet rs = super.pstmt.executeQuery();
		 if(rs.next()) {
			 dept = new Dept();
			 dept.setDid(rs.getLong(1));
			 dept.setDname(rs.getString(2));
			 dept.setEid(rs.getString(3));
			 dept.setMaxnum(rs.getInt(4));
			 dept.setCurrnum(rs.getInt(5));
		 }
		return dept;
	}

	

	@Override
	public List<Dept> findAll() throws SQLException {
		 List<Dept> all = new ArrayList<>();
		 Dept dept = null;
		 String sql = "select did,dname,eid,maxnum,currnum from dept";
		 super.pstmt = super.conn.prepareStatement(sql);
		 ResultSet rs = super.pstmt.executeQuery();
		 while(rs.next()) {
			 dept = new Dept();
			 dept.setDid(rs.getLong(1));
			 dept.setDname(rs.getString(2));
			 dept.setEid(rs.getString(3));
			 dept.setMaxnum(rs.getInt(4));
			 dept.setCurrnum(rs.getInt(5));
			 all.add(dept);
		 }
		return all;
	}

	@Override
	public List<Dept> findAll(Long currentPage, Integer lineSize) throws Exception {
		return null;
	}

	@Override
	public List<Dept> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public Map<Long, String> findByDid(Set<Long> dids) throws SQLException {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT did,dname FROM dept WHERE did IN( ");
		Iterator<Long> iter=dids.iterator();
		while(iter.hasNext()) {
			sql.append(iter.next()).append(",");
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
	public Integer getRestCount(Long did) throws SQLException {
		String sql = " select ((select maxnum from dept where did = ?) - "
				+ " (select currnum from dept where did = ?)) from dual ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, did);
		pstmt.setLong(2, did);
		ResultSet set = pstmt.executeQuery();
		if(set.next()) {
			return set.getInt(1);
		}
		return 0;
	}

	@Override
	public String findEidByLevelIdAndDid(Long lid, Long did) throws SQLException {
		String s = "";
		String sql = " select eid from emp where lid = ? and did=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1,lid);
		super.pstmt.setLong(2,did);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		return s;
	}
	
	@Override
	public boolean currentCountAotu(Long did,Integer newcount) throws SQLException {
		String sql="UPDATE dept SET currnum=? where did=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1,newcount);
		super.pstmt.setLong(2,did);
		return super.pstmt.executeUpdate()>0;
	}
	@Override
	public Integer getCurrnum(Long did) throws SQLException {
		String sql="SELECT currnum FROM dept WHERE did=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1,did);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Emp> findNotNomalEmpInDept(String eid) throws SQLException {
		String sql = " select eid,lid from emp where lid < ? and did = ( "
						+ " select did from emp where eid = ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, 3);
		pstmt.setString(2, eid);
		ResultSet set = pstmt.executeQuery();
		List<Emp> list = new ArrayList<Emp>();
		while(set.next()) {
			Emp emp = new Emp();
			emp.setEid(set.getString("eid"));
			emp.setLid(set.getLong("lid"));
			list.add(emp);
		}
		return list;
	}

	@Override
	public boolean deposeRecursion(List<Emp> emps) throws SQLException {
		String sql = " update emp set lid = ? where eid = ? ";
		pstmt = conn.prepareStatement(sql);
		for(int i = 0; i < emps.size(); i ++) {
			Emp emp = emps.get(i);
			pstmt.setLong(1, emp.getLid() + 1);
			pstmt.setString(2,emp.getEid());
			pstmt.addBatch();
//			System.out.println("批处理参数添加");
		}
		int[] array = pstmt.executeBatch();
		for(int i = 0; i <array.length; i ++) {
			if(array[i] == 0) {
//				System.out.println("批处理失败");
				return false;
			}
		}
//		System.out.println("批处理成功");
		return true;
	}

	@Override
	public String getDnameByEid(String eid) throws SQLException {
		String sql="SELECT dname FROM dept WHERE eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,eid);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	@Override
	public boolean editByDid(Long did,String eid) throws SQLException {
		String sql="UPDATE dept SET eid=? WHERE did=?";
		super.pstmt=super.conn.prepareStatement(sql);
		if(!(eid==null||"".equals(eid))){
			super.pstmt.setString(1,eid);
		}else {
			super.pstmt.setNull(1,Types.NULL);
		}
		super.pstmt.setLong(2,did);
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public Map<Long, String> findById() throws SQLException {
		Map<Long,String> map = new HashMap<>();
		String sql = "select did,dname,eid,maxnum,currnum from dept";
		super.pstmt = DatabaseConnection.getConnection().prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			map.put(rs.getLong(1), rs.getString(2));
		}
		
		return map;
	}

	


}
