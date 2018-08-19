package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.service.back.IItemServiceBack;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.factory.Factory;

public class ItemServiceBackImpl implements IItemServiceBack {

	@Override
	public Map<String,Object> list() throws Exception {
		IItemDAO ii=Factory.getDAOInstance("item.dao");
		Map<String,Object> map=new HashMap<>();
		map.put("allItems", ii.findAll());
		return map;
	}

	@Override
	public Item getItemByIid(Long iid) throws SQLException {
		IItemDAO dao = Factory.getDAOInstance("item.dao");
		return dao.findByIid(iid);
	}

}
