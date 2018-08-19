package cn.mldn.eusplatform.web.action;

import java.util.Map;

import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.CookieUtil;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;

public class LoginAction extends AbstractAction {
	public static final String ACTION_TITLE = "雇员" ;
	/**
	 * 用户登录注销，登录注销后所有的Cookie信息将被删除
	 * @return 提示页面，随后跳转回登录页
	 */
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(),ServletObjectUtil.getResponse()) ;
		cookieUtil.cleanMid() ;
		ServletObjectUtil.getRequest().setAttribute("name", ServletObjectUtil.getRequest().getSession().getAttribute("name"));
		ServletObjectUtil.getRequest().setAttribute("photo", ServletObjectUtil.getRequest().getSession().getAttribute("photo"));
		ServletObjectUtil.getRequest().getSession().invalidate();
		
		super.setUrlAndMsg(mav, "login.page", "logout.success",ACTION_TITLE );
		return mav;
	}
	/**
	 * 验证码检测，用于ajax异步验证处理
	 * @param code 输入验证码
	 */
	public void check(String code) {
		String rand = (String) ServletObjectUtil.getRequest().getSession().getAttribute("rand") ;
		if (rand == null || "".equals(rand)) {
			super.print(false);
		} else {
			super.print(rand.equalsIgnoreCase(code));
		}
	}
	/**
	 * 用户登录处理
	 * @param vo 包含有用户登录信息
	 * @param rememberme 是否要执行免登录
	 * @return 登录成功返回信息提示页（随后跳转到商品列表页），登录失败返回登录页
	 */
	public ModelAndView login(Emp emp) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("login.page"));
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back");
		emp.setPassword(PasswordUtil.encoder(emp.getPassword()));
		try {
			Map<String, Object> loginMap = empService.login(emp) ;	// 登录处理
			if (loginMap.get("emp") != null) { // 登录成功
				Emp selectEmp = (Emp) loginMap.get("emp") ;
				ServletObjectUtil.getRequest().setAttribute("count", 0);
				ServletObjectUtil.getRequest().getSession().setAttribute("eid", selectEmp.getEid());
				ServletObjectUtil.getRequest().getSession().setAttribute("photo", selectEmp.getPhoto());
				ServletObjectUtil.getRequest().getSession().setAttribute("ename", selectEmp.getEname());
				ServletObjectUtil.getRequest().getSession().setAttribute("lid", selectEmp.getLid());
				ServletObjectUtil.getRequest().getSession().setAttribute("allRoles", loginMap.get("allRoles"));
				ServletObjectUtil.getRequest().getSession().setAttribute("allActions", loginMap.get("allActions"));
				mav.setUrl(ActionResourceUtil.getPage("forward.page"));
				super.setUrlAndMsg(mav, "back.index.page", "login.success",ACTION_TITLE );
			} else {
				super.setMsg(mav, "login.failure");
			}
		} catch (Exception e) {
			super.setMsg(mav, "login.failure");
			e.printStackTrace();
		}
		return mav;
	}
}
