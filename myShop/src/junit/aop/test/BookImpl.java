package junit.aop.test;

public class BookImpl implements BookService {

	public String addBook(String bookName) {
		System.out.println(bookName);
		return null;
	}
	
//	public void book(String bookname){
//		System.out.println("123"+bookname);
//	}

}
