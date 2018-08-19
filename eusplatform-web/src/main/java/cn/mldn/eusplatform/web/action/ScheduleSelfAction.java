package cn.mldn.eusplatform.web.action;

import java.sql.SQLException;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class ScheduleSelfAction extends AbstractAction {
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("scheduleSelf.list.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		try {
			SplitPageUtil spu=new SplitPageUtil("标题:title", "scheduleSelf.list.action");
			mav.addObjectMap(is.list(eid,spu.getKeyWord(), spu.getColumn(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	public ModelAndView listEmp() throws Exception {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("scheduleEmp.list.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		SplitPageUtil spu=new SplitPageUtil("标题:title", "scheduleEmp.list.action");
		try {
			mav.addObjectMap(is.listEmp(eid,spu.getKeyWord(), spu.getColumn(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	public ModelAndView listscheduless() throws Exception {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("scheduless.list.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		String eid=(String)ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		SplitPageUtil spu=new SplitPageUtil("标题:title", "scheduless.list.action");
		try {
			mav.addObjectMap(is.listScheduleReport(eid,spu.getColumn(), spu.getKeyWord(),spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	public ModelAndView listSchedule(String title) {
		
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedule.list.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		
		try {
			mav.addObjectMap(is.listSchedule(title));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	public ModelAndView listSchedules(String title) {
		
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("schedules.list.page"));
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		try {
			mav.addObjectMap(is.listSchedule(title));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	
   
	
	
	
	

}
