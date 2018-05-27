package dao;

import java.util.List;

import vo.ReserveExperiment;
import vo.labBan;
import vo.laboratory;
import vo.teacherCourse;

/**
* @author 李浩
* 类说明
*/
public interface IResDAO {
	/**
	 *
	 * @param laboratoryID  实验室编号
	 * @param week 周次
	 * @param teacherID 教师编号
	 * @return 返回按周次,实验室和教师编号查询的结果，教师编号也可为空。
	 * @throws Exception
	 */
	public List<ReserveExperiment> find(String laboratoryID, int week, String teacherID) throws Exception;

	/**
	 * @param
	 * @return 返回所有实验室的编号
	 */
	public List<laboratory> findAllLab() throws Exception;

	/**
	 * @param laboratoryID 实验室编号; section 节次; whichday 周几; startweek 起始周; endweek 结束周
	 * @return 返回该实验室时间段是否未预约
	 */
	public boolean insertJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek)
			throws Exception;

	/**
	 * 被禁用返回false 未被禁用返回true
	 * @param laboratoryID 实验室编号
	 * @param section 节次
	 * @param whichday 周几
	 * @param startweek 开始周
	 * @param endweeek 结束周
	 * @return 返回该实验室该时间段是否未被禁用
	 * @throws Exception
	 */
	public boolean banJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek)
			throws Exception;

	/**
	 *
	 * @param laboratoryID 实验室编号
	 * @param week 周
	 * @return 返回当前实验室，当前周的禁用信息。
	 * @throws Exception
	 */
	public List<labBan> findLabBan(String laboratoryID, int week) throws Exception;

	/**
	 * 根据教师编号查询课程
	 * @param teacherID 教师编号
	 * @return 返回查询得到的课程信息
	 * @throws Exception
	 */
	public List<teacherCourse> findByteacherID(String teacherID) throws Exception;

	/**
	 *
	 * @param teacherID 教师编号
	 * @param laboratoryID 实验室编号
	 * @param courseID 课程编号
	 * @param whichDay 周几
	 * @param section 第几节
	 * @param startweek 起始周
	 * @param endweek 结束周
	 * @return 是否插入成功
	 */
	public boolean insertRes(String teacherID, String laboratoryID, String courseID, int whichDay, int section,
			int startweek, int endweek) throws Exception;

	/**
	 * 删除单节实验预约
	 * @param orderID 预约编号
	 * @param week 周次
	 * @return 返回受影响行数
	 * @throws Exception
	 */
	public Integer deleteOrder(int orderID, int week) throws Exception;

	/**
	 * 根据课程编号查询课程名称
	 * @param courseID
	 * @return
	 * @throws Exception
	 */
	public List<teacherCourse> findCourseName(String courseID) throws Exception;

	/**
	 *
	 * @param orderID
	 * @param week
	 * @return 查询到的预约详细记录
	 * @throws Exception
	 */
	public List<ReserveExperiment> searchOrder(int orderID, int week) throws Exception;

	/**
	 * @param ClassID 课程编号
	 * @return 课程名称
	 */
	public List<teacherCourse> findClassName(String ClassID) throws Exception;

	/**
	 *
	 * @param ClassID 课程编号
	 * @return 返回该课程的实验预约信息
	 * @throws Exception
	 */
	public List<ReserveExperiment> findMassage(String ClassID) throws Exception;
	
	/**
	 * 通过教师编号查询教师名称
	 * @param number 教师编号
	 * @return
	 * @throws Exception
	 */
	public String findTeacherName(String number) throws Exception;
}
