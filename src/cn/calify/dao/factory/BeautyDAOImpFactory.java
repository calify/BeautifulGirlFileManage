package cn.calify.dao.factory;

import cn.calify.dao.imp.BeautyDAOImp;

public class BeautyDAOImpFactory {
	public static BeautyDAOImp generaterBeautyDAOImp(){
		return new BeautyDAOImp();
	}
}
