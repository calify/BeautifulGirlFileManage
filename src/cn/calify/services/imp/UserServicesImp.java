package cn.calify.services.imp;

import java.util.List;

import cn.calify.beans.User;
import cn.calify.dao.factory.UserDAOImpFactory;
import cn.calify.dao.imp.UserDAOImp;
import cn.calify.services.OperationDAOServices;

public class UserServicesImp implements OperationDAOServices {
	private UserDAOImp userdaoimp = null;
	
	public UserServicesImp(){
		userdaoimp = UserDAOImpFactory.generaterUserDAOImp();
	}
	public boolean doAddByBean(Object o) {
		return userdaoimp.doAddByBean(o);
	}

	public boolean doDelById(int id) {
		return userdaoimp.doDelById(id);
	}

	public List doQueryALL() {
		return userdaoimp.doQueryALL();
	}

	public Object doQueryById(int id) {
		return userdaoimp.doQueryById(id);
	}

	public List doQueryByName(String name) {
		return userdaoimp.doQueryByName(name);
	}	
	
	public boolean doUpdata(Object o) {
		return userdaoimp.doUpdata(o);
	}
	public String doLogin(User user){
		return userdaoimp.doLogin(user);
	}

}
