package Evan;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SQLiteJDBCTest {

    @Test
    public void Train0In2Minutes() {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        JavaData jd = (JavaData) context.getBean("javaData");

        String trainMessage = jd.Test(0, new Date(9*60*60*1000+42*60*1000 - 24*60*60*1000));

        assertEquals("\nThe next train is arriving in 2 minutes.", trainMessage);
    }

    @Test
    public void Train22In1Minute() {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        JavaData jd = (JavaData) context.getBean("javaData");

        String trainMessage = jd.Test(0, new Date(15*60*60*1000+9*60*1000 - 24*60*60*1000));

        assertEquals("\nThe next train is arriving in 1 minute.", trainMessage);
    }

    @Test
    public void Midnight() {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        JavaData jd = (JavaData) context.getBean("javaData");

        String trainMessage = jd.Test(0, new Date(-14*60*60*1000));

        assertEquals("\nThe next train is arriving in 4 minutes.", trainMessage);
    }

}
