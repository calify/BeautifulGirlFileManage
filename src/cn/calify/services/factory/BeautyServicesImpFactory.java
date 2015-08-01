package cn.calify.services.factory;

import cn.calify.services.imp.BeautyServicesImp;

public class BeautyServicesImpFactory {
	public static BeautyServicesImp generaterBeautyServicesImp(){
		return new BeautyServicesImp();
	}
}
