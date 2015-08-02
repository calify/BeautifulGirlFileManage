package cn.calify.services.factory;

import cn.calify.services.imp.UserServicesImp;

public class UserServicesImpFactory {
	public static UserServicesImp generaterUserServicesImp(){
		return new UserServicesImp();
	}
}
