package com.skynet.standard;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skynet.standard.model.User;
import com.skynet.standard.service.IBaseService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        IBaseService service = (IBaseService) ac.getBean("baseService");
        User user = new User();
        user.setName("ethan ling");
        service.save(user);
    }

}
