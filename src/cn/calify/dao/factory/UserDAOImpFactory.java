package cn.calify.dao.factory;

import cn.calify.dao.imp.UserDAOImp;

public class UserDAOImpFactory {
	public static UserDAOImp generaterUserDAOImp(){
		return new UserDAOImp();
	}

}
