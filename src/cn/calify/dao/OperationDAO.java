package cn.calify.dao;

import java.util.List;

public interface OperationDAO {
	public List doQueryALL();
	public Object doQueryById(int id);
	public Object doQueryByName(String name);
	public boolean doAddByBean(Object o);
	public boolean doDelById(int id);
	public boolean doUpdata(Object o);
}
