package com.jmy.testDemo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jmy.model.PersonInfo;

public class Qurey {
	public static void main(String arg[])
	{
		 Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 List list=sess.createQuery("select n from PersonInfo n where n.name= :resultname").
				 setString("resultname","baba").list();
		 PersonInfo info=null;
		 for(Iterator it=list.iterator();it.hasNext();)
		 {
			 info=(PersonInfo) it.next();
			 System.out.println("√‹¬Î «:"+info.getPassword());
		 }
	     ts.commit();
	     sess.close();
	
	}


}
