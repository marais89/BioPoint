package org.bio.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bio.model.LostMvt;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class LostMvtDaoImpl implements LostMvtDao,Serializable {

	@Autowired
	private SessionFactory sessionFactory;
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		@Override
		public void insertLostMvt(LostMvt LostMvt) {
			getSessionFactory().getCurrentSession().saveOrUpdate(LostMvt);

		}

		@Override
		public void updateLostMvt(LostMvt LostMvt) {
			getSessionFactory().getCurrentSession().saveOrUpdate(LostMvt);

		}

		@Override
		public List<LostMvt> findAllLostMvts() {
			/*List<Integer> list= new ArrayList<>();
			Integer [] tab = null;
			list.addAll(getSessionFactory().getCurrentSession().createQuery("select DISTINCT lm.id.bckId from LostMvt as lm ").list());
		if(list.size()>0){
		 tab=new Integer[list.size()];
		for(int i=0;i<list.size();i++)
		{
			tab[i] =list.get(i);
		}
		}*/
		return getSessionFactory().getCurrentSession().createQuery("from LostMvt as lm where lm.terminaux.idter in (select DISTINCT ml.terminaux.idter from LostMvt as ml group by ml.terminaux.idter)").list();

		}

		@Override
		public void deleteLostMvt(LostMvt LostMvt) {
			getSessionFactory().getCurrentSession().delete(LostMvt);

		}

		@Override
		public LostMvt getByid(int id) {
			
			return (LostMvt) getSessionFactory().getCurrentSession().get(LostMvt.class, id);
		}
		
		@Override
		public List<LostMvt> getByBckid(int bckid) {
			System.out.println("avant retrait de lostMvt");
			return getSessionFactory().getCurrentSession().createQuery("from LostMvt as lm where lm.id.bckId=:bckid").setParameter("bckid", bckid).list();
			
		}


}
