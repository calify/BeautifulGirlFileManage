package cn.calify.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.calify.beans.Beauty;
import cn.calify.dao.OperationDAO;
import cn.calify.dao.db.DBConnection;

import com.mysql.jdbc.PreparedStatement;

public class BeautyDAOImp implements OperationDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;

	//通过构造方法取得数据库的连接
	public BeautyDAOImp(){
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
	
	//通过接收Bean对象来添加美女
	public boolean doAddByBean(Object o) {
		boolean result = false;
		Beauty beauty = (Beauty) o;
		sql = "INSERT INTO `beauty` (`name`, `height`, `weight`, `age`, `area`, `instruction`) VALUES (?,?,?,?,?,?)";
		try{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, beauty.getName());
			pstmt.setInt(2, beauty.getHeight());
			pstmt.setInt(3, beauty.getWeight());
			pstmt.setInt(4, beauty.getAge());
			pstmt.setString(5, beauty.getArea());
			pstmt.setString(6, beauty.getInstruction());
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
	
	//通过接收指定id来删除美女
	public boolean doDelById(int id) {
		boolean result = false;
		sql = "DELETE FROM `beauty` WHERE (`id`=?)";
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
	
	//查找所有美女
	public List<Beauty> doQueryALL() {
		 List<Beauty> list = new ArrayList<Beauty>();
		 sql = "SELECT * FROM `beauty`";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Beauty beauty = new Beauty();
					beauty.setId(rs.getInt(1));
					beauty.setName(rs.getString(2));
					beauty.setHeight(rs.getInt(3));
					beauty.setWeight(rs.getInt(4));
					beauty.setAge(rs.getInt(5));
					beauty.setArea(rs.getString(6));
					beauty.setInstruction(rs.getString(7));
					list.add(beauty);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return list;
	}

	//通过接收指定id来查找美女
	public Beauty doQueryById(int id) {
		Beauty beauty = new Beauty();
		sql = "SELECT * FROM `beauty` WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					beauty.setId(rs.getInt(1));
					beauty.setName(rs.getString(2));
					beauty.setHeight(rs.getInt(3));
					beauty.setWeight(rs.getInt(4));
					beauty.setAge(rs.getInt(5));
					beauty.setArea(rs.getString(6));
					beauty.setInstruction(rs.getString(7));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return beauty;
	}
	
	//通过接收指定名字来查找美女
	public Object doQueryByName(String name){
		Beauty beauty = new Beauty();
		sql = "SELECT * FROM `beauty` WHERE (`name` like ?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();
				while(rs.next()){
					beauty.setId(rs.getInt(1));
					beauty.setName(rs.getString(2));
					beauty.setHeight(rs.getInt(3));
					beauty.setWeight(rs.getInt(4));
					beauty.setAge(rs.getInt(5));
					beauty.setArea(rs.getString(6));
					beauty.setInstruction(rs.getString(7));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return beauty;
	}
	
	//通过id修改用户信息
	public boolean doUpdata(Object o){
		Beauty beauty =(Beauty) o;
		boolean result = false;
		sql = "UPDATE `beauty` SET `name`=?, `height`=?, `weight`=?, `age`=?, `area`=?, `instruction`=? WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, beauty.getName());
				pstmt.setInt(2, beauty.getHeight());
				pstmt.setInt(3, beauty.getWeight());
				pstmt.setInt(4, beauty.getAge());
				pstmt.setString(5, beauty.getArea());
				pstmt.setString(6, beauty.getInstruction());
				pstmt.setInt(7, beauty.getId());
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
}
