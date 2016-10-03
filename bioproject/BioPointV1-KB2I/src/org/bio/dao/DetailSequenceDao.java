package org.bio.dao;

import java.util.List;

import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;;

public interface DetailSequenceDao {
	public void insertDetailSequence(SequenceDetail detailSequence);
	public void updateDetailSequence(SequenceDetail detailSequence);
	public List<SequenceDetail> findAllDetailSequences();
	public void deleteDetailSequence(SequenceDetail detailSequence);
	public SequenceDetail getByid(int  id);
	public List<SequenceDetail> findByIdseq(Sequence ids);
}
