package org.bio.dao;

import java.util.List;

import org.bio.model.LostMvt;

public interface LostMvtDao {
	public void insertLostMvt(LostMvt LostMvt);
	public void updateLostMvt(LostMvt LostMvt);
	public List<LostMvt> findAllLostMvts();
	public void deleteLostMvt(LostMvt LostMvt);
	public LostMvt getByid(int  id);
	public List<LostMvt> getByBckid(int bckid);
}
