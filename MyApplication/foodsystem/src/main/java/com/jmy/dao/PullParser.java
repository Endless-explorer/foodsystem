package com.jmy.dao;

import com.jmy.model.Division;
import com.jmy.model.Location;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnseg on 2017/5/13.
 */

public class PullParser {
        private List<Division> cityList=null;
        private Division mDivsion=null;
        private Location mlocation=null;



    public List<Division> parserXml(InputStream is){
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(is,"utf-8");
            int event = xpp.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_DOCUMENT:
                        cityList = new ArrayList<Division>();
                        break;
                    case XmlPullParser.START_TAG:
                        if("id".equals(xpp.getName())){
                            mDivsion = new Division();
                            String id = xpp.nextText();
                            mDivsion.setId(id);
                        }
                        if("name".equals(xpp.getName())){
                            String name= xpp.nextText();
                            mDivsion.setName(name);
                        }
                        if("location".equals(xpp.getName())){
                            mlocation = new Location();
                        }
                        if("timezone".equals(xpp.getName())){
                            String setTimezone= xpp.nextText();
                            mlocation.setTimezone(setTimezone);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("division".equals(xpp.getName())){
                            cityList.add(mDivsion);
                            mDivsion = null;
                            break;
                        }
                        if("location".equals(xpp.getName())){
                            mDivsion.setLocation(mlocation);
                            mlocation = null;
                        }
                }
                event = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  cityList;
    }

      /* public List<Division> parser(InputStream in) throws Exception {

            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlparser=factory.newPullParser();
            Log.d("all1","getin");
            xmlparser.setInput(in,"UTF-8");
            int event=xmlparser.getEventType();
            Log.d("all1","getin1");
            while (event!= XmlPullParser.END_DOCUMENT)
            {

                switch(event) //v为view
                {
                   case XmlPullParser.START_DOCUMENT:
                       list=new ArrayList<Division>();

                   break;
                   case XmlPullParser.START_TAG:
                       String nodename=xmlparser.getName();
                       division=new Division();
                       if("id".equals(nodename))
                       {
                           String id=xmlparser.nextText();
                           //Log.d("all1", "id="+id);
                           division.setId(id);
                       }
                       if("name".equals(nodename))
                       {
                           division.setName(xmlparser.nextText());
                       }
                       if("location".equals(nodename))
                       {
                           location=new Location();

                       }
                       if("timezone".equals(nodename))
                       {
                           location.setTimezone(xmlparser.nextText());
                       }
                       if("timezone_offset_gmt".equals(nodename))
                       {
                           long timezone_offset_gmt=Long.valueOf(xmlparser.nextText());
                           location.setTimezone_offset_gmt(timezone_offset_gmt);
                       }
                       if("latitude".equals(nodename))
                       {   float latitude=Float.valueOf(xmlparser.nextText());
                           location.setLatitude(latitude);

                       }
                       if("longitude".equals(nodename))
                       {
                           float longitude=Float.valueOf(xmlparser.nextText());
                           location.setLongitude(longitude);

                       }

                        break;
                   case XmlPullParser.END_TAG:

                       if("division".equals(xmlparser.getName())){
                           list.add(division);
                           division = null;
                           break;
                       }
                       if("location".equals(xmlparser.getName())){
                           division.setLocation(location);
                           location = null;
                       }

                }
                event=xmlparser.next();
            }

            return  list;
        }*/


}
