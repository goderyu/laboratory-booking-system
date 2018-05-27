package vo;

public class CourseInfo {
	private String Pk_Id;//排课编号
	private String Room_Id;//教室编号
	private int Sweek;//起始周
	private int Eweek;//结束周
	private int Day;//日期
	private int Part;//第几节
	private String Tea_Id;//教师编号
	private String Class_Id;//班级编号
	private String Sub_Name;//课程名称
	private String Sub_Id;//课程编号
	public String getPk_Id() {
		return Pk_Id;
	}

	public void setPk_Id(String Pk_Id) {
		this.Pk_Id = Pk_Id;
	}

	public String getRoom_Id() {
		return Room_Id;
	}

	public void setRoom_Id(String Room_Id) {
		this.Room_Id = Room_Id;
	}

	public int getSweek() {
		return Sweek;
	}

	public void setSweek(int Sweek) {
		this.Sweek = Sweek;
	}

	public int getEweek() {
		return Eweek;
	}

	public void setEweek(int Eweek) {
		this.Eweek = Eweek;
	}

	public int getDay() {
		return Day;
	}

	public void setDay(int Day) {
		this.Day = Day;
	}

	public int getpart() {
		return Part;
	}

	public void setPart(int Part) {
		this.Part = Part;
	}

	public String getTea_Id() {
		return Tea_Id;
	}

	public void setTea_Id(String Tea_Id) {
		this.Tea_Id = Tea_Id;
	}

	public String getClass_Id() {
		return Class_Id;
	}

	public void setClass_Id(String Class_Id) {
		this.Class_Id = Class_Id;
	}

	public String getSub_Name() {
		return Sub_Name;
	}

	public void setSub_Name(String Sub_Name) {
		this.Sub_Name = Sub_Name;
	}

	public String getSub_Id() {
		return Sub_Id;
	}
	
	public void setSub_Id(String Sub_Id) {
		this.Sub_Id = Sub_Id;
	}
}
