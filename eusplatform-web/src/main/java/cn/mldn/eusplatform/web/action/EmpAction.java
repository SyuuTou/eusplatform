package cn.mldn.eusplatform.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.StringUtils;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ParameterUtil;
import cn.mldn.util.web.ServletObjectUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class EmpAction extends AbstractAction{
	private static final String EMP_TITLE = "雇员";
	public ModelAndView listPro() {
		String eid=(String) ServletObjectUtil.getRequest().getSession().getAttribute("eid");
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("back.emp.emplist.page"));
		IEmpServiceBack empservice=Factory.getServiceInstance("emp.service.back");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			map=empservice.list(eid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObjectMap(map);
		return mav;
	}
	
	/**
	 * 查询出所有的部门信息，所有的职位信息
	 * @return 跳转到员工添加页面
	 * @throws SQLException SQL异常
	 */
	public ModelAndView preAdd() throws SQLException {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("emp.add.page"));
		IDeptServiceBack deptServiceBack = Factory.getServiceInstance("dept.service.back");
		ILevelServiceBack levelServiceBack = Factory.getServiceInstance("level.service.back");
		List<Dept> depts = deptServiceBack.list();
		List<Level> levels = levelServiceBack.list();
		ServletObjectUtil.getRequest().setAttribute("depts", depts);
		ServletObjectUtil.getRequest().setAttribute("levels", levels);
		return mav;
	}

	
	/**
	 * 执行职员添加逻辑操作
	 *   <li>员工登录ID不允许重复；</li>
	 * <li>判断是否满人；</li>
	 * <li>职位调动规则；</li>
	 * <li>工资等级更新；</li>
	 * @return 无论成功失败最终返回本页
	 * @throws SQLException SQL异常
	 */
	public ModelAndView add(Emp vo) throws Exception {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		if(ServletObjectUtil.getParam().isUpload("pic")) {
			String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0);
			vo.setPhoto(fileName);
		}else {
			vo.setPhoto("nophoto.jpg"); 
		}
		IDeptServiceBack deptServiceBack = Factory.getServiceInstance("dept.service.back");
		IEmpServiceBack empServiceBack = Factory.getServiceInstance("emp.service.back");
		ILevelServiceBack levelServiceBack = Factory.getServiceInstance("level.service.back");
		System.out.println("注册信息 : " + vo);
		if(!empServiceBack.isExisted(vo.getEid())) {
			//System.out.println("eid不存在，可以注册新员");
			if(!deptServiceBack.isFull(vo.getDid())) {
				//System.out.println("部门人数未满，可以添人");
				//System.out.println("添加普通员工不需要让坑");
				if(vo.getLid() != 3) {
					String eid = deptServiceBack.findEidByLidAndDid(vo.getLid(), vo.getDid());
					if(!(null == eid || "".equals(eid))) {
						//System.out.println("该部门该岗位有人，该人要让坑");
						deptServiceBack.depose(eid, vo.getLid());
						deptServiceBack.editByEid(vo.getDid(),vo.getEid());
					}
				}
				boolean isSuccess = empServiceBack.add(vo,getCurrentUserEid());
				//System.out.println("添加结果 ： " + isSuccess);
				if(ServletObjectUtil.getParam().isUpload("pic")){
					String filePath=ServletObjectUtil.getApplication().getRealPath("/upload/emp/")+vo.getPhoto();
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
				}
				empServiceBack.autoDeptCurrnum(vo.getDid());
				super.setUrlAndMsg(mav, "emp.preAdd.action", "register.success",EMP_TITLE );
			}else {
				//System.out.println("满人，没坑");
				super.setUrlAndMsg(mav, "emp.preAdd.action", "register_fail_fulled_dept.fail",EMP_TITLE );
			}
		} else {
			//System.out.println("eid已经被注册，请重新输入");
			super.setUrlAndMsg(mav, "emp.preAdd.action", "register_fail_eid_exist.fail",EMP_TITLE );
		}
		return mav;
	}
	
	public ModelAndView editPro(String eid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("back.emp.empedit.page"));
		IEmpServiceBack empservice=Factory.getServiceInstance("emp.service.back");
		IDeptServiceBack deptServiceBack = Factory.getServiceInstance("dept.service.back");
		ILevelServiceBack levelServiceBack = Factory.getServiceInstance("level.service.back");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			List<Dept> depts = deptServiceBack.list();
			List<Level> levels = levelServiceBack.list();
			map=empservice.editPro(eid);
			map.put("depts",depts);
			map.put("levels",levels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObjectMap(map);
		return mav;
	}
	
	public ModelAndView edit(Emp vo,String oldphoto) {
		ModelAndView mav=new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		vo.setPhoto(oldphoto);
		vo.setIneid(getCurrentUserEid());
		if(ServletObjectUtil.getParam().isUpload("pic")) {
			System.out.println("有文件上传");
			if(oldphoto.equals("nophoto.png")) {
				//System.out.println("原来文件为默认");
				String fileName = ServletObjectUtil.getParam().createUploadFileName("pic").get(0);
				vo.setPhoto(fileName);
			}
		}
		IDeptServiceBack deptServiceBack = Factory.getServiceInstance("dept.service.back");
		IEmpServiceBack empServiceBack = Factory.getServiceInstance("emp.service.back");
		ILevelServiceBack levelServiceBack = Factory.getServiceInstance("level.service.back");
		//System.out.println("注册信息 : " + vo);
		try {
			long didPre=empServiceBack.findByEid(vo.getEid());
			if(empServiceBack.findByEid(vo.getEid())==vo.getDid()) {   //没换部门
				//System.out.println("没换部门");
				if(vo.getLid() != 3) {  
					String eid = deptServiceBack.findEidByLidAndDid(vo.getLid(), vo.getDid());
					if(!(null == eid || "".equals(eid))) {
						//System.out.println("该部门该岗位有人，该人要让坑");
						deptServiceBack.depose(eid, vo.getLid());
						deptServiceBack.editByEid(vo.getDid(),vo.getEid());
					}
				}
				empServiceBack.edit(vo,getCurrentUserEid());
				//System.out.println("11111111");
				if(ServletObjectUtil.getParam().isUpload("pic")){
					String filePath=ServletObjectUtil.getApplication().getRealPath("/upload/emp/")+vo.getPhoto();
					ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
				}
				if(deptServiceBack.findEidByLidAndDid(2L, vo.getDid())==null||"".equals(deptServiceBack.findEidByLidAndDid(2L, vo.getDid()))) {
					deptServiceBack.editByEid(vo.getDid(),null);
				}
				super.setUrlAndMsg(mav, "emp.preList.action", "edit.success",EMP_TITLE );
			}else {
				//System.out.println("更换了部门");
				if(!deptServiceBack.isFull(vo.getDid())) {
					//System.out.println("部门人数未满，可以添人");
					if(vo.getLid() != 3) {
						String eid = deptServiceBack.findEidByLidAndDid(vo.getLid(), vo.getDid());
						if(!(null == eid || "".equals(eid))) {
							//System.out.println("该部门该岗位有人，该人要让坑");
							deptServiceBack.depose(eid, vo.getLid());
							deptServiceBack.editByEid(vo.getDid(),vo.getEid());
						}
					}
					if(empServiceBack.delete(vo.getEid())) {
						boolean isSuccess=empServiceBack.add(vo,getCurrentUserEid());
					}
					if(ServletObjectUtil.getParam().isUpload("pic")){
						String filePath=ServletObjectUtil.getApplication().getRealPath("/upload/emp/")+vo.getPhoto();
						ServletObjectUtil.getParam().saveUploadFile("pic", filePath);
					}
					empServiceBack.autoDeptCurrnum(vo.getDid());    //新部门增加人
					empServiceBack.subDeptCurrnum(didPre);    // 旧部门 减人
					super.setUrlAndMsg(mav, "emp.preList.action", "edit.success",EMP_TITLE );
				}else {
					//System.out.println("满人，没坑");
					super.setUrlAndMsg(mav, "emp.preList.action", "edit.false",EMP_TITLE );
				}
			}
		} catch (Exception e) {
			super.setUrlAndMsg(mav, "emp.preList.action", "edit.false",EMP_TITLE );
			e.printStackTrace();
		} 
		return mav;
	}
	
	public void list(String eid) {
		
		IEmpServiceBack ie = Factory.getServiceInstance("emp.service.back");
		JsonConfig config = new JsonConfig() ;
		//config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		try {
			JSONObject json = new JSONObject();
			json.putAll(ie.findById(eid),config);
			super.print(json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
