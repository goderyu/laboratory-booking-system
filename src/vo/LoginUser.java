package vo;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午9:49:58
* @description 登录用户信息类，对应登录用户信息表
*/


/**
 * 
 * @userId 定义用户登录名，与用户登录信息表中的用户编号对应
 * @userPass 定义用户登录密码，与用户登录信息表中的用户密码对应
 * @userType 定义用户类别，与用户登录信息表中的用户类别对应
 */
public class LoginUser {
	private String userId;
	private String userPass;
	private int userType;	//定义为int类型，取值只能为0和1,0对应教师身份
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
}
