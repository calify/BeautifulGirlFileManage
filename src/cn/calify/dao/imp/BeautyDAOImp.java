package cn.calify.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.calify.beans.Beauty;
import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;
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
					beauty.setSupport(rs.getInt(8));
					beauty.setAgainst(rs.getInt(9));
					beauty.setVisitnumber(rs.getInt(10));
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
					beauty.setSupport(rs.getInt(8));
					beauty.setAgainst(rs.getInt(9));
					beauty.setVisitnumber(rs.getInt(10));
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
		sql = "UPDATE `beauty` SET `name`=?, `height`=?, `weight`=?, `age`=?, `area`=?, `instruction`=? WHERE `id`=?";
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
	
	//点赞
	public boolean doSupport(int support,int id){
		boolean result = false;
		sql = "UPDATE `beauty` SET `support`=? WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);				
				pstmt.setInt(1, support);
				pstmt.setInt(2, id);
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

	//恶心
	public boolean doAgainst(int against,int id){
		boolean result = false;
		sql = "UPDATE `beauty` SET `against`=? WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);				
				pstmt.setInt(1, against);
				pstmt.setInt(2, id);
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
	
	//访问量
	public boolean doVisit(int visit,int id){
		boolean result = false;
		sql = "UPDATE `beauty` SET `visitnumber`=? WHERE (`id`=?)";
		 try{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);				
				pstmt.setInt(1, visit);
				pstmt.setInt(2, id);
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
	
	//组合查询+分页
	public TemplateJson doQueryByBean(Object o,Page page){
		List<Beauty> list = new ArrayList<Beauty>();
		TemplateJson returnlist = new TemplateJson();
		returnlist.setResult("fail");
		Beauty beauty = (Beauty)o;
		String sql_count = "SELECT COUNT(*)";
		String sql_search = "SELECT *";
		String sql_excute = null;
		sql = " FROM beauty WHERE 1=1";
		int index = 0;
		int sum = 0;
		if(null != beauty){
			if(null != beauty.getName() && !"".equals(beauty.getName())){
				sql += " and name like ?";
				sum++;
			}
			if(-1 != beauty.getAge()){
				sql += " and age = ?";
				sum++;
			}
			if(null != beauty.getArea() && !"".equals(beauty.getArea())){
				sql += " and area like ?";
				sum++;
			}
		}
		
		//查询总记录数
		sql_excute = sql_count + sql;
		try{
			index = sum;
			pstmt = (PreparedStatement) conn.prepareStatement(sql_excute);
			if(null != beauty.getArea() && !"".equals(beauty.getArea())){
				pstmt.setString(index, "%" + beauty.getArea() + "%");
				index--;
			}
			if(-1 != beauty.getAge()){
				pstmt.setInt(index, beauty.getAge());
				index--;
			}
			if(null != beauty.getName() && !"".equals(beauty.getName())){
				pstmt.setString(index, "%" + beauty.getName() + "%");
				index--;
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				page.setRowcount(rs.getInt(1));
			}
			
			//计算总页数
			if(page.getRowcount() % page.getPagesize() == 0){
				page.setPagecount(page.getRowcount() / page.getPagesize());
			}
			else{
				page.setPagecount(page.getRowcount() / page.getPagesize() + 1);
			}
			
			//组合查询
			if(page.getRowcount() > 0){
				sql_excute = sql_search + sql + " limit ?,?";
				index = sum + 2;
				pstmt = (PreparedStatement) conn.prepareStatement(sql_excute);
				pstmt.setInt(index, page.getPagesize());
				index--;
				pstmt.setInt(index, ((page.getPagenow()-1)*page.getPagesize()));
				index--;
				if(null != beauty.getArea() && !"".equals(beauty.getArea())){
					pstmt.setString(index, "%" + beauty.getArea() + "%");
					index--;
				}
				if(-1 != beauty.getAge()){
					pstmt.setInt(index, beauty.getAge());
					index--;
				}
				if(null != beauty.getName() && !"".equals(beauty.getName())){
					pstmt.setString(index, "%" + beauty.getName() + "%");
					index--;
				}
				rs = pstmt.executeQuery();
				while(rs.next()){
					Beauty b = new Beauty();
					b.setId(rs.getInt(1));
					b.setName(rs.getString(2));
					b.setHeight(rs.getInt(3));
					b.setWeight(rs.getInt(4));
					b.setAge(rs.getInt(5));
					b.setArea(rs.getString(6));
					b.setInstruction(rs.getString(7));
					b.setSupport(rs.getInt(8));
					b.setAgainst(rs.getInt(9));
					b.setVisitnumber(rs.getInt(10));
					list.add(b);
				}
			}
			returnlist.setReturnlist(list);
			returnlist.setObj(page);
			returnlist.setResult("success");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.doClose();
		}
		return returnlist;
	}
}
