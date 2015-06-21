package Evan;

import Evan.models.Stop;
import Evan.models.StopTime;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class SQLiteJDBC
{
    private List<Stop> stops;
    @Autowired
    private SessionFactory sessionFactoryBean;

    public int listStops()
    {

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Stop.class);
        criteria.add(Restrictions.like("stopName", "%METROLINK STATION%"));
        stops = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();

        int count = 0;

        for (Stop stop : stops) {
            System.out.println(String.format("Stop #%s: %s", Integer.toString(count), stop.getStopName()));
            count++;
        }

        System.out.println("");

        return count;
    }

    public String nextTrainAt(int stop, Date present) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-6:00"));

        long millInDay = 24 * 60 * 60 * 1000;

        Date now = new Date((present.getTime() % millInDay) + 3600000 + millInDay);

        Date firstOfDay = new Date(now.getTime() + millInDay);
        Date nextStop = null;

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(StopTime.class);
        criteria.add(Restrictions.eq("id", stops.get(stop).getId()));
        List<StopTime> times = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();

        for (StopTime time : times) {

            try {
                Date t = sdf.parse(time.getArrivalTime());
                if (t.before(firstOfDay)) firstOfDay = t;
                if (t.after(now)) {
                    if (nextStop == null || t.before(nextStop)) nextStop = t;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        if (nextStop == null) nextStop = firstOfDay;

        String minutes;

        if ((nextStop.getTime() - now.getTime())/60000 == 1) minutes = " minute.";
        else minutes = " minutes.";

        return "\nThe next train is arriving in " + String.valueOf((nextStop.getTime() - now.getTime())/60000) + minutes;

    }

}