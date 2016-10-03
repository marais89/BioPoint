package org.bio.service;

import java.util.Date;
import java.util.List;

import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;

public interface SequenceService {
	public void insertSequence(Sequence sequence);
	public void updateSequence(Sequence sequence);
	public List<Sequence> findAllSequences();
	public void deleteSequence(Sequence sequence);
	public Sequence getByid(int  id);
	public Sequence getCurrentSequence(Personnel p);
	public SequenceDetail getjourDetail(Sequence s,int jour);
	public List<Sequence> findAllDaySequence();
	public Sequence findCurrentDaySequence(Personnel p);
	public List<SequenceDetail>findAllDayDetail(Sequence s);
	public Sequence getcurrentSequencebyDay(Date d,Personnel p);
	public List<Sequence> findSequence(String s);
	public Sequence findCurrentSequenceByDate(Date d, Personnel p);
}
