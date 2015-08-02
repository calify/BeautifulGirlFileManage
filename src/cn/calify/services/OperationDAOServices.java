package cn.calify.services;

import java.util.List;

public interface OperationDAOServices {
	public List doQueryALL();
	public Object doQueryById(int id);
	public List doQueryByName(String name);
	public boolean doAddByBean(Object o);
	public boolean doDelById(int id);
	public boolean doUpdata(Object o);
}
