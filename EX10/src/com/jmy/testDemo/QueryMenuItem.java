package com.jmy.testDemo;


import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmy.model.MenuItem;

public class QueryMenuItem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 try{
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 MenuItem menuItem=null; 
		 List<MenuItem> list=(List<MenuItem>) sess.createQuery("select n from MenuItem n ").list();
		 ts.commit();
	     sess.close();
		 System.out.println("这个是list"+list.size());
		if(list.size()>0)
		{
			/*JSONArray jsonarray=JSONArray.fromObject(list);
			   System.out.println(jsonarray.toString());
			   List<MenuItem> my=new ArrayList<MenuItem>();
			  my=JSONArray.toList(jsonarray,MenuItem.class);
			  System.out.println(my.get(0).getId()+"\n");
			 */
			
			Gson gson = new Gson();   
			String str = gson.toJson(list);
			System.out.println("这是Google.Gson的gson流"+str);
			
			Gson gson1 = new Gson();  
			List<MenuItem> lists = gson.fromJson(str, new TypeToken<List<MenuItem>>(){}.getType()); 
			for(int i=0;i<lists.size();i++)
			{
			System.out.println(lists.get(i).getBusiness_id()+"\n");
			}
			   
		   for(Iterator it=list.iterator();it.hasNext();)
		     {
			   menuItem=(MenuItem) it.next();
			    System.out.println("----------ID:"+menuItem.getBusiness_id()+"--店家:"+menuItem.getTitle()+"--价格:"+menuItem.getPrice()+"--评星:"+menuItem.getAveragestart()+"--------------------------");
		     }
		   
		   
		   
		}
		else
		{
			System.out.println("---------------数据采集为空-------------");
			
		}
		
		      
		 }   
			 catch(Exception e)
			 {
				 e.printStackTrace();
				 
				 
			 }
		 
	}
}


