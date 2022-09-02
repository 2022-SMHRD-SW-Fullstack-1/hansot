package gittest;

public class Test {

	public static void main(String[] args) {

		System.out.println("First Commit!");
		System.out.println("Second Commit!");
		try {
			for (int i = 0; i < 2; i++) {
				Thread.sleep(2000); // 1000 단위당 1초를 의미
				System.out.println("Sleep "+i);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
