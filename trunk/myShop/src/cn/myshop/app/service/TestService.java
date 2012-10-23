package cn.myshop.app.service;


public interface TestService {
	public String getOrderId();
	public int updateCart();
	public int delCart();
	public int[] delAndUpdate() throws RuntimeException;
	public int insertCart() ;
}
