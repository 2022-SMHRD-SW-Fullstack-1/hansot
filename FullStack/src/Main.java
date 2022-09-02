import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StoreController stcn = new StoreController();

		int menu = 0;
		int grade = 0;

		while (true) {
			System.out.print("[1]음식점보기 [2]미용실보기 [3]상세보기 [4]평점보기 [5]종료 >> ");
			menu = sc.nextInt();
			if (menu == 1) {
				stcn.restshow();
			}

			else if (menu == 2) {
				stcn.salonshow();

			} else if (menu == 3) {
				stcn.detailshow();
			}

			else if (menu == 4) {
				stcn.totalshow();

			} else if (menu == 5) {
				break;
			} else {
				System.out.println("정확한 숫자를 입력해주세요");
			}
		}

	}

}
