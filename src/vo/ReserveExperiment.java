package vo;
/**
* @author 李浩
* 类说明：实验预约记录
*/
public class ReserveExperiment {
	private int orderID;
	private String teacherName;
	private String laboratoryID;
	private String courseID;
	private int whichDay;
	private int section;
	private int startweek;
	private int endweek;
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getLaboratoryID() {
		return laboratoryID;
	}
	public void setLaboratoryID(String laboratoryID) {
		this.laboratoryID = laboratoryID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public int getWhichDay() {
		return whichDay;
	}
	public void setWhichDay(int whichDay) {
		this.whichDay = whichDay;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getStartweek() {
		return startweek;
	}
	public void setStartweek(int startweek) {
		this.startweek = startweek;
	}
	public int getEndweek() {
		return endweek;
	}
	public void setEndweek(int endweek) {
		this.endweek = endweek;
	}
}
