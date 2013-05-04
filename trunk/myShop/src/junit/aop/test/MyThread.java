package junit.aop.test;

import java.util.Hashtable;
import java.util.Map;

public class MyThread implements Runnable {
	public   static int c=0;
	public static void main(String args[]) {
	MyThread mt = new MyThread();
	MyThread mt1 = new MyThread();
	Thread t1 = new Thread(mt, "tA1 ");
	Thread t2 = new Thread(mt1, "tB1 ");
	Thread t3 = new Thread(mt, "tA2 ");
	Thread t4 = new Thread(mt1, "tB2 ");
	Thread t5 = new Thread(mt, "tA3 ");
	Thread t6 = new Thread(mt1, "tB3 ");
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
	t6.start();
	}
	public void run() {
	//synchronized (this) {
//	System.out.println(Thread.currentThread().getName());
	this.test(Thread.currentThread().getName()+"-调用时间："+System.currentTimeMillis());
	//this.staticTest(Thread.currentThread().getName());
   this.testa(Thread.currentThread().getName());
	//}
	}
	
	public synchronized void testa(String n){
		System.out.println("----"+n+"----方法[ 开始 ]时间: "+System.currentTimeMillis());
		String b="";
		for (int a=0;a<10000;a++){
			b+=a;
		}
		System.out.println("----"+n+"----方法[ 结束 ]时间: "+System.currentTimeMillis());
	}
	
	public synchronized static void staticTest(String n){
		System.out.println("----"+n+"----静态方法[ 开始 ]时间: "+System.currentTimeMillis());
		String b="";
		for (int a=0;a<10000;a++){
			b+=a;
		}
		System.out.println("----"+n+"----静态方法[ 结束 ]时间: "+System.currentTimeMillis());
	}
	
	public synchronized  void test(String n){
		Map ab=new Hashtable();
		System.out.println("调用"+n+"  test方法开始[ 开始 ]时间: "+System.currentTimeMillis());
		String b="";
		for (int a=0;a<10000;a++){
			b+=a;
		}
		// synchronized(n){
			//try {
			//	Thread.sleep(2000);
			//} catch (InterruptedException e) {
			//	e.printStackTrace();
			//}
		System.out.println("调用"+n+"  test方法结束[ 结束 ]时间: "+System.currentTimeMillis());
		//}
	} 	
	}	
