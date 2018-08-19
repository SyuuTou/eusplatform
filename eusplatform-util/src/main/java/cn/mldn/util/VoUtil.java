package cn.mldn.util;

import java.lang.reflect.Method;

public class VoUtil {
	/**
	 * vo参数设置
	 * @param vo 需要判断是否有空字段的vo；
	 * @param vo1 将vo不为空的属性，设置给vo1
	 */
	public static void FieldIsNull(Object vo,Object vo1) {
		Method[] methods=vo.getClass().getMethods();
		for(Method temp:methods) {
			if(temp.getName().startsWith("get")&&!temp.getName().equals("getClass")) {
				try {
					if(temp.invoke(vo)!=null) {
						String [] result=temp.getName().split("get",2);
						vo.getClass().getMethod("set"+result[1], temp.getReturnType()).invoke(vo1, temp.invoke(vo));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
