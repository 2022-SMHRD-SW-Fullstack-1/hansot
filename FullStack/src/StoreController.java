import java.util.ArrayList;
import java.util.Scanner;

public class StoreController {

	ArrayList<Store> restaurantlist = new ArrayList<>();
	ArrayList<Store> salonlist = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	public StoreController() {
		restaurantlist.add(new Restaurant("한솥", "도시락", 80.0, 90.0, 75.0, 95.0));
		restaurantlist.add(new Restaurant("서브웨이", "샌드위치", 85.0, 95.0, 80.0, 90.0));
		salonlist.add(new Salon("유숙", "펌전문", 80.0, 100.0, 85.0));
		salonlist.add(new Salon("단두대", "커트전문", 90.0, 95.0, 95.0));
	}

	public void restshow() {
		System.out.println("====음식점 보기====");
		for (int i = 0; i < restaurantlist.size(); i++) {
			System.out.print(i + 1 + ". " + restaurantlist.get(i).name + " ");
			System.out.print("평점: " + restaurantlist.get(i).grade());
			System.out.println();
		}
	}

	public void salonshow() {
		System.out.println("====미용실 보기====");
		for (int i = 0; i < salonlist.size(); i++) {
			System.out.print(i + 1 + ". " + salonlist.get(i).name + " ");
			System.out.print("평점: " + salonlist.get(i).grade());
			System.out.println();
		}
	}

	public void detailshow() {
		int index = 0;
		System.out.print("가게명 입력 : ");
		String name = sc.next();
		for (int i = 0; i < restaurantlist.size(); i++) {
			if (name.equals(restaurantlist.get(i).name)) {
				index = i;
			}
			System.out.println("가게명 : " + restaurantlist.get(index).name);
		}
		
			System.out.println(
					"10 \t" + "20 \t" + "30 \t" + "40 \t" + "50 \t" + "60 \t" + "70 \t" + "80 \t" + "90 \t" + "100 \t");
			for (int q = 1;q<=10;q++) {
				if((restaurantlist.get(index).grade()/(100-(q*10)))>0) {
					System.out.print("*"+"\t");
				}
				else {
					System.out.println(" "+"\t");
				}
			}
			System.out.println();
		}
	

	public void totalshow() {

		int a항목 = 30;
		int b항목 = 85;
		int c항목 = 90;

		for (int i = 10; i >= 1; i--) {
			String a별점 = "";
			String b별점 = "";
			String c별점 = "";
			for (int a = 0; a < restaurantlist.size(); a++)

				if (restaurantlist.get(i).grade() / (i * 10) > 0) {
					a별점 = "*";
				}
			if (b항목 / (i * 10) > 0) {
				b별점 = "*";
			}
			if (c항목 / (i * 10) > 0) {
				c별점 = "*";
			}

			System.out.print(i * 10 + "\t" + a별점 + "\t" + b별점 + "\t" + c별점);
			System.out.println();

		}
		for (int j = 0; j < restaurantlist.size(); j++) {
			System.out.println(restaurantlist.get(j).name + "\t");
		}
	}

}
