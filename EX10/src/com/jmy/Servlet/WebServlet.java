package com.jmy.Servlet;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.jmy.iterface.Crud;
import com.jmy.model.MenuItem;
import com.jmy.model.PersonInfo;


public class WebServlet extends HttpServlet implements Serializable,Crud{

	private static final long serialVersionUID = 1L;
	private static final String content="success";

	public WebServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        doPost(req, resp);
	}



	public void doPost(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String state=req.getParameter("state");
		String name=req.getParameter("username");
		String pass=req.getParameter("userpass");
		String sex=req.getParameter("sex");
		String email=req.getParameter("email");
		String birthday=req.getParameter("birthday");
		String favorite=req.getParameter("favorite");
		String url=req.getParameter("url");
		String myposition=req.getParameter("position");
		
		/*System.out.println("state"+state+"  name "+name+"  pass "+pass+"   sex  "+sex+"  email  "+email+"birthday "+birthday+"favorite  "+favorite);*/
	    OutputStream out=resp.getOutputStream();
	    BufferedWriter wr=new BufferedWriter(new OutputStreamWriter(out));
	    switch (state) {
	    
	       /*****登录模块******/
		case "login":
			
			List<PersonInfo> loginlist=new ArrayList<PersonInfo>();
			loginlist=doQueryforLogin(name, pass);
        	Gson gson1 = new Gson();   
			String gsondata1 = gson1.toJson(loginlist);
			System.out.println("这是Google.Gson的gson流"+gsondata1);
			wr.write(gsondata1);
			wr.flush();
			wr.close();
			
			break;
			
			/*****注册模块******/
		case "reg":
			if(doInsertforReg(name, pass, Integer.parseInt(sex), birthday, email, favorite))
		    {
		    wr.write(content);
			System.out.println(content);
		    }
		    else
		    {
		    	wr.write("fail");
		    	System.out.println("fail");
		    }
		    wr.flush();
		    wr.close();
			break;
			
			/*****加载listview内容模块******/
		case "menulist":
			List<MenuItem> list;
			if((list=doQueryforMenuItem())!=null)
			{
				Gson gson = new Gson();   
				String gsonlist = gson.toJson(list);
				System.out.println("这是Google.Gson的gson流"+gsonlist);
				wr.write(gsonlist);
				wr.flush();
				wr.close();
			}
			break;
		case "downimg":
			 System.out.println("我进来了的");
			 String str=getImage(Integer.parseInt(myposition));
			 System.out.println("我是第:"+Integer.parseInt(myposition)+"个图片");
			 System.out.println("下载地址是:"+str);
			 InputStream in=new FileInputStream(str);
			 System.out.println("图片流大小:"+in.available());		 
			 byte[] buffer = new byte[1024*20];
			 int len = 0;
			 OutputStream outs=resp.getOutputStream();
			 while ((len = in.read(buffer)) != -1) {
			   
			    outs.write(buffer);
			 }
			 
			 
			 outs.flush();
			 outs.close();
			
			 
			
			break;
		
			
		default:
			break;
		}
	   
	    
	}



	public void init() throws ServletException {
		
		
	}


	@Override
	public List<PersonInfo> doQueryforLogin(String name, String password) {
		// TODO Auto-generated method stub
		Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 PersonInfo info=null;
		 List<PersonInfo> list=sess.createQuery("select n from PersonInfo n where n.name= :resultname").
				 setString("resultname",name).list();
		 System.out.println("这个是list"+list.size());
		 List<PersonInfo> mylist=new ArrayList<PersonInfo>();
		if(list.size()>0)
		{
		   for(Iterator it=list.iterator();it.hasNext();)
		     {
			    info=(PersonInfo) it.next();
			    PersonInfo myinfo=new PersonInfo();
			    myinfo.setName(info.getName());
			    myinfo.setPassword(info.getPassword()); 
			    myinfo.setBirthdate(info.getBirthdate());
			    myinfo.setEmail(info.getEmail());
			    myinfo.setFavorite(info.getFavorite());
			    System.out.println("密码是:"+myinfo.getPassword());
			    mylist.add(myinfo);
		     }
		    
		}
	     ts.commit();
	     sess.close();
	     sf.close();
	     
	    return mylist;
		
	}


	@Override
	public Boolean doInsertforReg(String name, String password, int sex,
			String birthdate, String email, String favorite) {
		// TODO Auto-generated method stub
		Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 try{
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 PersonInfo info=new PersonInfo();
		 info.setName(name);
		 info.setPassword(password);
		 info.setSex(sex);
		 info.setBirthdate(birthdate);
		 info.setEmail(email);
		 info.setFavorite(favorite);
		 sess.save(info);
	     ts.commit();
	     sess.close();
	     sf.close();
	     return true;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
			 
		 }
		
		
	}


	@Override
	public List<MenuItem> doQueryforMenuItem() {
		// TODO Auto-generated method stub
		Configuration conf=new Configuration().configure();
		 SessionFactory sf=conf.buildSessionFactory();
		 try{
		 Session sess=sf.openSession();
		 Transaction ts=sess.beginTransaction();
		 MenuItem menuItem=null; 
		 List list=sess.createQuery("select n from MenuItem n ").list();
		 
		 ts.commit();
	     sess.close();
	     sf.close();
		 System.out.println("这个集合的大小为"+list.size());
		if(list.size()>0)
		{  List<MenuItem> mylist=new ArrayList<MenuItem>();
		   for(Iterator it=list.iterator();it.hasNext();)
		     {
			   menuItem=(MenuItem) it.next();
			   MenuItem mymenuitem=new MenuItem();
			   mymenuitem.setAveragestart(menuItem.getAveragestart());
			   mymenuitem.setTitle(menuItem.getTitle());
			   mymenuitem.setPrice(menuItem.getPrice());
			   mymenuitem.setDownurl(menuItem.getDownurl());
			   mylist.add(mymenuitem);
			    System.out.println("------------店家:"+mymenuitem.getTitle()+"-------------");
			   
			    
			  
			    
			   
			  
		     }
		   return mylist;
		}
		else
		{
			System.out.println("---------------数据采集为空-------------");
			return null;
		}
		
		      
		 }   
			 catch(Exception e)
			 {
				 e.printStackTrace();
				 return null;
				 
			 }
		 
	}


	@Override
	public String getImage(int position) {
		// TODO Auto-generated method stub
		List<MenuItem> list=doQueryforMenuItem();
		String str=list.get(position).getDownurl();
		return str;
		
	}

}
