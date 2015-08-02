package cn.calify.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.calify.beans.Beauty;
import cn.calify.beans.User;
import cn.calify.dao.OperationDAO;
import cn.calify.dao.db.DBConnection;

import com.mysql.jdbc.PreparedStatement;

public class UserDAOImp implements OperationDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;

	//通过构造方法取得数据库的连接
	public UserDAOImp(){
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
	
	//通过接收Bean对象来添加用户
	public boolean doAddByBean(Object o) {
		boolean result = false;
		User user = (User) o;
		sql = "INSERT INTO `user` (`username`, `password`, `role`) VALUES (?,?,?)";
		try{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRole());
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
	
	//通过接收指定id来删除用户
	public boolean doDelById(int id) {
		boolean result = false;
		sql = "DELETE FROM `user` WHERE (`id`=?)";
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
	
	//查找所有用户
	public List<User> doQueryALL() {
		 List<User> list = new ArrayList<User>();
		 sql = "SELECT * FROM `user`";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					User user = new User();
					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setRole(rs.getString(4));
					list.add(user);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return list;
	}

	//通过接收指定id来查找用户
	public User doQueryById(int id) {
		User user = new User();
		sql = "SELECT * FROM `user` WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setRole(rs.getString(4));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return user;
	}
	
	//模糊查找
	public List doQueryByName(String name) {
		List<User> list = new ArrayList<User>();
		sql = "SELECT * FROM `user` WHERE (`username` like ?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1,"%" + name + "%");
				rs = pstmt.executeQuery();
				while(rs.next()){
					User user = new User();
					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setRole(rs.getString(4));
					list.add(user);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.doClose();
			}
		return list;
	}
	
	//通过id修改用户信息
	public boolean doUpdata(Object o){
		User user =(User) o;
		boolean result = false;
		sql = "UPDATE `user` SET `username`=?, `password`=?, `role`=? WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getRole());
				pstmt.setInt(4, user.getId());
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
	
	//新增方法：校验登录信息
	public String doLogin(User user) {
		String role = null;
		sql = "SELECT * FROM `user` WHERE (`username`=? and `password`=?)";
		try{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				role = rs.getString(4);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.doClose();
		}
		return role;
	}
	
}
