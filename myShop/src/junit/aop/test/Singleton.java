package junit.aop.test;

public class Singleton {
 private static Singleton singleton=new Singleton();
 private Singleton(){};
 public static Singleton getInstance(){
	 return singleton;
 }
 
 public  static synchronized Singleton getInstanceA(){
	 if(singleton==null){
		 return new Singleton();
	 }else{
		 return singleton;
	 }
 }
 
 
 private static class Sing{
	 public final static Singleton instance=new Singleton();
 }
 
 public static Singleton getInstanceB(){
	 return Sing.instance;
 }
}
