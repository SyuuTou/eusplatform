package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.eusplatform.vo.Level;

public interface ILevelServiceBack {
	/**
	 * 列出所有的职位
	 * @return 返回所有的职位
	 * @throws SQLException sql异常
	 */
	List<Level> list() throws SQLException;
	
	/**
	 * 根据职位等级id查询职位等级的具体信息
	 * @param lid	待查询的职位id
	 * @return 返回职位等级的实例
	 * @throws SQLException sql异常
	 */
	Level getLevelByLid(Long lid) throws SQLException;
}
