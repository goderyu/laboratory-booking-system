package dao;

import java.util.List;

import vo.FindLabInfo;

/**
* @author 于好贤
* @version 创建时间：2018年5月22日 下午12:10:32
* @description 
*/
public interface ISearchDao {
	public List<String> number() throws Exception;

	public List<String> teacherName() throws Exception;
	
	public List<FindLabInfo> find(String sql) throws Exception;
}
