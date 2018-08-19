package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.VoUtil;
import cn.mldn.util.factory.Factory;

public class ScheduleServiceBackImpl implements IScheduleServiceBack{

	@Override
	public boolean add(String eid,Schedule vo) throws Exception {
		IScheduleEmpDAO ise=Factory.getDAOInstance("scheduleEmp.dao");
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		Set<Long> sids=ise.findById(eid);
		System.out.println(sids);
		ScheduleEmp evo=new ScheduleEmp();
		if(sids==null||sids.size()==0) {
			is.doCreate(vo);
			evo.setSid(ise.find_last_insert());
			evo.setEid(eid);
			return ise.doCreate(evo);
		}else {
			List<Schedule> all=is.findAllBySid(sids);
			Iterator<Schedule> iter=all.iterator();
			while(iter.hasNext()) {//5表示任务已完成，可以再次申请
				Schedule schedule=iter.next();
				if(schedule.getAudit()<5) {
					return false;
				}
			}
			is.doCreate(vo);
			evo.setSid(ise.find_last_insert());
			evo.setEid(eid);
			return ise.doCreate(evo);
		}
	}

	@Override
	public Map<String, Object> list(String eid,String keyword,String column,Long currentPage,Integer linesize) throws Exception {
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		if(eid==null||"".equals(eid)) {
			return null;
		}
		Map<String,Object> map=new HashMap<>();
		if("".equals(column)||column==null||"".equals(keyword)||keyword==null) {
			map.put("allSchedules", is.findAllBySeid(eid, currentPage, linesize));
			map.put("allCounts", is.getCount(eid));
		}else {
			map.put("allSchedules",is.findAllBySeid(eid, keyword, column, currentPage, linesize));
			map.put("allCounts", is.getCount(eid, keyword, column));
		}
		return map;
	}

	@Override
	public Map<String, Object> get(String eid,Long sid) throws Exception {
		if(eid==null||"".equals(eid)) {
			return null;
		}
		Map<String,Object> map=new HashMap<>();
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		IItemDAO ii=Factory.getDAOInstance("item.dao");
		Schedule vo=is.findBySeid(sid);
		map.put("schedule", vo);
		map.put("item", ii.findByIid(vo.getIid()));
		return map;
	}


	@Override
	public Map<String, Object> listEmp(String eid, String keyword, String column, Long currentPage, Integer linesize)throws Exception {
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		if(eid==null||"".equals(eid)) {
			return null;
		}
		Map<String,Object> map=new HashMap<>();
		if("".equals(column)||column==null||"".equals(keyword)||keyword==null) {
			map.put("allSchedules", is.findAllEmp(eid, currentPage, linesize));
			map.put("allCounts", is.getCountEmp(eid));
		}else {
			map.put("allSchedules",is.findAllEmp(eid, keyword, column, currentPage, linesize));
			map.put("allCounts", is.getCountEmp(eid, keyword, column));
		}
		return map;
	}

	@Override
	public boolean edit(Schedule vo,String eid) throws Exception {
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		Schedule sc=is.findBySeid(vo.getSid());
		if(vo==null||sc==null) {
			return false;
		}
		VoUtil.FieldIsNull(vo, sc);
		return is.doEdit(sc);
	}

	@Override
	public Map<String, Object> listEdit(String eid,Long sid) throws Exception {
		if(eid==null||"".equals(eid)) {
			return null;
		}
		Map<String,Object> map=new HashMap<>();
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		IItemDAO ii=Factory.getDAOInstance("item.dao");
		Schedule vo=is.findBySeid(sid);
		map.put("schedule", vo);
		map.put("allItems", ii.findAll());
		return map;
	}

	@Override
	public boolean delete(Long sid) throws Exception {
		if(sid==null) {
			return false;
		}
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		IReportDAO ir=Factory.getDAOInstance("report.dao");
		IScheduleEmpDAO isc=Factory.getDAOInstance("scheduleEmp.dao");
		return is.doRemove(sid)||ir.doRemove(sid)||isc.doRemove(sid);
	}

	@Override
	public Schedule getScheduleById(Long sid) throws SQLException {
		IScheduleDAO dao=Factory.getDAOInstance("schedule.dao");
		return dao.findById(sid);
	}

	@Override
	public List<Emp> getEmpsBySid(Long sid) throws SQLException {
		IScheduleDAO dao = Factory.getDAOInstance("schedule.dao");
		return dao.getEmpsBySid(sid);
	}

	@Override
	public Map<Long, String> getLevelNamesByEmps(Set<Emp> emps) throws SQLException {
		ILevelDAO dao = Factory.getDAOInstance("level.dao");
		return dao.findByEmps(emps);
	}
	
	@Override
	public Map<Long, String> getLevelNamesByEmps(List<Emp> emps) throws SQLException {
		Set<Emp> set = new HashSet<Emp>();
		for(int i = 0; i < emps.size(); i ++) {
			set.add(emps.get(i));
		}
		return getLevelNamesByEmps(set);
	}

	@Override
	public Map<Long, String> getDnamesByEmps(Collection<Emp> emps) throws SQLException {
		IEmpDAO dao = Factory.getDAOInstance("emp.dao");
		return dao.findDameByEmps(emps);
	}

	@Override
	public Map<String, Object> listSchedule(String title) throws SQLException {
		Map<String,Object> map = new HashMap<>();
		IScheduleDAO sDao = Factory.getDAOInstance("schedule.dao");
		IItemDAO iDao = Factory.getDAOInstance("item.dao");
		IEmpDAO eDao = Factory.getDAOInstance("emp.dao");
		IDeptDAO dDao = Factory.getDAOInstance("dept.dao");
		ILevelDAO lDao = Factory.getDAOInstance("level.dao");
		IReportDAO rDao = Factory.getDAOInstance("report.dao");
		
		map.put("schedule", sDao.findByTitle(title));
		map.put("item", iDao.findByIid(sDao.findByTitle(title).getIid()));
		
		map.put("idEmp", eDao.findById(sDao.findByTitle(title).getIid()));
		map.put("report", rDao.list());
		map.put("reportCount", rDao.findBySid());
		
		map.put("emp", eDao.findTitle(title));
		map.put("dept", dDao.findById());
		map.put("level",lDao.findByLid());
		return map;
	}

	@Override
	public boolean addSchduleEmp(String eid,Long sid) throws SQLException {
		IScheduleEmpDAO ise=Factory.getDAOInstance("scheduleEmp.dao");
		IScheduleDAO is=Factory.getDAOInstance("schedule.dao");
		Set<Long> sids=ise.findById(eid);
		System.out.println("sids="+sid);
		ScheduleEmp evo=new ScheduleEmp();
		if(sids==null||sids.size()==0) {
			evo.setEid(eid);
			evo.setSid(sid);
			return ise.doCreate(evo);
		}else {
			Iterator<ScheduleEmp> iterEmp=ise.findBySId(sid).iterator();
			while(iterEmp.hasNext()) {
				ScheduleEmp sce=iterEmp.next();
				if(sce.getEid().equals(eid)) {
					return false;
				}
			}
			List<Schedule> all=is.findAllBySid(sids);
			Iterator<Schedule> iter=all.iterator();
				while(iter.hasNext()) {
					Schedule sc=iter.next();
					long datetime=sc.getSdate().getTime()-sc.getSubdate().getTime();
					System.out.println("datetime"+datetime);
					if(datetime>0&&datetime<=86400000) {//任务正在执行中
						return false;
					}
			}
				evo.setSid(sid);
				evo.setEid(eid);
				System.out.println("sid="+sid);
				ise.doCreate(evo);
				Schedule vo=new Schedule();
				System.out.println("eid="+eid+"sid="+sid);
				Schedule sc=is.findBySeid(sid);
				System.out.println("findBySeid--"+sc);
				vo.setEcount(sc.getEcount()+1);
				VoUtil.FieldIsNull(vo, sc);
				System.out.println(sc);
				return 	is.doEdit(sc);
			}	
		}
	
	
	@Override
	public Map<String, Object> listScheduleReport(String eid, String column, String keyWord, long currentPage,
			int lineSize) throws SQLException {
		IScheduleDAO sDao = Factory.getDAOInstance("schedule.dao");
		IReportDAO rDao = Factory.getDAOInstance("report.dao");
		Map<String,Object> map=new HashMap<>();
		if("".equals(column)||column==null||"".equals(keyWord)||keyWord==null) {
			map.put("allSchedules",sDao.findReport(eid, currentPage, lineSize));
			map.put("allCounts", sDao.findReport(eid));
		}else {
			map.put("allSchedules",sDao.findReport(eid, column, keyWord, currentPage, lineSize));
			map.put("allCounts", sDao.findReport(eid, column, keyWord));
		}
		map.put("report", rDao.findSplitReport(eid));
		
		return map;
		
	}


	@Override
	public Map<String, Object> ListEmp(String eid) throws SQLException {
		if(eid==null||"".equals(eid)) {
			return null;
		}
		Map<String,Object> map=new HashMap<>();
		IEmpDAO ie=Factory.getDAOInstance("emp.dao");
		IDeptDAO id=Factory.getDAOInstance("dept.dao");
		ILevelDAO il=Factory.getDAOInstance("level.dao");
		Emp evo=ie.findByEid(eid);
		map.put("emp", evo);
		map.put("dept", id.findById(evo.getDid()));
		map.put("level", il.findById(evo.getLid()));
		map.put("flag", "true");
		return map;
	}

	@Override
	public Map<String, Object> ListAllEmp(Long sid) throws SQLException {
		Map<String,Object> map=new HashMap<>();
		IScheduleEmpDAO isc=Factory.getDAOInstance("scheduleEmp.dao");
		IDeptDAO ide=Factory.getDAOInstance("dept.dao");
		IEmpDAO iem=Factory.getDAOInstance("emp.dao");
		ILevelDAO ile=Factory.getDAOInstance("level.dao");
		Iterator<ScheduleEmp> iterEmp=isc.findBySId(sid).iterator();
		Set<String> eids=new HashSet<>();
		while(iterEmp.hasNext()) {
			ScheduleEmp sc=iterEmp.next();
			eids.add(sc.getEid());
		}
		map.put("allEmps", iem.findByEids(eids));
		map.put("allDepts", ide.findAll());
		map.put("allLevels", ile.findAll());
		return map;
	}

	@Override
	public boolean delete(Long sid, String eid) throws SQLException {
		IScheduleEmpDAO isc=Factory.getDAOInstance("scheduleEmp.dao");
		return isc.doRemove(sid,eid);
	}

}
