package dao;

import java.util.* ;
import vo.LabInfo ;
public interface ILabInfoDAO {//定义DAO操作标准
	public boolean doCreate(LabInfo labInfo) throws Exception ;//增加操作
	public List<LabInfo> findAll(String keyWord) throws Exception ;//查询全部
	public LabInfo findById(String id) throws Exception ;//根据实验室编号查询
	public boolean doUpdate(LabInfo labInfo) throws Exception;//修改操作
	public boolean doRemove(String id) throws Exception;//删除操作

}
