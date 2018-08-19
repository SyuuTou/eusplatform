package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.eusplatform.dao.IRoleDAO;
import cn.mldn.eusplatform.service.back.IRoleServiceBack;
import cn.mldn.util.factory.Factory;

public class RoleServiceBack implements IRoleServiceBack{

	@Override
	public List<String> listRid(String eid) throws SQLException {
		IRoleDAO dao = Factory.getDAOInstance("role.dao");
		return dao.findRidByEid(eid);
	}

}
