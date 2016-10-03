package org.bio.dao;

import java.util.List;

import org.bio.model.Calendrier;

public interface CalendrierDao {
	public void insertCalendrier(Calendrier calendrier);
	public void updateCalendrier(Calendrier calendrier);
	public List<Calendrier> findAllCalendriers();
	public void deleteCalendrier(Calendrier calendrier);
	public Calendrier getByid(int  id);
}
