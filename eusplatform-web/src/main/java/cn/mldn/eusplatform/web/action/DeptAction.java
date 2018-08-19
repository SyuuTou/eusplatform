package cn.mldn.eusplatform.web.action;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import net.sf.json.JSONObject;

public class DeptAction extends AbstractAction {
	
	public void add(Dept dept) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back");
		try {
			super.print(deptService.add(dept));
		} catch (Exception e) {
			e.printStackTrace();
			super.print(true);
		}
		
	}
	public ModelAndView list() {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back");
	ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("dept.list.page"));
		try {
			mav.addObjectMap(deptService.list(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	public void edit(Dept dept) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back");
		try {
			super.print(deptService.edit(dept));
			
		} catch (Exception e) {
			e.printStackTrace();
				super.print(false);
		}

	}
	
	public void empInfo(String eid) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back");
		try {
			Map<String,Object> map=deptService.getEidAll(eid);
			JSONObject obj = new JSONObject() ;
			obj.put("emp",(Emp)map.get("emp"));
			obj.put("dname",(String)map.get("dname"));
			obj.put("title",(String)map.get("title"));
			obj.put("time",(String)map.get("time"));
			super.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
