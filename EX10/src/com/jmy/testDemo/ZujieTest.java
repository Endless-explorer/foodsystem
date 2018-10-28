package com.jmy.testDemo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;



public class ZujieTest {
    @Test
	public void testDemo()
	{
    	Configuration configuration=new Configuration().configure();
		ServiceRegistry Registry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    SessionFactory sessionFactory=configuration.buildSessionFactory(Registry);
	    SchemaExport export=new SchemaExport(configuration);
	    export.create(true, true);
	    
	}

}
