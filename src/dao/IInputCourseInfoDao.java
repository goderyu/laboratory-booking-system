package dao;

/**
* @author 于好贤
* @version 创建时间：2018年5月19日 上午10:24:44
* @description 定义从Excel表导入教师信息表的标准
*/
import vo.CourseInfo;

public interface IInputCourseInfoDao {
	/**
	 * 
	 * @param ms 课程信息表的VO对象
	 * @return 返回插入成功或者失败标志
	 */
	public boolean insertExcel(CourseInfo ms) throws Exception;

	/**
	 * 
	 * @param ms 课程信息表的VO对象
	 * @return 返回查询成功或者失败标志
	 */
	public boolean findTea(CourseInfo ms) throws Exception;
}
