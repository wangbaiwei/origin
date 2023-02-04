package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Driver driver = context.getBean("driver", Driver.class);
        Tank tank = context.getBean("tank", Tank.class);
        System.out.println(tank.driver);

    }
}
