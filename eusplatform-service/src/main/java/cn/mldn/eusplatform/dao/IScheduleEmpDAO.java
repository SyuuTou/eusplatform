package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleEmpDAO extends IBaseDAO<Long, ScheduleEmp> {
	/**
	 * 根据任务申请者编号查找
	 * @param eid 任务申请者编号
	 * @return 所有任务编号
	 * @throws SQLException
	 */
	public Set<Long> findById(String eid) throws SQLException;
	/**
	 * 查找最后增加的调度任务申请
	 * @return 调度申请任务编号
	 * @throws SQLException
	 */
	public Long find_last_insert() throws SQLException;
	/**
	 * 根据任务编号查找
	 * @param sid 任务编号
	 * @return 所有参与人员
	 * @throws SQLException
	 */
	public List<ScheduleEmp> findBySId(Long sid) throws SQLException;
	/**
	 * 删除指定参与人员
	 * @param sid 任务编号
	 * @param eid 参与者编号
	 * @return 成功返回true
	 * @throws SQLException
	 */
	public boolean doRemove(Long sid,String eid)throws SQLException;
}