package vo;
/**
* @author 于好贤
* @version 创建时间：2018年5月22日 下午1:07:20
* @description 多条件查询的vo对象
*/
public class FindLabInfo {
	private String labID;
	private String teaID;
	private int sweek;
	private int eweek;
	private int day;
	private int section;
	public String getLabID() {
		return labID;
	}
	public void setLabID(String labID) {
		this.labID = labID;
	}
	public String getTeaID() {
		return teaID;
	}
	public void setTeaID(String teaID) {
		this.teaID = teaID;
	}
	@Override
	public String toString() {
		return "FindLabInfo [labID=" + labID + ", teaID=" + teaID + ", sweek=" + sweek + ", eweek=" + eweek + ", day="
				+ day + ", section=" + section + "]";
	}
	public int getSweek() {
		return sweek;
	}
	public void setSweek(int sweek) {
		this.sweek = sweek;
	}
	public int getEweek() {
		return eweek;
	}
	public void setEweek(int eweek) {
		this.eweek = eweek;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
}
