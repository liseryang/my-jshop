package junit.aop.test;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect	
public class BookBeforeAdvice {
	
	@Before(value="execution(* junit.aop.test.BookService.addBook(..))")
	public void before(String a) {
//        String arg=String.valueOf(arg1[0]);
        System.out.println("通知"+a);
	}

}
