package junit.aop.test;

public class Sorting {
	private int num[] = { 6, 3, 7, 1, 5, 0 };
	private int a[] = { 6, 1, 2, 4, 7, 0 };

	public int[] testA() {
		for (int i = 1; i < 6; i++) {
			for (int a = 0; a < 5; a++) {
				if (num[a] > num[a + 1]) {
					int temp;
					temp = num[a];
					num[a] = num[a + 1];
					num[a + 1] = temp;
				}
			}
			System.out.println("------------");
			for (int ab : this.num) {
				System.out.println(ab);
			}
		}
		return null;
	}

	public void print(int[] result) {
		for (int ab : this.num) {
			System.out.print(ab);
		}
	}

	//选择排序
	public void printA() {
		int n = 6;
		int min_index;
		for (int i = 0; i < n - 1; i++) {
			min_index = i;
			for (int j = i + 1; j < n; j++) {
				// 每次扫描选择最小项
				if (num[j] < num[min_index]) {
					min_index = j;
				}
			}

			if (min_index != i)// 找到最小项交换，即将这一项移到列表中的正确位置
			{
				int temp;
				temp = num[i];
				num[i] = num[min_index];
				num[min_index] = temp;
			}
		}
		Problems reported while synchronizing SVNStatusSubscriber. 0 of 1 resources were synchronized.
		  An error occurred synchronizing /myShop: Error getting status for resource P/myShop org.tigris.subversion.javahl.ClientException: RA layer request failed
		svn: OPTIONS of 'https://my-jshop.googlecode.com/svn/trunk/myShop': could not connect to server (https://my-jshop.googlecode.com)

		    Error getting status for resource P/myShop org.tigris.subversion.javahl.ClientException: RA layer request failed
		svn: OPTIONS of 'https://my-jshop.googlecode.com/svn/trunk/myShop': could not connect to server (https://my-jshop.googlecode.com)

		      org.tigris.subversion.javahl.ClientException: RA layer request failed
		svn: OPTIONS of 'https://my-jshop.googlecode.com/svn/trunk/myShop': could not connect to server (https://my-jshop.googlecode.com)

		      org.tigris.subversion.javahl.ClientException: RA layer request failed
		svn: OPTIONS of 'https://my-jshop.googlecode.com/svn/trunk/myShop': could not connect to server (https://my-jshop.googlecode.com)


	}

	//插入排序  6, 3, 7, 1, 5, 0
	public void InsertSortnumay() {
		int n = 6;
		for (int i = 1; i < n; i++)// 循环从第二个数组元素开始，因为num[0]作为最初已排序部分
		{
			int temp = num[i];// temp标记为未排序第一个元素
			int j = i - 1;
			while (j >= 0 && num[j] > temp)/* 将temp与已排序元素从小到大比较，寻找temp应插入的位置 */
			{
				num[j + 1] = num[j];
				j--;
			}
			num[j + 1] = temp;
		}
	}

	public static void main(String[] ar) {
		Sorting s = new Sorting();
		// s.print(s.testA());
		//s.printA();
		s.InsertSortnumay();
		s.print(null);

	}
}
