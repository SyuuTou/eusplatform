package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class DeptServiceBackImpl extends AbstractService implements IDeptServiceBack{

	@Override
	public boolean add(Dept dept) {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		try {
				if(deptDao.findDeptByName(dept.getDname()) == null) {
					return deptDao.doCreate(dept);
				}else {
					return false;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}


	@Override
	public List<Dept> list() throws SQLException {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		return deptDao.findAll();
	}

	@Override
	public int getMaxCountInDept(Long did) throws SQLException {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		return deptDao.findById(did) == null ? 0 : deptDao.findById(did).getMaxnum();
	}

	@Override
	public boolean isFull(Long did) throws SQLException {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		return !(deptDao.getRestCount(did) > 0);
	}


	@Override
	public boolean depose(String eid, Long lid) throws SQLException {
//		IEmpDAO dao = Factory.getDAOInstance("emp.dao");
		IDeptDAO dao = Factory.getDAOInstance("dept.dao");
		List<Emp> emps = dao.findNotNomalEmpInDept(eid);
//		System.out.println(emps);
		return dao.deposeRecursion(emps);
	}


	@Override
	public String findEidByLidAndDid(Long lid, Long did) throws SQLException {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		return deptDao.findEidByLevelIdAndDid(lid, did);
	}


	@Override
	public Map<String, Object> list(int lid) {
		Map<String,Object> map = new HashMap<String,Object>();
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		IEmpDAO empDao = Factory.getDAOInstance("emp.dao");
		try {
			map.put("allDept",deptDao.findAll());
			map.put("empName",empDao.findName(lid) );
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean edit(Dept dept) {
		IDeptDAO deptDao = Factory.getDAOInstance("dept.dao");
		try {
			String oldName = dept.getDname();
			Dept dbDept = deptDao.findDeptByName(oldName);//根据name获取数据库中的dept
			if(dbDept==null) { //数据库中不存在该部门的数据，则可以执行更新
				return deptDao.doEdit(dept) ;
			}else {
				if(dbDept.getDid()==dept.getDid()) {//是同一条数据
					return deptDao.doEdit(dept);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public Map<String, Object> getEidAll(String eid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao");
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao");
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao");
		Emp vo=empDAO.findByEid(eid);
		Date date=new Date(vo.getHiredate().getTime());
		vo.setHiredate(new Date(vo.getHiredate().getTime()));
		String dname=deptDAO.getDnameByEid(eid);
		String title=levelDAO.getTitleByLid(eid);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("emp",vo);
		map.put("dname",dname);
		map.put("title",title);
		map.put("time",new SimpleDateFormat("yyyy-MM-dd").format(vo.getHiredate()));
		return map;
	}


	@Override
	public boolean editByEid(Long did, String eid) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao");
		return deptDAO.editByDid(did, eid);
	}


}
