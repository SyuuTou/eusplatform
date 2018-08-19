package cn.mldn.eusplatform.dao;


import java.sql.SQLException;

import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.dao.IBaseDAO;

public interface IItemDAO extends IBaseDAO<Long, Item> {
	/**
	 * 查找任务分类
	 * @param iid 分类id
	 * @return 分类
	 * @throws SQLException
	 */
	public Item findByIid(Long iid) throws SQLException;
	
	
	
	
}
