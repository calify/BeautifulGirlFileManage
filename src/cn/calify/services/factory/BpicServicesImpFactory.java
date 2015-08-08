package cn.calify.services.factory;

import cn.calify.services.imp.BpicServicesImp;

public class BpicServicesImpFactory {
	public static BpicServicesImp generaterBpicServicesImp(){
		return new BpicServicesImp();
	}
}
