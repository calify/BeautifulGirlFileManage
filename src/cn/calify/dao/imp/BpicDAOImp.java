package cn.calify.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.calify.beans.Bpic;
import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;
import cn.calify.dao.OperationDAO;
import cn.calify.dao.db.DBConnection;

import com.mysql.jdbc.PreparedStatement;

public class BpicDAOImp implements OperationDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;

	//通过构造方法取得数据库的连接
	public BpicDAOImp(){
		if(conn == null){
			conn = new DBConnection().getConnection();
		}
	}
	
	//关闭资源
	public void doClose(){
		try{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(pstmt != null){
				pstmt.close();
				pstmt = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}catch(Exception e){
			e.printStackTrace();
			}
	}

	public boolean doAddByBean(Object o) {
		boolean result = false;
		Bpic bpic = (Bpic) o;
		sql = "INSERT INTO `bpic` (`name`,`bid`, `path`, `description`) VALUES (?,?,?,?)";
		try{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, bpic.getName());
			pstmt.setInt(2, bpic.getBid());
			pstmt.setString(3, bpic.getPath());
			pstmt.setString(4, bpic.getDescription());
			if(pstmt.executeUpdate() > 0){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.doClose();
		}
		return result;
	}

	public boolean doDelById(int id) {
		boolean result = false;
		sql = "DELETE FROM `bpic` WHERE `id`=?";
		try{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			if(pstmt.executeUpdate() > 0){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.doClose();
		}
		return result;
	}

	//新增方法，通过美女id查找图片
	public List doQueryBybid(int id) {
		List<Bpic> list = new ArrayList<Bpic>();
		 sql = "SELECT * FROM `bpic` where bid=?";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Bpic bpic = new Bpic();
					bpic.setId(rs.getInt(1));
					bpic.setName(rs.getString(2));
					bpic.setBid(rs.getInt(3));
					bpic.setPath(rs.getString(4));
					bpic.setDescription(rs.getString(5));
					list.add(bpic);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return list;
	}
	
	//组合查询+分页
	public TemplateJson doQueryByBean(Object o,Page page){
		TemplateJson list =  new TemplateJson();
		Bpic bpic = (Bpic) o;
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			}finally{
			this.doClose();
			}
		return list;
	}
	public List doQueryALL() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doQueryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean doUpdata(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
