package lv.lu.mpt.pd2.interfaces.service;

import java.util.List;

import lv.lu.mpt.pd2.model.Referee;

public interface RefereeService {
	
	Referee getReferee(Referee referee);
	
	List<Referee> getAllReferees();

}
