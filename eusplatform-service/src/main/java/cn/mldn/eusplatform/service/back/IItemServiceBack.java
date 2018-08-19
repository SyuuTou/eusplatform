package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.eusplatform.vo.Item;

public interface IItemServiceBack {
	/**
	 * 列表显示所有调度任务类型
	 * @return 所有的任务类型
	 * @throws Exception
	 */
	public Map<String,Object> list() throws Exception;
	
	/**
	 * 根据iid找到item
	 * @param iid 待查找item的iid
	 * @return 返回item实例
	 * @throws SQLException sql异常
	 */
	Item getItemByIid(Long iid) throws SQLException;
}
