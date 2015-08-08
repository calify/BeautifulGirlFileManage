package cn.calify.dao.factory;

import cn.calify.dao.imp.BpicDAOImp;

public class BpicDAOImpFactory {
	public static BpicDAOImp generaterBpidDAOImp(){
		return new BpicDAOImp();
	}
}
