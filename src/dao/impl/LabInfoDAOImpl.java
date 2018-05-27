package dao.impl;


import java.util.* ;
import java.sql.* ;
import dao.ILabInfoDAO ;
import vo.LabInfo ;
//真实主题实现类
public class LabInfoDAOImpl implements ILabInfoDAO {
	private Connection conn = null ;//数据库连接对象
	private PreparedStatement pstmt = null ;//数据库操作对象
	public LabInfoDAOImpl(Connection conn){//通过构造方法取得数据库连接
		this.conn = conn ;//取得数据库连接
	}
	@Override
	public boolean doCreate(LabInfo labinfo) throws Exception{
		boolean flag = false ;//定义标志位
		String sql = "INSERT INTO 实验室信息表 (实验室编号,位置,实验室类型,可容纳人数,备注) VALUES (?,?,?,?,?)" ;
		this.pstmt = this.conn.prepareStatement(sql) ;//实例化prepareStatement对象
		//this.pstmt = this.conn.prepareStatement("{call dbo.syincrease(?,?,?,?,?)}");
		this.pstmt.setString(1,labinfo.getId()) ;
		this.pstmt.setString(2,labinfo.getWeizhi()) ;
		this.pstmt.setString(3,labinfo.getLeixing()) ;
		this.pstmt.setInt(4,labinfo.getRenshu()) ;
		this.pstmt.setString(5,labinfo.getBeizhu()) ;
		if(this.pstmt.executeUpdate() > 0){//更新记录的行数大于0
			flag = true ;//修改标志位
		}
		this.pstmt.close() ;//关闭prepareStatement操作
		return flag ;
	}
	@Override
	public List<LabInfo> findAll(String keyWord) throws Exception{
		List<LabInfo> all = new ArrayList<LabInfo>() ;//定义集合，接收全部数据
		String sql = "SELECT 实验室编号,位置,实验室类型,可容纳人数,备注 FROM 实验室信息表 WHERE 位置 LIKE ? OR 实验室类型 LIKE ?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;//实例化prepareStatement对象
		
		//this.pstmt = this.conn.prepareStatement("{call dbo.sysearch(?)}");
		this.pstmt.setString(1,"%"+keyWord+"%") ;//设计查询关键字
		this.pstmt.setString(2,"%"+keyWord+"%") ;
		ResultSet rs = this.pstmt.executeQuery() ;//执行查询操作
		LabInfo labinfo = null ;//定义Emp对象
		while(rs.next()){//依次取出
			labinfo = new LabInfo() ;//实例化新的Emp对象
			labinfo.setId(rs.getString(1)) ;
			labinfo.setWeizhi(rs.getString(2)) ;
			labinfo.setLeixing(rs.getString(3)) ;
			labinfo.setRenshu(rs.getInt(4)) ;
			labinfo.setBeizhu(rs.getString(5)) ;
			all.add(labinfo) ;//向集合中增加对象
		}
		this.pstmt.close() ;
		return all ;
	}
	@Override
	public LabInfo findById(String id) throws Exception{
		LabInfo labinfo = null ;
		String sql = "SELECT 实验室编号,位置,实验室类型,可容纳人数,备注 FROM 实验室信息表  WHERE 实验室编号=?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		
		//this.pstmt = this.conn.prepareStatement("{call dbo.sysearch1(?)}");
		this.pstmt.setString(1,id) ;//设置实验室编号
		ResultSet rs = this.pstmt.executeQuery() ;//执行查询操作
		if(rs.next()){
			labinfo = new LabInfo() ;
			labinfo.setId(rs.getString(1)) ;
			labinfo.setWeizhi(rs.getString(2)) ;
			labinfo.setLeixing(rs.getString(3)) ;
			labinfo.setRenshu(rs.getInt(4)) ;
			labinfo.setBeizhu(rs.getString(5)) ;
		}
		this.pstmt.close() ;
		return labinfo ;
	}
	@Override
	public boolean doUpdate(LabInfo labinfo) throws Exception{
		boolean flag=false;
		String sql = "update 实验室信息表 set 位置=?,实验室类型=?,可容纳人数=?,备注=? where 实验室编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		//this.pstmt = this.conn.prepareStatement("{call dbo.syedit(?,?,?,?,?)}");
		this.pstmt.setString(1,labinfo.getWeizhi()) ;
		this.pstmt.setString(2,labinfo.getLeixing()) ;
		this.pstmt.setInt(3,labinfo.getRenshu()) ;
		this.pstmt.setString(4,labinfo.getBeizhu()) ;
		this.pstmt.setString(5,labinfo.getId()) ;
		
		if(this.pstmt.executeUpdate() > 0){//更新记录的行数大于0
			flag = true ;
		}
		this.pstmt.close() ;
		return flag ;
	}
	@Override
	public boolean doRemove(String id) throws Exception {
		String sql = "DELETE FROM 实验室信息表 WHERE 实验室编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		
		//this.pstmt = this.conn.prepareStatement("{call dbo.syremove(?)}");
		this.pstmt.setString(1, id);
		
		if(this.pstmt.executeUpdate() > 0){
			return true;
		}
		return false;
	}
}