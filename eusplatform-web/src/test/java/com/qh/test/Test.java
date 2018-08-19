package com.qh.test;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.util.factory.Factory;

public class Test {

	
	
	public static void main(String[] args) {
		IScheduleServiceBack is=Factory.getServiceInstance("schedule.service.back");
		try {
			System.out.println(is.listEmp("魔乐科技-32", "", "", 4L, 3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
