package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.factory.Factory;

public class LevelServiceBackImpl implements ILevelServiceBack{

	@Override
	public List<Level> list() throws SQLException {
		ILevelDAO dao = Factory.getDAOInstance("level.dao");
		return dao.findAll();
	}

	@Override
	public Level getLevelByLid(Long lid) throws SQLException {
		ILevelDAO dao = Factory.getDAOInstance("level.dao");
		return dao.findById(lid);
	}

}
