package cn.mldn.eusplatform.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.service.back.IItemServiceBack;
import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ScheduleAction extends AbstractAction {
	public static final String ACTION_TITLE = "雇员" ;
	
	public ModelAndView addPre() {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.add.page"));
		IItemServiceBack ii=Factory.getServiceInstance("item.service.back");
		try {
			mav.addObjectMap(ii.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void add(Schedule vo) {
//		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("forword.page"));
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		vo.setSubdate(new Date());
		vo.setAudit(0);
		vo.setSeid(eid);
		try {
			super.print(is.add(eid, vo));
//			super.setUrlAndMsg(mav, "schedule.addPre.action", "login.success", "zz");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModelAndView listShow(Long sid) {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.listShow.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			mav.addObjectMap(is.get(eid,sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void reportShow(Long sid) {
		IReportServiceBack ir=Factory.getServiceInstance("report.service.back");
		JSONObject jsonObject=new JSONObject();
		String eid=(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			jsonObject.put("all",ir.list(eid,sid));
			super.print(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void taskApply(Schedule vo) {
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		vo.setAudit(1);
		vo.setSubdate(new Date());
		try {
			Schedule sh=(Schedule)is.get(eid, vo.getSid()).get("schedule");
			if(sh.getAudit()==0) {
				super.print(is.edit(vo, eid));
			}else {
				super.print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ModelAndView taskEditList(Long sid) {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.listEdit.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			mav.addObjectMap(is.listEdit(eid, sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public ModelAndView taskAdd(Schedule vo) {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.listEdit.action"));
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		try {
			super.print(is.edit(vo, eid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	public void taskDelete(Long sid) {
		System.out.println(sid);
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		try {
			super.print(is.delete(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编辑按钮验证
	 * @param sid 任务标号
	 */
	public void taskEdit(Long sid) {
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			Schedule sh=(Schedule)is.get(eid, sid).get("schedule");
			if(sh.getAudit()==0) {
				super.print(true);
			}else {	
				super.print(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 调度审核模块-待审核申请操作
	 * @throws Exception 异常
	 */
	public ModelAndView preAudit() throws Exception {
		IScheduleServiceBack scheduleService=Factory.getServiceInstance("schedule.service.back");
		Map<String, Object> map = scheduleService.list(getCurrentUserEid(), "", "", 1L, Integer.MAX_VALUE);
		List<Schedule> schdules = (List<Schedule>) map.get("allSchedules");
		Long schdulesCount = (Long) map.get("allCounts");
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.showAuditSchdulePre.page"));
		ServletObjectUtil.getRequest().setAttribute("schdules", schdules);
		ServletObjectUtil.getRequest().setAttribute("schdulesCount", schdulesCount);
		return mav;
	}
	
	/**
	 * 调度审核模块-申请列表操作
	 * @throws Exception 异常
	 */
	public ModelAndView listPre() throws Exception{
		IScheduleServiceBack scheduleService=Factory.getServiceInstance("schedule.service.back");
		Map<String, Object> map = scheduleService.list(getCurrentUserEid(), "", "", 1L, Integer.MAX_VALUE);
		List<Schedule> schdules = (List<Schedule>) map.get("allSchedules");
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.audit_schedule_list.page"));
		mav.addObject("schedules", schdules);
		return mav;
	}
	
	/**
	 * 任务协调审核-处理申请
	 * @return 
	 * @throws Exception
	 */
	public ModelAndView preProcessApply() throws Exception {
		System.out.println("preProcessApply be perform!!");
		String sid = ServletObjectUtil.getRequest().getParameter("sid");
		if(sid == null) {
			sid = (String) ServletObjectUtil.getRequest().getSession().getAttribute("sid");
			ServletObjectUtil.getRequest().getSession().removeAttribute("sid");
		}
		System.out.println("preProcessApply sid : "+sid);
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.scheduleApply.page"));
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back");
		IItemServiceBack itemService = Factory.getServiceInstance("item.service.back");
		Schedule schedule = scheduleService.getScheduleById(Long.parseLong(sid));
		Item item = itemService.getItemByIid(schedule.getIid());
		List<Emp> emps = scheduleService.getEmpsBySid(Long.parseLong(sid));
		Map<Long,String> dnames = scheduleService.getDnamesByEmps(emps);
		Map<Long,String> levels = scheduleService.getLevelNamesByEmps(emps);
		
		mav.addObject("sid", sid);
		mav.addObject("schedule", schedule);
		mav.addObject("item", item);
		mav.addObject("emps", emps);
		mav.addObject("empCount", emps.size());
		mav.addObject("dnames", dnames);
		mav.addObject("levels", levels);
//		System.out.println("preProcessApply  -sid  :" + getAttribute("sid"));
//		System.out.println("preProcessApply  -schedule  :" + getAttribute("schedule"));
//		System.out.println("preProcessApply  -item  :" + getAttribute("item"));
//		System.out.println("preProcessApply  -emps  :" + getAttribute("emp"));
//		System.out.println("preProcessApply  -empCount  :" + getAttribute("empCount"));
//		System.out.println("preProcessApply  -dnames  :" + getAttribute("dnames"));
//		System.out.println("preProcessApply  -levels  :" + getAttribute("levels"));
		return mav;
	}
	
	/**
	 * 同意/拒绝调度申请操作
	 * @return
	 * @throws Exception
	 */
	public ModelAndView processApply() throws Exception {
		String seid = ServletObjectUtil.getRequest().getParameter("seid");
		String sid = ServletObjectUtil.getRequest().getParameter("sid");
		String audit = ServletObjectUtil.getRequest().getParameter("audit");
		String note = ServletObjectUtil.getRequest().getParameter("note");
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back");
		Schedule schedule = scheduleService.getScheduleById(Long.parseLong(sid));
		schedule.setSid(Long.parseLong(sid));
		schedule.setSeid(seid);
		schedule.setAudit(Integer.parseInt(audit));
		schedule.setNote(note);
		schedule.setAuddate(new Date());
		schedule.setAeid(getCurrentUserEid());
//		System.out.println("processApply"+sid);
//		System.out.println("审核任务 ： " + schedule);
		boolean isSuccess = scheduleService.edit(schedule, seid);
		System.out.println("审核提交结果 ： " + isSuccess);
//		setAttribute("sid", sid);
		ServletObjectUtil.getRequest().getSession().setAttribute("sid", sid);
//		System.out.println("processApply  -sid  :" + getAttribute("sid"));
//		System.out.println("processApply  -schedule  :" + getAttribute("schedule"));
//		System.out.println("processApply  -item  :" + getAttribute("item"));
//		System.out.println("processApply  -emps  :" + getAttribute("emp"));
//		System.out.println("processApply  -empCount  :" + getAttribute("empCount"));
//		System.out.println("processApply  -dnames  :" + getAttribute("dnames"));
//		System.out.println("processApply  -levels  :" + getAttribute("levels"));
		if(isSuccess) {
			super.setUrlAndMsg(mav, "schedule.scheduleApply.action" , "audit.success" , ACTION_TITLE);
			System.out.println("审核跳转 ： " + isSuccess);
		}else {
			super.setUrlAndMsg(mav, "schedule.scheduleApply.action", "audit.fail" , ACTION_TITLE);
			System.out.println("审核跳转 ： " + isSuccess);
		}
		return mav;
	}
	
	

	public void listDept() {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back");
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject.put("dept", deptService.list());
				super.print(jsonObject);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void listDeptEmp(Long did,long cp,int ls) {
		IEmpServiceBack ie=Factory.getServiceInstance("emp.service.back");
		try {
			if(did>0) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("all", ie.list(did,cp,ls));
				super.print(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addEmp(Long sid,String eid) {
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back");
		String seid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			JSONObject jsonObject=new JSONObject();
			if(scheduleService.addSchduleEmp(eid, sid)) {
				jsonObject.putAll(scheduleService.ListEmp(seid));
				System.out.println(jsonObject);
				super.print(jsonObject);
			}else {
				jsonObject.put("flag", "false");
				super.print(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ModelAndView listEditEmp(Long sid) {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.listEditEmp.page"));
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back");
		try {
//			if(true) {
//				JSONObject jsonObject=new JSONObject();
//				jsonObject.put("all", scheduleService.ListAllEmp(sid) );
				mav.addObjectMap(scheduleService.ListAllEmp(sid));
//				super.print(jsonObject);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	public void deleteEmp(Long sid,String eid) {
		System.out.println(sid+"---"+eid);
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back");
		try {
			super.print(scheduleService.delete(sid,eid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
