package cn.calify.services.imp;

import java.util.List;

import cn.calify.dao.factory.BeautyDAOImpFactory;
import cn.calify.dao.imp.BeautyDAOImp;
import cn.calify.services.OperationDAOServices;

public class BeautyServicesImp implements OperationDAOServices {
	private BeautyDAOImp beautydaoimp = null;
	
	public BeautyServicesImp(){
		beautydaoimp = BeautyDAOImpFactory.generaterBeautyDAOImp();
	}
	public boolean doAddByBean(Object o) {
		return beautydaoimp.doAddByBean(o);
	}

	public boolean doDelById(int id) {
		return beautydaoimp.doDelById(id);
	}

	public List doQueryALL() {
		return beautydaoimp.doQueryALL();
	}

	public Object doQueryById(int id) {
		return beautydaoimp.doQueryById(id);
	}

	public List doQueryByName(String name) {
		return beautydaoimp.doQueryByName(name);
	}	
	
	public boolean doUpdata(Object o) {
		return beautydaoimp.doUpdata(o);
	}

}
