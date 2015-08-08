package cn.calify.services.imp;

import java.util.List;

import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;
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
	
	public boolean doUpdata(Object o) {
		return beautydaoimp.doUpdata(o);
	}
	public boolean doSupport(int support,int id){
		return beautydaoimp.doSupport(support, id);
	}
	public boolean doAgainst(int against, int id){
		return beautydaoimp.doAgainst(against, id);
	}
	public boolean doVisit(int visit, int id){
		return beautydaoimp.doVisit(visit, id);
	}
	public TemplateJson doQueryByBean(Object o,Page page){
		return beautydaoimp.doQueryByBean(o, page);
	}
}
