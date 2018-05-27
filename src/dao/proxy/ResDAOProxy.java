package dao.proxy; 
/**
* @author 李浩
* 类说明
*/

import java.util.ArrayList;
import java.util.List;

import dao.IResDAO;
import dao.impl.ResDAOImpl;
import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import vo.ReserveExperiment;
import vo.labBan;
import vo.laboratory;
import vo.teacherCourse;

public class ResDAOProxy implements IResDAO{
	private DatabaseConnection dbc = null;
	private ResDAOImpl dao = null;
	public ResDAOProxy() throws Exception{
		this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
		this.dao = new ResDAOImpl(this.dbc.getConnection());
	}
	public List<ReserveExperiment> find (String laboratoryID,int week,String teacherID) throws Exception{
		List<ReserveExperiment> all = null;
		all = this.dao.find(laboratoryID, week,teacherID);
		this.dbc.close();
		return all;
	}
	@Override
	public List<laboratory> findAllLab() throws Exception {
		// TODO 自动生成的方法存根
		List<laboratory> all = null;
		all = this.dao.findAllLab();
		this.dbc.close();
		return all;
	}

	@Override
	public boolean insertJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek) throws Exception {
		boolean flag = false;
		if(this.dao.insertJudge(laboratoryID,section,whichday,startweek,endweeek)){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean banJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek) throws Exception {
		boolean flag = false;
		if(this.dao.banJudge(laboratoryID,section,whichday,startweek,endweeek)){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<labBan> findLabBan(String laboratoryID, int week) throws Exception {
		List<labBan> all = new ArrayList<labBan>();
		all = this.dao.findLabBan(laboratoryID,week);
		this.dbc.close();
		return all;
	}

	@Override
	public List<teacherCourse> findByteacherID(String teacherID) throws Exception {
		List<teacherCourse> all = new ArrayList<teacherCourse>();
		all = this.dao.findByteacherID(teacherID);
		this.dbc.close();
		return all;
	}

	@Override
	public boolean insertRes(String teacherID, String laboratoryID, String courseID, int whichDay, int section, int startweek, int endweek) throws Exception {
		boolean flag = false;
		if(this.dao.insertRes(teacherID,laboratoryID, courseID,whichDay,section,startweek,endweek)){
			flag = true;
		}
		this.dbc.close();
		return flag;
	}

	@Override
	public Integer deleteOrder(int orderID, int week) throws Exception {
		int k=0;
		k = this.dao.deleteOrder(orderID,week);
		this.dbc.close();
		return k;
	}

	public List<teacherCourse> findCourseName(String courseID) throws Exception {
		List<teacherCourse> all = new ArrayList<teacherCourse>();
		all = this.dao.findCourseName(courseID);
		this.dbc.close();
		return all;
	}

	@Override
	public List<ReserveExperiment> searchOrder(int orderID, int week) throws Exception {
		List<ReserveExperiment> all = null;
		all = this.dao.searchOrder(orderID,week);
		this.dbc.close();
		return all;
	}

	@Override
	public List<teacherCourse> findClassName(String ClassID) throws Exception {
		List<teacherCourse> all =null;
		all=this.dao.findClassName(ClassID);
		this.dbc.close();
		return all;
	}

	@Override
	public List<ReserveExperiment> findMassage(String ClassID) throws Exception {
		List<ReserveExperiment> all =null;
		all=this.dao.findMassage(ClassID);
		this.dbc.close();
		return all;
	}

	public String findTeacherName(String number) throws Exception {	
		// TODO Auto-generated method stub
		String name = this.dao.findTeacherName(number);
		this.dbc.close();
		return name;
	}
}
