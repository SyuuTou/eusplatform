package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.List;

public interface IRoleServiceBack {
	/**
	 * 根据员工id列出该员工的所有权限id
	 * @param eid 该员工的id
	 * @return 返回该员工的所有权限id
	 * @throws SQLException sql异常
	 */
	List<String> listRid(String eid) throws SQLException;
}
