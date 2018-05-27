package vo;
//定义实验室信息的VO类
public class LabInfo {
	private String id ;
	private String weizhi ;
	private String leixing ;
	private int renshu ;
	private String beizhu ;
	public void setId(String id){
		this.id = id ;
	}
	public void setWeizhi(String weizhi){
		this.weizhi = weizhi ;
	}
	public void setLeixing(String leixing){
		this.leixing = leixing ;
	}
	
	public void setRenshu(int renshu){
		this.renshu = renshu ;
	}
	public void setBeizhu(String beizhu){
		this.beizhu = beizhu ;
	}
	public String getId(){
		return this.id ;
	}
	public String getWeizhi(){
		return this.weizhi ;
	}
	public String getLeixing(){
		return this.leixing ;
	}
	public int getRenshu(){
		return this.renshu ;
	}
	public String getBeizhu(){
		return this.beizhu ;
	}
}
