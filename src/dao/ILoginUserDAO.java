package dao;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午10:27:25
* @description DAO操作标准接口
*/
import java.util.List;
import vo.LoginUser;
public interface ILoginUserDAO {
	/**
	 * 数据的增加操作
	 * @param loginUser 要增加的数据对象
	 * @return 是否增加成功的标记
	 * @throws Exception 有异常交给被调用处处理
	 */
	public boolean doCreate(LoginUser loginUser) throws Exception;
	/**
	 * 查询全部的数据
	 * @param keyWord 查询关键字
	 * @return 返回全部的查询结果，每一个LoginUser对象表示表的一行记录
	 * @throws Exception 有异常交给被调用处处理
	 */
	public List<LoginUser> findAll(String keyWord) throws Exception;
	/**
	 * 根据用户编号查询用户信息
	 * @param loginId 用户编号
	 * @return 用户的 vo 对象
	 * @throws Exception 有异常交给被调用处处理
	 */
	public LoginUser findById(String loginId) throws Exception;
}
