package com.jmy.testDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jmy.model.MenuItem;
import com.jmy.model.PersonInfo;
import com.jmy.model.StarRating;


public class Insert {
	public static void main(String arg[]) throws Exception
	{
		 Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		/* PersonInfo info=new PersonInfo("XXX", "XXDD", "DDDD","DDDSD", 1, "sdsdddsdads");
		 StarRating sRating=new StarRating("DDD", 5);
	     sess.save(info);
	     sess.save(sRating);*/
		/* InputStream in=new FileInputStream("C:\\Users\\johnseg\\Desktop\\ScreenShot_20170529164650.png");
		 LobHelper lobHelper=sess.getLobHelper();
		 Blob img=lobHelper.createBlob(in, in.available());*/
		 for(int i=1;i<38;i++)
		 {
			 Random rand = new Random();
			 int randNum = rand.nextInt(5)+1;
			 int radnum=rand.nextInt(88)+15;
		 String img="C:\\Users\\johnseg\\Desktop\\nnn\\a"+" "+"("+i+").jpg";
		 MenuItem memu=new MenuItem( img, "hahah"+i,randNum, radnum);
		 sess.save(memu);
		 }
		 ts.commit();
	     sess.close();
	
	}

}
