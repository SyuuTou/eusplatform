package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IActionDAO;
import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IRoleDAO;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {

	@Override
	public Map<String, Object> login(Emp emp) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>() ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp selectEmp = empDAO.findById(emp.getEid()) ;	// 根据雇员编号查询雇员信息
		result.put("emp", selectEmp) ;	// 设置查询出来的雇员信息
		if (selectEmp != null) {	// 雇员登录成功
			IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;	// 获取角色信息
			IActionDAO actionDAO = Factory.getDAOInstance("action.dao") ;	// 获取权限信息
			result.put("allRoles", roleDAO.findAllByDept(selectEmp.getDid())) ;	// 根据部门编号查询角色信息
			result.put("allActions", actionDAO.findAllByDept(selectEmp.getDid())) ;
		}
		return result ;
	}

	@Override
	public Map<String, Object> list(String eid) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		List<Emp> emps=empDAO.findAll();
		Set<Long> dids=new HashSet<Long>();
		Set<Long> lid=new HashSet<Long>();
		Set<String> eids=new HashSet<String>();
		Iterator<Emp> empIter=emps.iterator();
		while(empIter.hasNext()) {
			Emp emp=empIter.next();
			dids.add(emp.getDid());
			lid.add(emp.getLid());
			eids.add(emp.getEid());
		}
		Map<Long,String> depts=deptDAO.findByDid(dids);
		Map<Long,String> levels=levelDAO.findByLid(lid);
		Map<String,Long> lids=empDAO.findByEid(eids);
		map.put("emps",emps);
		map.put("depts",depts);
		map.put("levels",levels);
		map.put("lids",lids);
		map.put("dname", empDAO.findDname(eid));
		return map;
	}

	@Override
	public boolean isExisted(String eid) throws SQLException {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp emp = empDAO.findById(eid);
		if(emp == null || !emp.getEid().equals(eid)) {
			return false;
		}
		return true;
	}
	@Override
	public boolean isExistedNotMy(String eid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp emp = empDAO.findById(eid);
		if(emp == null || emp.getEid().equals(eid)) {
			return false;
		}
		return false;
	}
	@Override
	public boolean add(Emp vo, String hrEid) throws SQLException {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		return empDAO.doCreate(vo,hrEid);
	}
	
	@Override
	public boolean edit(Emp vo, String hrEid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		return empDAO.doEditEmp(vo, hrEid);
	}
	
	@Override
	public boolean delete(String eid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		return empDAO.doRemove(eid);
	}
	
	@Override
	public Map<String, Object> editPro(String eid) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp emp=empDAO.findByEid(eid);
		map.put("emp",emp);
		return map;
	}
	@Override
	public boolean autoDeptCurrnum(Long did) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		Integer currnum=deptDAO.getCurrnum(did)+1;
		return deptDAO.currentCountAotu(did,currnum);
	}
	
	@Override
	public boolean subDeptCurrnum(Long did) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		Integer currnum=deptDAO.getCurrnum(did)-1;
		return deptDAO.currentCountAotu(did, currnum);
	}
	
	@Override
	public Long findByEid(String eid) throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		return empDAO.findDnameGetDid(eid);
	}

	@Override
	public Map<String, Object> list(long did,long currengPage,int linesize) throws Exception {
		Map<String,Object> map=new HashMap<>();
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao");
		map.put("allEmps", empDAO.findByDid(did,currengPage,linesize));
		map.put("allRecorders", empDAO.getCount(did));
		return map;
	}

	@Override
	public Map<String, Object> findById(String eid) throws SQLException {
		Map<String,Object> map=new HashMap<>();
		IDeptDAO dDao = Factory.getDAOInstance("dept.dao");
		ILevelDAO lDao = Factory.getDAOInstance("level.dao");
		IEmpDAO eDao = Factory.getDAOInstance("emp.dao");
		map.put("dept", dDao.findById());
		map.put("level", lDao.findByLid());
		map.put("emp", eDao.findByEid(eid));
		return map;
	}

}
