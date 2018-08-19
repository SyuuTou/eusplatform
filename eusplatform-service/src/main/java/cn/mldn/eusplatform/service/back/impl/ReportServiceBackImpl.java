package cn.mldn.eusplatform.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.factory.Factory;

public class ReportServiceBackImpl implements IReportServiceBack {

	@Override
	public boolean add(Report vo) throws Exception {
		IReportDAO ir=Factory.getDAOInstance("report.dao");
		if(ir.findById(vo.getEid())==null) {
			return ir.doCreate(vo);
		}
		return false;
	}

	@Override
	public Map<String, Object> list(String eid, Long sid) throws Exception{
		if(eid==null||"".equals(eid)||sid==null||sid==0) {
			return null;
		}
		IReportDAO ir=Factory.getDAOInstance("report.dao");
		Map<String,Object> map=new HashMap<>();
		map.put("report", ir.findById(eid, sid));
		return map;
	}

	@Override
	public Map<String, Object> list(String eid, String column, String keyWord, long currentPage, int lineSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
