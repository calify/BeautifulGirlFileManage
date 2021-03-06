package cn.calify.services;

import java.util.List;

import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;

public interface OperationDAOServices {
	public List doQueryALL();
	public Object doQueryById(int id);
	public TemplateJson doQueryByBean(Object o,Page page);//组合查询
	public boolean doAddByBean(Object o);
	public boolean doDelById(int id);
	public boolean doUpdata(Object o);
}
