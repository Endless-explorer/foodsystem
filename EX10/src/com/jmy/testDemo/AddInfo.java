package com.jmy.testDemo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import com.jmy.model.PersonInfo;
import com.jmy.model.StarRating;

public class AddInfo {
	
	
	@Test
	public void InsertTest()
	{
		Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 StarRating starRating1=new StarRating( "¼¦¹«ìÒ1", 5);
		 StarRating starRating2=new StarRating( "¼¦¹«ìÒ2", 5);
		 StarRating starRating3=new StarRating( "¼¦¹«ìÒ3", 5);
		 StarRating starRating4=new StarRating( "¼¦¹«ìÒ4", 5);
		 StarRating starRating5=new StarRating( "¼¦¹«ìÒ5", 5);
		 Set<StarRating> set1=new HashSet<StarRating>();
		 set1.add(starRating1);
		 set1.add(starRating2);
		 set1.add(starRating3);
		 set1.add(starRating4);
		 set1.add(starRating5);
		 PersonInfo personInfo=new PersonInfo("½­Ã÷Òã","1234555" ,"wangyi533988@163.com", "19960623", 1,"×ãÇò£¬ÀºÇò£¬¸ß¶û·ò");
		 personInfo.setStarrating(set1);
		 sess.save(starRating1);
		 sess.save(starRating2);
		 sess.save(starRating3);
		 sess.save(starRating4);
		 sess.save(starRating5);
		 sess.save(personInfo);
		 
		 ts.commit();
		 
	}

}
