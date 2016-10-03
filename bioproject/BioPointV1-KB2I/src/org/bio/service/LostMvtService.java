package org.bio.service;

import java.util.List;

import org.bio.model.LostMvt;

public interface LostMvtService {
	public void insertLostMvt(LostMvt LostMvt);
	public void updateLostMvt(LostMvt LostMvt);
	public List<LostMvt> findAllLostMvts();
	public void deleteLostMvt(LostMvt LostMvt);
	public LostMvt getByid(int  id);
	public List<LostMvt> getByBckid(int bckid);
}
