package junit.aop.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
	public static void main(String[] a){
	 BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService book = (BookService) factory.getBean("bookService");
		book.addBook("三国");

 }
}
