package lv.lu.mpt.pd2.impl.service;

import lv.lu.mpt.pd2.interfaces.CommonDAO;

public class BaseService {

	private CommonDAO commonDAO;

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}


}
