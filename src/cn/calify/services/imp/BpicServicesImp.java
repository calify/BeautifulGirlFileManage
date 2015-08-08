package cn.calify.services.imp;

import java.util.List;

import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;
import cn.calify.dao.factory.BpicDAOImpFactory;
import cn.calify.dao.imp.BpicDAOImp;
import cn.calify.services.OperationDAOServices;

public class BpicServicesImp implements OperationDAOServices {
	private BpicDAOImp bpicdaoimp = null;
	
	public BpicServicesImp(){
		bpicdaoimp = BpicDAOImpFactory.generaterBpidDAOImp();
	}
	
	public boolean doAddByBean(Object o) {
		return bpicdaoimp.doAddByBean(o);
	}

	public boolean doDelById(int id) {
		return bpicdaoimp.doDelById(id);
	}

	public List doQueryALL() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object doQueryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List doQueryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean doUpdata(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public List doQueryBybid(int id){
		return bpicdaoimp.doQueryBybid(id);
	}
	public TemplateJson doQueryByBean(Object o,Page page){
		return bpicdaoimp.doQueryByBean(o, page);
	}
}
