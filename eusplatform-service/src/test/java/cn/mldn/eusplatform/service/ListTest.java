package cn.mldn.eusplatform.service;

import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.VoUtil;

//import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
//import cn.mldn.util.factory.Factory;

public class ListTest {
	public static void main(String[] args) {
//		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
//		try {
//			System.out.println(is.list("mldn-president", "qqq", "title", 1L, 1));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Schedule vo=new Schedule();
		Schedule vo1=new Schedule();
		vo.setSid(22L);
		vo.setIid(3L);
		VoUtil.FieldIsNull(vo,vo1);
		System.out.println(vo1);
	}
}
